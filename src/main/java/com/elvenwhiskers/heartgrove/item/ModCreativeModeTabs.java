package com.elvenwhiskers.heartgrove.item;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HeartGrove.MOD_ID);

    public static final Supplier<CreativeModeTab> HEARTGROVE_ITEMS_TAB = CREATIVE_MODE_TAB.register("heartgrove_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.AEGIS_INGOT.get()))
                    .title(Component.translatable("creativetab.heartgrove.heartgrove_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.AEGIS_INGOT);
                        output.accept(ModItems.RAW_AEGIS);
                        output.accept(ModBlocks.AEGIS_BLOCK);
                        output.accept(ModBlocks.AEGIS_ORE);
                    }).build());

    public static final Supplier<CreativeModeTab> HEARTGROVE_WOODS_TAB = CREATIVE_MODE_TAB.register("heartgrove_woods_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.LARKSPUR_LOG))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(HeartGrove.MOD_ID, "heartgrove_items_tab"))
                    .title(Component.translatable("creativetab.heartgrove.heartgrove_woods"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.LARKSPUR_LOG);
                        output.accept(ModBlocks.LARKSPUR_WOOD);
                        output.accept(ModBlocks.STRIPPED_LARKSPUR_LOG);
                        output.accept(ModBlocks.STRIPPED_LARKSPUR_WOOD);
                        output.accept(ModBlocks.LARKSPUR_PLANKS);
                        output.accept(ModBlocks.LARKSPUR_LEAVES);
                        output.accept(ModBlocks.LARKSPUR_SAPLING);
                        output.accept(ModBlocks.LARKSPUR_STAIRS);
                        output.accept(ModBlocks.LARKSPUR_SLAB);
                        output.accept(ModBlocks.LARKSPUR_PRESSURE_PLATE);
                        output.accept(ModBlocks.LARKSPUR_BUTTON);
                        output.accept(ModBlocks.LARKSPUR_FENCE);
                        output.accept(ModBlocks.LARKSPUR_FENCE_GATE);
                        output.accept(ModBlocks.LARKSPUR_WALL);
                        output.accept(ModBlocks.LARKSPUR_DOOR);
                        output.accept(ModBlocks.LARKSPUR_TRAPDOOR);
                        output.accept(ModBlocks.LARKSPUR_CRAFTING_TABLE);

                        output.accept(ModBlocks.WISTERIA_LOG);
                        output.accept(ModBlocks.WISTERIA_WOOD);
                        output.accept(ModBlocks.STRIPPED_WISTERIA_LOG);
                        output.accept(ModBlocks.STRIPPED_WISTERIA_WOOD);
                        output.accept(ModBlocks.WISTERIA_PLANKS);
                        output.accept(ModBlocks.WISTERIA_LEAVES);
                        output.accept(ModBlocks.BLUE_WISTERIA_LEAVES);
                        output.accept(ModBlocks.BLUE_WISTERIA_BLOSSOMS);
                        output.accept(ModBlocks.BLUE_WISTERIA_SAPLING);
                        output.accept(ModBlocks.BLUE_WISTERIA_VINES);
                        output.accept(ModBlocks.WISTERIA_STAIRS);
                        output.accept(ModBlocks.WISTERIA_SLAB);
                        output.accept(ModBlocks.WISTERIA_PRESSURE_PLATE);
                        output.accept(ModBlocks.WISTERIA_BUTTON);
                        output.accept(ModBlocks.WISTERIA_FENCE);
                        output.accept(ModBlocks.WISTERIA_FENCE_GATE);
                        output.accept(ModBlocks.WISTERIA_WALL);
                        output.accept(ModBlocks.WISTERIA_DOOR);
                        output.accept(ModBlocks.WISTERIA_TRAPDOOR);
                        //output.accept(ModBlocks.WISTERIA_CRAFTING_TABLE);
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
