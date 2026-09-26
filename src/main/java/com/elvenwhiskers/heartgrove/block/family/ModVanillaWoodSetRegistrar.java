package com.elvenwhiskers.heartgrove.block.family;

import com.elvenwhiskers.heartgrove.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Blocks;
import com.elvenwhiskers.heartgrove.block.custom.ModStandingSignBlock;
import com.elvenwhiskers.heartgrove.block.custom.ModWallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModVanillaWoodSetRegistrar {

    public static ModVanillaWoodSet register(
            DeferredRegister.Blocks blocks,
            String name,
            ModWoodFamily woodFamily,
            WoodType woodType
    ) {

        DeferredBlock<ModStandingSignBlock> sign = blocks.register(
                name + "_sign",
                () -> new ModStandingSignBlock(
                        woodType,
                        BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SIGN)
                )
        );

        DeferredBlock<ModWallSignBlock> wallSign = blocks.register(
                name + "_wall_sign",
                () -> new ModWallSignBlock(
                        woodType,
                        BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_WALL_SIGN)
                )
        );

        var signItem = ModItems.ITEMS.register(
                name + "_sign",
                () -> new SignItem(
                        new Item.Properties().stacksTo(16),
                        sign.get(),
                        wallSign.get()
                )
        );

        return new ModVanillaWoodSet(
                woodFamily,
                sign,
                wallSign,
                signItem
        );
    }
}