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
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamilies;
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamily;
import com.elvenwhiskers.heartgrove.block.family.ModNormalTreeFamily;
import com.elvenwhiskers.heartgrove.block.family.ModNormalTreeFamilies;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider( HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }



    @Override
    protected void generate() {
        dropSelf(ModBlocks.AEGIS_BLOCK.get()); //drop self for just regular drops self. obviously.

        for (ModWoodFamily family : ModWoodFamilies.ALL) {
            dropWoodFamily(family);
        }

        for (ModNormalTreeFamily tree : ModNormalTreeFamilies.ALL) {
            dropNormalTree(tree);
        }

        dropSelf(ModBlocks.LARKSPUR_CRAFTING_TABLE.get());

        dropSelf(ModBlocks.BLUE_WISTERIA_SAPLING.get());
        add(ModBlocks.WISTERIA_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.BLUE_WISTERIA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(ModBlocks.BLUE_WISTERIA_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.BLUE_WISTERIA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(ModBlocks.BLUE_WISTERIA_BLOSSOMS.get(), block ->
                createLeavesDrops(block, ModBlocks.BLUE_WISTERIA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.BLUE_WISTERIA_VINES.get());
        //add crafting table

        dropSelf(ModBlocks.SAWMILL.get());

        add(ModBlocks.AEGIS_ORE.get(),
                block -> createOreDrop(ModBlocks.AEGIS_ORE.get(), ModItems.AEGIS_INGOT.get())); //regular ore loot table.

    }

    private void dropWoodFamily(ModWoodFamily family) {
        dropSelf(family.getLog().get());
        dropSelf(family.getWood().get());
        dropSelf(family.getStrippedLog().get());
        dropSelf(family.getStrippedWood().get());
        dropSelf(family.getPlanks().get());

        dropSelf(family.getStairs().get());
        dropSelf(family.getSlab().get());
        dropSelf(family.getPressurePlate().get());
        dropSelf(family.getButton().get());
        dropSelf(family.getFence().get());
        dropSelf(family.getFenceGate().get());
        dropSelf(family.getWall().get());
        dropSelf(family.getDoor().get());
        dropSelf(family.getTrapdoor().get());
    }

    private void dropNormalTree(ModNormalTreeFamily tree) {
        dropSelf(tree.getSapling().get());

        add(
                tree.getLeaves().get(),
                block -> createLeavesDrops(
                        block,
                        tree.getSapling().get(),
                        NORMAL_LEAVES_SAPLING_CHANCES
                )
        );
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
