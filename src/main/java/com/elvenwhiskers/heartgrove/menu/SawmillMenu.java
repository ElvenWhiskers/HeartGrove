package com.elvenwhiskers.heartgrove.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.Container;
import com.elvenwhiskers.heartgrove.block.ModBlocks;

public class SawmillMenu extends AbstractContainerMenu {
    private final Container inputContainer = new SimpleContainer(1);
    private final Container outputContainer = new SimpleContainer(1);


    public SawmillMenu(int containerId, Inventory playerInventory) {
        super(ModMenuTypes.SAWMILL_MENU.get(), containerId);

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

                // Temporary hardcoded test recipe:
                // 1 Wisteria Log = 4 Wisteria Planks
                if (!inputStack.is(ModBlocks.WISTERIA_LOG.get().asItem())) {
                    return ItemStack.EMPTY;
                }

                ItemStack plankResult = new ItemStack(ModBlocks.WISTERIA_PLANKS.get());

                int availableSpace = getPlayerInventorySpace(plankResult);

                // Each log creates exactly 4 planks.
                int craftsThatFit = availableSpace / 4;

                // We cannot craft more times than we have logs.
                int craftsToMake = Math.min(inputStack.getCount(), craftsThatFit);

                if (craftsToMake <= 0) {
                    return ItemStack.EMPTY;
                }

                int totalPlanks = craftsToMake * 4;

                ItemStack craftedStack =
                        new ItemStack(ModBlocks.WISTERIA_PLANKS.get(), totalPlanks);

                // Main inventory FIRST.
                this.moveItemStackTo(craftedStack, 2, 29, false);

                // Anything that didn't fit there may use the hotbar.
                if (!craftedStack.isEmpty()) {
                    this.moveItemStackTo(craftedStack, 29, 38, false);
                }

                // Only consume the logs for crafts we successfully calculated.
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
            this.outputContainer.setItem(
                    0,
                    new ItemStack(ModBlocks.WISTERIA_PLANKS.get(), 4)
            );
        } else {
            this.outputContainer.setItem(0, ItemStack.EMPTY);
        }

        this.broadcastChanges();
    }

    //So items don't get voided and return to me lol.
    @Override
    public void removed(Player player) {
        super.removed(player);

        this.clearContainer(player, this.inputContainer);
    }
}