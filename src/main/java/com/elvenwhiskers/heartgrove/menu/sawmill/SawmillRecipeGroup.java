package com.elvenwhiskers.heartgrove.menu.sawmill;

public enum SawmillRecipeGroup {

    PLANK(1, 1, 0),
    STICK(1, 2, 1),
    FENCE(1, 1, 2),
    SLAB(1, 1, 3),
    STAIRS(2, 1, 4),
    WALL(1, 1, 5),
    BUTTON(1, 1, 6),
    PRESSURE_PLATE(2, 1, 7),
    FENCE_GATE(2, 1, 8);

    private final int plankCost;
    private final int outputCount;
    private final int displayOrder;


    SawmillRecipeGroup(int plankCost, int outputCount, int displayOrder) {
        this.plankCost = plankCost;
        this.outputCount = outputCount;
        this.displayOrder = displayOrder;
    }


    public int getPlankCost() {
        return plankCost;
    }


    public int getOutputCount() {
        return outputCount;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }
}