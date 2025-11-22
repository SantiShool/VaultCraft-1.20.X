package net.squaants.vaultcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.squaants.vaultcraft.blockentity.ModBlockEntities;
import net.squaants.vaultcraft.blockentity.custom.JukeboxEntity;
import org.jetbrains.annotations.Nullable;

public class Jukebox extends Block implements EntityBlock {

    public Jukebox(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new JukeboxEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {

        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        BlockEntity be = level.getBlockEntity(pos);
        if (!(be instanceof JukeboxEntity jukebox)) {
            return InteractionResult.PASS;
        }

        if (player.isShiftKeyDown()) {
            // Shift-right-click: skip to next song (no overlap)
            jukebox.skipTrack(level);
            return InteractionResult.CONSUME;
        } else {
            // Right-click: open storage GUI only (no on/off toggle here)
            player.openMenu(jukebox);
            return InteractionResult.CONSUME;
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            Level level, BlockState state, BlockEntityType<T> type) {

        return level.isClientSide ? null :
                (lvl, pos, st, be) -> {
                    if (type == ModBlockEntities.JUKEBOX.get() && be instanceof JukeboxEntity jukebox) {
                        JukeboxEntity.serverTick(lvl, pos, st, jukebox);
                    }
                };
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos,
                         BlockState newState, boolean isMoving) {

        if (!level.isClientSide && !state.is(newState.getBlock())) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof JukeboxEntity jukebox) {
                jukebox.stopPlaying(level);
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }
}