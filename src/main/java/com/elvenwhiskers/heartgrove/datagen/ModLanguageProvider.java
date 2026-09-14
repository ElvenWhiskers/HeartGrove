package com.elvenwhiskers.heartgrove.datagen;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamilies;
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamily;
import com.elvenwhiskers.heartgrove.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import com.elvenwhiskers.heartgrove.block.family.ModNormalTreeFamily;
import com.elvenwhiskers.heartgrove.block.family.ModNormalTreeFamilies;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(PackOutput output) {
        super(output, HeartGrove.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

        // Regular items
        addItem(ModItems.AEGIS_INGOT, "Aegis Ingot");
        addItem(ModItems.RAW_AEGIS, "Raw Aegis");


        // Regular blocks
        addBlock(ModBlocks.AEGIS_BLOCK, "Aegis Block");
        addBlock(ModBlocks.AEGIS_ORE, "Aegis Ore");


        // Standard wood families
        for (ModWoodFamily family : ModWoodFamilies.ALL) {
            addWoodFamilyTranslations(family);
        }

        for (ModNormalTreeFamily tree : ModNormalTreeFamilies.ALL) {
            addNormalTreeTranslations(tree);
        }


        // Larkspur-specific
        addBlock(ModBlocks.LARKSPUR_CRAFTING_TABLE, "Larkspur Crafting Table");


        // Wisteria-specific
        addBlock(ModBlocks.WISTERIA_LEAVES, "Wisteria Leaves");
        addBlock(ModBlocks.BLUE_WISTERIA_LEAVES, "Blue Wisteria Leaves");
        addBlock(ModBlocks.BLUE_WISTERIA_BLOSSOMS, "Blue Wisteria Blossoms");
        addBlock(ModBlocks.BLUE_WISTERIA_VINES, "Blue Wisteria Vines");
        addBlock(ModBlocks.BLUE_WISTERIA_SAPLING, "Blue Wisteria Sapling");


        // Workstations
        addBlock(ModBlocks.SAWMILL, "Sawmill");
        add("container.heartgrove.sawmill", "Sawmill");


        // Creative tabs
        add(
                "creativetab.heartgrove.heartgrove_items",
                "Heartgrove Items"
        );

        add(
                "creativetab.heartgrove.heartgrove_woods",
                "Heartgrove Woods"
        );
    }


    private void addWoodFamilyTranslations(ModWoodFamily family) {
        String name = titleCase(family.getName());

        addBlock(family.getLog(), name + " Log");
        addBlock(family.getWood(), name + " Wood");

        addBlock(
                family.getStrippedLog(),
                "Stripped " + name + " Log"
        );

        addBlock(
                family.getStrippedWood(),
                "Stripped " + name + " Wood"
        );

        addBlock(family.getPlanks(), name + " Planks");

        addBlock(family.getStairs(), name + " Stairs");
        addBlock(family.getSlab(), name + " Slab");
        addBlock(
                family.getPressurePlate(),
                name + " Pressure Plate"
        );

        addBlock(family.getButton(), name + " Button");
        addBlock(family.getFence(), name + " Fence");
        addBlock(
                family.getFenceGate(),
                name + " Fence Gate"
        );

        addBlock(family.getWall(), name + " Wall");
        addBlock(family.getDoor(), name + " Door");
        addBlock(family.getTrapdoor(), name + " Trapdoor");
    }

    private void addNormalTreeTranslations(ModNormalTreeFamily tree) {
        String name = titleCase(tree.getName());

        addBlock(tree.getLeaves(), name + " Leaves");
        addBlock(tree.getSapling(), name + " Sapling");
    }


    private String titleCase(String name) {
        String[] words = name.split("_");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!result.isEmpty()) {
                result.append(" ");
            }

            result.append(
                    Character.toUpperCase(word.charAt(0))
            );

            result.append(word.substring(1));
        }

        return result.toString();
    }
}
