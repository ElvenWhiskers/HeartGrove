package com.elvenwhiskers.heartgrove.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class WisteriaVineBlock extends Block {

    public static final MapCodec<WisteriaVineBlock> CODEC =
            simpleCodec(WisteriaVineBlock::new);

    public static final BooleanProperty BOTTOM = BooleanProperty.create("bottom");
    public static final IntegerProperty GROWTH_REMAINING = IntegerProperty.create("growth_remaining", 0, 6);

    public WisteriaVineBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.defaultBlockState()
                        .setValue(BOTTOM, true)
                        .setValue(GROWTH_REMAINING, 0)
        );
    }

    @Override
    public MapCodec<WisteriaVineBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        super.createBlockStateDefinition(builder);
        builder.add(BOTTOM, GROWTH_REMAINING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        BlockPos belowPos = context.getClickedPos().below();
        BlockState belowState = context.getLevel().getBlockState(belowPos);

        boolean isBottom = !belowState.is(this);

        return this.defaultBlockState()
                .setValue(BOTTOM, isBottom);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos
    ) {
        if (direction == Direction.UP && !state.canSurvive(level, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        }

        if (direction == Direction.DOWN) {
            boolean isBottom = !neighborState.is(this);

            return state.setValue(BOTTOM, isBottom);
        }

        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState aboveState = level.getBlockState(pos.above());

        return !aboveState.isAir();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!state.getValue(BOTTOM)) {
            return;
        }

        if (state.getValue(GROWTH_REMAINING) <= 0) {
            return;
        }

        BlockPos belowPos = pos.below();

        if (!level.getBlockState(belowPos).isAir()) {
            return;
        }

        int growthRemaining = state.getValue(GROWTH_REMAINING);

        BlockState newTipState = this.defaultBlockState()
                .setValue(BOTTOM, true)
                .setValue(GROWTH_REMAINING, growthRemaining - 1);

        level.setBlock(belowPos, newTipState, Block.UPDATE_ALL);
    }
}
