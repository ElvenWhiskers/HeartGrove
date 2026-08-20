package com.elvenwhiskers.heartgrove.datagen;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
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

        logBlock(((RotatedPillarBlock) ModBlocks.LARKSPUR_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.LARKSPUR_WOOD.get()), blockTexture(ModBlocks.LARKSPUR_LOG.get()), blockTexture(ModBlocks.LARKSPUR_LOG.get()));
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_LARKSPUR_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_LARKSPUR_WOOD.get()), blockTexture(ModBlocks.STRIPPED_LARKSPUR_LOG.get()), blockTexture(ModBlocks.STRIPPED_LARKSPUR_LOG.get()));

        blockItem(ModBlocks.LARKSPUR_LOG);
        blockItem(ModBlocks.LARKSPUR_WOOD);
        blockItem(ModBlocks.STRIPPED_LARKSPUR_LOG);
        blockItem(ModBlocks.STRIPPED_LARKSPUR_WOOD);

        blockWithItem(ModBlocks.LARKSPUR_PLANKS);

        leavesBlock(ModBlocks.LARKSPUR_LEAVES);
        saplingBlock(ModBlocks.LARKSPUR_SAPLING);



    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("heartgrove:block/" + deferredBlock.getId().getPath()));
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
}
