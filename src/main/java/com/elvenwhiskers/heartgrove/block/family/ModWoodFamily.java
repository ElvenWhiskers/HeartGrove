package com.elvenwhiskers.heartgrove.block.family;

import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.registries.DeferredBlock;


public class ModWoodFamily {

    private final String name;

    private final DeferredBlock<Block> log;
    private final DeferredBlock<Block> wood;
    private final DeferredBlock<Block> strippedLog;
    private final DeferredBlock<Block> strippedWood;
    private final DeferredBlock<Block> planks;

    private final DeferredBlock<StairBlock> stairs;
    private final DeferredBlock<SlabBlock> slab;
    private final DeferredBlock<PressurePlateBlock> pressurePlate;
    private final DeferredBlock<ButtonBlock> button;
    private final DeferredBlock<FenceBlock> fence;
    private final DeferredBlock<FenceGateBlock> fenceGate;
    private final DeferredBlock<WallBlock> wall;
    private final DeferredBlock<DoorBlock> door;
    private final DeferredBlock<TrapDoorBlock> trapdoor;


    public ModWoodFamily(
            String name,
            DeferredBlock<Block> log,
            DeferredBlock<Block> wood,
            DeferredBlock<Block> strippedLog,
            DeferredBlock<Block> strippedWood,
            DeferredBlock<Block> planks,
            DeferredBlock<StairBlock> stairs,
            DeferredBlock<SlabBlock> slab,
            DeferredBlock<PressurePlateBlock> pressurePlate,
            DeferredBlock<ButtonBlock> button,
            DeferredBlock<FenceBlock> fence,
            DeferredBlock<FenceGateBlock> fenceGate,
            DeferredBlock<WallBlock> wall,
            DeferredBlock<DoorBlock> door,
            DeferredBlock<TrapDoorBlock> trapdoor
    ) {
        this.name = name;
        this.log = log;
        this.wood = wood;
        this.strippedLog = strippedLog;
        this.strippedWood = strippedWood;
        this.planks = planks;
        this.stairs = stairs;
        this.slab = slab;
        this.pressurePlate = pressurePlate;
        this.button = button;
        this.fence = fence;
        this.fenceGate = fenceGate;
        this.wall = wall;
        this.door = door;
        this.trapdoor = trapdoor;
    }


    public String getName() {
        return name;
    }


    public DeferredBlock<Block> getLog() {
        return log;
    }


    public DeferredBlock<Block> getWood() {
        return wood;
    }


    public DeferredBlock<Block> getStrippedLog() {
        return strippedLog;
    }


    public DeferredBlock<Block> getStrippedWood() {
        return strippedWood;
    }


    public DeferredBlock<Block> getPlanks() {
        return planks;
    }


    public DeferredBlock<StairBlock> getStairs() {
        return stairs;
    }


    public DeferredBlock<SlabBlock> getSlab() {
        return slab;
    }


    public DeferredBlock<PressurePlateBlock> getPressurePlate() {
        return pressurePlate;
    }


    public DeferredBlock<ButtonBlock> getButton() {
        return button;
    }


    public DeferredBlock<FenceBlock> getFence() {
        return fence;
    }


    public DeferredBlock<FenceGateBlock> getFenceGate() {
        return fenceGate;
    }


    public DeferredBlock<WallBlock> getWall() {
        return wall;
    }


    public DeferredBlock<DoorBlock> getDoor() {
        return door;
    }


    public DeferredBlock<TrapDoorBlock> getTrapdoor() {
        return trapdoor;
    }
}