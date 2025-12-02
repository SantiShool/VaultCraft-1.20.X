package net.squaants.vaultcraft.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.squaants.vaultcraft.block.ModBlocks;
import net.squaants.vaultcraft.item.ModItems;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.METAL_PANELING.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot",
                        inventoryTrigger(ItemPredicate.Builder.item()
                                .of(ModItems.STEEL_INGOT.get()).build()))
                .save(writer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 4)
                .requires(ModBlocks.METAL_PANELING.get())
                .unlockedBy("has_metal_paneling",
                        inventoryTrigger(ItemPredicate.Builder.item()
                                .of(ModBlocks.METAL_PANELING.get()).build()))
                .save(writer);
    }
}