package net.cydhra.vibrant.interfaces.world;

import net.cydhra.vibrant.api.entity.VibrantZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

/**
 *
 */
public aspect WorldInterface {
    
    declare parents: (World) implements net.cydhra.vibrant.api.world.VibrantWorld;
    
    public VibrantZombie World.createZombie() { return (VibrantZombie) new EntityZombie(this); }
}
