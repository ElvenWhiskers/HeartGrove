package com.elvenwhiskers.heartgrove.datagen;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import com.elvenwhiskers.heartgrove.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> AEGIS_SMELTABLES = List.of(ModItems.RAW_AEGIS,
                ModBlocks.AEGIS_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AEGIS_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.AEGIS_INGOT.get())
                .unlockedBy("has_aegis", has(ModItems.AEGIS_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.AEGIS_INGOT.get(), 9)
                .requires(ModBlocks.AEGIS_BLOCK)
                .unlockedBy("has_aegis_block", has(ModBlocks.AEGIS_BLOCK)).save(recipeOutput);


        oreSmelting(recipeOutput, AEGIS_SMELTABLES, RecipeCategory.MISC, ModItems.AEGIS_INGOT.get(), 0.25f, 200, "aegis");
        oreBlasting(recipeOutput, AEGIS_SMELTABLES, RecipeCategory.MISC, ModItems.AEGIS_INGOT.get(), 0.25f, 100, "aegis");

        //Tree stuffs part 1:
        treeParts(recipeOutput, RecipeCategory.MISC,
                ModBlocks.LARKSPUR_LOG.get(),
                ModBlocks.LARKSPUR_WOOD.get(),
                ModBlocks.LARKSPUR_PLANKS.get(),
                ModBlocks.STRIPPED_LARKSPUR_LOG.get(),
                ModBlocks.STRIPPED_LARKSPUR_WOOD.get());

        //Tree stuffs part 2:
        allShapeParts(recipeOutput,
                ModBlocks.LARKSPUR_PLANKS.get(),
                ModBlocks.LARKSPUR_BUTTON.get(),
                ModBlocks.LARKSPUR_DOOR.get(),
                ModBlocks.LARKSPUR_FENCE.get(),
                ModBlocks.LARKSPUR_FENCE_GATE.get(),
                ModBlocks.LARKSPUR_PRESSURE_PLATE.get(),
                ModBlocks.LARKSPUR_SLAB.get(),
                ModBlocks.LARKSPUR_STAIRS.get(),
                ModBlocks.LARKSPUR_TRAPDOOR.get(),
                ModBlocks.LARKSPUR_WALL.get());

    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, HeartGrove.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void treeParts(RecipeOutput pFinishedRecipe, RecipeCategory pCategory, ItemLike pLog, ItemLike pWood, ItemLike pPlanks, ItemLike pSTLog, ItemLike pSTWood){
        //makes 4 planks from log
        ShapelessRecipeBuilder.shapeless(pCategory, pPlanks, 4)
                .requires(pLog)
                .unlockedBy("has_" + getItemName(pLog), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pLog).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pPlanks) + "_from_" + getItemName(pLog));

        //makes planks from wood
        ShapelessRecipeBuilder.shapeless(pCategory, pPlanks, 4)
                .requires(pWood)
                .unlockedBy("has_" + getItemName(pWood), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pWood).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pPlanks) + "_from_" + getItemName(pWood));

        //makes sticks from planks
        ShapedRecipeBuilder.shaped(pCategory, Items.STICK, 4)
                .pattern("A")
                .pattern("A")
                .define('A', pPlanks)
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pPlanks).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + "sticks_from_" + getItemName(pPlanks));

        //makes planks from STRIPPED log
        ShapelessRecipeBuilder.shapeless(pCategory, pPlanks, 4)
                .requires(pSTLog)
                .unlockedBy("has_" + getItemName(pSTLog), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pSTLog).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pPlanks) + "_from_" + getItemName(pSTLog));

        //makes planks from STRIPPED wood
        ShapelessRecipeBuilder.shapeless(pCategory, pPlanks, 4)
                .requires(pSTWood)
                .unlockedBy("has_" + getItemName(pSTWood), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pSTWood).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pPlanks) + "_from_" + getItemName(pSTWood));

        //makes wood from logs
        ShapedRecipeBuilder.shaped(pCategory, pWood, 4)
                .pattern("AA")
                .pattern("AA")
                .define('A', pLog)
                .unlockedBy("has_" + getItemName(pWood), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pWood).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pWood) + "_from_" + getItemName(pLog));

        //makes stripped wood from stripped logs
        ShapedRecipeBuilder.shaped(pCategory, pSTWood, 4)
                .pattern("AA")
                .pattern("AA")
                .define('A', pSTLog)
                .unlockedBy("has_" + getItemName(pSTWood), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pSTWood).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pSTWood) + "_from_" + getItemName(pSTLog));
    }

    //Makes shapesss
    protected static void allShapeParts(RecipeOutput pFinishedRecipe, ItemLike pPlanks, ItemLike pButton, ItemLike pDoor, ItemLike pFence, ItemLike pFenceGate, ItemLike pPressurePlate, ItemLike pSlab, ItemLike pStairs, ItemLike pTrap, ItemLike pWall){
        buttonBuilder(pButton, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pButton) + "_from_" + getItemName(pPlanks));
        doorBuilder(pDoor, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pDoor) + "_from_" + getItemName(pPlanks));
        fenceBuilder(pFence, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pFence) + "_from_" + getItemName(pPlanks));
        fenceGateBuilder(pFenceGate, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pFenceGate) + "_from_" + getItemName(pPlanks));
        pressurePlate(pFinishedRecipe, pPressurePlate, pPlanks);
        slabBuilder(RecipeCategory.MISC , pSlab, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pSlab) + "_from_" + getItemName(pPlanks));
        stairBuilder(pStairs, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pStairs) + "_from_" + getItemName(pPlanks));
        trapdoorBuilder(pTrap, Ingredient.of(pPlanks))
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().of(pPlanks).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pTrap) + "_from_" + getItemName(pPlanks));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, pWall, 6)
                .pattern("ABA")
                .pattern("ABA")
                .define('A', pPlanks)
                .define('B', pSlab)
                .unlockedBy("has_" + getItemName(pPlanks), inventoryTrigger(ItemPredicate.Builder.item().
                        of(pPlanks).build()))
                .save(pFinishedRecipe, HeartGrove.MOD_ID + ":" + getItemName(pWall) + "_from_" + getItemName(pPlanks));

    }
}
