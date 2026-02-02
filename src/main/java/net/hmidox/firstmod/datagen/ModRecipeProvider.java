package net.hmidox.firstmod.datagen;

import net.hmidox.firstmod.FirstMod;
import net.hmidox.firstmod.block.ModBlocks;
import net.hmidox.firstmod.item.Moditems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> ABDELHAMID_SMELTABLES = List.of(Moditems.ABDELHAMID.get(),
            ModBlocks.ABDELHAMID_BLOCK.get(),ModBlocks.ABDELHAMID_ORE.get());
private static final List<ItemLike> BAALLA_SMELTABLES = List.of(Moditems.BAALLA.get(),
         ModBlocks.BAALLA_BLOCK.get(), ModBlocks.BAALLA_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, ABDELHAMID_SMELTABLES, RecipeCategory.MISC, Moditems.ABDELHAMID.get(), 0.25f, 200, "abdelhamid");
        oreBlasting(pWriter, ABDELHAMID_SMELTABLES, RecipeCategory.MISC, Moditems.ABDELHAMID.get(), 0.25f, 100, "abdelhamid");
        oreSmelting(pWriter, BAALLA_SMELTABLES, RecipeCategory.MISC, Moditems.BAALLA.get(), 0.25f, 200, "baalla");
        oreBlasting(pWriter, BAALLA_SMELTABLES, RecipeCategory.MISC, Moditems.BAALLA.get(), 0.25f, 100, "baalla");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ABDELHAMID_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Moditems.ABDELHAMID.get())
                .unlockedBy(getHasName(Moditems.ABDELHAMID.get()), has(Moditems.ABDELHAMID.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BAALLA_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', Moditems.BAALLA.get())
                .unlockedBy(getHasName(Moditems.BAALLA.get()), has(Moditems.BAALLA.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Moditems.ABDELHAMID.get(), 9)
                .requires(ModBlocks.ABDELHAMID_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ABDELHAMID_BLOCK.get()), has(ModBlocks.ABDELHAMID_BLOCK.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Moditems.BAALLA.get(), 9)
                .requires(ModBlocks.BAALLA_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.BAALLA_BLOCK.get()), has(ModBlocks.BAALLA_BLOCK.get()))
                .save(pWriter);

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike),
                    pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, FirstMod.MOD_ID + ":" + getItemName(pResult) +
                            pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
