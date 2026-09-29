package com.elvenwhiskers.heartgrove.worldgen.tree.normal;

import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

import java.util.function.Supplier;

public class ModTreeShape {

    private final Supplier<TreeConfiguration> configuration;

    public ModTreeShape(Supplier<TreeConfiguration> configuration) {
        this.configuration = configuration;
    }

    public TreeConfiguration createConfiguration() {
        return configuration.get();
    }
}