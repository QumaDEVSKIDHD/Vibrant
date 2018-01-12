package net.cydhra.vibrant.hooks;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;

/**
 *
 */
public aspect EntityInterface {
    
    declare parents: (Entity) implements net.cydhra.vibrant.api.entity.VibrantEntity;
    
    declare parents: (EntityPlayerSP) implements net.cydhra.vibrant.api.entity.VibrantPlayerSP;
    
    public boolean Entity.getOnGround() {
        return onGround;
    }
    
    public void Entity.setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
    
    public boolean EntityPlayerSP.isAllowedFlying() {
        return this.capabilities.allowFlying;
    }
    
    public void EntityPlayerSP.setAllowedFlying(boolean allowedFlying) {
        this.capabilities.allowFlying = allowedFlying;
    }
    
    public boolean EntityPlayerSP.isFlying() {
        return this.capabilities.isFlying;
    }
    
    public void EntityPlayerSP.setFlying(boolean isFlying) {
        this.capabilities.isFlying = isFlying;
    }
}
