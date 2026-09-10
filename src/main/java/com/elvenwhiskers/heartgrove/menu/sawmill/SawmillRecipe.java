package com.elvenwhiskers.heartgrove.menu.sawmill;

import net.minecraft.world.item.Item;

public class SawmillRecipe {

    private final Item output;
    private final SawmillMaterialForm materialForm;
    private final SawmillRecipeGroup group;


    public SawmillRecipe(Item output, SawmillMaterialForm materialForm, SawmillRecipeGroup group) {
        this.output = output;
        this.materialForm = materialForm;
        this.group = group;
    }


    public Item getOutput() {
        return output;
    }


    public SawmillMaterialForm getMaterialForm() {
        return materialForm;
    }


    public SawmillRecipeGroup getGroup() {
        return group;
    }
}