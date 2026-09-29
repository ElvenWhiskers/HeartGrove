package com.elvenwhiskers.heartgrove.block.family.tree;

import com.elvenwhiskers.heartgrove.block.custom.wood.ModFlammableLeaves;
import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamily;
import com.elvenwhiskers.heartgrove.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModNormalTreeRegistrar {

    // Registered tree families are automatically collected for DataGen and creative tabs.
    private static final List<ModNormalTreeFamily> TREE_FAMILIES = new ArrayList<>();

    public static List<ModNormalTreeFamily> getTreeFamilies() {
        return List.copyOf(TREE_FAMILIES);
    }

    public static ModNormalTreeFamily register(
            DeferredRegister.Blocks blocks,
            String name,
            ModWoodFamily woodFamily,
            TreeGrower treeGrower
    ) {
        DeferredBlock<Block> leaves = registerBlock(
                blocks,
                name + "_leaves",
                () -> new ModFlammableLeaves(
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
                )
        );

        DeferredBlock<Block> sapling = registerBlock(
                blocks,
                name + "_sapling",
                () -> new SaplingBlock(
                        treeGrower,
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
                )
        );

        ModNormalTreeFamily treeFamily = new ModNormalTreeFamily(
                name,
                woodFamily,
                leaves,
                sapling
        );

        TREE_FAMILIES.add(treeFamily);

        return treeFamily;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(
            DeferredRegister.Blocks blocks,
            String name,
            Supplier<T> block
    ) {
        DeferredBlock<T> registeredBlock = blocks.register(name, block);

        ModItems.ITEMS.register(
                name,
                () -> new BlockItem(registeredBlock.get(), new Item.Properties())
        );

        return registeredBlock;
    }
}