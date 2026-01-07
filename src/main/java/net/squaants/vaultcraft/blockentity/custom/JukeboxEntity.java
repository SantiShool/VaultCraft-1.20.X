package net.squaants.vaultcraft.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.squaants.vaultcraft.block.custom.Jukebox;
import net.squaants.vaultcraft.blockentity.ModBlockEntities;

public class JukeboxEntity extends BlockEntity implements Container, net.minecraft.world.MenuProvider {

    private static final int SLOT_COUNT = 27;
    private static final int DEFAULT_SONG_TICKS = 20 * 180;

    private NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);

    private boolean playing = false;
    private boolean paused = false;

    private int playTimer = 0;
    private int currentSlot = -1;        // last slot played
    private int currentRecordId = 0;

    // NEW: marks where the playlist began so we can stop instead of looping forever
    private int playlistStartSlot = -1;

    public JukeboxEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.JUKEBOX.get(), pos, state);
    }

    /* ---------- simple getters ---------- */

    public boolean isPlaying() {
        return playing;
    }

    /* ---------- helpers ---------- */

    private void setLit(Level level, boolean lit) {
        if (level == null || level.isClientSide) return;

        BlockState state = level.getBlockState(worldPosition);
        if (!state.hasProperty(Jukebox.LIT)) return;

        boolean current = state.getValue(Jukebox.LIT);
        if (current == lit) return;

        level.setBlock(worldPosition, state.setValue(Jukebox.LIT, lit), 3);
    }

    private void stopSound(Level level) {
        if (!level.isClientSide) {
            // STOP record sound at this position (correct event)
            level.levelEvent(null, 1011, worldPosition, 0);
        }
    }

    private void playSound(Level level, int recordId) {
        if (!level.isClientSide) {
            // PLAY record sound
            level.levelEvent(null, 1010, worldPosition, recordId);
        }
    }

    /**
     * Finds the next RecordItem after startSlot (exclusive).
     * Returns the slot index or -1 if none found in the forward scan.
     */
    private int findNextRecordSlotForward(int startSlotExclusive) {
        for (int step = 1; step <= SLOT_COUNT; step++) {
            int idx = (startSlotExclusive + step) % SLOT_COUNT;
            ItemStack stack = items.get(idx);
            if (!stack.isEmpty() && stack.getItem() instanceof RecordItem) {
                return idx;
            }
        }
        return -1;
    }

    /**
     * Returns true if moving from currentSlot -> nextSlot wrapped around the inventory.
     * (i.e., nextSlot is "before" currentSlot in slot order)
     */
    private boolean didWrap(int fromSlot, int toSlot) {
        return toSlot < fromSlot;
    }

    /* ---------- music controls ---------- */

    private void startPlaylist(Level level) {
        this.playing = true;
        this.paused = false;
        this.playTimer = 0;
        this.currentSlot = -1;
        this.currentRecordId = 0;
        this.playlistStartSlot = -1;

        // We light up once we actually start playing a disc (serverTick will do it),
        // but leaving this true makes it obvious it's "active".
        setLit(level, true);

        setChanged();
    }

    public void stopPlaying(Level level) {
        stopSound(level);

        this.playing = false;
        this.paused = false;
        this.playTimer = 0;
        this.currentRecordId = 0;
        this.currentSlot = -1;
        this.playlistStartSlot = -1;

        setLit(level, false);

        setChanged();
    }

    /**
     * Shift+RightClick:
     * - if not playing => start playlist
     * - if playing => skip to next track (within the same one-pass playlist rules)
     */
    public void skipOrStart(Level level) {
        if (isEmpty()) return;

        if (!playing) {
            startPlaylist(level);
            return;
        }

        // skip: stop current sound and force next selection immediately
        stopSound(level);

        this.paused = false;
        this.playTimer = 0;

        setLit(level, true);

        setChanged();
    }

    /**
     * Optional: if you still want pause/resume while playing.
     * (If you change Shift+LeftClick to STOP, you may not call this anymore.)
     */
    public void togglePause(Level level) {
        if (!playing) return;

        if (!paused) {
            stopSound(level);
            paused = true;
            setLit(level, false);
            setChanged();
            return;
        }

        paused = false;

        // "resume" re-triggers the record (can't resume mid-track)
        if (currentRecordId != 0) {
            playSound(level, currentRecordId);
            setLit(level, true);
        }

        setChanged();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, JukeboxEntity be) {
        if (level.isClientSide) return;
        if (!be.playing) return;
        if (be.paused) return;

        if (be.isEmpty()) {
            be.stopPlaying(level);
            return;
        }

        if (be.playTimer > 0) {
            be.playTimer--;
            return;
        }

        // Determine where to start scanning:
        // - If currentSlot == -1, we haven't played anything yet, so scan from -1 (meaning slot 0 first)
        int scanFrom = be.currentSlot;
        if (scanFrom < 0) scanFrom = -1;

        int nextSlot = be.findNextRecordSlotForward(scanFrom);

        if (nextSlot == -1) {
            // No records at all
            be.stopPlaying(level);
            return;
        }

        // If this is the first track of the session, define the start slot
        if (be.playlistStartSlot == -1) {
            be.playlistStartSlot = nextSlot;
        } else {
            // We already started: if selecting nextSlot would wrap AND lands at/after a wrap,
            // stop instead of looping back to the beginning.
            // In practice: if we wrapped and the nextSlot is <= playlistStartSlot, we consider playlist done.
            if (be.currentSlot != -1 && be.didWrap(be.currentSlot, nextSlot)) {
                // wrapped around — any wrapped choice means we're heading back to earlier slots
                // so stop once we'd return to the beginning region.
                be.stopPlaying(level);
                return;
            }
        }

        ItemStack stack = be.items.get(nextSlot);
        int id = Item.getId(stack.getItem());

        be.playSound(level, id);

        be.currentSlot = nextSlot;
        be.currentRecordId = id;
        be.playTimer = DEFAULT_SONG_TICKS;

        be.setLit(level, true);

        be.setChanged();
    }

    /* ---------- MenuProvider (vanilla UI) ---------- */

    @Override
    public Component getDisplayName() {
        // This is correct; the "container.vaultcraft.jukebox" issue is your lang file missing the key.
        return Component.translatable("container.vaultcraft.jukebox");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return ChestMenu.threeRows(id, inv, this);
    }

    /* ---------- Container implementation ---------- */

    @Override
    public int getContainerSize() {
        return SLOT_COUNT;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack stack = ContainerHelper.removeItem(items, index, count);
        if (!stack.isEmpty()) setChanged();
        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack stack = items.get(index);
        if (stack.isEmpty()) return ItemStack.EMPTY;
        items.set(index, ItemStack.EMPTY);
        setChanged();
        return stack;
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        items.set(index, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level == null) return false;
        if (this.level.getBlockEntity(this.worldPosition) != this) return false;

        return player.distanceToSqr(
                this.worldPosition.getX() + 0.5D,
                this.worldPosition.getY() + 0.5D,
                this.worldPosition.getZ() + 0.5D
        ) <= 64.0D;
    }

    @Override
    public void clearContent() {
        items.clear();
        setChanged();
    }

    /* ---------- NBT ---------- */

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, items);

        tag.putBoolean("Playing", playing);
        tag.putBoolean("Paused", paused);
        tag.putInt("PlayTimer", playTimer);
        tag.putInt("CurrentSlot", currentSlot);
        tag.putInt("CurrentRecordId", currentRecordId);
        tag.putInt("PlaylistStartSlot", playlistStartSlot);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, items);

        playing = tag.getBoolean("Playing");
        paused = tag.getBoolean("Paused");
        playTimer = tag.getInt("PlayTimer");
        currentSlot = tag.getInt("CurrentSlot");
        currentRecordId = tag.getInt("CurrentRecordId");
        playlistStartSlot = tag.getInt("PlaylistStartSlot");
    }
}
