package net.cydhra.vibrant.interfaces.world;

public aspect BlockInterface {

    declare parents: net.minecraft.block.Block implements net.cydhra.vibrant.api.world.VibrantBlock;
}
