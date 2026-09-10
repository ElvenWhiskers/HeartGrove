package com.elvenwhiskers.heartgrove.menu.sawmill;

import java.util.List;
import net.minecraft.world.item.Item;
import java.util.Comparator;

public class SawmillFamilyCatalog {

    private final SawmillWoodFamily woodFamily;
    private final List<SawmillRecipe> recipes;


    public SawmillFamilyCatalog(SawmillWoodFamily woodFamily, List<SawmillRecipe> recipes) {
        this.woodFamily = woodFamily;
        this.recipes = recipes;
    }


    public SawmillWoodFamily getWoodFamily() {
        return woodFamily;
    }


    public List<SawmillRecipe> getRecipes() {
        return recipes;
    }

    public SawmillMaterialForm getInputForm(Item item) {
        return woodFamily.getForm(item);
    }

    public boolean canAccessRecipe(SawmillMaterialForm inputForm, SawmillRecipe recipe) {
        return inputForm.canAccess(recipe.getMaterialForm());
    }

    public List<SawmillRecipe> getAvailableRecipes(SawmillMaterialForm inputForm) {
        return recipes.stream()
                .filter(recipe -> canAccessRecipe(inputForm, recipe))
                .sorted(
                        Comparator
                                .comparingInt((SawmillRecipe recipe) ->
                                        recipe.getGroup().getDisplayOrder()
                                )
                                .thenComparingInt(recipe ->
                                        recipe.getMaterialForm().getDisplayOrder()
                                )
                )
                .toList();
    }

    public List<SawmillRecipe> getAvailableRecipes(Item inputItem) {
        SawmillMaterialForm inputForm = getInputForm(inputItem);

        if (inputForm == null) {
            return List.of();
        }

        return getAvailableRecipes(inputForm);
    }


}
