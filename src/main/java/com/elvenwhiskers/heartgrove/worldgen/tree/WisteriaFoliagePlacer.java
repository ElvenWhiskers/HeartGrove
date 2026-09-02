package com.elvenwhiskers.heartgrove.worldgen.tree;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import com.elvenwhiskers.heartgrove.block.custom.WisteriaVineBlock;
import java.util.ArrayList;
import java.util.List;

public class WisteriaFoliagePlacer extends FoliagePlacer{

    public static final MapCodec<WisteriaFoliagePlacer> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    foliagePlacerParts(instance)
                            .apply(instance, WisteriaFoliagePlacer::new)
            );

    private final WisteriaColor wisteriaColor;


    public WisteriaFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
        this.wisteriaColor = WisteriaColor.BLUE;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.WISTERIA.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset
    ) {
        BlockPos center = attachment.pos(); //The spot where the leaves attach to the trunk
        BlockState transitionLeaves = wisteriaColor.transitionLeaves().get().defaultBlockState();
        BlockState blossoms = wisteriaColor.blossoms().get().defaultBlockState();
        List<BlockPos> lowerBlossomPositions = new ArrayList<>();


        //top layer - Y + 1 - all green
        placeTopGreenLayer(level, foliageSetter, random, config, center);

        //second layer - Y 0 - Half green
        placeTransitionLayer(center, foliageSetter, transitionLeaves);

        //3rd layer - y-1 - Full color
        placeUpperBlossomLayer(center, foliageSetter, blossoms, lowerBlossomPositions);

        //4th layer - y-2 - full color
        placeLowerBlossomLayer(center, foliageSetter, blossoms, lowerBlossomPositions);

        //Fifth layer - Y -3 - blossoms - outer tips
        placeBottomBlossomLayer(center, foliageSetter, blossoms, lowerBlossomPositions);

        //vine layer
        placeHangingVines(foliageSetter, random, lowerBlossomPositions);
    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 1;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }

    private void placeHangingVines(FoliageSetter foliageSetter, RandomSource random, List<BlockPos> lowerBlossomPositions) {
        for (BlockPos blossomPos : lowerBlossomPositions) {

            BlockPos vinePos = blossomPos.below();

            if (lowerBlossomPositions.contains(vinePos)) {
                continue;
            }

            if (random.nextFloat() >= 0.5F) {
                continue;
            }

            BlockState vineState =
                    wisteriaColor.vines().get()
                            .defaultBlockState()
                            .setValue(WisteriaVineBlock.BOTTOM, true)
                            .setValue(
                                    WisteriaVineBlock.GROWTH_REMAINING,
                                    random.nextInt(7)
                            );

            foliageSetter.set(vinePos, vineState);
        }
    }

    private void placeBottomBlossomLayer(BlockPos center, FoliageSetter foliageSetter, BlockState blossoms, List<BlockPos> lowerBlossomPositions) {
        BlockPos outerNorth = center.offset(0, -3, -3);
        BlockPos outerWest = center.offset(-3, -3, 0);
        BlockPos outerEast = center.offset(3, -3, 0);
        BlockPos outerSouth = center.offset(0, -3, 3);

        foliageSetter.set(outerNorth, blossoms);
        foliageSetter.set(outerWest, blossoms);
        foliageSetter.set(outerEast, blossoms);
        foliageSetter.set(outerSouth, blossoms);

        lowerBlossomPositions.add(outerNorth);
        lowerBlossomPositions.add(outerWest);
        lowerBlossomPositions.add(outerEast);
        lowerBlossomPositions.add(outerSouth);

        BlockPos innerNorthWest = center.offset(-1, -3, -1);
        BlockPos innerNorthEast = center.offset(1, -3, -1);
        BlockPos innerSouthWest = center.offset(-1, -3, 1);
        BlockPos innerSouthEast = center.offset(1, -3, 1);

        foliageSetter.set(innerNorthWest, blossoms);
        foliageSetter.set(innerNorthEast, blossoms);
        foliageSetter.set(innerSouthWest, blossoms);
        foliageSetter.set(innerSouthEast, blossoms);

        lowerBlossomPositions.add(innerNorthWest);
        lowerBlossomPositions.add(innerNorthEast);
        lowerBlossomPositions.add(innerSouthWest);
        lowerBlossomPositions.add(innerSouthEast);
    }

    private void placeLowerBlossomLayer(BlockPos center, FoliageSetter foliageSetter, BlockState blossoms, List<BlockPos> lowerBlossomPositions) {
        // Three wide middle rows.
        for (int z = -1; z <= 1; z++) {
            for (int x = -3; x <= 3; x++) {

                // Leave room for the trunk in the center.
                if (x == 0 && z == 0) {
                    continue;
                }

                BlockPos leafPos = center.offset(x, -2, z);

                foliageSetter.set(leafPos, blossoms);
                lowerBlossomPositions.add(leafPos);
            }
        }

        // Narrow outer rows.
        for (int z = -3; z <= 3; z++) {

            // The middle rows were already handled above.
            if (Math.abs(z) <= 1) {
                continue;
            }

            for (int x = -1; x <= 1; x++) {
                BlockPos leafPos = center.offset(x, -2, z);

                foliageSetter.set(leafPos, blossoms);
                lowerBlossomPositions.add(leafPos);
            }
        }
    }

    private void placeUpperBlossomLayer(BlockPos center, FoliageSetter foliageSetter, BlockState blossoms, List<BlockPos> lowerBlossomPositions) {
        for (int z = -3; z <= 3; z++) {
            int distanceFromCenter = Math.abs(z);
            int xReach = 3 - distanceFromCenter;

            for (int x = -xReach; x <= xReach; x++) {
                if (x == 0 && z == 0) {
                    continue;
                }

                BlockPos leafPos = center.offset(x, -1, z);
                foliageSetter.set(leafPos, blossoms);
            }
        }

        BlockPos layer3NorthWest = center.offset(-2, -1, -2);
        BlockPos layer3NorthEast = center.offset(2, -1, -2);
        BlockPos layer3SouthWest = center.offset(-2, -1, 2);
        BlockPos layer3SouthEast = center.offset(2, -1, 2);

        foliageSetter.set(layer3NorthWest, blossoms);
        foliageSetter.set(layer3NorthEast, blossoms);
        foliageSetter.set(layer3SouthWest, blossoms);
        foliageSetter.set(layer3SouthEast, blossoms);

        lowerBlossomPositions.add(layer3NorthWest);
        lowerBlossomPositions.add(layer3NorthEast);
        lowerBlossomPositions.add(layer3SouthWest);
        lowerBlossomPositions.add(layer3SouthEast);
    }

    private void placeTransitionLayer(BlockPos center, FoliageSetter foliageSetter, BlockState transitionLeaves) {
        for (int z = -2; z <= 2; z++) {
            for (int x = -2; x <= 2; x++) {

                // Skip the four outer corners.
                if (Math.abs(x) == 2 && Math.abs(z) == 2) {
                    continue;
                }

                BlockPos leafPos = center.offset(x, 0, z);
                foliageSetter.set(leafPos, transitionLeaves);
            }
        }
    }

    private void placeTopGreenLayer(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, BlockPos center) {
        for (int z = -1; z <= 1; z++) {
            for (int x = -1; x <= 1; x++) {

                BlockPos leafPos = center.offset(x, 1, z);
                tryPlaceLeaf(level, foliageSetter, random, config, leafPos);
            }
        }
    }



}
