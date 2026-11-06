package com.elvenwhiskers.heartgrove.item;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.elvenwhiskers.heartgrove.item.custom.builder.PaletteBrushItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HeartGrove.MOD_ID);

    public static final DeferredItem<Item> AEGIS_INGOT = ITEMS.register("aegis_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_AEGIS = ITEMS.register("raw_aegis",
            () -> new Item(new Item.Properties()));

    // Builder's Tools
    // Builder's Tools
    public static final DeferredItem<Item> PALETTE_BRUSH = ITEMS.register("palette_brush",
            () -> new PaletteBrushItem(new Item.Properties().stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
