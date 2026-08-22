package com.elvenwhiskers.heartgrove.menu;

import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.block.Blocks;

public class ModCraftingMenu extends CraftingMenu {

    private final ContainerLevelAccess access;

    public ModCraftingMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(containerId, playerInventory, access);
        this.access = access;
    }

    @Override
    public boolean stillValid(Player player) {

        return stillValid(this.access, player, ModBlocks.LARKSPUR_CRAFTING_TABLE.get());
    }

}
