package net.squaants.vaultcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class RustingBlock extends Block {

    // The rusty version this block should turn into
    private final Supplier<Block> rustyVariant;

    public RustingBlock(Supplier<Block> rustyVariant, BlockBehaviour.Properties properties) {
        // enable random ticks so randomTick is called
        super(properties.randomTicks());
        this.rustyVariant = rustyVariant;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Chance for the block to rust each random tick
        // tweak this number however you like (lower = more often)
        if (random.nextInt(20) == 0) { // 1 in 20
            Block rusty = rustyVariant.get();
            if (rusty != null) {
                level.setBlock(pos, rusty.defaultBlockState(), 3);
            }
        }
    }
}