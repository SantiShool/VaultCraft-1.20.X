package net.squaants.vaultcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.squaants.vaultcraft.blockentity.ModBlockEntities;
import net.squaants.vaultcraft.blockentity.custom.JukeboxEntity;
import org.jetbrains.annotations.Nullable;

public class Jukebox extends Block implements EntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    // 0..16 x/z, 0..24 y  ->  full footprint, 1.5 blocks tall
    private static final VoxelShape SHAPE = Block.box(
            0.0D, 0.0D, 0.0D,
            16.0D, 24.0D, 16.0D
    );

    public Jukebox(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }

    // ---------- Blockstate / facing ----------

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Face the player when placed
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level,
                               BlockPos pos, CollisionContext context) {
        // Same collision for all facings (model is rotated by blockstate)
        return SHAPE;
    }

    // ---------- Block entity ----------

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
            // Right-click: open storage GUI only
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
                // Stop the music
                jukebox.stopPlaying(level);

                // Drop all discs/items stored inside
                Containers.dropContents(level, pos, jukebox);

                // Update comparators if you ever hook redstone up
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }
}