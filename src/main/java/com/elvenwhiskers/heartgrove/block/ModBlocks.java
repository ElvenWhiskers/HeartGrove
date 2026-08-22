package com.elvenwhiskers.heartgrove.block;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.custom.ModCraftingTable;
import com.elvenwhiskers.heartgrove.block.custom.ModFlammableLeaves;
import com.elvenwhiskers.heartgrove.block.custom.ModFlammablePlanks;
import com.elvenwhiskers.heartgrove.block.custom.ModFlammableRotatedPillarBlock;
import com.elvenwhiskers.heartgrove.item.ModItems;
import com.elvenwhiskers.heartgrove.worldgen.tree.ModTreeGrowers;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(HeartGrove.MOD_ID);


    public static final DeferredBlock<Block> AEGIS_BLOCK = registerBlock("aegis_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> AEGIS_ORE = registerBlock("aegis_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));


    public static final DeferredBlock<Block> LARKSPUR_LOG = registerBlock("larkspur_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));
    public static final DeferredBlock<Block> LARKSPUR_WOOD = registerBlock("larkspur_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_LARKSPUR_LOG = registerBlock("stripped_larkspur_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> STRIPPED_LARKSPUR_WOOD = registerBlock("stripped_larkspur_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));
    public static final DeferredBlock<Block> LARKSPUR_PLANKS = registerBlock("larkspur_planks",
            () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> LARKSPUR_LEAVES = registerBlock("larkspur_leaves",
            () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<Block> LARKSPUR_SAPLING = registerBlock("larkspur_sapling",
            () -> new SaplingBlock(ModTreeGrowers.LARKSPUR,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<StairBlock> LARKSPUR_STAIRS = registerBlock("larkspur_stairs",
            () -> new StairBlock(ModBlocks.LARKSPUR_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(2f)));
    public static final DeferredBlock<SlabBlock> LARKSPUR_SLAB = registerBlock("larkspur_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2f)));
    public static final DeferredBlock<PressurePlateBlock> LARKSPUR_PRESSURE_PLATE = registerBlock("larkspur_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(2f)));
    public static final DeferredBlock<ButtonBlock> LARKSPUR_BUTTON = registerBlock("larkspur_button",
            () -> new ButtonBlock(BlockSetType.OAK, 20, BlockBehaviour.Properties.of().strength(2f).noCollission()));
    public static final DeferredBlock<FenceBlock> LARKSPUR_FENCE = registerBlock("larkspur_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(2f)));
    public static final DeferredBlock<FenceGateBlock> LARKSPUR_FENCE_GATE = registerBlock("larkspur_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.of().strength(2f)));
    public static final DeferredBlock<WallBlock> LARKSPUR_WALL = registerBlock("larkspur_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(2f)));
    public static final DeferredBlock<DoorBlock> LARKSPUR_DOOR = registerBlock("larkspur_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(2f).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> LARKSPUR_TRAPDOOR = registerBlock("larkspur_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(2f).noOcclusion()));
    public static final DeferredBlock<CraftingTableBlock> LARKSPUR_CRAFTING_TABLE = registerBlock("larkspur_crafting_table",
            () -> new ModCraftingTable(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));



    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
