package com.elvenwhiskers.heartgrove.menu.sawmill;

import net.minecraft.world.item.Item;

public class SawmillWoodFamily {

    private final Item plank;
    private final Item log;
    private final Item strippedLog;
    private final Item wood;
    private final Item strippedWood;


    public SawmillWoodFamily(Item plank, Item log, Item strippedLog, Item wood, Item strippedWood) {
        this.plank = plank;
        this.log = log;
        this.strippedLog = strippedLog;
        this.wood = wood;
        this.strippedWood = strippedWood;
    }


    public Item getPlank() {
        return plank;
    }


    public Item getLog() {
        return log;
    }


    public Item getWood() {
        return wood;
    }

    public Item getStrippedLog() {
        return strippedLog;
    }

    public Item getStrippedWood() {
        return strippedWood;
    }

    public Item getItem(SawmillMaterialForm form) {
        return switch (form) {
            case PLANK -> plank;
            case LOG -> log;
            case STRIPPED_LOG -> strippedLog;
            case WOOD -> wood;
            case STRIPPED_WOOD -> strippedWood;
        };
    }

    public SawmillMaterialForm getForm(Item item) {
        if (item == plank) {
            return SawmillMaterialForm.PLANK;
        }

        if (item == log) {
            return SawmillMaterialForm.LOG;
        }

        if (item == strippedLog) {
            return SawmillMaterialForm.STRIPPED_LOG;
        }

        if (item == wood) {
            return SawmillMaterialForm.WOOD;
        }

        if (item == strippedWood) {
            return SawmillMaterialForm.STRIPPED_WOOD;
        }

        return null;
    }

}