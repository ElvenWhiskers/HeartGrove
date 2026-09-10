package com.elvenwhiskers.heartgrove.menu.sawmill;

import java.util.List;
import net.minecraft.world.item.Item;

public class SawmillCatalog {

    private final List<SawmillFamilyCatalog> families;


    public SawmillCatalog(List<SawmillFamilyCatalog> families) {
        this.families = families;
    }


    public List<SawmillFamilyCatalog> getFamilies() {
        return families;
    }

    public SawmillFamilyCatalog findFamily(Item item) {
        for (SawmillFamilyCatalog family : families) {
            if (family.getInputForm(item) != null) {
                return family;
            }
        }

        return null;
    }

    public SawmillMaterialForm getInputForm(Item item) {
        SawmillFamilyCatalog family = findFamily(item);

        if (family == null) {
            return null;
        }

        return family.getInputForm(item);
    }

    public List<SawmillRecipe> getAvailableRecipes(Item item) {
        SawmillFamilyCatalog family = findFamily(item);

        if (family == null) {
            return List.of();
        }

        return family.getAvailableRecipes(item);
    }
}