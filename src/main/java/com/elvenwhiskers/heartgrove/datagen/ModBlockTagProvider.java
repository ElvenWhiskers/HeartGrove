package com.elvenwhiskers.heartgrove.datagen;


import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HeartGrove.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.AEGIS_BLOCK.get())
                .add(ModBlocks.AEGIS_ORE.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.LARKSPUR_LOG.get())
                .add(ModBlocks.LARKSPUR_WOOD.get())
                .add(ModBlocks.STRIPPED_LARKSPUR_LOG.get())
                .add(ModBlocks.STRIPPED_LARKSPUR_WOOD.get())
                .add(ModBlocks.LARKSPUR_PLANKS.get())
                .add(ModBlocks.LARKSPUR_STAIRS.get())
                .add(ModBlocks.LARKSPUR_SLAB.get())
                .add(ModBlocks.LARKSPUR_PRESSURE_PLATE.get())
                .add(ModBlocks.LARKSPUR_BUTTON.get())
                .add(ModBlocks.LARKSPUR_FENCE.get())
                .add(ModBlocks.LARKSPUR_FENCE_GATE.get())
                .add(ModBlocks.LARKSPUR_WALL.get())
                .add(ModBlocks.LARKSPUR_DOOR.get())
                .add(ModBlocks.LARKSPUR_TRAPDOOR.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.LARKSPUR_FENCE_GATE.get());

        tag(BlockTags.FENCES)
                .add(ModBlocks.LARKSPUR_FENCE.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.LARKSPUR_FENCE.get());

        tag(BlockTags.LEAVES)
                .add(ModBlocks.LARKSPUR_LEAVES.get());

        tag(BlockTags.WALLS)
                .add(ModBlocks.LARKSPUR_WALL.get());

        tag(BlockTags.STAIRS)
                .add(ModBlocks.LARKSPUR_STAIRS.get());

        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.LARKSPUR_STAIRS.get());

        tag(BlockTags.LOGS)
                .add(ModBlocks.LARKSPUR_LOG.get());

        tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)
                .add(ModBlocks.LARKSPUR_LOG.get())
                .add(ModBlocks.LARKSPUR_WOOD.get())
                .add(ModBlocks.STRIPPED_LARKSPUR_LOG.get())
                .add(ModBlocks.STRIPPED_LARKSPUR_WOOD.get());

        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.LARKSPUR_SAPLING.get());

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.LARKSPUR_TRAPDOOR.get());

        tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.LARKSPUR_PRESSURE_PLATE.get());

        tag(BlockTags.DOORS)
                .add(ModBlocks.LARKSPUR_DOOR.get());

        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.LARKSPUR_BUTTON.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.LARKSPUR_PLANKS.get());



        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.AEGIS_ORE.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.LARKSPUR_LOG.get())
                .add(ModBlocks.LARKSPUR_WOOD.get())
                .add(ModBlocks.STRIPPED_LARKSPUR_LOG.get())
                .add(ModBlocks.STRIPPED_LARKSPUR_WOOD.get());

    }
}
