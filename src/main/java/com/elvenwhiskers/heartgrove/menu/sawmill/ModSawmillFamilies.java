package com.elvenwhiskers.heartgrove.menu.sawmill;

import com.elvenwhiskers.heartgrove.block.ModBlocks;
import java.util.List;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModSawmillFamilies {

    public static final SawmillWoodFamily WISTERIA = new SawmillWoodFamily(
            ModBlocks.WISTERIA_PLANKS.get().asItem(),
            ModBlocks.WISTERIA_LOG.get().asItem(),
            ModBlocks.STRIPPED_WISTERIA_LOG.get().asItem(),
            ModBlocks.WISTERIA_WOOD.get().asItem(),
            ModBlocks.STRIPPED_WISTERIA_WOOD.get().asItem()
    );

    public static final SawmillRecipe WISTERIA_PLANKS = new SawmillRecipe(
            ModBlocks.WISTERIA_PLANKS.get().asItem(),
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.PLANK
    );

    public static final SawmillRecipe WISTERIA_STICKS = new SawmillRecipe(
            Items.STICK,
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.STICK
    );

    public static final SawmillRecipe WISTERIA_PLANK_FENCE = new SawmillRecipe(
            ModBlocks.WISTERIA_FENCE.get().asItem(),
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.FENCE
    );

    public static final SawmillRecipe WISTERIA_STAIRS = new SawmillRecipe(
            ModBlocks.WISTERIA_STAIRS.get().asItem(),
            SawmillMaterialForm.PLANK,
            SawmillRecipeGroup.STAIRS
    );

    public static final SawmillFamilyCatalog WISTERIA_CATALOG = new SawmillFamilyCatalog(
            WISTERIA,
            List.of(
                    WISTERIA_PLANKS,
                    WISTERIA_STICKS,
                    WISTERIA_PLANK_FENCE,
                    WISTERIA_STAIRS
            )
    );

    public static final SawmillCatalog SAWMILL_CATALOG = new SawmillCatalog(
            List.of(
                    WISTERIA_CATALOG
            )
    );

    public static void debugCatalog() {
        printRecipes("Wisteria Log", ModBlocks.WISTERIA_LOG.get().asItem());
        printRecipes("Wisteria Planks", ModBlocks.WISTERIA_PLANKS.get().asItem());
        printRecipes("Potato", Items.POTATO);
    }

    private static void printRecipes(String label, Item inputItem) {
        System.out.println("=== " + label + " ===");

        for (SawmillRecipe recipe : SAWMILL_CATALOG.getAvailableRecipes(inputItem)) {
            System.out.println(recipe.getOutput());
        }
    }

}
