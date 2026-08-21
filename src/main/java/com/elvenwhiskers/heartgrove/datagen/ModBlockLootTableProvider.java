package com.elvenwhiskers.heartgrove.datagen;

import com.elvenwhiskers.heartgrove.block.ModBlocks;
import com.elvenwhiskers.heartgrove.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider( HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.AEGIS_BLOCK.get()); //drop self for just regular drops self. obviously.

        dropSelf(ModBlocks.LARKSPUR_LOG.get());
        dropSelf(ModBlocks.LARKSPUR_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_LARKSPUR_LOG.get());
        dropSelf(ModBlocks.STRIPPED_LARKSPUR_WOOD.get());
        dropSelf(ModBlocks.LARKSPUR_PLANKS.get());
        dropSelf(ModBlocks.LARKSPUR_SAPLING.get());
        add(ModBlocks.LARKSPUR_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.LARKSPUR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.LARKSPUR_STAIRS.get());
        dropSelf(ModBlocks.LARKSPUR_SLAB.get());
        dropSelf(ModBlocks.LARKSPUR_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.LARKSPUR_BUTTON.get());
        dropSelf(ModBlocks.LARKSPUR_FENCE.get());
        dropSelf(ModBlocks.LARKSPUR_FENCE_GATE.get());
        dropSelf(ModBlocks.LARKSPUR_WALL.get());
        dropSelf(ModBlocks.LARKSPUR_DOOR.get());
        dropSelf(ModBlocks.LARKSPUR_TRAPDOOR.get());

        add(ModBlocks.AEGIS_ORE.get(),
                block -> createOreDrop(ModBlocks.AEGIS_ORE.get(), ModItems.AEGIS_INGOT.get())); //regular ore loot table.

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
