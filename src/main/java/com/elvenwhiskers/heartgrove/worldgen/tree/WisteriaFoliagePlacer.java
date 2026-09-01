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

public class WisteriaFoliagePlacer extends FoliagePlacer{

    public static final MapCodec<WisteriaFoliagePlacer> CODEC =
            RecordCodecBuilder.mapCodec(instance ->
                    foliagePlacerParts(instance)
                            .apply(instance, WisteriaFoliagePlacer::new)
            );

    public WisteriaFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.WISTERIA.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset
    ) {
        BlockPos center = attachment.pos(); //The spot where the leaves attach to the trunk
        BlockState transitionLeaves = ModBlocks.BLUE_WISTERIA_LEAVES.get().defaultBlockState();
        BlockState blossoms = ModBlocks.BLUE_WISTERIA_BLOSSOMS.get().defaultBlockState();

        for (int z = -1; z <= 1; z++) { //top layer - Y + 1 - all green
            for (int x = -1; x <= 1; x++) {

                BlockPos leafPos = center.offset(x, 1, z);
                tryPlaceLeaf(level, foliageSetter, random, config, leafPos);
            }
        }

        for (int z = -2; z <= 2; z++) { //second layer - Y 0 - Half green
            for (int x = -2; x <= 2; x++) {

                if (Math.abs(x) == 2 && Math.abs(z) == 2) {
                    continue;
                }

                BlockPos leafPos = center.offset(x, 0, z);
                foliageSetter.set(leafPos, transitionLeaves);
            }
        }

        for (int z = -3; z <= 3; z++) { //3rd layer - y-1 - Full color
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

        foliageSetter.set(center.offset(-2, -1, -2), blossoms);
        foliageSetter.set(center.offset(2, -1, -2), blossoms);
        foliageSetter.set(center.offset(-2, -1, 2), blossoms);
        foliageSetter.set(center.offset(2, -1, 2), blossoms);

        //4th layer - y-2 - full color
        for (int z = -1; z <= 1; z++) {
            for (int x = -3; x <= 3; x++) {

                if (x == 0 && z == 0) {
                    continue; //The log is here
                }

                BlockPos leafPos = center.offset(x, -2, z);
                foliageSetter.set(leafPos, blossoms);
            }
        }

        //Fourth layer - Y -2 - blossoms - skinny outer rows
        for (int z = -3; z <= 3; z++) {

            if (Math.abs(z) <= 1) {
                continue;
            }

            for (int x = -1; x <= 1; x++) {
                BlockPos leafPos = center.offset(x, -2, z);
                foliageSetter.set(leafPos, blossoms);
            }
        }

        //Fifth layer - Y -3 - blossoms - outer tips
        foliageSetter.set(center.offset(0, -3, -3), blossoms);
        foliageSetter.set(center.offset(-3, -3, 0), blossoms);
        foliageSetter.set(center.offset(3, -3, 0), blossoms);
        foliageSetter.set(center.offset(0, -3, 3), blossoms);

        //Fifth layer - Y -3 - blossoms - inner pieces
        foliageSetter.set(center.offset(-1, -3, -1), blossoms);
        foliageSetter.set(center.offset(1, -3, -1), blossoms);
        foliageSetter.set(center.offset(-1, -3, 1), blossoms);
        foliageSetter.set(center.offset(1, -3, 1), blossoms);

    }

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 1;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}
