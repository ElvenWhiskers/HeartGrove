package com.elvenwhiskers.heartgrove.item;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import com.elvenwhiskers.heartgrove.block.family.tree.ModNormalTreeRegistrar;
import com.elvenwhiskers.heartgrove.block.family.tree.ModNormalTreeFamily;
import com.elvenwhiskers.heartgrove.block.family.vanilla.ModVanillaWoodSet;
import com.elvenwhiskers.heartgrove.block.family.vanilla.ModVanillaWoodSetRegistrar;
import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamilyRegistrar;
import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamily;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.elvenwhiskers.heartgrove.block.custom.hedge.ModHedge;
import com.elvenwhiskers.heartgrove.block.custom.hedge.ModHedgeRegistrar;

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
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.LARKSPUR.getLog()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(HeartGrove.MOD_ID, "heartgrove_items_tab"))
                    .title(Component.translatable("creativetab.heartgrove.heartgrove_woods"))
                    .displayItems((itemDisplayParameters, output) -> {

                        // Baseline wood families
                        for (ModWoodFamily family : ModWoodFamilyRegistrar.getWoodFamilies()) {
                            addWoodFamily(output, family);
                        }

                        // Normal tree biology
                        for (ModNormalTreeFamily tree : ModNormalTreeRegistrar.getTreeFamilies()) {
                            addNormalTree(output, tree);
                        }

                        for (ModVanillaWoodSet vanillaSet : ModVanillaWoodSetRegistrar.getVanillaWoodSets()) {
                            addVanillaWoodSet(output, vanillaSet);
                        }

                        // Hedges
                        for (ModHedge hedge : ModHedgeRegistrar.getHedges()) {
                            addHedge(output, hedge);
                        }

                        // Larkspur-specific extras
                        output.accept(ModBlocks.LARKSPUR_CRAFTING_TABLE);

                        // Wisteria-specific biology
                        output.accept(ModBlocks.WISTERIA_LEAVES);
                        output.accept(ModBlocks.BLUE_WISTERIA_LEAVES);
                        output.accept(ModBlocks.BLUE_WISTERIA_BLOSSOMS);
                        output.accept(ModBlocks.BLUE_WISTERIA_SAPLING);
                        output.accept(ModBlocks.BLUE_WISTERIA_VINES);

                        // Workstations
                        output.accept(ModBlocks.SAWMILL);

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

    private static void addWoodFamily(
            CreativeModeTab.Output output,
            ModWoodFamily family
    ) {
        output.accept(family.getLog());
        output.accept(family.getWood());
        output.accept(family.getStrippedLog());
        output.accept(family.getStrippedWood());
        output.accept(family.getPlanks());

        output.accept(family.getStairs());
        output.accept(family.getSlab());
        output.accept(family.getPressurePlate());
        output.accept(family.getButton());
        output.accept(family.getFence());
        output.accept(family.getFenceGate());
        output.accept(family.getWall());
        output.accept(family.getDoor());
        output.accept(family.getTrapdoor());
    }

    private static void addVanillaWoodSet(
            CreativeModeTab.Output output,
            ModVanillaWoodSet vanillaSet
    ) {
        output.accept(vanillaSet.getSignItem());
    }

    private static void addNormalTree(
            CreativeModeTab.Output output,
            ModNormalTreeFamily tree
    ) {
        output.accept(tree.getLeaves());
        output.accept(tree.getSapling());
    }

    private static void addHedge(
            CreativeModeTab.Output output,
            ModHedge hedge
    ) {
        output.accept(hedge.getHedge());
    }
}
