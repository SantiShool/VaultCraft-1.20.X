package net.squaants.vaultcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.squaants.vaultcraft.VaultCraft;
import net.squaants.vaultcraft.block.ModBlocks;
import net.squaants.vaultcraft.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {

        // ===== BLASTING: RAW -> INGOT =====

        SimpleCookingRecipeBuilder.generic(
                        Ingredient.of(ModItems.RAW_BAUXITE.get()),
                        RecipeCategory.MISC,
                        ModItems.ALUMINUM_INGOT.get(),
                        1.0f,
                        200,
                        RecipeSerializer.BLASTING_RECIPE)
                .group("aluminum_ingot")
                .unlockedBy("has_raw_bauxite", has(ModItems.RAW_BAUXITE.get()))
                .save(writer, VaultCraft.MOD_ID + ":aluminum_ingot_from_blasting_raw_bauxite");

        SimpleCookingRecipeBuilder.generic(
                        Ingredient.of(ModItems.RAW_BLACK_TITANIUM.get()),
                        RecipeCategory.MISC,
                        ModItems.BLACK_TITANIUM_INGOT.get(),
                        1.0f,
                        400,
                        RecipeSerializer.BLASTING_RECIPE)
                .group("black_titanium_ingot")
                .unlockedBy("has_raw_black_titanium", has(ModItems.RAW_BLACK_TITANIUM.get()))
                .save(writer, VaultCraft.MOD_ID + ":black_titanium_ingot_from_blasting_raw_black_titanium");

        SimpleCookingRecipeBuilder.generic(
                        Ingredient.of(ModItems.RAW_LEAD.get()),
                        RecipeCategory.MISC,
                        ModItems.LEAD_INGOT.get(),
                        1.0f,
                        200,
                        RecipeSerializer.BLASTING_RECIPE)
                .group("lead_ingot")
                .unlockedBy("has_raw_lead", has(ModItems.RAW_LEAD.get()))
                .save(writer, VaultCraft.MOD_ID + ":lead_ingot_from_blasting_raw_lead");

        SimpleCookingRecipeBuilder.generic(
                        Ingredient.of(ModItems.RAW_SILVER.get()),
                        RecipeCategory.MISC,
                        ModItems.SILVER_INGOT.get(),
                        1.0f,
                        200,
                        RecipeSerializer.BLASTING_RECIPE)
                .group("silver_ingot")
                .unlockedBy("has_raw_silver", has(ModItems.RAW_SILVER.get()))
                .save(writer, VaultCraft.MOD_ID + ":silver_ingot_from_blasting_raw_silver");


        // METAL BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.METAL_BLOCK.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT.get()))
                .save(writer, VaultCraft.MOD_ID + ":metal_block_from_steel");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.METAL_STAIRS.get(), 4)
                .pattern("P  ")
                .pattern("PP ")
                .pattern("PPP")
                .define('P', ModBlocks.METAL_BLOCK.get())
                .unlockedBy("has_metal_block", has(ModBlocks.METAL_BLOCK.get()))
                .save(writer, VaultCraft.MOD_ID + ":metal_stairs_from_metal_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.METAL_SLAB.get(), 6)
                .pattern("PPP")
                .define('P', ModBlocks.METAL_BLOCK.get())
                .unlockedBy("has_metal_block", has(ModBlocks.METAL_BLOCK.get()))
                .save(writer, VaultCraft.MOD_ID + ":metal_slab_from_metal_block");


        // WAREHOUSE BLOCK
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAREHOUSE_BLOCK.get(), 4)
                .pattern("SS")
                .pattern("WW")
                .define('S', ModItems.STEEL_INGOT.get())
                .define('W', ItemTags.create(new ResourceLocation("vaultcraft", "wooden_planks")))
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT.get()))
                .save(writer, VaultCraft.MOD_ID + ":warehouse_block_from_steel_and_planks");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAREHOUSE_STAIRS.get(), 4)
                .pattern("S  ")
                .pattern("WS ")
                .pattern("WWS")
                .define('S', ModItems.STEEL_INGOT.get())
                .define('W', ItemTags.create(new ResourceLocation("vaultcraft", "wooden_planks")))
                .unlockedBy("has_warehouse_block", has(ModBlocks.WAREHOUSE_BLOCK.get()))
                .save(writer, VaultCraft.MOD_ID + ":warehouse_stairs_from_steel_and_planks");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAREHOUSE_SLAB.get(), 6)
                .pattern("SWS")
                .define('S', ModItems.STEEL_INGOT.get())
                .define('W', ItemTags.create(new ResourceLocation("vaultcraft", "wooden_planks")))
                .unlockedBy("has_warehouse_block", has(ModBlocks.WAREHOUSE_BLOCK.get()))
                .save(writer, VaultCraft.MOD_ID + ":warehouse_slab_from_steel_and_planks");


        // WOOD
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ACACIA_PLANK.get(), 4)
                .requires(Items.ACACIA_LOG)
                .unlockedBy("has_acacia_log", has(Items.ACACIA_LOG))
                .save(writer, VaultCraft.MOD_ID + ":acacia_plank_from_acacia_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BIRCH_PLANK.get(), 4)
                .requires(Items.BIRCH_LOG)
                .unlockedBy("has_birch_log", has(Items.BIRCH_LOG))
                .save(writer, VaultCraft.MOD_ID + ":birch_plank_from_birch_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHERRY_PLANK.get(), 4)
                .requires(Items.CHERRY_LOG)
                .unlockedBy("has_cherry_log", has(Items.CHERRY_LOG))
                .save(writer, VaultCraft.MOD_ID + ":cherry_plank_from_cherry_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DARK_OAK_PLANK.get(), 4)
                .requires(Items.DARK_OAK_LOG)
                .unlockedBy("has_dark_oak_log", has(Items.DARK_OAK_LOG))
                .save(writer, VaultCraft.MOD_ID + ":dark_oak_plank_from_dark_oak_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.JUNGLE_PLANK.get(), 4)
                .requires(Items.JUNGLE_LOG)
                .unlockedBy("has_jungle_log", has(Items.JUNGLE_LOG))
                .save(writer, VaultCraft.MOD_ID + ":jungle_plank_from_jungle_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MANGROVE_PLANK.get(), 4)
                .requires(Items.MANGROVE_LOG)
                .unlockedBy("has_mangrove_log", has(Items.MANGROVE_LOG))
                .save(writer, VaultCraft.MOD_ID + ":mangrove_plank_from_mangrove_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.OAK_PLANK.get(), 4)
                .requires(Items.OAK_LOG)
                .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                .save(writer, VaultCraft.MOD_ID + ":oak_plank_from_oak_log");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SPRUCE_PLANK.get(), 4)
                .requires(Items.SPRUCE_LOG)
                .unlockedBy("has_spruce_log", has(Items.SPRUCE_LOG))
                .save(writer, VaultCraft.MOD_ID + ":spruce_plank_from_spruce_log");


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 4)
                .requires(ModBlocks.METAL_BLOCK.get())
                .unlockedBy("has_metal_block", has(ModBlocks.METAL_BLOCK.get()))
                .save(writer, VaultCraft.MOD_ID + ":steel_ingot_from_metal_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 2)
                .requires(ModBlocks.WAREHOUSE_BLOCK.get())
                .unlockedBy("has_warehouse_block", has(ModBlocks.WAREHOUSE_BLOCK.get()))
                .save(writer, VaultCraft.MOD_ID + ":steel_ingot_from_warehouse_block");
    }
}