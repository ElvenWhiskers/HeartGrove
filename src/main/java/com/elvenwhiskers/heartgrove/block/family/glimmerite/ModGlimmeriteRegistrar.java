
package com.elvenwhiskers.heartgrove.block.family.glimmerite;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.elvenwhiskers.heartgrove.block.custom.glimmerite.GlimmeriteClusterBlock;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ModGlimmeriteRegistrar {

    private ModGlimmeriteRegistrar() {
    }

    public static Map<String, DeferredBlock<Block>> register(
            DeferredRegister.Blocks blocks
    ) {
        Map<String, DeferredBlock<Block>> colors = new LinkedHashMap<>();

        String[] names = {
                "purple",
                "green",
                "orange",
                "yellow",
                "white",
                "pink",
                "red",
                "indigo",
                "blue",
                "black"
        };

        for (String color : names) {
            String name = color + "_glimmerite";

            DeferredBlock<Block> block = blocks.register(
                    name,
                    () -> new Block(
                            BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
                                    .lightLevel(state -> 10)
                    )
            );

            colors.put(color, block);
        }

        return java.util.Collections.unmodifiableMap(colors);
    }


    public static DeferredBlock<GlimmeriteClusterBlock> registerCluster(
            DeferredRegister.Blocks blocks,
            String color
    ) {
        return blocks.register(
                color + "_glimmerite_cluster",
                () -> new GlimmeriteClusterBlock(
                        BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER)
                                .lightLevel(state -> 10)
                                .noOcclusion()
                )
        );
    }

}
