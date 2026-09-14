package com.elvenwhiskers.heartgrove.block.family;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModNormalTreeFamily {

    private final String name;
    private final ModWoodFamily woodFamily;

    private final DeferredBlock<Block> leaves;
    private final DeferredBlock<Block> sapling;


    public ModNormalTreeFamily(
            String name,
            ModWoodFamily woodFamily,
            DeferredBlock<Block> leaves,
            DeferredBlock<Block> sapling
    ) {
        this.name = name;
        this.woodFamily = woodFamily;
        this.leaves = leaves;
        this.sapling = sapling;
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
}