package com.elvenwhiskers.heartgrove.menu.sawmill;

import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.List;

public class ModSawmillFamilies {

    public static final SawmillWoodFamily WISTERIA = new SawmillWoodFamily(
            ModBlocks.WISTERIA.getPlanks().get().asItem(),
            ModBlocks.WISTERIA.getLog().get().asItem(),
            ModBlocks.WISTERIA.getStrippedLog().get().asItem(),
            ModBlocks.WISTERIA.getWood().get().asItem(),
            ModBlocks.WISTERIA.getStrippedWood().get().asItem()
    );

    public static final SawmillRecipe WISTERIA_PLANKS = new SawmillRecipe(
            ModBlocks.WISTERIA.getPlanks().get().asItem(),
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.PLANK
    );

    public static final SawmillRecipe WISTERIA_STICKS = new SawmillRecipe(
            Items.STICK,
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.STICK
    );

    public static final SawmillRecipe WISTERIA_PLANK_FENCE = new SawmillRecipe(
            ModBlocks.WISTERIA.getFence().get().asItem(),
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.FENCE
    );

    public static final SawmillRecipe WISTERIA_SLAB = new SawmillRecipe(
            ModBlocks.WISTERIA.getSlab().get().asItem(),
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.SLAB
    );

    public static final SawmillRecipe WISTERIA_STAIRS = new SawmillRecipe(
            ModBlocks.WISTERIA.getStairs().get().asItem(),
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.STAIRS
    );

    public static final SawmillRecipe WISTERIA_WALL = new SawmillRecipe(
            ModBlocks.WISTERIA.getWall().get().asItem(),
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.WALL
    );

    public static final SawmillRecipe WISTERIA_BUTTON = new SawmillRecipe(
            ModBlocks.WISTERIA.getButton().get().asItem(),
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.BUTTON
    );

    public static final SawmillRecipe WISTERIA_PRESSURE_PLATE = new SawmillRecipe(
            ModBlocks.WISTERIA.getPressurePlate().get().asItem(),
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.PRESSURE_PLATE
    );

    public static final SawmillRecipe WISTERIA_FENCE_GATE = new SawmillRecipe(
            ModBlocks.WISTERIA.getFenceGate().get().asItem(),
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.FENCE_GATE
    );

    public static final SawmillFamilyCatalog WISTERIA_CATALOG = new SawmillFamilyCatalog(
            WISTERIA,
            List.of(
                    WISTERIA_PLANKS,
                    WISTERIA_STICKS,
                    WISTERIA_PLANK_FENCE,
                    WISTERIA_SLAB,
                    WISTERIA_STAIRS,
                    WISTERIA_WALL,
                    WISTERIA_BUTTON,
                    WISTERIA_PRESSURE_PLATE,
                    WISTERIA_FENCE_GATE
            )
    );

    public static final SawmillCatalog SAWMILL_CATALOG = new SawmillCatalog(
            List.of(
                    WISTERIA_CATALOG
            )
    );

    public static void debugCatalog() {
        printRecipes("Wisteria Log", ModBlocks.WISTERIA.getLog().get().asItem());
        printRecipes("Wisteria Planks", ModBlocks.WISTERIA.getPlanks().get().asItem());
        printRecipes("Potato", Items.POTATO);
    }

    private static void printRecipes(String label, Item inputItem) {
        System.out.println("=== " + label + " ===");

        for (SawmillRecipe recipe : SAWMILL_CATALOG.getAvailableRecipes(inputItem)) {
            System.out.println(recipe.getOutput());
        }
    }
}