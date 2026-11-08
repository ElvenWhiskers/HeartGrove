
package com.elvenwhiskers.heartgrove.block.custom.glimmerite;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;

public class GlimmeriteClusterBlock extends Block {

    public static final IntegerProperty STAGE =
            IntegerProperty.create("stage", 1, 3);

    public static final DirectionProperty FACING =
            BlockStateProperties.FACING;


    public GlimmeriteClusterBlock(BlockBehaviour.Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(STAGE, 1)
                        .setValue(FACING, Direction.UP)
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        builder.add(STAGE, FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getClickedFace());
    }


    @Override
    protected VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            net.minecraft.core.BlockPos pos,
            CollisionContext context
    ) {
        int stage = state.getValue(STAGE);
        Direction facing = state.getValue(FACING);

        int width = switch (stage) {
            case 1 -> 6;
            case 2 -> 8;
            default -> 10;
        };

        int height = switch (stage) {
            case 1 -> 3;
            case 2 -> 6;
            default -> 9;
        };

        double min = (16.0 - width) / 2.0;
        double max = (16.0 + width) / 2.0;

        return switch (facing) {
            case UP -> Block.box(min, 0, min, max, height, max);
            case DOWN -> Block.box(min, 16 - height, min, max, 16, max);

            case NORTH -> Block.box(min, min, 0, max, max, height);
            case SOUTH -> Block.box(min, min, 16 - height, max, max, 16);

            case WEST -> Block.box(0, min, min, height, max, max);
            case EAST -> Block.box(16 - height, min, min, 16, max, max);
        };
    }


    @Override
    protected VoxelShape getCollisionShape(
            BlockState state,
            BlockGetter level,
            net.minecraft.core.BlockPos pos,
            CollisionContext context
    ) {
        // Players and mobs can walk through the crystals.
        return Shapes.empty();
    }
}
