package com.elvenwhiskers.heartgrove.block;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.custom.*;
import com.elvenwhiskers.heartgrove.item.ModItems;
import com.elvenwhiskers.heartgrove.worldgen.tree.ModTreeGrowers;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamily;
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamilyRegistrar;
import com.elvenwhiskers.heartgrove.block.family.ModNormalTreeFamily;
import com.elvenwhiskers.heartgrove.block.family.ModNormalTreeRegistrar;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(HeartGrove.MOD_ID);

    public static final ModWoodFamily WISTERIA =
            ModWoodFamilyRegistrar.register(BLOCKS, "wisteria");

    public static final ModWoodFamily LARKSPUR =
            ModWoodFamilyRegistrar.register(BLOCKS, "larkspur");

    public static final ModNormalTreeFamily LARKSPUR_TREE =
            ModNormalTreeRegistrar.register(BLOCKS, "larkspur", LARKSPUR, ModTreeGrowers.LARKSPUR);



    public static final DeferredBlock<Block> AEGIS_BLOCK = registerBlock("aegis_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> AEGIS_ORE = registerBlock("aegis_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));


    // Temporary aliases while the rest of HeartGrove migrates to ModWoodFamily.
    public static final DeferredBlock<Block> WISTERIA_LOG = WISTERIA.getLog();
    public static final DeferredBlock<Block> WISTERIA_WOOD = WISTERIA.getWood();
    public static final DeferredBlock<Block> STRIPPED_WISTERIA_LOG = WISTERIA.getStrippedLog();
    public static final DeferredBlock<Block> STRIPPED_WISTERIA_WOOD = WISTERIA.getStrippedWood();
    public static final DeferredBlock<Block> WISTERIA_PLANKS = WISTERIA.getPlanks();
    public static final DeferredBlock<StairBlock> WISTERIA_STAIRS = WISTERIA.getStairs();
    public static final DeferredBlock<SlabBlock> WISTERIA_SLAB = WISTERIA.getSlab();
    public static final DeferredBlock<PressurePlateBlock> WISTERIA_PRESSURE_PLATE = WISTERIA.getPressurePlate();
    public static final DeferredBlock<ButtonBlock> WISTERIA_BUTTON = WISTERIA.getButton();
    public static final DeferredBlock<FenceBlock> WISTERIA_FENCE = WISTERIA.getFence();
    public static final DeferredBlock<FenceGateBlock> WISTERIA_FENCE_GATE = WISTERIA.getFenceGate();
    public static final DeferredBlock<WallBlock> WISTERIA_WALL = WISTERIA.getWall();
    public static final DeferredBlock<DoorBlock> WISTERIA_DOOR = WISTERIA.getDoor();
    public static final DeferredBlock<TrapDoorBlock> WISTERIA_TRAPDOOR = WISTERIA.getTrapdoor();

    public static final DeferredBlock<Block> LARKSPUR_LOG = LARKSPUR.getLog();
    public static final DeferredBlock<Block> LARKSPUR_WOOD = LARKSPUR.getWood();
    public static final DeferredBlock<Block> STRIPPED_LARKSPUR_LOG = LARKSPUR.getStrippedLog();
    public static final DeferredBlock<Block> STRIPPED_LARKSPUR_WOOD = LARKSPUR.getStrippedWood();
    public static final DeferredBlock<Block> LARKSPUR_PLANKS = LARKSPUR.getPlanks();
    public static final DeferredBlock<StairBlock> LARKSPUR_STAIRS = LARKSPUR.getStairs();
    public static final DeferredBlock<SlabBlock> LARKSPUR_SLAB = LARKSPUR.getSlab();
    public static final DeferredBlock<PressurePlateBlock> LARKSPUR_PRESSURE_PLATE = LARKSPUR.getPressurePlate();
    public static final DeferredBlock<ButtonBlock> LARKSPUR_BUTTON = LARKSPUR.getButton();
    public static final DeferredBlock<FenceBlock> LARKSPUR_FENCE = LARKSPUR.getFence();
    public static final DeferredBlock<FenceGateBlock> LARKSPUR_FENCE_GATE = LARKSPUR.getFenceGate();
    public static final DeferredBlock<WallBlock> LARKSPUR_WALL = LARKSPUR.getWall();
    public static final DeferredBlock<DoorBlock> LARKSPUR_DOOR = LARKSPUR.getDoor();
    public static final DeferredBlock<TrapDoorBlock> LARKSPUR_TRAPDOOR = LARKSPUR.getTrapdoor();
    public static final DeferredBlock<Block> LARKSPUR_LEAVES = LARKSPUR_TREE.getLeaves();
    public static final DeferredBlock<Block> LARKSPUR_SAPLING = LARKSPUR_TREE.getSapling();

    public static final DeferredBlock<Block> WISTERIA_LEAVES = registerBlock("wisteria_leaves",
            () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<Block> BLUE_WISTERIA_LEAVES = registerBlock("blue_wisteria_leaves",
            () -> new DirectionalLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<Block> BLUE_WISTERIA_VINES = registerBlock("blue_wisteria_vines",
            () -> new WisteriaVineBlock(BlockBehaviour.Properties.of().noCollission().noOcclusion().randomTicks()));
    public static final DeferredBlock<Block> BLUE_WISTERIA_BLOSSOMS = registerBlock("blue_wisteria_blossoms",
            () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredBlock<Block> BLUE_WISTERIA_SAPLING = registerBlock("blue_wisteria_sapling",
            () -> new SaplingBlock(ModTreeGrowers.BLUE_WISTERIA,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<CraftingTableBlock> LARKSPUR_CRAFTING_TABLE = registerBlock("larkspur_crafting_table",
            () -> new ModCraftingTable(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));

    // Workstations
    public static final DeferredBlock<Block> SAWMILL = registerBlock("sawmill",
            () -> new SawmillBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)));



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
