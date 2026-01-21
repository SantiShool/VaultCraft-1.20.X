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
        // Camp Blocks
        this.dropSelf(ModBlocks.JUKEBOX.get());

        this.dropSelf(ModBlocks.METAL_BLOCK.get());
        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_BLOCK.get());
        this.dropSelf(ModBlocks.WAREHOUSE_BLOCK.get());
        this.dropSelf(ModBlocks.METAL_STAIRS.get());
        this.add(ModBlocks.METAL_SLAB.get(), block -> createSlabItemTable(ModBlocks.METAL_SLAB.get()));
        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_BLOCK.get());
        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_STAIRS.get());
        this.add(ModBlocks.RUSTY_WAREHOUSE_SLAB.get(), block -> createSlabItemTable(ModBlocks.RUSTY_WAREHOUSE_SLAB.get()));
        this.dropSelf(ModBlocks.WAREHOUSE_BLOCK.get());
        this.dropSelf(ModBlocks.WAREHOUSE_STAIRS.get());
        this.add(ModBlocks.WAREHOUSE_SLAB.get(), block -> createSlabItemTable(ModBlocks.WAREHOUSE_SLAB.get()));

        // Ores
        this.add(ModBlocks.BAUXITE_ORE.get(), block -> createOreDrop(ModBlocks.BAUXITE_ORE.get(), ModItems.RAW_BAUXITE.get()));
        this.add(ModBlocks.DEEPSLATE_BAUXITE_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_BAUXITE_ORE.get(), ModItems.RAW_BAUXITE.get()));
        this.add(ModBlocks.BLACK_TITANIUM_ORE.get(), block -> createOreDrop(ModBlocks.BLACK_TITANIUM_ORE.get(), ModItems.RAW_BLACK_TITANIUM.get()));
        this.add(ModBlocks.DEEPSLATE_BLACK_TITANIUM_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_BLACK_TITANIUM_ORE.get(), ModItems.RAW_BLACK_TITANIUM.get()));
        this.add(ModBlocks.LEAD_ORE.get(), block -> createOreDrop(ModBlocks.LEAD_ORE.get(), ModItems.RAW_LEAD.get()));
        this.add(ModBlocks.DEEPSLATE_LEAD_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_LEAD_ORE.get(), ModItems.RAW_LEAD.get()));
        this.add(ModBlocks.SILVER_ORE.get(), block -> createOreDrop(ModBlocks.SILVER_ORE.get(), ModItems.RAW_SILVER.get()));
        this.add(ModBlocks.DEEPSLATE_SILVER_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_SILVER_ORE.get(), ModItems.RAW_SILVER.get()));
        this.add(ModBlocks.ULTRACITE_ORE.get(), block -> createOreDrop(ModBlocks.ULTRACITE_ORE.get(), ModItems.ULTRACITE.get()));
        this.add(ModBlocks.DEEPSLATE_ULTRACITE_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_ULTRACITE_ORE.get(), ModItems.ULTRACITE.get()));
        this.add(ModBlocks.URANIUM_ORE.get(), block -> createOreDrop(ModBlocks.URANIUM_ORE.get(), ModItems.URANIUM.get()));
        this.add(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), ModItems.URANIUM.get()));

        // Wood

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}