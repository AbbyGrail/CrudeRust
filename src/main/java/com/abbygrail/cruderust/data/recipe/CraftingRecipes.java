package com.abbygrail.cruderust.data.recipe;

import com.abbygrail.cruderust.cruderust;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.concurrent.CompletableFuture;

public class CraftingRecipes extends RecipeProvider {

    public CraftingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        // 🔨 Recipe: 3 logs -> 1 firewood item
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, cruderust.FIREWOOD_ITEM.get(), 6)
                .pattern("ppp")
                .define('p', Ingredient.of(ItemTags.LOGS_THAT_BURN))
                .save(output);
    }
}
