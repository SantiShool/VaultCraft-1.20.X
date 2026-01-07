package net.squaants.vaultcraft.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.ForgeRegistries;
import net.squaants.vaultcraft.VaultCraft;
import net.squaants.vaultcraft.util.ModTags;

import java.util.List;

public class CampItem extends Item {

    public CampItem(Properties properties) {
        super(properties);
    }

    // EXACT families based on your registry names
    private static final List<String> METAL = List.of(
            "metal_brick", "metal_engraved", "metal_paneling"
    );
    private static final List<String> METAL_STAIRS = List.of(
            "metal_brick_stairs", "metal_engraved_stairs", "metal_paneling_stairs"
    );
    private static final List<String> METAL_SLAB = List.of(
            "metal_brick_slab", "metal_engraved_slab", "metal_paneling_slab"
    );

    private static final List<String> WAREHOUSE = List.of(
            "warehouse_plating", "warehouse_treading", "warehouse_walling"
    );
    private static final List<String> WAREHOUSE_STAIRS = List.of(
            "warehouse_plating_stairs", "warehouse_treading_stairs", "warehouse_walling_stairs"
    );
    private static final List<String> WAREHOUSE_SLAB = List.of(
            "warehouse_plating_slab", "warehouse_treading_slab", "warehouse_walling_slab"
    );

    private static final List<String> RUSTY_WAREHOUSE = List.of(
            "rusty_warehouse_plating", "rusty_warehouse_treading", "rusty_warehouse_walling"
    );
    private static final List<String> RUSTY_WAREHOUSE_STAIRS = List.of(
            "rusty_warehouse_plating_stairs", "rusty_warehouse_treading_stairs", "rusty_warehouse_walling_stairs"
    );
    private static final List<String> RUSTY_WAREHOUSE_SLAB = List.of(
            "rusty_warehouse_plating_slab", "rusty_warehouse_treading_slab", "rusty_warehouse_walling_slab"
    );

    private static List<String> familyFor(String path) {
        if (METAL.contains(path)) return METAL;
        if (METAL_STAIRS.contains(path)) return METAL_STAIRS;
        if (METAL_SLAB.contains(path)) return METAL_SLAB;

        if (WAREHOUSE.contains(path)) return WAREHOUSE;
        if (WAREHOUSE_STAIRS.contains(path)) return WAREHOUSE_STAIRS;
        if (WAREHOUSE_SLAB.contains(path)) return WAREHOUSE_SLAB;

        if (RUSTY_WAREHOUSE.contains(path)) return RUSTY_WAREHOUSE;
        if (RUSTY_WAREHOUSE_STAIRS.contains(path)) return RUSTY_WAREHOUSE_STAIRS;
        if (RUSTY_WAREHOUSE_SLAB.contains(path)) return RUSTY_WAREHOUSE_SLAB;

        return null;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;

        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        // Gate by tag (make sure your stairs/slabs are included in camp_blocks.json)
        if (!state.is(ModTags.Blocks.CAMP_BLOCKS)) {
            return InteractionResult.PASS;
        }

        ResourceLocation key = ForgeRegistries.BLOCKS.getKey(state.getBlock());
        if (key == null) return InteractionResult.PASS;

        // Only our mod blocks
        if (!VaultCraft.MOD_ID.equals(key.getNamespace())) {
            return InteractionResult.PASS;
        }

        String path = key.getPath();

        List<String> family = familyFor(path);
        if (family == null) return InteractionResult.PASS;

        int idx = family.indexOf(path);
        if (idx < 0) return InteractionResult.PASS;

        String nextPath = family.get((idx + 1) % family.size());

        Block nextBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(VaultCraft.MOD_ID, nextPath));
        if (nextBlock == null) return InteractionResult.PASS;

        BlockState newState = copyCommonProperties(state, nextBlock.defaultBlockState());

        if (!level.isClientSide) {
            level.setBlock(pos, newState, 3);
            level.playSound(null, pos, SoundEvents.ANVIL_PLACE, SoundSource.BLOCKS, 0.6f, 1.1f);
        }

        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static BlockState copyCommonProperties(BlockState from, BlockState to) {
        for (Property prop : from.getProperties()) {
            if (to.hasProperty(prop)) {
                to = to.setValue(prop, from.getValue(prop));
            }
        }
        return to;
    }
}
