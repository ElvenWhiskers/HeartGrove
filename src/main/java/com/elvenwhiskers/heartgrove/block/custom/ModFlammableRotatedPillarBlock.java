package com.elvenwhiskers.heartgrove.block.custom;

import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;

import javax.annotation.Nullable;
import java.util.Map;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    private static class Strippables {
        private static final Map<Block, Block> MAP = Map.of(
                ModBlocks.LARKSPUR_LOG.get(), ModBlocks.STRIPPED_LARKSPUR_LOG.get(),
                ModBlocks.LARKSPUR_WOOD.get(), ModBlocks.STRIPPED_LARKSPUR_WOOD.get(),
                ModBlocks.WISTERIA_LOG.get(), ModBlocks.STRIPPED_WISTERIA_LOG.get(),
                ModBlocks.WISTERIA_WOOD.get(), ModBlocks.STRIPPED_WISTERIA_WOOD.get()
        );
    }

    public ModFlammableRotatedPillarBlock(Properties properties) {
        super(properties);
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
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context,
                                                     ItemAbility itemAbility, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            Block strippedBlock = Strippables.MAP.get(state.getBlock());

            if (strippedBlock != null) {
                return strippedBlock.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}
