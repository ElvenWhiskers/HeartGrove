package com.elvenwhiskers.heartgrove.datagen;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamilies;
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamily;
import com.elvenwhiskers.heartgrove.item.ModItems;
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

    public ModRecipeProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries
    ) {
        super(output, registries);
    }


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        // Aegis
        List<ItemLike> aegisSmeltables = List.of(
                ModItems.RAW_AEGIS,
                ModBlocks.AEGIS_ORE
        );

        ShapedRecipeBuilder.shaped(
                        RecipeCategory.MISC,
                        ModBlocks.AEGIS_BLOCK.get()
                )
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.AEGIS_INGOT.get())
                .unlockedBy("has_aegis", has(ModItems.AEGIS_INGOT))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC,
                        ModItems.AEGIS_INGOT.get(),
                        9
                )
                .requires(ModBlocks.AEGIS_BLOCK)
                .unlockedBy("has_aegis_block", has(ModBlocks.AEGIS_BLOCK))
                .save(recipeOutput);

        oreSmelting(
                recipeOutput,
                aegisSmeltables,
                RecipeCategory.MISC,
                ModItems.AEGIS_INGOT.get(),
                0.25f,
                200,
                "aegis"
        );

        oreBlasting(
                recipeOutput,
                aegisSmeltables,
                RecipeCategory.MISC,
                ModItems.AEGIS_INGOT.get(),
                0.25f,
                100,
                "aegis"
        );


        // Standard wood families
        for (ModWoodFamily family : ModWoodFamilies.ALL) {
            woodFamilyRecipes(recipeOutput, family);
        }


        // Larkspur-specific utility blocks
        craftingTableRecipe(
                recipeOutput,
                ModBlocks.LARKSPUR.getPlanks().get(),
                ModBlocks.LARKSPUR_CRAFTING_TABLE.get()
        );
    }


    private static void woodFamilyRecipes(
            RecipeOutput recipeOutput,
            ModWoodFamily family
    ) {
        treePartRecipes(recipeOutput, family);
        woodShapeRecipes(recipeOutput, family);
    }


    private static void treePartRecipes(
            RecipeOutput recipeOutput,
            ModWoodFamily family
    ) {
        ItemLike log = family.getLog().get();
        ItemLike wood = family.getWood().get();
        ItemLike strippedLog = family.getStrippedLog().get();
        ItemLike strippedWood = family.getStrippedWood().get();
        ItemLike planks = family.getPlanks().get();


        // Log -> 4 planks
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC,
                        planks,
                        4
                )
                .requires(log)
                .unlockedBy("has_" + getItemName(log), has(log))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(planks) +
                                "_from_" +
                                getItemName(log)
                );


        // Wood -> 4 planks
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC,
                        planks,
                        4
                )
                .requires(wood)
                .unlockedBy("has_" + getItemName(wood), has(wood))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(planks) +
                                "_from_" +
                                getItemName(wood)
                );


        // Stripped log -> 4 planks
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC,
                        planks,
                        4
                )
                .requires(strippedLog)
                .unlockedBy(
                        "has_" + getItemName(strippedLog),
                        has(strippedLog)
                )
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(planks) +
                                "_from_" +
                                getItemName(strippedLog)
                );


        // Stripped wood -> 4 planks
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC,
                        planks,
                        4
                )
                .requires(strippedWood)
                .unlockedBy(
                        "has_" + getItemName(strippedWood),
                        has(strippedWood)
                )
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(planks) +
                                "_from_" +
                                getItemName(strippedWood)
                );


        // 2 planks -> 4 sticks
        ShapedRecipeBuilder.shaped(
                        RecipeCategory.MISC,
                        Items.STICK,
                        4
                )
                .pattern("A")
                .pattern("A")
                .define('A', planks)
                .unlockedBy("has_" + getItemName(planks), has(planks))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":sticks_from_" +
                                getItemName(planks)
                );


        // 4 logs -> 4 wood
        ShapedRecipeBuilder.shaped(
                        RecipeCategory.MISC,
                        wood,
                        4
                )
                .pattern("AA")
                .pattern("AA")
                .define('A', log)
                .unlockedBy("has_" + getItemName(log), has(log))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(wood) +
                                "_from_" +
                                getItemName(log)
                );


        // 4 stripped logs -> 4 stripped wood
        ShapedRecipeBuilder.shaped(
                        RecipeCategory.MISC,
                        strippedWood,
                        4
                )
                .pattern("AA")
                .pattern("AA")
                .define('A', strippedLog)
                .unlockedBy(
                        "has_" + getItemName(strippedLog),
                        has(strippedLog)
                )
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(strippedWood) +
                                "_from_" +
                                getItemName(strippedLog)
                );
    }


    private static void woodShapeRecipes(
            RecipeOutput recipeOutput,
            ModWoodFamily family
    ) {
        ItemLike planks = family.getPlanks().get();

        buttonBuilder(
                family.getButton().get(),
                Ingredient.of(planks)
        )
                .unlockedBy("has_" + getItemName(planks), has(planks))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(family.getButton().get()) +
                                "_from_" +
                                getItemName(planks)
                );


        doorBuilder(
                family.getDoor().get(),
                Ingredient.of(planks)
        )
                .unlockedBy("has_" + getItemName(planks), has(planks))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(family.getDoor().get()) +
                                "_from_" +
                                getItemName(planks)
                );


        fenceBuilder(
                family.getFence().get(),
                Ingredient.of(planks)
        )
                .unlockedBy("has_" + getItemName(planks), has(planks))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(family.getFence().get()) +
                                "_from_" +
                                getItemName(planks)
                );


        fenceGateBuilder(
                family.getFenceGate().get(),
                Ingredient.of(planks)
        )
                .unlockedBy("has_" + getItemName(planks), has(planks))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(family.getFenceGate().get()) +
                                "_from_" +
                                getItemName(planks)
                );


        pressurePlate(
                recipeOutput,
                family.getPressurePlate().get(),
                planks
        );


        slabBuilder(
                RecipeCategory.MISC,
                family.getSlab().get(),
                Ingredient.of(planks)
        )
                .unlockedBy("has_" + getItemName(planks), has(planks))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(family.getSlab().get()) +
                                "_from_" +
                                getItemName(planks)
                );


        stairBuilder(
                family.getStairs().get(),
                Ingredient.of(planks)
        )
                .unlockedBy("has_" + getItemName(planks), has(planks))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(family.getStairs().get()) +
                                "_from_" +
                                getItemName(planks)
                );


        trapdoorBuilder(
                family.getTrapdoor().get(),
                Ingredient.of(planks)
        )
                .unlockedBy("has_" + getItemName(planks), has(planks))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(family.getTrapdoor().get()) +
                                "_from_" +
                                getItemName(planks)
                );


        // HeartGrove wooden wall recipe
        ShapedRecipeBuilder.shaped(
                        RecipeCategory.MISC,
                        family.getWall().get(),
                        6
                )
                .pattern("ABA")
                .pattern("ABA")
                .define('A', planks)
                .define('B', family.getSlab().get())
                .unlockedBy("has_" + getItemName(planks), has(planks))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(family.getWall().get()) +
                                "_from_" +
                                getItemName(planks)
                );
    }


    private static void craftingTableRecipe(
            RecipeOutput recipeOutput,
            ItemLike planks,
            ItemLike craftingTable
    ) {
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC,
                        craftingTable
                )
                .requires(planks, 4)
                .unlockedBy("has_" + getItemName(planks), has(planks))
                .save(
                        recipeOutput,
                        HeartGrove.MOD_ID + ":" +
                                getItemName(craftingTable) +
                                "_from_" +
                                getItemName(planks)
                );
    }


    protected static void oreSmelting(
            RecipeOutput recipeOutput,
            List<ItemLike> ingredients,
            RecipeCategory category,
            ItemLike result,
            float experience,
            int cookingTime,
            String group
    ) {
        oreCooking(
                recipeOutput,
                RecipeSerializer.SMELTING_RECIPE,
                SmeltingRecipe::new,
                ingredients,
                category,
                result,
                experience,
                cookingTime,
                group,
                "_from_smelting"
        );
    }


    protected static void oreBlasting(
            RecipeOutput recipeOutput,
            List<ItemLike> ingredients,
            RecipeCategory category,
            ItemLike result,
            float experience,
            int cookingTime,
            String group
    ) {
        oreCooking(
                recipeOutput,
                RecipeSerializer.BLASTING_RECIPE,
                BlastingRecipe::new,
                ingredients,
                category,
                result,
                experience,
                cookingTime,
                group,
                "_from_blasting"
        );
    }


    protected static <T extends AbstractCookingRecipe> void oreCooking(
            RecipeOutput recipeOutput,
            RecipeSerializer<T> cookingSerializer,
            AbstractCookingRecipe.Factory<T> factory,
            List<ItemLike> ingredients,
            RecipeCategory category,
            ItemLike result,
            float experience,
            int cookingTime,
            String group,
            String recipeName
    ) {
        for (ItemLike ingredient : ingredients) {
            SimpleCookingRecipeBuilder.generic(
                            Ingredient.of(ingredient),
                            category,
                            result,
                            experience,
                            cookingTime,
                            cookingSerializer,
                            factory
                    )
                    .group(group)
                    .unlockedBy(getHasName(ingredient), has(ingredient))
                    .save(
                            recipeOutput,
                            HeartGrove.MOD_ID + ":" +
                                    getItemName(result) +
                                    recipeName +
                                    "_" +
                                    getItemName(ingredient)
                    );
        }
    }
}