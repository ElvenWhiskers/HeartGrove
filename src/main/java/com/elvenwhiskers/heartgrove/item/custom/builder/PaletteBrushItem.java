
package com.elvenwhiskers.heartgrove.item.custom.builder;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;

public class PaletteBrushItem extends Item {

    public PaletteBrushItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag
    ) {
        tooltipComponents.add(
                Component.translatable("tooltip.heartgrove.palette_brush")
                        .withStyle(ChatFormatting.GRAY)
        );

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Player player = context.getPlayer();

        if (player == null) {
            return InteractionResult.PASS;
        }

        // Choose a random block from the hotbar.
        ItemStack selectedStack = getRandomPaletteBlock(player);

        if (selectedStack.isEmpty()) {
            return InteractionResult.FAIL;
        }

        // Only ordinary block items are eligible.
        if (!(selectedStack.getItem() instanceof BlockItem blockItem)) {
            return InteractionResult.FAIL;
        }

        // Recreate the original click using the selected block stack.
        BlockHitResult hitResult = new BlockHitResult(
                context.getClickLocation(),
                context.getClickedFace(),
                context.getClickedPos(),
                context.isInside()
        );

        UseOnContext placementContext = new UseOnContext(
                context.getLevel(),
                player,
                context.getHand(),
                selectedStack,
                hitResult
        );

        // Let Minecraft handle normal block placement.
        return blockItem.useOn(placementContext);
    }

    // Find a random block stack from the player's hotbar.
    private ItemStack getRandomPaletteBlock(Player player) {

        List<ItemStack> availableBlocks = new ArrayList<>();

        // Check all 9 hotbar slots.
        for (int slot = 0; slot < 9; slot++) {

            ItemStack stack = player.getInventory().getItem(slot);

            // Ignore empty slots and non-block items.
            if (stack.isEmpty() || !(stack.getItem() instanceof BlockItem)) {
                continue;
            }

            availableBlocks.add(stack);
        }

        // No blocks available.
        if (availableBlocks.isEmpty()) {
            return ItemStack.EMPTY;
        }

        // Every eligible hotbar slot gets an equal chance.
        int randomIndex = player.getRandom().nextInt(availableBlocks.size());

        return availableBlocks.get(randomIndex);
    }
}
