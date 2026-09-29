package com.elvenwhiskers.heartgrove.block.family.wood;

import com.elvenwhiskers.heartgrove.block.custom.wood.ModFlammablePlanks;
import com.elvenwhiskers.heartgrove.block.custom.wood.ModFlammableRotatedPillarBlock;
import com.elvenwhiskers.heartgrove.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModWoodFamilyRegistrar {

    // Registered wood families are automatically collected for DataGen and creative tabs.
    private static final List<ModWoodFamily> WOOD_FAMILIES = new ArrayList<>();

    public static List<ModWoodFamily> getWoodFamilies() {
        return List.copyOf(WOOD_FAMILIES);
    }

    public static ModWoodFamily register(DeferredRegister.Blocks blocks, String name) {
        DeferredBlock<Block> strippedLog = registerBlock(
                blocks,
                "stripped_" + name + "_log",
                () -> new ModFlammableRotatedPillarBlock(
                        BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)
                )
        );

        DeferredBlock<Block> strippedWood = registerBlock(
                blocks,
                "stripped_" + name + "_wood",
                () -> new ModFlammableRotatedPillarBlock(
                        BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)
                )
        );

        DeferredBlock<Block> log = registerBlock(
                blocks,
                name + "_log",
                () -> new ModFlammableRotatedPillarBlock(
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG),
                        strippedLog::get
                )
        );

        DeferredBlock<Block> wood = registerBlock(
                blocks,
                name + "_wood",
                () -> new ModFlammableRotatedPillarBlock(
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD),
                        strippedWood::get
                )
        );

        DeferredBlock<Block> planks = registerBlock(
                blocks,
                name + "_planks",
                () -> new ModFlammablePlanks(
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                )
        );

        DeferredBlock<StairBlock> stairs = registerBlock(
                blocks,
                name + "_stairs",
                () -> new StairBlock(
                        planks.get().defaultBlockState(),
                        BlockBehaviour.Properties.of().strength(2f)
                )
        );

        DeferredBlock<SlabBlock> slab = registerBlock(
                blocks,
                name + "_slab",
                () -> new SlabBlock(
                        BlockBehaviour.Properties.of().strength(2f)
                )
        );

        DeferredBlock<PressurePlateBlock> pressurePlate = registerBlock(
                blocks,
                name + "_pressure_plate",
                () -> new PressurePlateBlock(
                        BlockSetType.OAK,
                        BlockBehaviour.Properties.of().strength(2f)
                )
        );

        DeferredBlock<ButtonBlock> button = registerBlock(
                blocks,
                name + "_button",
                () -> new ButtonBlock(
                        BlockSetType.OAK,
                        20,
                        BlockBehaviour.Properties.of().strength(2f).noCollission()
                )
        );

        DeferredBlock<FenceBlock> fence = registerBlock(
                blocks,
                name + "_fence",
                () -> new FenceBlock(
                        BlockBehaviour.Properties.of().strength(2f)
                )
        );

        DeferredBlock<FenceGateBlock> fenceGate = registerBlock(
                blocks,
                name + "_fence_gate",
                () -> new FenceGateBlock(
                        WoodType.OAK,
                        BlockBehaviour.Properties.of().strength(2f)
                )
        );

        DeferredBlock<WallBlock> wall = registerBlock(
                blocks,
                name + "_wall",
                () -> new WallBlock(
                        BlockBehaviour.Properties.of().strength(2f)
                )
        );

        DeferredBlock<DoorBlock> door = registerBlock(
                blocks,
                name + "_door",
                () -> new DoorBlock(
                        BlockSetType.OAK,
                        BlockBehaviour.Properties.of().strength(2f).noOcclusion()
                )
        );

        DeferredBlock<TrapDoorBlock> trapdoor = registerBlock(
                blocks,
                name + "_trapdoor",
                () -> new TrapDoorBlock(
                        BlockSetType.OAK,
                        BlockBehaviour.Properties.of().strength(2f).noOcclusion()
                )
        );

        ModWoodFamily woodFamily = new ModWoodFamily(
                name,
                log,
                wood,
                strippedLog,
                strippedWood,
                planks,
                stairs,
                slab,
                pressurePlate,
                button,
                fence,
                fenceGate,
                wall,
                door,
                trapdoor
        );

        WOOD_FAMILIES.add(woodFamily);

        return woodFamily;
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