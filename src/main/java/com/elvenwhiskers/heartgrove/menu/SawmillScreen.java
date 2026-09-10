package com.elvenwhiskers.heartgrove.menu;

import com.elvenwhiskers.heartgrove.HeartGrove;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import com.elvenwhiskers.heartgrove.menu.sawmill.SawmillRecipe;
import java.util.List;

public class SawmillScreen extends AbstractContainerScreen<SawmillMenu> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            HeartGrove.MOD_ID,
            "textures/gui/container/sawmill.png"
    );

    // Temporary recipe layout.
    private static final int RECIPE_X = 52;
    private static final int RECIPE_Y = 14;
    private static final int RECIPE_SIZE = 18;
    private static final int RECIPE_SPACING = 20;

    private static final int RECIPE_COLUMNS = 3;
    private static final int RECIPE_ROWS = 2;
    private static final int MAX_VISIBLE_RECIPES = RECIPE_COLUMNS * RECIPE_ROWS;


    public SawmillScreen(SawmillMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }


    // --------------------------------------------------
    // Screen rendering
    // --------------------------------------------------

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

        if (!this.menu.hasValidInput()) {
            return;
        }

        renderRecipeIcons(guiGraphics);
        renderRecipeHighlights(guiGraphics, mouseX, mouseY);
    }


    private void renderRecipeIcons(GuiGraphics guiGraphics) {
        List<SawmillRecipe> recipes = this.menu.getAvailableRecipes();

        for (int index = 0; index < recipes.size(); index++) {
            SawmillRecipe recipe = recipes.get(index);

            guiGraphics.renderItem(
                    new ItemStack(recipe.getOutput()),
                    getRecipeX(index) + 1,
                    getRecipeY(index) + 1
            );
        }
    }


    private void renderRecipeHighlights(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        List<SawmillRecipe> recipes = this.menu.getAvailableRecipes();
        int selectedRecipe = this.menu.getSelectedRecipe();

        for (int index = 0; index < recipes.size(); index++) {
            int recipeX = getRecipeX(index);
            int recipeY = getRecipeY(index);

            if (isMouseOverRecipe(mouseX, mouseY, index)) {
                guiGraphics.fill(
                        recipeX,
                        recipeY,
                        recipeX + RECIPE_SIZE,
                        recipeY + RECIPE_SIZE,
                        0x40FFFFFF
                );
            }

            if (selectedRecipe == index) {
                guiGraphics.fill(
                        recipeX,
                        recipeY,
                        recipeX + RECIPE_SIZE,
                        recipeY + RECIPE_SIZE,
                        0x60FFFFFF
                );
            }
        }
    }


    // --------------------------------------------------
    // Recipe interaction
    // --------------------------------------------------

    private int getRecipeX(int recipeIndex) {
        int column = recipeIndex % RECIPE_COLUMNS;

        return this.leftPos + RECIPE_X + column * RECIPE_SPACING;
    }


    private int getRecipeY(int recipeIndex) {
        int row = recipeIndex / RECIPE_COLUMNS;

        return this.topPos + RECIPE_Y + row * RECIPE_SPACING;
    }


    private boolean isMouseOverRecipe(double mouseX, double mouseY, int recipeIndex) {
        int recipeX = getRecipeX(recipeIndex);
        int recipeY = getRecipeY(recipeIndex);

        return mouseX >= recipeX
                && mouseX < recipeX + RECIPE_SIZE
                && mouseY >= recipeY
                && mouseY < recipeY + RECIPE_SIZE;
    }


    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!this.menu.hasValidInput()) {
            return super.mouseClicked(mouseX, mouseY, button);
        }

        List<SawmillRecipe> recipes = this.menu.getAvailableRecipes();

        for (int index = 0; index < recipes.size(); index++) {
            if (isMouseOverRecipe(mouseX, mouseY, index)) {
                selectRecipe(index);
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }


    private void selectRecipe(int recipeId) {
        Minecraft.getInstance().gameMode.handleInventoryButtonClick(
                this.menu.containerId,
                recipeId
        );
    }
}