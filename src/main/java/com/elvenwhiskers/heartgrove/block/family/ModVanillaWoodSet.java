package com.elvenwhiskers.heartgrove.block.family;

import com.elvenwhiskers.heartgrove.block.custom.ModStandingSignBlock;
import com.elvenwhiskers.heartgrove.block.custom.ModWallSignBlock;
import net.minecraft.world.item.SignItem;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModVanillaWoodSet {

    private final ModWoodFamily woodFamily;

    private final DeferredBlock<ModStandingSignBlock> sign;
    private final DeferredBlock<ModWallSignBlock> wallSign;
    private final DeferredItem<SignItem> signItem;

    public ModVanillaWoodSet(
            ModWoodFamily woodFamily,
            DeferredBlock<ModStandingSignBlock> sign,
            DeferredBlock<ModWallSignBlock> wallSign,
            DeferredItem<SignItem> signItem
    ) {
        this.woodFamily = woodFamily;
        this.sign = sign;
        this.wallSign = wallSign;
        this.signItem = signItem;
    }

    public ModWoodFamily getWoodFamily() {
        return woodFamily;
    }

    public DeferredBlock<ModStandingSignBlock> getSign() {
        return sign;
    }

    public DeferredBlock<ModWallSignBlock> getWallSign() {
        return wallSign;
    }

    public DeferredItem<SignItem> getSignItem() {
        return signItem;
    }
}