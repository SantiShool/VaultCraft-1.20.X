package net.squaants.vaultcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.squaants.vaultcraft.VaultCraft;
import net.squaants.vaultcraft.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VaultCraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.METAL_BLOCK);
        blockWithItem(ModBlocks.RUSTY_WAREHOUSE_BLOCK);
        blockWithItem(ModBlocks.WAREHOUSE_BLOCK);

        stairsBlock(ModBlocks.METAL_STAIRS.get(), blockTexture(ModBlocks.METAL_BLOCK.get()));
        blockItem(ModBlocks.METAL_STAIRS);
        slabBlock(ModBlocks.METAL_SLAB.get(), blockTexture(ModBlocks.METAL_BLOCK.get()), blockTexture(ModBlocks.METAL_BLOCK.get()));
        blockItem(ModBlocks.METAL_SLAB);
        stairsBlock(ModBlocks.RUSTY_WAREHOUSE_STAIRS.get(), blockTexture(ModBlocks.RUSTY_WAREHOUSE_BLOCK.get()));
        blockItem(ModBlocks.RUSTY_WAREHOUSE_STAIRS);
        slabBlock(ModBlocks.RUSTY_WAREHOUSE_SLAB.get(), blockTexture(ModBlocks.RUSTY_WAREHOUSE_BLOCK.get()), blockTexture(ModBlocks.RUSTY_WAREHOUSE_BLOCK.get()));
        blockItem(ModBlocks.RUSTY_WAREHOUSE_SLAB);
        stairsBlock(ModBlocks.WAREHOUSE_STAIRS.get(), blockTexture(ModBlocks.WAREHOUSE_BLOCK.get()));
        blockItem(ModBlocks.WAREHOUSE_STAIRS);
        slabBlock(ModBlocks.WAREHOUSE_SLAB.get(), blockTexture(ModBlocks.WAREHOUSE_BLOCK.get()), blockTexture(ModBlocks.WAREHOUSE_BLOCK.get()));
        blockItem(ModBlocks.WAREHOUSE_SLAB);

        blockWithItem(ModBlocks.BAUXITE_ORE);
        blockWithItem(ModBlocks.BLACK_TITANIUM_ORE);
        blockWithItem(ModBlocks.LEAD_ORE);
        blockWithItem(ModBlocks.SILVER_ORE);
        blockWithItem(ModBlocks.ULTRACITE_ORE);
        blockWithItem(ModBlocks.URANIUM_ORE);

        blockWithItem(ModBlocks.DEEPSLATE_BAUXITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_BLACK_TITANIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_LEAD_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SILVER_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ULTRACITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_URANIUM_ORE);
    }


    private <T extends Block> void blockItem(RegistryObject<T> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath())));
    }

    private <T extends Block> void blockWithItem(RegistryObject<T> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}