package net.cydhra.vibrant.interfaces.world;

/**
 *
 */
public aspect WorldInterface {
    
    declare parents: (World) implements net.cydhra.vibrant.api.world.VibrantWorld;
}
