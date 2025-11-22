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
import net.squaants.vaultcraft.blockentity.ModBlockEntities;

public class JukeboxEntity extends BlockEntity implements Container, net.minecraft.world.MenuProvider {

    private static final int SLOT_COUNT = 27;
    private static final int DEFAULT_SONG_TICKS = 20 * 180; // ~3 min placeholder

    private NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);

    private boolean playing = false;
    private int playTimer = 0;
    private int currentSlot = -1;

    public JukeboxEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.JUKEBOX.get(), pos, state);
    }

    /* ---------- playback logic ---------- */

    public void startPlaying() {
        this.playing = true;
        this.playTimer = 0;
        this.currentSlot = -1; // will start from first valid disc
        setChanged();
    }

    public void stopPlaying(Level level) {
        if (!level.isClientSide) {
            // vanilla-style: stop jukebox sound at this position
            level.levelEvent(null, 1010, worldPosition, 0);
        }
        this.playing = false;
        this.playTimer = 0;
        setChanged();
    }

    /** Called when player shift-right-clicks to go to next song */
    public void skipTrack(Level level) {
        if (!playing) {
            // if not currently playing, just start from first valid track
            startPlaying();
            return;
        }

        // stop current jukebox sound and force advance next tick
        stopPlaying(level);
        this.playing = true;    // keep playing state true so serverTick will start next track
        this.playTimer = 0;
        setChanged();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, JukeboxEntity be) {
        if (!be.playing || level.isClientSide) return;

        if (be.isEmpty()) {
            be.playing = false;
            return;
        }

        if (be.playTimer > 0) {
            be.playTimer--;
            return;
        }

        // find next record
        for (int i = 0; i < SLOT_COUNT; i++) {
            be.currentSlot = (be.currentSlot + 1) % SLOT_COUNT;
            ItemStack stack = be.items.get(be.currentSlot);

            if (!stack.isEmpty() && stack.getItem() instanceof RecordItem record) {
                // vanilla jukebox-style playback:
                level.levelEvent(null, 1010, pos, Item.getId(record));
                be.playTimer = DEFAULT_SONG_TICKS;
                be.setChanged();
                return;
            }
        }

        // no records found
        be.playing = false;
    }

    /* ---------- MenuProvider ---------- */

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.vaultcraft.jukebox");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        // 3-row chest GUI for 27 slots
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
                (double)this.worldPosition.getX() + 0.5D,
                (double)this.worldPosition.getY() + 0.5D,
                (double)this.worldPosition.getZ() + 0.5D
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
        tag.putInt("PlayTimer", playTimer);
        tag.putInt("CurrentSlot", currentSlot);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, items);
        playing = tag.getBoolean("Playing");
        playTimer = tag.getInt("PlayTimer");
        currentSlot = tag.getInt("CurrentSlot");
    }
}