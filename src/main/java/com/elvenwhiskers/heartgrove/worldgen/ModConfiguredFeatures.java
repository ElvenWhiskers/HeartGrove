package com.elvenwhiskers.heartgrove.worldgen;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import com.elvenwhiskers.heartgrove.worldgen.tree.wisteria.WisteriaColor;
import com.elvenwhiskers.heartgrove.worldgen.tree.wisteria.WisteriaFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import com.elvenwhiskers.heartgrove.block.family.tree.ModNormalTreeFamily;
import com.elvenwhiskers.heartgrove.block.family.tree.ModNormalTreeRegistrar;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_WISTERIA_KEY = registerKey("blue_wisteria");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        for (ModNormalTreeFamily treeFamily : ModNormalTreeRegistrar.getTreeFamilies()) {
            register(
                    context,
                    treeFamily.getConfiguredFeatureKey(),
                    Feature.TREE,
                    treeFamily.getTreeShape().createConfiguration()
            );
        }

        registerWisteriaTree(
                context,
                BLUE_WISTERIA_KEY,
                WisteriaColor.BLUE
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

}
