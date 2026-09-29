package com.elvenwhiskers.heartgrove.block.custom.wood;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.common.ItemAbilities;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {

    @Nullable
    private final Supplier<? extends Block> strippedBlock;

    public ModFlammableRotatedPillarBlock(Properties properties) {
        this(properties, null);
    }

    public ModFlammableRotatedPillarBlock(
            Properties properties,
            @Nullable Supplier<? extends Block> strippedBlock
    ) {
        super(properties);
        this.strippedBlock = strippedBlock;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(
            BlockState state,
            UseOnContext context,
            ItemAbility itemAbility,
            boolean simulate
    ) {
        if (itemAbility == ItemAbilities.AXE_STRIP && strippedBlock != null) {
            return strippedBlock.get()
                    .defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS));
        }

        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}