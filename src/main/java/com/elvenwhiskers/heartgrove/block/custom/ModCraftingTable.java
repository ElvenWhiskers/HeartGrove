package com.elvenwhiskers.heartgrove.block.custom;

import com.elvenwhiskers.heartgrove.menu.ModCraftingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModCraftingTable extends CraftingTableBlock {
    private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");

    public ModCraftingTable(Properties properties) {
        super(properties);
    }

    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider((containerId, playerInventory, player) -> {
            return new ModCraftingMenu(
                    containerId,
                    playerInventory,
                    ContainerLevelAccess.create(level, pos)
            );
        }, CONTAINER_TITLE);
    }
}
