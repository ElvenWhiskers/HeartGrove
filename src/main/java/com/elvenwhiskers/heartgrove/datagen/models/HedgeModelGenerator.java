package com.elvenwhiskers.heartgrove.datagen.models;

import com.elvenwhiskers.heartgrove.block.custom.hedge.HedgeBlock;
import com.elvenwhiskers.heartgrove.block.custom.hedge.ModHedge;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;

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

        ResourceLocation sideLeavesTexture =
                blockStates.blockTexture(hedge.getSideLeaves().get());

        ResourceLocation woodTexture =
                blockStates.blockTexture(hedge.getWoodFamily().getLog().get());

        createHedgeBlockState(
                name,
                hedge.getHedge().get()
        );

        createIsolatedHedge(
                name,
                leavesTexture,
                sideLeavesTexture,
                woodTexture
        );

        createHedgeFoliageModels(
                name,
                leavesTexture,
                sideLeavesTexture,
                true
        );

        createHedgeFoliageModels(
                name,
                leavesTexture,
                sideLeavesTexture,
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
            ResourceLocation sideLeavesTexture,
            ResourceLocation woodTexture
    ) {
        BlockModelBuilder model = models
                .withExistingParent(name, "block/block")
                .texture("leaves", leavesTexture)
                .texture("side_leaves", sideLeavesTexture)
                .texture("wood", woodTexture)
                .texture("particle", leavesTexture)
                .renderType("cutout");

        // Main foliage body:
        // 12x14x12, centered in the block.
        // Leaves begin above the 2px wooden foundation.
        model.element()
                .from(2, 2, 2)
                .to(14, 16, 14)
                .face(Direction.UP).texture("#leaves").end()
                .face(Direction.DOWN).texture("#leaves").end()
                .face(Direction.NORTH).texture("#side_leaves").end()
                .face(Direction.SOUTH).texture("#side_leaves").end()
                .face(Direction.EAST).texture("#side_leaves").end()
                .face(Direction.WEST).texture("#side_leaves").end()
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
            ResourceLocation sideLeavesTexture,
            float fromX,
            float fromY,
            float fromZ,
            float toX,
            float toY,
            float toZ,
            Direction hiddenFace
    ) {
        BlockModelBuilder model = models
                .withExistingParent(name, "block/block")
                .texture("leaves", leavesTexture)
                .texture("side_leaves", sideLeavesTexture)
                .texture("particle", leavesTexture)
                .renderType("cutout");

        var element = model.element()
                .from(fromX, fromY, fromZ)
                .to(toX, toY, toZ);

        if (hiddenFace != Direction.UP) {
            var upFace = element.face(Direction.UP)
                    .texture("#leaves");

            if (toY == 16) {
                upFace.cullface(Direction.UP);
            }

            upFace.end();
        }

        if (hiddenFace != Direction.DOWN) {
            var downFace = element.face(Direction.DOWN)
                    .texture("#leaves");

            if (fromY == 0) {
                downFace.cullface(Direction.DOWN);
            }

            downFace.end();
        }

        if (hiddenFace != Direction.NORTH) {
            var northFace = element.face(Direction.NORTH)
                    .texture("#side_leaves");

            if (fromZ == 0) {
                northFace.cullface(Direction.NORTH);
            }

            northFace.end();
        }

        if (hiddenFace != Direction.SOUTH) {
            var southFace = element.face(Direction.SOUTH)
                    .texture("#side_leaves");

            if (toZ == 16) {
                southFace.cullface(Direction.SOUTH);
            }

            southFace.end();
        }

        if (hiddenFace != Direction.EAST) {
            var eastFace = element.face(Direction.EAST)
                    .texture("#side_leaves");

            if (toX == 16) {
                eastFace.cullface(Direction.EAST);
            }

            eastFace.end();
        }

        if (hiddenFace != Direction.WEST) {
            var westFace = element.face(Direction.WEST)
                    .texture("#side_leaves");

            if (fromX == 0) {
                westFace.cullface(Direction.WEST);
            }

            westFace.end();
        }

        element.end();

        return model;
    }

    public void createHedgeFoliageModels(
            String name,
            ResourceLocation leavesTexture,
            ResourceLocation sideLeavesTexture,
            boolean hasBase
    ) {
        float bottomY = hasBase ? 2 : 0;
        String suffix = hasBase ? "_base" : "_full";

        // Center of the hedge.
        // No face is permanently hidden because the core can be exposed
        // whenever there is no connection in that direction.
        createFoliagePiece(
                name + "_foliage_core" + suffix,
                leavesTexture,
                sideLeavesTexture,
                2, bottomY, 2,
                14, 16, 14,
                null
        );

        // Connection toward north.
        // Its south face is permanently buried against the core.
        createFoliagePiece(
                name + "_foliage_north" + suffix,
                leavesTexture,
                sideLeavesTexture,
                2, bottomY, 0,
                14, 16, 2,
                Direction.SOUTH
        );

        // Connection toward east.
        // Its west face is permanently buried against the core.
        createFoliagePiece(
                name + "_foliage_east" + suffix,
                leavesTexture,
                sideLeavesTexture,
                14, bottomY, 2,
                16, 16, 14,
                Direction.WEST
        );

        // Connection toward south.
        // Its north face is permanently buried against the core.
        createFoliagePiece(
                name + "_foliage_south" + suffix,
                leavesTexture,
                sideLeavesTexture,
                2, bottomY, 14,
                14, 16, 16,
                Direction.NORTH
        );

        // Connection toward west.
        // Its east face is permanently buried against the core.
        createFoliagePiece(
                name + "_foliage_west" + suffix,
                leavesTexture,
                sideLeavesTexture,
                0, bottomY, 2,
                2, 16, 14,
                Direction.EAST
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