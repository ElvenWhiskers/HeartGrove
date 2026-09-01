package com.elvenwhiskers.heartgrove.datagen;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, HeartGrove.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.AEGIS_BLOCK);

        blockWithItem(ModBlocks.AEGIS_ORE);

        //blockItem(ModBlocks.LARKSPUR_CRAFTING_TABLE);


        //step 1: logset
        logSet(ModBlocks.LARKSPUR_LOG, ModBlocks.LARKSPUR_WOOD, ModBlocks.STRIPPED_LARKSPUR_LOG, ModBlocks.STRIPPED_LARKSPUR_WOOD);
        logSet(ModBlocks.WISTERIA_LOG, ModBlocks.WISTERIA_WOOD, ModBlocks.STRIPPED_WISTERIA_LOG, ModBlocks.STRIPPED_WISTERIA_WOOD);

        //step 2: plankShapes
        plankShapes(ModBlocks.LARKSPUR_PLANKS, ModBlocks.LARKSPUR_STAIRS, ModBlocks.LARKSPUR_SLAB, ModBlocks.LARKSPUR_PRESSURE_PLATE, ModBlocks.LARKSPUR_BUTTON, ModBlocks.LARKSPUR_FENCE, ModBlocks.LARKSPUR_FENCE_GATE, ModBlocks.LARKSPUR_WALL);
        plankShapes(ModBlocks.WISTERIA_PLANKS, ModBlocks.WISTERIA_STAIRS, ModBlocks.WISTERIA_SLAB, ModBlocks.WISTERIA_PRESSURE_PLATE, ModBlocks.WISTERIA_BUTTON, ModBlocks.WISTERIA_FENCE, ModBlocks.WISTERIA_FENCE_GATE, ModBlocks.WISTERIA_WALL);

        //step 3: doorSet
        doorSet(ModBlocks.LARKSPUR_DOOR, ModBlocks.LARKSPUR_TRAPDOOR);
        doorSet(ModBlocks.WISTERIA_DOOR, ModBlocks.WISTERIA_TRAPDOOR);

        leavesBlock(ModBlocks.LARKSPUR_LEAVES);
        saplingBlock(ModBlocks.LARKSPUR_SAPLING);
        leavesBlock(ModBlocks.WISTERIA_LEAVES);
        //leavesBlock(ModBlocks.BLUE_WISTERIA_LEAVES);
        leavesBlock(ModBlocks.BLUE_WISTERIA_BLOSSOMS);
        saplingBlock(ModBlocks.BLUE_WISTERIA_SAPLING);

        directionalLeavesBlock(ModBlocks.BLUE_WISTERIA_LEAVES, ModBlocks.WISTERIA_LEAVES, ModBlocks.BLUE_WISTERIA_BLOSSOMS);

        //step 4: ideally
        //woodSet("larkspur");

        //Other blocks
        ModelFile testModel = models().orientable("larkspur_crafting_table", modLoc("block/larkspur_crafting_table_side"), modLoc("block/larkspur_crafting_table_front"), modLoc("block/larkspur_crafting_table_top"));
        simpleBlockWithItem(ModBlocks.LARKSPUR_CRAFTING_TABLE.get(), testModel);
    }

    private void woodSet(){

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
        blockItem(button);
        fenceBlock((FenceBlock) fence.get(), blockTexture(planks.get()));
        fenceGateBlock((FenceGateBlock) fenceGate.get(), blockTexture(planks.get()));
        blockItem(fenceGate);
        wallBlock((WallBlock) wall.get(), blockTexture(planks.get()));
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
}
