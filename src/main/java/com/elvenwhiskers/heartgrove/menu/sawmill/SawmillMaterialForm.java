package com.elvenwhiskers.heartgrove.menu.sawmill;

public enum SawmillMaterialForm {

    PLANK(1, 0),
    LOG(4, 1),
    STRIPPED_LOG(4, 2),
    WOOD(4, 3),
    STRIPPED_WOOD(4, 4);

    private final int plankValue;
    private final int displayOrder;


    SawmillMaterialForm(int plankValue, int displayOrder) {
        this.plankValue = plankValue;
        this.displayOrder = displayOrder;
    }


    public int getPlankValue() {
        return plankValue;
    }

    public boolean canAccess(SawmillMaterialForm outputForm) {
        if (this == PLANK) {
            return outputForm == PLANK;
        }

        return true;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }
}