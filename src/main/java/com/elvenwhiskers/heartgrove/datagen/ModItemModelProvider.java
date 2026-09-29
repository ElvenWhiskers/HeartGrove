package com.elvenwhiskers.heartgrove.datagen;


import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import com.elvenwhiskers.heartgrove.block.family.tree.ModNormalTreeRegistrar;
import com.elvenwhiskers.heartgrove.block.family.tree.ModNormalTreeFamily;
import com.elvenwhiskers.heartgrove.block.family.vanilla.ModVanillaWoodSet;
import com.elvenwhiskers.heartgrove.block.family.vanilla.ModVanillaWoodSetRegistrar;
import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamilyRegistrar;
import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamily;
import com.elvenwhiskers.heartgrove.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import com.elvenwhiskers.heartgrove.block.custom.hedge.ModHedge;
import com.elvenwhiskers.heartgrove.block.custom.hedge.ModHedgeRegistrar;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HeartGrove.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.AEGIS_INGOT.get());
        basicItem(ModItems.RAW_AEGIS.get());



        for (ModWoodFamily family : ModWoodFamilyRegistrar.getWoodFamilies()) {
            woodFamilyItems(family);
        }

        for (ModNormalTreeFamily tree : ModNormalTreeRegistrar.getTreeFamilies()) {
            normalTreeItems(tree);
        }

        for (ModVanillaWoodSet vanillaSet : ModVanillaWoodSetRegistrar.getVanillaWoodSets()) {
            vanillaWoodSetItems(vanillaSet);
        }

        for (ModHedge hedge : ModHedgeRegistrar.getHedges()) {
            hedgeItem(hedge);
        }

        // Wisteria-specific
        saplingItem(ModBlocks.BLUE_WISTERIA_SAPLING);

        withExistingParent(
                ModBlocks.BLUE_WISTERIA_VINES.getId().getPath(),
                "item/generated"
        ).texture(
                "layer0",
                ResourceLocation.fromNamespaceAndPath(
                        HeartGrove.MOD_ID,
                        "block/blue_wisteria_vines_bottom"
                )
        );
    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(HeartGrove.MOD_ID,"block/" + item.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(HeartGrove.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(HeartGrove.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    private void woodFamilyItems(ModWoodFamily family) {
        fenceItem(
                family.getFence(),
                family.getPlanks()
        );

        basicItem(
                family.getDoor().asItem()
        );

        wallItem(
                family.getWall(),
                family.getPlanks()
        );
    }

    private void hedgeItem(ModHedge hedge) {
        String name = hedge.getHedge().getId().getPath();

        getBuilder(name)
                .parent(new ModelFile.UncheckedModelFile(
                        modLoc("block/" + name)
                ));
    }

    private void vanillaWoodSetItems(ModVanillaWoodSet vanillaSet) {
        basicItem(vanillaSet.getSignItem().get());
    }

    private void normalTreeItems(ModNormalTreeFamily tree) {
        saplingItem(tree.getSapling());
    }

}
