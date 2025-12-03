package net.squaants.vaultcraft.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.squaants.vaultcraft.block.ModBlocks;
import net.squaants.vaultcraft.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // Decorative / metal blocks just drop themselves
        this.dropSelf(ModBlocks.METAL_BRICK.get());
        this.dropSelf(ModBlocks.METAL_ENGRAVED.get());
        this.dropSelf(ModBlocks.METAL_PANELING.get());

        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_PLATING.get());
        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_TREADING.get());
        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_WALLING.get());

        this.dropSelf(ModBlocks.WAREHOUSE_PLATING.get());
        this.dropSelf(ModBlocks.WAREHOUSE_TREADING.get());
        this.dropSelf(ModBlocks.WAREHOUSE_WALLING.get());

        this.dropSelf(ModBlocks.JUKEBOX.get());

        // Ores – vanilla-style: silk touch gets block, otherwise raw item + fortune + explosion decay
        this.add(ModBlocks.BAUXITE_ORE.get(),
                block -> createOreDrop(ModBlocks.BAUXITE_ORE.get(), ModItems.RAW_BAUXITE.get()));
        this.add(ModBlocks.DEEPSLATE_BAUXITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_BAUXITE_ORE.get(), ModItems.RAW_BAUXITE.get()));

        this.add(ModBlocks.SILVER_ORE.get(),
                block -> createOreDrop(ModBlocks.SILVER_ORE.get(), ModItems.RAW_SILVER.get()));
        this.add(ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_SILVER_ORE.get(), ModItems.RAW_SILVER.get()));

        this.add(ModBlocks.ULTRACITE_ORE.get(),
                block -> createOreDrop(ModBlocks.ULTRACITE_ORE.get(), ModItems.RAW_ULTRACITE.get()));
        this.add(ModBlocks.DEEPSLATE_ULTRACITE_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_ULTRACITE_ORE.get(), ModItems.RAW_ULTRACITE.get()));

        this.add(ModBlocks.URANIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.URANIUM_ORE.get(), ModItems.RAW_URANIUM.get()));
        this.add(ModBlocks.DEEPSLATE_URANIUM_ORE.get(),
                block -> createOreDrop(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), ModItems.RAW_URANIUM.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}