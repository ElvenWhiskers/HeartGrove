package com.elvenwhiskers.heartgrove.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class DirectionalLeavesBlock extends LeavesBlock {

    public static final MapCodec<DirectionalLeavesBlock> CODEC =
            simpleCodec(DirectionalLeavesBlock::new);

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public DirectionalLeavesBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.defaultBlockState()
                        .setValue(FACING, Direction.UP)
        );
    }

    @Override
    public MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);

        return state.setValue(FACING, context.getClickedFace());
    }
}