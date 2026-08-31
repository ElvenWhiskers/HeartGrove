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

        //Third layer - y -1 - blossoms
        for (int x = -3; x <= 3; x++) { //part 1 3 log 3

            if (x == 0) {
                continue; //The log is here
            }

            BlockPos leafPos = center.offset(x, -1, 0);
            foliageSetter.set(leafPos, blossoms);
        }

        for (int z = -1; z <= 1; z += 2) { // part 2 5 log 5
            for (int x = -2; x <= 2; x++) {

                BlockPos leafPos = center.offset(x, -1, z);
                foliageSetter.set(leafPos, blossoms);
            }
        }

        //Third layer - blossoms - next outer rows
        for (int z = -2; z <= 2; z += 4) {
            for (int x = -1; x <= 1; x++) {

                BlockPos leafPos = center.offset(x, -1, z);
                foliageSetter.set(leafPos, blossoms);
            }
        }

        //Third layer - blossoms - outer tips
        BlockPos northTip = center.offset(0, -1, -3);
        foliageSetter.set(northTip, blossoms);

        BlockPos southTip = center.offset(0, -1, 3);
        foliageSetter.set(southTip, blossoms);

    }

    /*
    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset
    ) {
        BlockPos center = attachment.pos(); //The spot where the leaves attach to the trunk

        for (int z = -1; z <= 1; z++) { //first layer - small - 0
            for (int x = -1; x <= 1; x++) {
                if (Math.abs(x) == 1 && Math.abs(z) == 1) {
                    continue;
                }

                BlockPos leafPos = center.offset(x, 0, z);
                tryPlaceLeaf(level, foliageSetter, random, config, leafPos);
            }
        }

        for (int z = -3; z <= 3; z++) { //second layer - very large - -1
            for (int x = -3; x <= 3; x++) {

                if (Math.abs(x) == 3 && Math.abs(z) == 3) {
                    continue;
                }

                BlockPos leafPos = center.offset(x, -1, z);
                tryPlaceLeaf(level, foliageSetter, random, config, leafPos);
            }
        }

        for (int z = -2; z <= 2; z++) { //third layer - medium - -2
            for (int x = -3; x <= 3; x++) {

                if (Math.abs(x) == 3 && Math.abs(z) == 2) {
                    continue;
                }

                BlockPos leafPos = center.offset(x, -2, z);
                tryPlaceLeaf(level, foliageSetter, random, config, leafPos);
            }
        }

    }
     */

    @Override
    public int foliageHeight(RandomSource random, int height, TreeConfiguration config) {
        return 1;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int localX, int localY, int localZ, int range, boolean large) {
        return false;
    }
}
