package com.elvenwhiskers.heartgrove.worldgen.tree;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower LARKSPUR = new TreeGrower(HeartGrove.MOD_ID + ":larkspur",
            Optional.empty(), Optional.of(ModConfiguredFeatures.LARKSPUR_KEY), Optional.empty());

    public static final TreeGrower BLUE_WISTERIA = new TreeGrower(HeartGrove.MOD_ID + ":blue_wisteria",
            Optional.empty(), Optional.of(ModConfiguredFeatures.BLUE_WISTERIA_KEY), Optional.empty());

}
