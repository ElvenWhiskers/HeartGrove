package com.elvenwhiskers.heartgrove.worldgen;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import com.elvenwhiskers.heartgrove.worldgen.tree.WisteriaColor;
import com.elvenwhiskers.heartgrove.worldgen.tree.WisteriaFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> LARKSPUR_KEY = registerKey("larkspur");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_WISTERIA_KEY = registerKey("blue_wisteria");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROSTBELL_BLOSSOM_KEY = registerKey("frostbell_blossom");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        register(context, LARKSPUR_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.LARKSPUR_LOG.get()),
                new ForkingTrunkPlacer(4, 4, 3),

                BlockStateProvider.simple(ModBlocks.LARKSPUR_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), 3),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        registerWisteriaTree(
                context,
                BLUE_WISTERIA_KEY,
                WisteriaColor.BLUE
        );

        register(
                context,
                FROSTBELL_BLOSSOM_KEY,
                Feature.TREE,
                frostbellBlossomTree().build()
        );


    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(HeartGrove.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    private static void registerWisteriaTree(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            WisteriaColor color
    ) {
        register(
                context,
                key,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.WISTERIA_LOG.get()),
                        new StraightTrunkPlacer(5, 2, 0),

                        BlockStateProvider.simple(ModBlocks.WISTERIA_LEAVES.get()),
                        new WisteriaFoliagePlacer(
                                ConstantInt.of(0),
                                ConstantInt.of(0),
                                color
                        ),

                        new TwoLayersFeatureSize(1, 0, 2)
                ).build()
        );
    }

    private static TreeConfiguration.TreeConfigurationBuilder frostbellBlossomTree() {
        return new TreeConfiguration.TreeConfigurationBuilder(
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
        ).ignoreVines();
    }

}
