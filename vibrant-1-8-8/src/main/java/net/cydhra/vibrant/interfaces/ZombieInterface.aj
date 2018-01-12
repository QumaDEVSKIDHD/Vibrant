package net.cydhra.vibrant.interfaces;

/**
 *
 */
public aspect ZombieInterface {
    
    declare parents: (net.minecraft.entity.monster.EntityZombie) implements net.cydhra.vibrant.api.entity.VibrantZombie;
}
