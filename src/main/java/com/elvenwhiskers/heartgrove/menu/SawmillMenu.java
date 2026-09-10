package com.elvenwhiskers.heartgrove.menu;

import com.elvenwhiskers.heartgrove.menu.sawmill.ModSawmillFamilies;
import com.elvenwhiskers.heartgrove.menu.sawmill.SawmillMaterialForm;
import com.elvenwhiskers.heartgrove.menu.sawmill.SawmillRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

import java.util.List;


public class SawmillMenu extends AbstractContainerMenu {

    private final Container inputContainer = new SimpleContainer(1);
    private final Container outputContainer = new SimpleContainer(1);

    private final DataSlot selectedRecipe = DataSlot.standalone();
    private final DataSlot hasValidInput = DataSlot.standalone();


    public SawmillMenu(int containerId, Inventory playerInventory) {
        super(ModMenuTypes.SAWMILL_MENU.get(), containerId);

        this.addDataSlot(this.selectedRecipe);
        this.addDataSlot(this.hasValidInput);

        this.addSlot(new Slot(inputContainer, 0, 20, 33) {
            @Override
            public void setChanged() {
                super.setChanged();
                updateOutput();
            }
        });

        this.addSlot(new Slot(outputContainer, 0, 143, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                craftOneBundle();
                super.onTake(player, stack);
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }


    // --------------------------------------------------
    // Sawmill crafting
    // --------------------------------------------------

    private void updateOutput() {
        ItemStack inputStack = this.inputContainer.getItem(0);

        SawmillMaterialForm inputForm =
                ModSawmillFamilies.SAWMILL_CATALOG.getInputForm(inputStack.getItem());

        this.hasValidInput.set(inputForm != null ? 1 : 0);

        if (inputForm == null) {
            clearOutput();
            return;
        }

        SawmillRecipe recipe = getSelectedSawmillRecipe();

        if (recipe == null || !hasEnoughInputForRecipe(inputStack, inputForm, recipe)) {
            clearOutput();
            return;
        }

        this.outputContainer.setItem(
                0,
                new ItemStack(
                        recipe.getOutput(),
                        getOutputsPerBundle(inputForm, recipe)
                )
        );

        this.broadcastChanges();
    }


    private void craftOneBundle() {
        ItemStack inputStack = this.inputContainer.getItem(0);

        SawmillMaterialForm inputForm =
                ModSawmillFamilies.SAWMILL_CATALOG.getInputForm(inputStack.getItem());

        SawmillRecipe recipe = getSelectedSawmillRecipe();

        if (inputForm == null
                || recipe == null
                || !hasEnoughInputForRecipe(inputStack, inputForm, recipe)) {
            updateOutput();
            return;
        }

        int inputsPerBundle = getInputsPerBundle(inputForm, recipe);

        this.inputContainer.removeItem(0, inputsPerBundle);
        updateOutput();
    }


    private void clearOutput() {
        this.outputContainer.setItem(0, ItemStack.EMPTY);
        this.broadcastChanges();
    }


    // --------------------------------------------------
    // Sawmill material math
    // --------------------------------------------------

    private int getInputsPerBundle(SawmillMaterialForm inputForm, SawmillRecipe recipe) {
        int inputValue = inputForm.getPlankValue();
        int recipeCost = recipe.getGroup().getPlankCost();

        if (inputValue >= recipeCost) {
            return 1;
        }

        return recipeCost / inputValue;
    }


    private int getOutputsPerBundle(SawmillMaterialForm inputForm, SawmillRecipe recipe) {
        int inputValue = inputForm.getPlankValue();
        int recipeCost = recipe.getGroup().getPlankCost();
        int recipeOutput = recipe.getGroup().getOutputCount();

        if (inputValue >= recipeCost) {
            return (inputValue / recipeCost) * recipeOutput;
        }

        return recipeOutput;
    }


    private boolean hasEnoughInputForRecipe(
            ItemStack inputStack,
            SawmillMaterialForm inputForm,
            SawmillRecipe recipe
    ) {
        return inputStack.getCount() >= getInputsPerBundle(inputForm, recipe);
    }


    // --------------------------------------------------
    // Recipe selection
    // --------------------------------------------------

    public List<SawmillRecipe> getAvailableRecipes() {
        ItemStack inputStack = this.inputContainer.getItem(0);

        if (inputStack.isEmpty()) {
            return List.of();
        }

        return ModSawmillFamilies.SAWMILL_CATALOG.getAvailableRecipes(
                inputStack.getItem()
        );
    }


    private SawmillRecipe getSelectedSawmillRecipe() {
        List<SawmillRecipe> availableRecipes = getAvailableRecipes();
        int selectedIndex = this.selectedRecipe.get();

        if (selectedIndex < 0 || selectedIndex >= availableRecipes.size()) {
            return null;
        }

        return availableRecipes.get(selectedIndex);
    }


    @Override
    public boolean clickMenuButton(Player player, int id) {
        List<SawmillRecipe> recipes = getAvailableRecipes();

        if (id >= 0 && id < recipes.size()) {
            this.selectedRecipe.set(id);
            updateOutput();

            return true;
        }

        return false;
    }


    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }


    public boolean hasValidInput() {
        return this.hasValidInput.get() == 1;
    }


    // --------------------------------------------------
    // Shift-click crafting
    // --------------------------------------------------

    private ItemStack quickCraftOutput(ItemStack originalStack) {
        ItemStack inputStack = this.inputContainer.getItem(0);

        SawmillMaterialForm inputForm =
                ModSawmillFamilies.SAWMILL_CATALOG.getInputForm(inputStack.getItem());

        SawmillRecipe recipe = getSelectedSawmillRecipe();

        if (inputForm == null || recipe == null) {
            return ItemStack.EMPTY;
        }

        int inputsPerBundle = getInputsPerBundle(inputForm, recipe);
        int outputsPerBundle = getOutputsPerBundle(inputForm, recipe);

        int availableBundles = inputStack.getCount() / inputsPerBundle;

        ItemStack resultStack = new ItemStack(recipe.getOutput());
        int availableSpace = getPlayerInventorySpace(resultStack);
        int bundlesThatFit = availableSpace / outputsPerBundle;

        int bundlesToMake = Math.min(
                availableBundles,
                bundlesThatFit
        );

        if (bundlesToMake <= 0) {
            return ItemStack.EMPTY;
        }

        int totalOutput = bundlesToMake * outputsPerBundle;
        int totalInput = bundlesToMake * inputsPerBundle;

        ItemStack craftedStack = new ItemStack(
                recipe.getOutput(),
                totalOutput
        );

        this.moveItemStackTo(craftedStack, 2, 29, false);

        if (!craftedStack.isEmpty()) {
            this.moveItemStackTo(craftedStack, 29, 38, false);
        }

        this.inputContainer.removeItem(0, totalInput);
        updateOutput();

        return originalStack;
    }


    private int getPlayerInventorySpace(ItemStack resultStack) {
        int availableSpace = 0;

        for (int index = 2; index < 38; index++) {
            Slot slot = this.slots.get(index);
            ItemStack stackInSlot = slot.getItem();

            if (stackInSlot.isEmpty()) {
                availableSpace += slot.getMaxStackSize(resultStack);

            } else if (ItemStack.isSameItemSameComponents(stackInSlot, resultStack)) {
                availableSpace += Math.max(
                        0,
                        slot.getMaxStackSize(resultStack) - stackInSlot.getCount()
                );
            }
        }

        return availableSpace;
    }


    // --------------------------------------------------
    // Player inventory layout
    // --------------------------------------------------

    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(
                        playerInventory,
                        column + row * 9 + 9,
                        8 + column * 18,
                        84 + row * 18
                ));
            }
        }
    }


    private void addPlayerHotbar(Inventory playerInventory) {
        for (int column = 0; column < 9; column++) {
            this.addSlot(new Slot(
                    playerInventory,
                    column,
                    8 + column * 18,
                    142
            ));
        }
    }


    // --------------------------------------------------
    // Vanilla menu behavior
    // --------------------------------------------------

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack originalStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stackInSlot = slot.getItem();
        originalStack = stackInSlot.copy();

        // Menu slot layout:
        // 0 = Sawmill input
        // 1 = Sawmill output
        // 2-28 = player inventory
        // 29-37 = hotbar

        if (index == 0) {
            if (!this.moveItemStackTo(stackInSlot, 2, 29, false)) {
                if (!this.moveItemStackTo(stackInSlot, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            }

        } else if (index == 1) {
            return quickCraftOutput(originalStack);

        } else {
            if (!this.moveItemStackTo(stackInSlot, 0, 1, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stackInSlot.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        if (stackInSlot.getCount() == originalStack.getCount()) {
            return ItemStack.EMPTY;
        }

        slot.onTake(player, stackInSlot);

        return originalStack;
    }


    @Override
    public boolean stillValid(Player player) {
        return true;
    }


    // --------------------------------------------------
    // Menu cleanup
    // --------------------------------------------------

    @Override
    public void removed(Player player) {
        super.removed(player);

        // Return unused input when the menu closes.
        // Output is only a preview and should disappear if unclaimed.
        this.clearContainer(player, this.inputContainer);
    }
}