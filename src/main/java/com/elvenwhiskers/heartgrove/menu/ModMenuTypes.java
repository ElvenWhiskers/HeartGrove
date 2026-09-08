package com.elvenwhiskers.heartgrove.menu;

import com.elvenwhiskers.heartgrove.HeartGrove;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.flag.FeatureFlags;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, HeartGrove.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<SawmillMenu>> SAWMILL_MENU =
            MENUS.register("sawmill",
                    () -> new MenuType<>(SawmillMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
