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
        blockWithItem(ModBlocks.METAL_BRICK);
        blockWithItem(ModBlocks.METAL_ENGRAVED);
        blockWithItem(ModBlocks.METAL_PANELING);

        stairsBlock(ModBlocks.METAL_BRICK_STAIRS.get(), blockTexture(ModBlocks.METAL_BRICK.get()));
        blockItem(ModBlocks.METAL_BRICK_STAIRS);
        stairsBlock(ModBlocks.METAL_ENGRAVED_STAIRS.get(), blockTexture(ModBlocks.METAL_ENGRAVED.get()));
        blockItem(ModBlocks.METAL_ENGRAVED_STAIRS);
        stairsBlock(ModBlocks.METAL_PANELING_STAIRS.get(), blockTexture(ModBlocks.METAL_PANELING.get()));
        blockItem(ModBlocks.METAL_PANELING_STAIRS);

        slabBlock(ModBlocks.METAL_BRICK_SLAB.get(), blockTexture(ModBlocks.METAL_BRICK.get()), blockTexture(ModBlocks.METAL_BRICK.get()));
        blockItem(ModBlocks.METAL_BRICK_SLAB);
        slabBlock(ModBlocks.METAL_ENGRAVED_SLAB.get(), blockTexture(ModBlocks.METAL_ENGRAVED.get()), blockTexture(ModBlocks.METAL_ENGRAVED.get()));
        blockItem(ModBlocks.METAL_ENGRAVED_SLAB);
        slabBlock(ModBlocks.METAL_PANELING_SLAB.get(), blockTexture(ModBlocks.METAL_PANELING.get()), blockTexture(ModBlocks.METAL_PANELING.get()));
        blockItem(ModBlocks.METAL_PANELING_SLAB);


        blockWithItem(ModBlocks.RUSTY_WAREHOUSE_PLATING);
        blockWithItem(ModBlocks.RUSTY_WAREHOUSE_TREADING);
        blockWithItem(ModBlocks.RUSTY_WAREHOUSE_WALLING);

        stairsBlock(ModBlocks.RUSTY_WAREHOUSE_PLATING_STAIRS.get(), blockTexture(ModBlocks.RUSTY_WAREHOUSE_PLATING.get()));
        blockItem(ModBlocks.RUSTY_WAREHOUSE_PLATING_STAIRS);
        stairsBlock(ModBlocks.RUSTY_WAREHOUSE_TREADING_STAIRS.get(), blockTexture(ModBlocks.RUSTY_WAREHOUSE_TREADING.get()));
        blockItem(ModBlocks.RUSTY_WAREHOUSE_TREADING_STAIRS);
        stairsBlock(ModBlocks.RUSTY_WAREHOUSE_WALLING_STAIRS.get(), blockTexture(ModBlocks.RUSTY_WAREHOUSE_WALLING.get()));
        blockItem(ModBlocks.RUSTY_WAREHOUSE_WALLING_STAIRS);

        slabBlock(ModBlocks.RUSTY_WAREHOUSE_PLATING_SLAB.get(), blockTexture(ModBlocks.RUSTY_WAREHOUSE_PLATING.get()), blockTexture(ModBlocks.RUSTY_WAREHOUSE_PLATING.get()));
        blockItem(ModBlocks.RUSTY_WAREHOUSE_PLATING_SLAB);
        slabBlock(ModBlocks.RUSTY_WAREHOUSE_TREADING_SLAB.get(), blockTexture(ModBlocks.RUSTY_WAREHOUSE_TREADING.get()), blockTexture(ModBlocks.RUSTY_WAREHOUSE_TREADING.get()));
        blockItem(ModBlocks.RUSTY_WAREHOUSE_TREADING_SLAB);
        slabBlock(ModBlocks.RUSTY_WAREHOUSE_WALLING_SLAB.get(), blockTexture(ModBlocks.RUSTY_WAREHOUSE_WALLING.get()), blockTexture(ModBlocks.RUSTY_WAREHOUSE_WALLING.get()));
        blockItem(ModBlocks.RUSTY_WAREHOUSE_WALLING_SLAB);


        blockWithItem(ModBlocks.WAREHOUSE_PLATING);
        blockWithItem(ModBlocks.WAREHOUSE_TREADING);
        blockWithItem(ModBlocks.WAREHOUSE_WALLING);

        stairsBlock(ModBlocks.WAREHOUSE_PLATING_STAIRS.get(), blockTexture(ModBlocks.WAREHOUSE_PLATING.get()));
        blockItem(ModBlocks.WAREHOUSE_PLATING_STAIRS);
        stairsBlock(ModBlocks.WAREHOUSE_TREADING_STAIRS.get(), blockTexture(ModBlocks.WAREHOUSE_TREADING.get()));
        blockItem(ModBlocks.WAREHOUSE_TREADING_STAIRS);
        stairsBlock(ModBlocks.WAREHOUSE_WALLING_STAIRS.get(), blockTexture(ModBlocks.WAREHOUSE_WALLING.get()));
        blockItem(ModBlocks.WAREHOUSE_WALLING_STAIRS);

        slabBlock(ModBlocks.WAREHOUSE_PLATING_SLAB.get(), blockTexture(ModBlocks.WAREHOUSE_PLATING.get()), blockTexture(ModBlocks.WAREHOUSE_PLATING.get()));
        blockItem(ModBlocks.WAREHOUSE_PLATING_SLAB);
        slabBlock(ModBlocks.WAREHOUSE_TREADING_SLAB.get(), blockTexture(ModBlocks.WAREHOUSE_TREADING.get()), blockTexture(ModBlocks.WAREHOUSE_TREADING.get()));
        blockItem(ModBlocks.WAREHOUSE_TREADING_SLAB);
        slabBlock(ModBlocks.WAREHOUSE_WALLING_SLAB.get(), blockTexture(ModBlocks.WAREHOUSE_WALLING.get()), blockTexture(ModBlocks.WAREHOUSE_WALLING.get()));
        blockItem(ModBlocks.WAREHOUSE_WALLING_SLAB);


        blockWithItem(ModBlocks.BAUXITE_ORE);
        blockWithItem(ModBlocks.SILVER_ORE);
        blockWithItem(ModBlocks.ULTRACITE_ORE);
        blockWithItem(ModBlocks.URANIUM_ORE);

        blockWithItem(ModBlocks.DEEPSLATE_BAUXITE_ORE);
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