package com.abbygrail.cruderust.data;

import com.abbygrail.cruderust.core.registry.CrudeRustItems;
import com.abbygrail.cruderust.cruderust;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.data.internal.NeoForgeItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, cruderust.BRONZE_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', CrudeRustItems.BRONZE_INGOT.get())
                .unlockedBy("has_bronze", has(CrudeRustItems.BRONZE_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CrudeRustItems.BRONZE_INGOT.get(), 9)
                        .requires(cruderust.BRONZE_BLOCK)
                        .unlockedBy("has_bronze_block", has(cruderust.BRONZE_BLOCK)).save(recipeOutput, "cruderust:bronze_ingot_from_block");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CrudeRustItems.BRONZE_INGOT.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', CrudeRustItems.BRONZE_NUGGET.get())
                .unlockedBy("has_bronze", has(CrudeRustItems.BRONZE_NUGGET)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CrudeRustItems.BRONZE_NUGGET.get(), 9)
                .requires(CrudeRustItems.BRONZE_INGOT)
                .unlockedBy("has_bronze_ingot", has(CrudeRustItems.BRONZE_INGOT)).save(recipeOutput, "cruderust:bronze_nugget_from_ingot");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, cruderust.FIREWOOD.get(), 6)
                .pattern("LLL")
                .define('L', ItemTags.LOGS_THAT_BURN)
                .unlockedBy("has_logs", has(ItemTags.LOGS_THAT_BURN)).save(recipeOutput);
    }
}
