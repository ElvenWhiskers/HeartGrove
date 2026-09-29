package com.elvenwhiskers.heartgrove.worldgen.tree.wisteria;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class WisteriaTreeGrowers {

    public static final TreeGrower BLUE_WISTERIA = new TreeGrower(
            HeartGrove.MOD_ID + ":blue_wisteria",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.BLUE_WISTERIA_KEY),
            Optional.empty()
    );
}