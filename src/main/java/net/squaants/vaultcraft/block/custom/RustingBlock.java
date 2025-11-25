package net.squaants.vaultcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class RustingBlock extends Block {

    private final Supplier<Block> rustyVariant;

    public RustingBlock(Supplier<Block> rustyVariant, BlockBehaviour.Properties properties) {
        // enable random ticks so randomTick() actually runs
        super(properties.randomTicks());
        this.rustyVariant = rustyVariant;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // 1 in 20 chance per random tick to rust
        if (random.nextInt(20) != 0) {
            return;
        }

        Block rustyBlock = rustyVariant.get();
        if (rustyBlock == null) {
            return;
        }

        BlockState rustyState = rustyBlock.defaultBlockState();
        level.setBlock(pos, rustyState, Block.UPDATE_ALL);
    }
}