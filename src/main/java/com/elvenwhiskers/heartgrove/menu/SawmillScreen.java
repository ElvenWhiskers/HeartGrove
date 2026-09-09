package com.elvenwhiskers.heartgrove.menu;

import com.elvenwhiskers.heartgrove.HeartGrove;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.client.Minecraft;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SawmillScreen extends AbstractContainerScreen<SawmillMenu> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    HeartGrove.MOD_ID,
                    "textures/gui/container/sawmill.png"
            );

    public SawmillScreen(SawmillMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        guiGraphics.blit(
                TEXTURE,
                this.leftPos,
                this.topPos,
                0,
                0,
                this.imageWidth,
                this.imageHeight
        );
        ItemStack plankIcon = new ItemStack(ModBlocks.WISTERIA_PLANKS.get());
        ItemStack stickIcon = new ItemStack(Items.STICK);

        guiGraphics.renderItem(
                plankIcon,
                this.leftPos + 53,
                this.topPos + 15
        );

        guiGraphics.renderItem(
                stickIcon,
                this.leftPos + 73,
                this.topPos + 15
        );

        int recipeX = this.leftPos + 52;
        int recipeY = this.topPos + 14;

// Hover highlight for recipe 0.
        if (mouseX >= recipeX && mouseX < recipeX + 18
                && mouseY >= recipeY && mouseY < recipeY + 18) {

            guiGraphics.fill(
                    recipeX,
                    recipeY,
                    recipeX + 18,
                    recipeY + 18,
                    0x40FFFFFF
            );
        }

// Hover highlight for recipe 1.
        if (mouseX >= recipeX + 20 && mouseX < recipeX + 38
                && mouseY >= recipeY && mouseY < recipeY + 18) {

            guiGraphics.fill(
                    recipeX + 20,
                    recipeY,
                    recipeX + 38,
                    recipeY + 18,
                    0x40FFFFFF
            );
        }

        // Selected highlight.
        if (this.menu.getSelectedRecipe() == 0) {

            guiGraphics.fill(
                    recipeX,
                    recipeY,
                    recipeX + 18,
                    recipeY + 18,
                    0x60FFFFFF
            );

        } else if (this.menu.getSelectedRecipe() == 1) {

            guiGraphics.fill(
                    recipeX + 20,
                    recipeY,
                    recipeX + 38,
                    recipeY + 18,
                    0x60FFFFFF
            );
        }

    }



    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {

        // Temporary recipe button positions.
        int recipeX = this.leftPos + 52;
        int recipeY = this.topPos + 14;

        // Recipe 0 button area.
        if (mouseX >= recipeX && mouseX < recipeX + 18
                && mouseY >= recipeY && mouseY < recipeY + 18) {

            Minecraft.getInstance().gameMode.handleInventoryButtonClick(
                    this.menu.containerId,
                    0
            );

            return true;
        }

        // Recipe 1 button area.
        if (mouseX >= recipeX + 20 && mouseX < recipeX + 38
                && mouseY >= recipeY && mouseY < recipeY + 18) {

            Minecraft.getInstance().gameMode.handleInventoryButtonClick(
                    this.menu.containerId,
                    1
            );

            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }
}