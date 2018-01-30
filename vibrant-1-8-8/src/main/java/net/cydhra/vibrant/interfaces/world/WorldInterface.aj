package net.cydhra.vibrant.interfaces.world;

/**
 *
 */
public aspect WorldInterface {
    
    declare parents:(net.minecraft.world.World)implements net.cydhra.vibrant.api.world.VibrantWorld;
}
