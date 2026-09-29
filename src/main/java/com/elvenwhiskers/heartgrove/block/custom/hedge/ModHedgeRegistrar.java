package com.elvenwhiskers.heartgrove.block.custom.hedge;

import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamily;
import com.elvenwhiskers.heartgrove.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModHedgeRegistrar {

    private static final List<ModHedge> HEDGES = new ArrayList<>();

    public static List<ModHedge> getHedges() {
        return Collections.unmodifiableList(HEDGES);
    }

    public static ModHedge register(
            DeferredRegister.Blocks blocks,
            String name,
            DeferredBlock<? extends Block> leaves,
            ModWoodFamily woodFamily
    ) {
        String hedgeName = name + "_hedge";

        DeferredBlock<HedgeBlock> hedge = blocks.register(
                hedgeName,
                () -> new HedgeBlock(
                        BlockBehaviour.Properties.ofFullCopy(leaves.get())
                )
        );

        ModItems.ITEMS.register(
                hedgeName,
                () -> new BlockItem(
                        hedge.get(),
                        new Item.Properties()
                )
        );

        ModHedge modHedge = new ModHedge(
                name,
                leaves,
                woodFamily,
                hedge
        );

        HEDGES.add(modHedge);

        return modHedge;
    }
}