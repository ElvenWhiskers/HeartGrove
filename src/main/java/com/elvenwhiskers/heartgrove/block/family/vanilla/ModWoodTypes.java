package com.elvenwhiskers.heartgrove.block.family.vanilla;

import com.elvenwhiskers.heartgrove.HeartGrove;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {

    public static final WoodType FROSTBELL_BLOSSOM = WoodType.register(
            new WoodType(
                    HeartGrove.MOD_ID + ":frostbell_blossom",
                    BlockSetType.CHERRY
            )
    );
}