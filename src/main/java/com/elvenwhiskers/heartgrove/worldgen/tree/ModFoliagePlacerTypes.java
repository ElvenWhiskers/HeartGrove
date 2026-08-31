package com.elvenwhiskers.heartgrove.worldgen.tree;

import com.elvenwhiskers.heartgrove.HeartGrove;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.bus.api.IEventBus;

public class ModFoliagePlacerTypes {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, HeartGrove.MOD_ID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<WisteriaFoliagePlacer>> WISTERIA =
            FOLIAGE_PLACER_TYPES.register(
                    "wisteria",
                    () -> new FoliagePlacerType<>(WisteriaFoliagePlacer.CODEC)
            );


    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACER_TYPES.register(eventBus);
    }

}
