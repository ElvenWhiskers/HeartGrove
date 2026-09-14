package com.elvenwhiskers.heartgrove.datagen;


import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamilies;
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamily;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import net.neoforged.neoforge.common.Tags;
import com.elvenwhiskers.heartgrove.block.family.ModNormalTreeFamily;
import com.elvenwhiskers.heartgrove.block.family.ModNormalTreeFamilies;

import java.util.concurrent.CompletableFuture;


public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, HeartGrove.MOD_ID, existingFileHelper);
    }


    @Override
    protected void addTags(HolderLookup.Provider provider) {

        for (ModWoodFamily family : ModWoodFamilies.ALL) {
            addWoodFamilyTags(family);
        }

        for (ModNormalTreeFamily tree : ModNormalTreeFamilies.ALL) {
            addNormalTreeTags(tree);
        }


        // Ores / mineral blocks
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.AEGIS_BLOCK.get())
                .add(ModBlocks.AEGIS_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.AEGIS_ORE.get());


        // Larkspur-specific tree blocks
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.LARKSPUR_CRAFTING_TABLE.get());


        // Wisteria-specific tree blocks
        tag(BlockTags.LEAVES)
                .add(ModBlocks.WISTERIA_LEAVES.get())
                .add(ModBlocks.BLUE_WISTERIA_LEAVES.get())
                .add(ModBlocks.BLUE_WISTERIA_BLOSSOMS.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(
                        ModBlocks.WISTERIA_LEAVES.get(),
                        ModBlocks.BLUE_WISTERIA_LEAVES.get(),
                        ModBlocks.BLUE_WISTERIA_BLOSSOMS.get()
                );

        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.BLUE_WISTERIA_SAPLING.get());


        // Workstations
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.SAWMILL.get());
    }


    private void addWoodFamilyTags(ModWoodFamily family) {

        // Tool behavior
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(
                        family.getLog().get(),
                        family.getWood().get(),
                        family.getStrippedLog().get(),
                        family.getStrippedWood().get(),
                        family.getPlanks().get(),
                        family.getStairs().get(),
                        family.getSlab().get(),
                        family.getPressurePlate().get(),
                        family.getButton().get(),
                        family.getFence().get(),
                        family.getFenceGate().get(),
                        family.getWall().get(),
                        family.getDoor().get(),
                        family.getTrapdoor().get()
                );


        // Log family
        tag(BlockTags.LOGS)
                .add(
                        family.getLog().get(),
                        family.getWood().get(),
                        family.getStrippedLog().get(),
                        family.getStrippedWood().get()
                );

        tag(BlockTags.LOGS_THAT_BURN)
                .add(
                        family.getLog().get(),
                        family.getWood().get(),
                        family.getStrippedLog().get(),
                        family.getStrippedWood().get()
                );

        tag(Tags.Blocks.STRIPPED_LOGS)
                .add(family.getStrippedLog().get());

        tag(Tags.Blocks.STRIPPED_WOODS)
                .add(family.getStrippedWood().get());

        tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)
                .add(
                        family.getLog().get(),
                        family.getWood().get(),
                        family.getStrippedLog().get(),
                        family.getStrippedWood().get()
                );

        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(family.getLog().get());


        // Planks
        tag(BlockTags.PLANKS)
                .add(family.getPlanks().get());


        // Stairs
        tag(BlockTags.STAIRS)
                .add(family.getStairs().get());

        tag(BlockTags.WOODEN_STAIRS)
                .add(family.getStairs().get());


        // Slabs
        tag(BlockTags.SLABS)
                .add(family.getSlab().get());

        tag(BlockTags.WOODEN_SLABS)
                .add(family.getSlab().get());


        // Fences
        tag(BlockTags.FENCES)
                .add(family.getFence().get());

        tag(BlockTags.WOODEN_FENCES)
                .add(family.getFence().get());

        tag(BlockTags.FENCE_GATES)
                .add(family.getFenceGate().get());


        // Walls
        tag(BlockTags.WALLS)
                .add(family.getWall().get());


        // Buttons
        tag(BlockTags.BUTTONS)
                .add(family.getButton().get());

        tag(BlockTags.WOODEN_BUTTONS)
                .add(family.getButton().get());


        // Pressure plates
        tag(BlockTags.PRESSURE_PLATES)
                .add(family.getPressurePlate().get());

        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(family.getPressurePlate().get());


        // Doors
        tag(BlockTags.DOORS)
                .add(family.getDoor().get());

        tag(BlockTags.WOODEN_DOORS)
                .add(family.getDoor().get());


        // Trapdoors
        tag(BlockTags.TRAPDOORS)
                .add(family.getTrapdoor().get());

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(family.getTrapdoor().get());

    }

    private void addNormalTreeTags(ModNormalTreeFamily tree) {
        tag(BlockTags.LEAVES)
                .add(tree.getLeaves().get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(tree.getLeaves().get());

        tag(BlockTags.SAPLINGS)
                .add(tree.getSapling().get());
    }
}