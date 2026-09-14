package com.elvenwhiskers.heartgrove.datagen;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import com.elvenwhiskers.heartgrove.block.custom.WisteriaVineBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamily;
import com.elvenwhiskers.heartgrove.block.family.ModWoodFamilies;
import com.elvenwhiskers.heartgrove.block.family.ModNormalTreeFamily;
import com.elvenwhiskers.heartgrove.block.family.ModNormalTreeFamilies;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, HeartGrove.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.AEGIS_BLOCK);

        blockWithItem(ModBlocks.AEGIS_ORE);

        for (ModWoodFamily family : ModWoodFamilies.ALL) {
            woodSet(family);
        }

        for (ModNormalTreeFamily tree : ModNormalTreeFamilies.ALL) {
            normalTreeBlocks(tree);
        }

        leavesBlock(ModBlocks.WISTERIA_LEAVES);
        leavesBlock(ModBlocks.BLUE_WISTERIA_BLOSSOMS);
        saplingBlock(ModBlocks.BLUE_WISTERIA_SAPLING);
        wisteriaVineBlock(ModBlocks.BLUE_WISTERIA_VINES);

        directionalLeavesBlock(ModBlocks.BLUE_WISTERIA_LEAVES, ModBlocks.WISTERIA_LEAVES, ModBlocks.BLUE_WISTERIA_BLOSSOMS);

        //Other blocks
        ModelFile testModel = models().orientable("larkspur_crafting_table", modLoc("block/larkspur_crafting_table_side"), modLoc("block/larkspur_crafting_table_front"), modLoc("block/larkspur_crafting_table_top"));
        simpleBlockWithItem(ModBlocks.LARKSPUR_CRAFTING_TABLE.get(), testModel);
    }

    private void woodSet(ModWoodFamily family) {
        logSet(
                family.getLog(),
                family.getWood(),
                family.getStrippedLog(),
                family.getStrippedWood()
        );

        plankShapes(
                family.getPlanks(),
                family.getStairs(),
                family.getSlab(),
                family.getPressurePlate(),
                family.getButton(),
                family.getFence(),
                family.getFenceGate(),
                family.getWall()
        );

        doorSet(
                family.getDoor(),
                family.getTrapdoor()
        );
    }

    private void logSet(DeferredBlock<?> log, DeferredBlock<?> wood, DeferredBlock<?> sLog, DeferredBlock<?> sWood){
        logBlock(((RotatedPillarBlock) log.get()));
        axisBlock(((RotatedPillarBlock) wood.get()), blockTexture(log.get()), blockTexture(log.get()));
        logBlock(((RotatedPillarBlock) sLog.get()));
        axisBlock(((RotatedPillarBlock) sWood.get()), blockTexture(sLog.get()), blockTexture(sLog.get()));

        blockItem(log);
        blockItem(wood);
        blockItem(sLog);
        blockItem(sWood);
    }

    private void plankShapes(DeferredBlock<?> planks, DeferredBlock<?> stairs, DeferredBlock<?> slab, DeferredBlock<?> pressurePlate, DeferredBlock<?> button, DeferredBlock<?> fence, DeferredBlock<?> fenceGate, DeferredBlock<?> wall){
        blockWithItem(planks);
        stairsBlock((StairBlock) stairs.get(), blockTexture(planks.get()));
        blockItem(stairs);
        slabBlock((SlabBlock) slab.get(), blockTexture(planks.get()), blockTexture(planks.get()));
        blockItem(slab);
        pressurePlateBlock((PressurePlateBlock) pressurePlate.get(), blockTexture(planks.get()));
        blockItem(pressurePlate);
        buttonBlock((ButtonBlock) button.get(), blockTexture(planks.get()));
        buttonItem(button, planks);
        fenceBlock((FenceBlock) fence.get(), blockTexture(planks.get()));
        fenceGateBlock((FenceGateBlock) fenceGate.get(), blockTexture(planks.get()));
        blockItem(fenceGate);
        wallBlock((WallBlock) wall.get(), blockTexture(planks.get()));
    }

    private void normalTreeBlocks(ModNormalTreeFamily tree) {
        leavesBlock(tree.getLeaves());
        saplingBlock(tree.getSapling());
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void doorSet(DeferredBlock<?> door, DeferredBlock<?> trapDoor){
        doorBlockWithRenderType((DoorBlock) door.get(), modLoc("block/" + door.getId().getPath() + "_bottom"), modLoc("block/" + door.getId().getPath() + "_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) trapDoor.get(), modLoc("block/" + trapDoor.getId().getPath()), true, "cutout");
        blockItem(trapDoor, "_bottom");
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("heartgrove:block/" + deferredBlock.getId().getPath()));
    }

    private void buttonItem(DeferredBlock<?> button, DeferredBlock<?> planks) {
        simpleBlockItem(
                button.get(),
                models().singleTexture(
                        button.getId().getPath() + "_inventory",
                        mcLoc("block/button_inventory"),
                        "texture",
                        blockTexture(planks.get())
                )
        );
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("heartgrove:block/" + deferredBlock.getId().getPath() + appendix));
    }

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void directionalLeavesBlock(DeferredBlock<Block> blockRegistryObject, DeferredBlock<Block> topTextureBlock, DeferredBlock<Block> bottomTextureBlock) {
        ModelFile model = models().cube(
                        BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(),

                        blockTexture(bottomTextureBlock.get()),   // down
                        blockTexture(topTextureBlock.get()),      // up
                        blockTexture(blockRegistryObject.get()),  // north
                        blockTexture(blockRegistryObject.get()),  // south
                        blockTexture(blockRegistryObject.get()),  // west
                        blockTexture(blockRegistryObject.get())   // east
                )
                .texture("particle", blockTexture(blockRegistryObject.get()))
                .renderType("cutout");

        directionalBlock(blockRegistryObject.get(), model);
        simpleBlockItem(blockRegistryObject.get(), model);
    }

    private void wisteriaVineBlock(DeferredBlock<Block> blockRegistryObject) {

        String blockName = BuiltInRegistries.BLOCK
                .getKey(blockRegistryObject.get())
                .getPath();

        ModelFile normalModel = models().cross(
                blockName,
                blockTexture(blockRegistryObject.get())
        ).renderType("cutout");

        ModelFile bottomModel = models().cross(
                blockName + "_bottom",
                modLoc("block/" + blockName + "_bottom")
        ).renderType("cutout");

        getVariantBuilder(blockRegistryObject.get())
                .partialState()
                .with(WisteriaVineBlock.BOTTOM, false)
                .modelForState()
                .modelFile(normalModel)
                .addModel()

                .partialState()
                .with(WisteriaVineBlock.BOTTOM, true)
                .modelForState()
                .modelFile(bottomModel)
                .addModel();
    }
}
