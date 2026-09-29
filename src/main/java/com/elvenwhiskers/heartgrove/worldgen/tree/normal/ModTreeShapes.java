package com.elvenwhiskers.heartgrove.worldgen.tree.normal;

import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;

public class ModTreeShapes {

    public static final ModTreeShape LARKSPUR = new ModTreeShape(
            () -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.LARKSPUR.getLog().get()),
                    new ForkingTrunkPlacer(4, 4, 3),

                    BlockStateProvider.simple(ModBlocks.LARKSPUR_TREE.getLeaves().get()),
                    new BlobFoliagePlacer(
                            ConstantInt.of(2),
                            ConstantInt.of(3),
                            3
                    ),

                    new TwoLayersFeatureSize(1, 0, 2)
            ).build()
    );

    public static final ModTreeShape FROSTBELL_BLOSSOM = new ModTreeShape(
            () -> new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.FROSTBELL_BLOSSOM.getLog().get()),
                    new CherryTrunkPlacer(
                            7,
                            1,
                            0,
                            new WeightedListInt(
                                    SimpleWeightedRandomList.<IntProvider>builder()
                                            .add(ConstantInt.of(1), 1)
                                            .add(ConstantInt.of(2), 1)
                                            .add(ConstantInt.of(3), 1)
                                            .build()
                            ),
                            UniformInt.of(2, 4),
                            UniformInt.of(-4, -3),
                            UniformInt.of(-1, 0)
                    ),

                    BlockStateProvider.simple(ModBlocks.FROSTBELL_BLOSSOM_TREE.getLeaves().get()),
                    new CherryFoliagePlacer(
                            ConstantInt.of(4),
                            ConstantInt.of(0),
                            ConstantInt.of(5),
                            0.25F,
                            0.5F,
                            0.16666667F,
                            0.33333334F
                    ),

                    new TwoLayersFeatureSize(1, 0, 2)
            ).ignoreVines().build()
    );

    private ModTreeShapes() {
    }
}