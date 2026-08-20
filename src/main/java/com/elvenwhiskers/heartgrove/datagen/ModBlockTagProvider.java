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
