package com.elvenwhiskers.heartgrove.worldgen.tree;

import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

public enum WisteriaColor implements StringRepresentable {

    BLUE(
            "blue",
            ModBlocks.BLUE_WISTERIA_LEAVES,
            ModBlocks.BLUE_WISTERIA_BLOSSOMS,
            ModBlocks.BLUE_WISTERIA_VINES
    );

    public static final StringRepresentable.EnumCodec<WisteriaColor> CODEC =
            StringRepresentable.fromEnum(WisteriaColor::values);

    private final String name;
    private final DeferredBlock<Block> transitionLeaves;
    private final DeferredBlock<Block> blossoms;
    private final DeferredBlock<Block> vines;

    WisteriaColor(
            String name,
            DeferredBlock<Block> transitionLeaves,
            DeferredBlock<Block> blossoms,
            DeferredBlock<Block> vines
    ) {
        this.name = name;
        this.transitionLeaves = transitionLeaves;
        this.blossoms = blossoms;
        this.vines = vines;
    }

    @Override
    public String getSerializedName() {
        return name;
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