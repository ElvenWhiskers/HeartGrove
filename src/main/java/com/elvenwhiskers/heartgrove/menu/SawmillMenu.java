package com.elvenwhiskers.heartgrove.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.Container;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.inventory.DataSlot;


public class SawmillMenu extends AbstractContainerMenu {
    private final Container inputContainer = new SimpleContainer(1);
    private final Container outputContainer = new SimpleContainer(1);
    private final DataSlot selectedRecipe = DataSlot.standalone();


    public SawmillMenu(int containerId, Inventory playerInventory) {
        super(ModMenuTypes.SAWMILL_MENU.get(), containerId);
        this.addDataSlot(this.selectedRecipe);

        //only input slot
        this.addSlot(new Slot(inputContainer, 0, 20, 33) {
            @Override
            public void setChanged() {
                super.setChanged();
                updateOutput();
            }
        });

        //only output slot
        this.addSlot(new Slot(outputContainer, 0, 143, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                inputContainer.removeItem(0, 1);
                updateOutput();

                super.onTake(player, stack);
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack originalStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            originalStack = stackInSlot.copy();

            // Slot layout:
            // 0 = Sawmill input
            // 1 = Sawmill output
            // 2-28 = player inventory
            // 29-37 = player hotbar

            if (index == 0) {

                // Move from Sawmill input:
                // first try main inventory
                if (!this.moveItemStackTo(stackInSlot, 2, 29, false)) {

                    // if main inventory is full, try hotbar
                    if (!this.moveItemStackTo(stackInSlot, 29, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                }

            } else if (index == 1) {

                ItemStack inputStack = this.inputContainer.getItem(0);

                if (!inputStack.is(ModBlocks.WISTERIA_LOG.get().asItem())) {
                    return ItemStack.EMPTY;
                }

                ItemStack resultStack;
                int resultPerCraft;

                if (selectedRecipe.get() == 0) {

                    // Recipe 0:
                    // 1 Wisteria Log = 4 Wisteria Planks
                    resultStack = new ItemStack(ModBlocks.WISTERIA_PLANKS.get());
                    resultPerCraft = 4;

                } else if (selectedRecipe.get() == 1) {

                    // Recipe 1:
                    // 1 Wisteria Log = 8 Sticks
                    resultStack = new ItemStack(Items.STICK);
                    resultPerCraft = 8;

                } else {
                    return ItemStack.EMPTY;
                }

                int availableSpace = getPlayerInventorySpace(resultStack);

                int craftsThatFit = availableSpace / resultPerCraft;

                int craftsToMake = Math.min(
                        inputStack.getCount(),
                        craftsThatFit
                );

                if (craftsToMake <= 0) {
                    return ItemStack.EMPTY;
                }

                int totalItems = craftsToMake * resultPerCraft;

                ItemStack craftedStack =
                        new ItemStack(resultStack.getItem(), totalItems);

                // Main inventory first.
                this.moveItemStackTo(craftedStack, 2, 29, false);

                // Hotbar second.
                if (!craftedStack.isEmpty()) {
                    this.moveItemStackTo(craftedStack, 29, 38, false);
                }

                // One log is consumed per completed craft.
                this.inputContainer.removeItem(0, craftsToMake);

                updateOutput();

                return originalStack;

            } else {

                // Move from player inventory/hotbar into Sawmill input.
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
        }

        return originalStack;
    }

    //precaution
    private int getPlayerInventorySpace(ItemStack resultStack) {
        int availableSpace = 0;

        // Player inventory + hotbar are menu slots 2 through 37.
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

    @Override
    public boolean stillValid(Player player) {
        return true;
    }


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

    private void updateOutput() {
        ItemStack inputStack = this.inputContainer.getItem(0);

        if (inputStack.is(ModBlocks.WISTERIA_LOG.get().asItem())) {

            if (selectedRecipe.get() == 0) {

                // Recipe 0:
                // 1 Wisteria Log = 4 Wisteria Planks
                this.outputContainer.setItem(
                        0,
                        new ItemStack(ModBlocks.WISTERIA_PLANKS.get(), 4)
                );

            } else if (selectedRecipe.get() == 1) {

                // Recipe 1:
                // 1 Wisteria Log = 8 Sticks
                this.outputContainer.setItem(
                        0,
                        new ItemStack(Items.STICK, 8)
                );

            } else {

                // No valid recipe selected.
                this.outputContainer.setItem(0, ItemStack.EMPTY);
            }

        } else {
            this.outputContainer.setItem(0, ItemStack.EMPTY);
        }

        this.broadcastChanges();
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {

        if (id == 0 || id == 1) {
            this.selectedRecipe.set(id);
            updateOutput();

            return true;
        }

        return false;
    }

    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    //So items don't get voided and return to me lol.
    @Override
    public void removed(Player player) {
        super.removed(player);

        this.clearContainer(player, this.inputContainer);
    }
}