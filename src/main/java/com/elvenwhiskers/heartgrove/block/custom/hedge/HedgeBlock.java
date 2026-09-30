package com.elvenwhiskers.heartgrove.block.custom.hedge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HedgeBlock extends Block {

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    public static final BooleanProperty BASE = BooleanProperty.create("base");

    private static final VoxelShape SHAPE = Block.box(
            2.0, 0.0, 2.0,
            14.0, 16.0, 14.0
    );

    public HedgeBlock(BlockBehaviour.Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(NORTH, false)
                        .setValue(EAST, false)
                        .setValue(SOUTH, false)
                        .setValue(WEST, false)
                        .setValue(BASE, true)
        );
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        LevelAccessor level = context.getLevel();

        return this.defaultBlockState()
                .setValue(NORTH, shouldConnectTo(level, pos.north(), Direction.NORTH))
                .setValue(EAST, shouldConnectTo(level, pos.east(), Direction.EAST))
                .setValue(SOUTH, shouldConnectTo(level, pos.south(), Direction.SOUTH))
                .setValue(WEST, shouldConnectTo(level, pos.west(), Direction.WEST))
                .setValue(BASE, !isHedge(level.getBlockState(pos.below())));
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            Direction direction,
            BlockState neighborState,
            LevelAccessor level,
            BlockPos pos,
            BlockPos neighborPos
    ) {
        if (direction == Direction.DOWN) {
            return state.setValue(BASE, !isHedge(neighborState));
        }

        if (direction.getAxis().isHorizontal()) {
            return state.setValue(
                    getConnectionProperty(direction),
                    shouldConnectTo(neighborState, level, neighborPos, direction)
            );
        }

        return state;
    }

    private static boolean shouldConnectTo(
            LevelAccessor level,
            BlockPos neighborPos,
            Direction direction
    ) {
        BlockState neighborState = level.getBlockState(neighborPos);

        return shouldConnectTo(
                neighborState,
                level,
                neighborPos,
                direction
        );
    }

    private static boolean shouldConnectTo(
            BlockState neighborState,
            BlockGetter level,
            BlockPos neighborPos,
            Direction direction
    ) {
        if (isHedge(neighborState)) {
            return true;
        }

        if (neighborState.is(BlockTags.FENCES)) {
            return true;
        }

        if (neighborState.is(BlockTags.WALLS)) {
            return true;
        }

        return neighborState.isFaceSturdy(
                level,
                neighborPos,
                direction.getOpposite()
        );
    }

    private static boolean isHedge(BlockState state) {
        return state.getBlock() instanceof HedgeBlock;
    }

    private static BooleanProperty getConnectionProperty(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;

            default -> throw new IllegalArgumentException(
                    "Cannot get hedge connection property for " + direction
            );
        };
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        builder.add(
                NORTH,
                EAST,
                SOUTH,
                WEST,
                BASE
        );
    }

    @Override
    protected boolean skipRendering(
            BlockState state,
            BlockState adjacentState,
            Direction direction
    ) {
        if (adjacentState.getBlock() instanceof HedgeBlock) {
            return true;
        }

        return super.skipRendering(state, adjacentState, direction);
    }

    @Override
    protected VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return SHAPE;
    }
}