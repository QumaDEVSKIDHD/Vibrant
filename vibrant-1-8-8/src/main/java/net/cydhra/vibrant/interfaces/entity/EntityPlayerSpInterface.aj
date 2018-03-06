package net.cydhra.vibrant.interfaces.entity;

import net.cydhra.vibrant.api.client.VibrantMovementInput;
import net.minecraft.client.entity.EntityPlayerSP;

/**
 *
 */
public aspect EntityPlayerSpInterface {
    declare parents: (EntityPlayerSP) implements net.cydhra.vibrant.api.entity.VibrantPlayerSP;
    
    public boolean EntityPlayerSP.isAllowedFlying() {
        return this.capabilities.allowFlying;
    }
    
    public void EntityPlayerSP.setAllowedFlying(boolean allowedFlying) {
        this.capabilities.allowFlying = allowedFlying;
    }

    public void EntityPlayerSP.swing() {
        this.swingItem();
    }

    public boolean EntityPlayerSP.isFlying() {
        return this.capabilities.isFlying;
    }
    
    public void EntityPlayerSP.setFlying(boolean isFlying) {
        this.capabilities.isFlying = isFlying;
    }
    
    public VibrantMovementInput EntityPlayerSP.getMovementInput() { return (VibrantMovementInput) this.movementInput; }
}
