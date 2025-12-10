package net.squaants.vaultcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.function.Supplier;

public class RustingSlabBlock extends SlabBlock {

    private final Supplier<Block> rustyVariant;

    public RustingSlabBlock(Supplier<Block> rustyVariant, BlockBehaviour.Properties properties) {
        super(properties.randomTicks());
        this.rustyVariant = rustyVariant;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (random.nextInt(20) != 0) { // same 1-in-20 chance as RustingBlock
            return;
        }

        Block rusty = rustyVariant.get();
        if (rusty == null) {
            return;
        }

        BlockState rustyState = rusty.defaultBlockState();

        // copy all shared properties (slab type, waterlogged, etc.)
        for (Property<?> property : state.getProperties()) {
            if (rustyState.hasProperty(property)) {
                rustyState = copyProperty(state, rustyState, property);
            }
        }

        level.setBlock(pos, rustyState, 3);
    }

    private static <T extends Comparable<T>> BlockState copyProperty(
            BlockState from,
            BlockState to,
            Property<T> property
    ) {
        return to.setValue(property, from.getValue(property));
    }
}