package com.elvenwhiskers.heartgrove.block.family.tree;

import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamily;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.neoforged.neoforge.registries.DeferredBlock;
import com.elvenwhiskers.heartgrove.worldgen.tree.normal.ModTreeShape;

public class ModNormalTreeFamily {

    private final String name;
    private final ModWoodFamily woodFamily;
    private final DeferredBlock<Block> leaves;
    private final DeferredBlock<Block> sapling;
    private final ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey;
    private final ModTreeShape treeShape;

    public ModNormalTreeFamily(
            String name,
            ModWoodFamily woodFamily,
            DeferredBlock<Block> leaves,
            DeferredBlock<Block> sapling,
            ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey,
            ModTreeShape treeShape
    ) {
        this.name = name;
        this.woodFamily = woodFamily;
        this.leaves = leaves;
        this.sapling = sapling;
        this.configuredFeatureKey = configuredFeatureKey;
        this.treeShape = treeShape;
    }

    public String getName() {
        return name;
    }

    public ModWoodFamily getWoodFamily() {
        return woodFamily;
    }

    public DeferredBlock<Block> getLeaves() {
        return leaves;
    }

    public DeferredBlock<Block> getSapling() {
        return sapling;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeatureKey() {
        return configuredFeatureKey;
    }

    public ModTreeShape getTreeShape() {
        return treeShape;
    }
}