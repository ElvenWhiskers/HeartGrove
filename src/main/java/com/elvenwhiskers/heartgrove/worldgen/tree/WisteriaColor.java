package com.elvenwhiskers.heartgrove.worldgen.tree;

import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public enum WisteriaColor {

    BLUE(
            ModBlocks.BLUE_WISTERIA_LEAVES,
            ModBlocks.BLUE_WISTERIA_BLOSSOMS,
            ModBlocks.BLUE_WISTERIA_VINES
    );

    private final DeferredBlock<Block> transitionLeaves;
    private final DeferredBlock<Block> blossoms;
    private final DeferredBlock<Block> vines;

    WisteriaColor(
            DeferredBlock<Block> transitionLeaves,
            DeferredBlock<Block> blossoms,
            DeferredBlock<Block> vines
    ) {
        this.transitionLeaves = transitionLeaves;
        this.blossoms = blossoms;
        this.vines = vines;
    }

    public DeferredBlock<Block> transitionLeaves() {
        return transitionLeaves;
    }

    public DeferredBlock<Block> blossoms() {
        return blossoms;
    }

    public DeferredBlock<Block> vines() {
        return vines;
    }
}