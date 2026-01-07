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
        this.dropSelf(ModBlocks.METAL_BRICK.get());
        this.dropSelf(ModBlocks.METAL_ENGRAVED.get());
        this.dropSelf(ModBlocks.METAL_PANELING.get());
        this.dropSelf(ModBlocks.METAL_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.METAL_ENGRAVED_STAIRS.get());
        this.dropSelf(ModBlocks.METAL_PANELING_STAIRS.get());
        this.add(ModBlocks.METAL_BRICK_SLAB.get(), block -> createSlabItemTable(ModBlocks.METAL_BRICK_SLAB.get()));
        this.add(ModBlocks.METAL_ENGRAVED_SLAB.get(), block -> createSlabItemTable(ModBlocks.METAL_ENGRAVED_SLAB.get()));
        this.add(ModBlocks.METAL_PANELING_SLAB.get(), block -> createSlabItemTable(ModBlocks.METAL_PANELING_SLAB.get()));


        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_PLATING.get());
        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_TREADING.get());
        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_WALLING.get());
        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_PLATING_STAIRS.get());
        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_TREADING_STAIRS.get());
        this.dropSelf(ModBlocks.RUSTY_WAREHOUSE_WALLING_STAIRS.get());
        this.add(ModBlocks.RUSTY_WAREHOUSE_PLATING_SLAB.get(), block -> createSlabItemTable(ModBlocks.RUSTY_WAREHOUSE_PLATING_SLAB.get()));
        this.add(ModBlocks.RUSTY_WAREHOUSE_TREADING_SLAB.get(), block -> createSlabItemTable(ModBlocks.RUSTY_WAREHOUSE_TREADING_SLAB.get()));
        this.add(ModBlocks.RUSTY_WAREHOUSE_WALLING_SLAB.get(), block -> createSlabItemTable(ModBlocks.RUSTY_WAREHOUSE_WALLING_SLAB.get()));


        this.dropSelf(ModBlocks.WAREHOUSE_PLATING.get());
        this.dropSelf(ModBlocks.WAREHOUSE_TREADING.get());
        this.dropSelf(ModBlocks.WAREHOUSE_WALLING.get());
        this.dropSelf(ModBlocks.WAREHOUSE_PLATING_STAIRS.get());
        this.dropSelf(ModBlocks.WAREHOUSE_TREADING_STAIRS.get());
        this.dropSelf(ModBlocks.WAREHOUSE_WALLING_STAIRS.get());
        this.add(ModBlocks.WAREHOUSE_PLATING_SLAB.get(), block -> createSlabItemTable(ModBlocks.WAREHOUSE_PLATING_SLAB.get()));
        this.add(ModBlocks.WAREHOUSE_TREADING_SLAB.get(), block -> createSlabItemTable(ModBlocks.WAREHOUSE_TREADING_SLAB.get()));
        this.add(ModBlocks.WAREHOUSE_WALLING_SLAB.get(), block -> createSlabItemTable(ModBlocks.WAREHOUSE_WALLING_SLAB.get()));


        this.dropSelf(ModBlocks.JUKEBOX.get());


        this.add(ModBlocks.BAUXITE_ORE.get(), block -> createOreDrop(ModBlocks.BAUXITE_ORE.get(), ModItems.RAW_BAUXITE.get()));
        this.add(ModBlocks.DEEPSLATE_BAUXITE_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_BAUXITE_ORE.get(), ModItems.RAW_BAUXITE.get()));

        this.add(ModBlocks.SILVER_ORE.get(), block -> createOreDrop(ModBlocks.SILVER_ORE.get(), ModItems.RAW_SILVER.get()));
        this.add(ModBlocks.DEEPSLATE_SILVER_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_SILVER_ORE.get(), ModItems.RAW_SILVER.get()));

        this.add(ModBlocks.ULTRACITE_ORE.get(), block -> createOreDrop(ModBlocks.ULTRACITE_ORE.get(), ModItems.ULTRACITE.get()));
        this.add(ModBlocks.DEEPSLATE_ULTRACITE_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_ULTRACITE_ORE.get(), ModItems.ULTRACITE.get()));

        this.add(ModBlocks.URANIUM_ORE.get(), block -> createOreDrop(ModBlocks.URANIUM_ORE.get(), ModItems.URANIUM.get()));
        this.add(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), ModItems.URANIUM.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}