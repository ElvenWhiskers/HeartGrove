package com.elvenwhiskers.heartgrove.block.entity;

import com.elvenwhiskers.heartgrove.HeartGrove;
import com.elvenwhiskers.heartgrove.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, HeartGrove.MOD_ID);

    public static final Supplier<BlockEntityType<ModSignBlockEntity>> SIGN =
            BLOCK_ENTITIES.register(
                    "sign",
                    () -> BlockEntityType.Builder.of(
                            ModSignBlockEntity::new,
                            ModBlocks.FROSTBELL_BLOSSOM_VANILLA.getSign().get(),
                            ModBlocks.FROSTBELL_BLOSSOM_VANILLA.getWallSign().get()
                    ).build(null)
            );
}