package net.cydhra.vibrant.hooks;

import net.minecraft.entity.Entity;

/**
 *
 */
public aspect EntityInterface {
    
    declare parents: (Entity) implements net.cydhra.vibrant.api.entity.VibrantEntity;
    
    declare parents: (net.minecraft.client.entity.EntityPlayerSP) implements net.cydhra.vibrant.api.entity.VibrantPlayerSP;
    
    public boolean Entity.getOnGround() {
        return onGround;
    }
    
    public void Entity.setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
    
}
