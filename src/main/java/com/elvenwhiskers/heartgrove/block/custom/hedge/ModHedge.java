package com.elvenwhiskers.heartgrove.block.custom.hedge;

import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamily;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModHedge {

    private final String name;
    private final DeferredBlock<? extends Block> leaves;
    private final ModWoodFamily woodFamily;
    private final DeferredBlock<HedgeBlock> hedge;

    public ModHedge(
            String name,
            DeferredBlock<? extends Block> leaves,
            ModWoodFamily woodFamily,
            DeferredBlock<HedgeBlock> hedge
    ) {
        this.name = name;
        this.leaves = leaves;
        this.woodFamily = woodFamily;
        this.hedge = hedge;
    }

    public String getName() {
        return name;
    }

    public DeferredBlock<? extends Block> getLeaves() {
        return leaves;
    }

    public ModWoodFamily getWoodFamily() {
        return woodFamily;
    }

    public DeferredBlock<HedgeBlock> getHedge() {
        return hedge;
    }
}
