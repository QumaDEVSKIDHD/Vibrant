package net.cydhra.vibrant.interfaces;

import net.minecraft.entity.Entity;

/**
 *
 */
public aspect EntityInterface {
    
    declare parents: (Entity) implements net.cydhra.vibrant.api.entity.VibrantEntity;
    
    
    public boolean Entity.getOnGround() {
        return onGround;
    }
    
    public void Entity.setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
