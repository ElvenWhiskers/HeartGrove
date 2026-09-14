package com.elvenwhiskers.heartgrove.block.family;

import com.elvenwhiskers.heartgrove.block.custom.ModFlammableLeaves;
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

public class ModNormalTreeRegistrar {

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

        return new ModNormalTreeFamily(
                name,
                woodFamily,
                leaves,
                sapling
        );
    }


    private static <T extends Block> DeferredBlock<T> registerBlock(
            DeferredRegister.Blocks blocks,
            String name,
            java.util.function.Supplier<T> block
    ) {
        DeferredBlock<T> registeredBlock = blocks.register(name, block);

        ModItems.ITEMS.register(
                name,
                () -> new BlockItem(
                        registeredBlock.get(),
                        new Item.Properties()
                )
        );

        return registeredBlock;
    }
}