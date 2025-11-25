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

import java.util.List;

public class CampItem extends Item {

    private static final List<Block> VARIANTS = List.of(
            ModBlocks.METAL_BRICK.get(),
            ModBlocks.METAL_ENGRAVED.get(),
            ModBlocks.METAL_PANELING.get(),
            ModBlocks.WAREHOUSE_PLATING.get(),
            ModBlocks.WAREHOUSE_TREADING.get(),
            ModBlocks.WAREHOUSE_WALLING.get()
    );

    public CampItem(Properties properties) {
        // stacksTo(1) and durability are set in ModItems
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        int index = VARIANTS.indexOf(block);
        if (index == -1) {
            return InteractionResult.PASS; // not one of our CAMP blocks
        }

        int damage = stack.getDamageValue();
        int max = stack.getMaxDamage();

        // If "broken": no more stealing OR cycling
        if (!level.isClientSide && player != null && max > 0 && damage >= max) {
            player.displayClientMessage(
                    Component.translatable("item.vaultcraft.camp.broken"),
                    true
            );
            return InteractionResult.sidedSuccess(false);
        }

        // SNEAK + RIGHT-CLICK -> PICK UP (uses durability)
        if (player != null && player.isShiftKeyDown()) {
            if (!level.isClientSide) {
                // Destroy block without normal drops (instant break)
                boolean destroyed = level.destroyBlock(pos, false, player);
                if (!destroyed) {
                    return InteractionResult.sidedSuccess(false);
                }

                // Give the block item to the player
                ItemStack drop = new ItemStack(block.asItem());
                if (!player.getInventory().add(drop)) {
                    player.drop(drop, false);
                }

                level.playSound(
                        null,
                        pos,
                        SoundEvents.ITEM_PICKUP,
                        SoundSource.PLAYERS,
                        0.6f,
                        1.0f
                );

                // Durability loss – item never disappears, just stops working at max
                if (!player.isCreative() && max > 0 && damage < max) {
                    stack.setDamageValue(damage + 1);
                }
            }

            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        // NORMAL RIGHT-CLICK -> CYCLE VARIANT (no durability cost, but blocked if "broken")
        Block next = VARIANTS.get((index + 1) % VARIANTS.size());
        BlockState newState = next.defaultBlockState();

        // Give pillars correct log-style rotation
        if (next instanceof RotatedPillarBlock) {
            newState = newState.setValue(
                    RotatedPillarBlock.AXIS,
                    context.getClickedFace().getAxis()
            );
        }

        if (!level.isClientSide) {
            level.setBlock(pos, newState, 3);
            level.playSound(
                    null,
                    pos,
                    SoundEvents.ANVIL_PLACE,
                    SoundSource.BLOCKS,
                    0.6f,
                    1.1f
            );
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}