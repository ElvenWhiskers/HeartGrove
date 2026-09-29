package com.elvenwhiskers.heartgrove.block.family.vanilla;

import com.elvenwhiskers.heartgrove.block.custom.sign.ModStandingSignBlock;
import com.elvenwhiskers.heartgrove.block.custom.sign.ModWallSignBlock;
import com.elvenwhiskers.heartgrove.block.family.wood.ModWoodFamily;
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