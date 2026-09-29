package com.elvenwhiskers.heartgrove.datagen.models;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.custom.HedgeBlock;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import com.elvenwhiskers.heartgrove.block.custom.hedge.ModHedge;

import static net.minecraft.client.gui.components.ImageWidget.texture;

public class HedgeModelGenerator {

    private final BlockStateProvider blockStates;
    private final BlockModelProvider models;

    public HedgeModelGenerator(BlockStateProvider blockStates) {
        this.blockStates = blockStates;
        this.models = blockStates.models();
    }

    public void createHedge(ModHedge hedge) {
        String name = hedge.getHedge().getId().getPath();

        ResourceLocation leavesTexture =
                blockStates.blockTexture(hedge.getLeaves().get());

        ResourceLocation woodTexture =
                blockStates.blockTexture(hedge.getWoodFamily().getLog().get());

        createHedgeBlockState(
                name,
                hedge.getHedge().get()
        );

        createIsolatedHedge(
                name,
                leavesTexture,
                woodTexture
        );

        createHedgeFoliageModels(
                name,
                leavesTexture,
                true
        );

        createHedgeFoliageModels(
                name,
                leavesTexture,
                false
        );

        createHedgeWoodModels(
                name,
                woodTexture
        );
    }

    public BlockModelBuilder createIsolatedHedge(
            String name,
            ResourceLocation leavesTexture,
            ResourceLocation woodTexture
    ) {
        BlockModelBuilder model = models
                .withExistingParent(name, "block/block")
                .texture("leaves", leavesTexture)
                .texture("wood", woodTexture)
                .texture("particle", leavesTexture)
                .renderType("cutout");

        // Main foliage body:
        // 12x14x12, centered in the block.
        // Leaves begin above the 2px wooden foundation.
        model.element()
                .from(2, 2, 2)
                .to(14, 16, 14)
                .allFaces((direction, face) -> face.texture("#leaves"))
                .end();

        // Wooden foundation:
        // 8x2x8, centered underneath the foliage.
        model.element()
                .from(4, 0, 4)
                .to(12, 2, 12)
                .allFaces((direction, face) -> face.texture("#wood"))
                .end();

        return model;
    }

    private BlockModelBuilder createFoliagePiece(
            String name,
            ResourceLocation leavesTexture,
            float fromX,
            float fromY,
            float fromZ,
            float toX,
            float toY,
            float toZ
    ) {
        BlockModelBuilder model = models
                .withExistingParent(name, "block/block")
                .texture("leaves", leavesTexture)
                .texture("particle", leavesTexture)
                .renderType("cutout");

        model.element()
                .from(fromX, fromY, fromZ)
                .to(toX, toY, toZ)
                .allFaces((direction, face) -> face.texture("#leaves"))
                .end();

        return model;
    }

    public void createHedgeFoliageModels(
            String name,
            ResourceLocation leavesTexture,
            boolean hasBase
    ) {
        float bottomY = hasBase ? 2 : 0;
        String suffix = hasBase ? "_base" : "_full";

        // Center of the hedge.
        createFoliagePiece(
                name + "_foliage_core" + suffix,
                leavesTexture,
                2, bottomY, 2,
                14, 16, 14
        );

        // Connection toward north.
        createFoliagePiece(
                name + "_foliage_north" + suffix,
                leavesTexture,
                2, bottomY, 0,
                14, 16, 2
        );

        // Connection toward east.
        createFoliagePiece(
                name + "_foliage_east" + suffix,
                leavesTexture,
                14, bottomY, 2,
                16, 16, 14
        );

        // Connection toward south.
        createFoliagePiece(
                name + "_foliage_south" + suffix,
                leavesTexture,
                2, bottomY, 14,
                14, 16, 16
        );

        // Connection toward west.
        createFoliagePiece(
                name + "_foliage_west" + suffix,
                leavesTexture,
                0, bottomY, 2,
                2, 16, 14
        );
    }

    private BlockModelBuilder createWoodPiece(
            String name,
            ResourceLocation woodTexture,
            float fromX,
            float fromY,
            float fromZ,
            float toX,
            float toY,
            float toZ
    ) {
        BlockModelBuilder model = models
                .withExistingParent(name, "block/block")
                .texture("wood", woodTexture)
                .texture("particle", woodTexture)
                .renderType("cutout");

        model.element()
                .from(fromX, fromY, fromZ)
                .to(toX, toY, toZ)
                .allFaces((direction, face) -> face.texture("#wood"))
                .end();

        return model;
    }

    public void createHedgeWoodModels(
            String name,
            ResourceLocation woodTexture
    ) {
        // Center wooden foundation.
        createWoodPiece(
                name + "_wood_core",
                woodTexture,
                4, 0, 4,
                12, 3, 12
        );

        // Wooden rail extending toward north.
        createWoodPiece(
                name + "_wood_north",
                woodTexture,
                4, 0, 0,
                12, 3, 4
        );

        // Wooden rail extending toward east.
        createWoodPiece(
                name + "_wood_east",
                woodTexture,
                12, 0, 4,
                16, 3, 12
        );

        // Wooden rail extending toward south.
        createWoodPiece(
                name + "_wood_south",
                woodTexture,
                4, 0, 12,
                12, 3, 16
        );

        // Wooden rail extending toward west.
        createWoodPiece(
                name + "_wood_west",
                woodTexture,
                0, 0, 4,
                4, 3, 12
        );
    }

    public void createHedgeBlockState(
            String name,
            HedgeBlock hedge
    ) {
        MultiPartBlockStateBuilder builder = blockStates.getMultipartBuilder(hedge);

        // BASE = true:
        // Use foliage that begins at Y=2, leaving room for the wood.
        builder.part()
                .modelFile(existingModel(name + "_foliage_core_base"))
                .addModel()
                .condition(HedgeBlock.BASE, true)
                .end();

        // BASE = false:
        // Use full-height foliage all the way down to Y=0.
        builder.part()
                .modelFile(existingModel(name + "_foliage_core_full"))
                .addModel()
                .condition(HedgeBlock.BASE, false)
                .end();


        // ----- NORTH -----

        builder.part()
                .modelFile(existingModel(name + "_foliage_north_base"))
                .addModel()
                .condition(HedgeBlock.BASE, true)
                .condition(HedgeBlock.NORTH, true)
                .end();

        builder.part()
                .modelFile(existingModel(name + "_foliage_north_full"))
                .addModel()
                .condition(HedgeBlock.BASE, false)
                .condition(HedgeBlock.NORTH, true)
                .end();


        // ----- EAST -----

        builder.part()
                .modelFile(existingModel(name + "_foliage_east_base"))
                .addModel()
                .condition(HedgeBlock.BASE, true)
                .condition(HedgeBlock.EAST, true)
                .end();

        builder.part()
                .modelFile(existingModel(name + "_foliage_east_full"))
                .addModel()
                .condition(HedgeBlock.BASE, false)
                .condition(HedgeBlock.EAST, true)
                .end();


        // ----- SOUTH -----

        builder.part()
                .modelFile(existingModel(name + "_foliage_south_base"))
                .addModel()
                .condition(HedgeBlock.BASE, true)
                .condition(HedgeBlock.SOUTH, true)
                .end();

        builder.part()
                .modelFile(existingModel(name + "_foliage_south_full"))
                .addModel()
                .condition(HedgeBlock.BASE, false)
                .condition(HedgeBlock.SOUTH, true)
                .end();


        // ----- WEST -----

        builder.part()
                .modelFile(existingModel(name + "_foliage_west_base"))
                .addModel()
                .condition(HedgeBlock.BASE, true)
                .condition(HedgeBlock.WEST, true)
                .end();

        builder.part()
                .modelFile(existingModel(name + "_foliage_west_full"))
                .addModel()
                .condition(HedgeBlock.BASE, false)
                .condition(HedgeBlock.WEST, true)
                .end();


        // ----- WOOD FOUNDATION -----

        // The center wood piece always appears on the bottom hedge.
        builder.part()
                .modelFile(existingModel(name + "_wood_core"))
                .addModel()
                .condition(HedgeBlock.BASE, true)
                .end();

        // Wood extensions follow the same connections as the foliage.
        builder.part()
                .modelFile(existingModel(name + "_wood_north"))
                .addModel()
                .condition(HedgeBlock.BASE, true)
                .condition(HedgeBlock.NORTH, true)
                .end();

        builder.part()
                .modelFile(existingModel(name + "_wood_east"))
                .addModel()
                .condition(HedgeBlock.BASE, true)
                .condition(HedgeBlock.EAST, true)
                .end();

        builder.part()
                .modelFile(existingModel(name + "_wood_south"))
                .addModel()
                .condition(HedgeBlock.BASE, true)
                .condition(HedgeBlock.SOUTH, true)
                .end();

        builder.part()
                .modelFile(existingModel(name + "_wood_west"))
                .addModel()
                .condition(HedgeBlock.BASE, true)
                .condition(HedgeBlock.WEST, true)
                .end();
    }

    private BlockModelBuilder existingModel(String name) {
        return models.getBuilder(name);
    }
}