package net.squaants.vaultcraft.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
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
                        Ingredient.of(ModItems.RAW_SILVER.get()),
                        RecipeCategory.MISC,
                        ModItems.SILVER_INGOT.get(),
                        1.0f,
                        200,
                        RecipeSerializer.BLASTING_RECIPE)
                .group("silver_ingot")
                .unlockedBy("has_raw_silver", has(ModItems.RAW_SILVER.get()))
                .save(writer, VaultCraft.MOD_ID + ":silver_ingot_from_blasting_raw_silver");

        SimpleCookingRecipeBuilder.generic(
                        Ingredient.of(Items.IRON_INGOT),
                        RecipeCategory.MISC,
                        ModItems.STEEL_INGOT.get(),
                        0.0f,
                        800,
                        RecipeSerializer.BLASTING_RECIPE)
                .group("steel_ingot")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(writer, VaultCraft.MOD_ID + ":steel_ingot_from_blasting_iron_ingot");


        // ===== SHAPED: STEEL → DECORATIVE BLOCKS =====

        // metal_paneling.json
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.METAL_PANELING.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT.get()))
                .save(writer);

        // warehouse_walling.json  (FIXED: now uses default registry ID)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WAREHOUSE_WALLING.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.STEEL_INGOT.get()).build()))
                .save(writer);

        // Alternate warehouse_walling recipe (steel + any vaultcraft:wooden_planks)
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.WAREHOUSE_WALLING.get())
                .pattern("AB")
                .pattern("BA")
                .define('A', ModItems.STEEL_INGOT.get())
                .define('B', Ingredient.of(
                        net.minecraft.tags.ItemTags.create(
                                new ResourceLocation("vaultcraft", "wooden_planks")
                        )))
                .unlockedBy("has_steel_or_planks",
                        inventoryTrigger(ItemPredicate.Builder.item()
                                .of(ModItems.STEEL_INGOT.get()).build()))
                .save(writer, VaultCraft.MOD_ID + ":warehouse_walling_from_planks");


        // ===== SHAPELESS: DECORATIVE BLOCKS → STEEL SCRAP =====

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 4)
                .requires(ModBlocks.METAL_BRICK.get())
                .unlockedBy("has_metal_brick", has(ModBlocks.METAL_BRICK.get()))
                .save(writer, VaultCraft.MOD_ID + ":steel_ingot_from_metal_brick");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 4)
                .requires(ModBlocks.METAL_ENGRAVED.get())
                .unlockedBy("has_metal_engraved", has(ModBlocks.METAL_ENGRAVED.get()))
                .save(writer, VaultCraft.MOD_ID + ":steel_ingot_from_metal_engraved");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 4)
                .requires(ModBlocks.METAL_PANELING.get())
                .unlockedBy("has_metal_paneling", has(ModBlocks.METAL_PANELING.get()))
                .save(writer, VaultCraft.MOD_ID + ":steel_ingot_from_metal_paneling");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 2)
                .requires(ModBlocks.WAREHOUSE_PLATING.get())
                .unlockedBy("has_warehouse_plating", has(ModBlocks.WAREHOUSE_PLATING.get()))
                .save(writer, VaultCraft.MOD_ID + ":steel_ingot_from_warehouse_plating");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 2)
                .requires(ModBlocks.WAREHOUSE_TREADING.get())
                .unlockedBy("has_warehouse_treading", has(ModBlocks.WAREHOUSE_TREADING.get()))
                .save(writer, VaultCraft.MOD_ID + ":steel_ingot_from_warehouse_treading");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 2)
                .requires(ModBlocks.WAREHOUSE_WALLING.get())
                .unlockedBy("has_warehouse_walling", has(ModBlocks.WAREHOUSE_WALLING.get()))
                .save(writer, VaultCraft.MOD_ID + ":steel_ingot_from_warehouse_walling");
    }
}