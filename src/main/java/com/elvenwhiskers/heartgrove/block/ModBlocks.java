package com.elvenwhiskers.heartgrove.block;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.custom.ModCraftingTable;
import com.elvenwhiskers.heartgrove.block.custom.SawmillBlock;
import com.elvenwhiskers.heartgrove.block.custom.hedge.ModHedge;
import com.elvenwhiskers.heartgrove.block.custom.hedge.ModHedgeRegistrar;
import com.elvenwhiskers.heartgrove.block.custom.wisteria.DirectionalLeavesBlock;
import com.elvenwhiskers.heartgrove.block.custom.wisteria.WisteriaVineBlock;
import com.elvenwhiskers.heartgrove.block.custom.wood.ModFlammableLeaves;
import com.elvenwhiskers.heartgrove.block.family.tree.ModNormalTreeFamily;
import com.elvenwhiskers.heartgrove.block.family.tree.ModNormalTreeRegistrar;
import com.elvenwhiskers.heartgrove.block.family.vanilla.ModVanillaWoodSet;
import com.elvenwhiskers.heartgrove.block.family.vanilla.ModVanillaWoodSetRegistrar;
import com.elvenwhiskers.heartgrove.block.family.vanilla.ModWoodTypes;
import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamily;
import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamilyRegistrar;
import com.elvenwhiskers.heartgrove.item.ModItems;
import com.elvenwhiskers.heartgrove.worldgen.tree.normal.ModTreeShapes;
import com.elvenwhiskers.heartgrove.worldgen.tree.wisteria.WisteriaTreeGrowers;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(HeartGrove.MOD_ID);


    // Wood families
    public static final ModWoodFamily WISTERIA =
            ModWoodFamilyRegistrar.register(BLOCKS, "wisteria");

    public static final ModWoodFamily LARKSPUR =
            ModWoodFamilyRegistrar.register(BLOCKS, "larkspur");

    public static final ModWoodFamily FROSTBELL_BLOSSOM =
            ModWoodFamilyRegistrar.register(BLOCKS, "frostbell_blossom");


    // Normal tree families
    public static final ModNormalTreeFamily LARKSPUR_TREE =
            ModNormalTreeRegistrar.register(BLOCKS, "larkspur", LARKSPUR, ModTreeShapes.LARKSPUR);

    public static final ModNormalTreeFamily FROSTBELL_BLOSSOM_TREE =
            ModNormalTreeRegistrar.register(BLOCKS, "frostbell_blossom", FROSTBELL_BLOSSOM, ModTreeShapes.FROSTBELL_BLOSSOM);


    // Vanilla-style wood expansions
    public static final ModVanillaWoodSet FROSTBELL_BLOSSOM_VANILLA =
            ModVanillaWoodSetRegistrar.register(BLOCKS, "frostbell_blossom", FROSTBELL_BLOSSOM, ModWoodTypes.FROSTBELL_BLOSSOM);

    // Wisteria foliage
    public static final DeferredBlock<Block> WISTERIA_LEAVES = registerBlock(
            "wisteria_leaves",
            () -> new ModFlammableLeaves(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
            )
    );

    public static final DeferredBlock<Block> BLUE_WISTERIA_LEAVES = registerBlock(
            "blue_wisteria_leaves",
            () -> new DirectionalLeavesBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
            )
    );

    public static final DeferredBlock<Block> BLUE_WISTERIA_VINES = registerBlock(
            "blue_wisteria_vines",
            () -> new WisteriaVineBlock(
                    BlockBehaviour.Properties.of()
                            .noCollission()
                            .noOcclusion()
                            .randomTicks()
            )
    );

    public static final DeferredBlock<Block> BLUE_WISTERIA_BLOSSOMS = registerBlock(
            "blue_wisteria_blossoms",
            () -> new ModFlammableLeaves(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)
            )
    );

    public static final DeferredBlock<Block> BLUE_WISTERIA_SAPLING = registerBlock(
            "blue_wisteria_sapling",
            () -> new SaplingBlock(
                    WisteriaTreeGrowers.BLUE_WISTERIA,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)
            )
    );

    // Hedges
    public static final ModHedge FROSTBELL_BLOSSOM_HEDGE_SET =
            ModHedgeRegistrar.register(BLOCKS, "frostbell_blossom", FROSTBELL_BLOSSOM_TREE.getLeaves(), FROSTBELL_BLOSSOM);

    public static final ModHedge LARKSPUR_HEDGE_SET =
            ModHedgeRegistrar.register(BLOCKS, "larkspur", LARKSPUR_TREE.getLeaves(), LARKSPUR);

    public static final ModHedge WISTERIA_HEDGE_SET =
            ModHedgeRegistrar.register(BLOCKS, "wisteria", WISTERIA_LEAVES, WISTERIA);

    public static final ModHedge BLUE_WISTERIA_BLOSSOM_HEDGE_SET =
            ModHedgeRegistrar.register(BLOCKS, "blue_wisteria_blossom", BLUE_WISTERIA_BLOSSOMS, WISTERIA);

    public static final ModHedge BLUE_WISTERIA_HEDGE_SET =
            ModHedgeRegistrar.register(BLOCKS, "blue_wisteria", WISTERIA_LEAVES, BLUE_WISTERIA_LEAVES, WISTERIA);


    // Aegis
    public static final DeferredBlock<Block> AEGIS_BLOCK = registerBlock(
            "aegis_block",
            () -> new Block(
                    BlockBehaviour.Properties.of()
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.AMETHYST)
            )
    );

    public static final DeferredBlock<Block> AEGIS_ORE = registerBlock(
            "aegis_ore",
            () -> new DropExperienceBlock(
                    UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of()
                            .strength(3f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)
            )
    );



    // Utility blocks
    public static final DeferredBlock<CraftingTableBlock> LARKSPUR_CRAFTING_TABLE = registerBlock(
            "larkspur_crafting_table",
            () -> new ModCraftingTable(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)
            )
    );


    // Workstations
    public static final DeferredBlock<Block> SAWMILL = registerBlock(
            "sawmill",
            () -> new SawmillBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)
            )
    );


    private static <T extends Block> DeferredBlock<T> registerBlock(
            String name,
            Supplier<T> block
    ) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(
            String name,
            DeferredBlock<T> block
    ) {
        ModItems.ITEMS.register(
                name,
                () -> new BlockItem(block.get(), new Item.Properties())
        );
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private ModBlocks() {
    }
}