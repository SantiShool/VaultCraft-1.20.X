package net.squaants.vaultcraft.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.squaants.vaultcraft.block.ModBlocks;
import net.squaants.vaultcraft.util.ModTags;

import java.util.List;

public class CampItem extends Item {

    private static final int MAX_USES = 128;

    public CampItem(Properties properties) {
        // Do NOT call stacksTo() on a damageable item in 1.20.1
        super(properties.durability(MAX_USES));
    }

    // Variant groups for cycling
    private static final List<Block> METAL_VARIANTS = List.of(
            ModBlocks.METAL_BRICK.get(),
            ModBlocks.METAL_ENGRAVED.get(),
            ModBlocks.METAL_PANELING.get()
    );

    private static final List<Block> WAREHOUSE_VARIANTS = List.of(
            ModBlocks.WAREHOUSE_PLATING.get(),
            ModBlocks.WAREHOUSE_TREADING.get(),
            ModBlocks.WAREHOUSE_WALLING.get()
    );

    /**
     * Treat rusty warehouse blocks as their clean versions so cycling works
     * no matter which one is currently placed.
     */
    private static Block normalizeBlock(Block block) {
        if (block == ModBlocks.RUSTY_WAREHOUSE_PLATING.get())  return ModBlocks.WAREHOUSE_PLATING.get();
        if (block == ModBlocks.RUSTY_WAREHOUSE_TREADING.get()) return ModBlocks.WAREHOUSE_TREADING.get();
        if (block == ModBlocks.RUSTY_WAREHOUSE_WALLING.get())  return ModBlocks.WAREHOUSE_WALLING.get();
        return block;
    }

    private static List<Block> getVariantGroup(Block block) {
        if (METAL_VARIANTS.contains(block)) return METAL_VARIANTS;
        if (WAREHOUSE_VARIANTS.contains(block)) return WAREHOUSE_VARIANTS;
        return null;
    }

    private boolean isCampBroken(ItemStack stack) {
        return stack.getDamageValue() >= stack.getMaxDamage();
    }

    private void sendBrokenMessage(Player player) {
        player.displayClientMessage(
                Component.literal("C.A.M.P.\u2122 offline. Please perform maintenance."),
                true
        );
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (player == null) {
            return InteractionResult.PASS;
        }

        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Block rawBlock = state.getBlock();
        Block normalizedBlock = normalizeBlock(rawBlock);

        // 1) Only handle blocks that are tagged as CAMP blocks
        if (!state.is(ModTags.Blocks.CAMP_BLOCKS)) {
            return InteractionResult.PASS;
        }

        // 2) If the CAMP device is broken, complain and do nothing
        if (isCampBroken(stack)) {
            if (!level.isClientSide) {
                sendBrokenMessage(player);
            }
            return InteractionResult.FAIL;
        }

        // ========== SHIFT + RIGHT CLICK: PICK UP BLOCK ==========
        if (player.isShiftKeyDown()) {
            if (!level.isClientSide) {
                // Remove block with no vanilla drops
                level.destroyBlock(pos, false, player);

                // Drop the actual placed block (rusty or not)
                ItemStack drop = new ItemStack(rawBlock.asItem());
                if (!player.getInventory().add(drop)) {
                    player.drop(drop, false);
                }

                level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.6f, 1.0f);

                // Durability -1
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(p.getUsedItemHand()));
            }

            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        // ========== NORMAL RIGHT CLICK: CYCLE VARIANT ==========
        List<Block> group = getVariantGroup(normalizedBlock);
        if (group == null) {
            // Tagged as CAMP block but not part of a variant cycle (e.g., jukebox)
            return InteractionResult.PASS;
        }

        int index = group.indexOf(normalizedBlock);
        if (index < 0) {
            return InteractionResult.PASS;
        }

        Block next = group.get((index + 1) % group.size());
        BlockState newState = next.defaultBlockState();

        // Preserve pillar orientation if this is a pillar-style block
        if (state.hasProperty(RotatedPillarBlock.AXIS) && newState.hasProperty(RotatedPillarBlock.AXIS)) {
            newState = newState.setValue(
                    RotatedPillarBlock.AXIS,
                    state.getValue(RotatedPillarBlock.AXIS)
            );
        }

        if (!level.isClientSide) {
            level.setBlock(pos, newState, 3);
            level.playSound(null, pos, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 0.6f, 1.1f);
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}