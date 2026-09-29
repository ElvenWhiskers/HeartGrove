package com.elvenwhiskers.heartgrove.block.family.vanilla;

import com.elvenwhiskers.heartgrove.block.custom.sign.ModStandingSignBlock;
import com.elvenwhiskers.heartgrove.block.custom.sign.ModWallSignBlock;
import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamily;
import com.elvenwhiskers.heartgrove.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class ModVanillaWoodSetRegistrar {

    // Registered vanilla wood sets are automatically collected for DataGen and creative tabs.
    private static final List<ModVanillaWoodSet> VANILLA_WOOD_SETS = new ArrayList<>();

    public static List<ModVanillaWoodSet> getVanillaWoodSets() {
        return List.copyOf(VANILLA_WOOD_SETS);
    }

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

        DeferredItem<SignItem> signItem = ModItems.ITEMS.register(
                name + "_sign",
                () -> new SignItem(
                        new Item.Properties().stacksTo(16),
                        sign.get(),
                        wallSign.get()
                )
        );

        ModVanillaWoodSet vanillaWoodSet = new ModVanillaWoodSet(
                woodFamily,
                sign,
                wallSign,
                signItem
        );

        VANILLA_WOOD_SETS.add(vanillaWoodSet);

        return vanillaWoodSet;
    }
}