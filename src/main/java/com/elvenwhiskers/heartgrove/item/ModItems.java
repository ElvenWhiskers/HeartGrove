package com.elvenwhiskers.heartgrove.item;

import com.elvenwhiskers.heartgrove.HeartGrove;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HeartGrove.MOD_ID);

    public static final DeferredItem<Item> AEGIS_INGOT = ITEMS.register("aegis_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_AEGIS = ITEMS.register("raw_aegis",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
