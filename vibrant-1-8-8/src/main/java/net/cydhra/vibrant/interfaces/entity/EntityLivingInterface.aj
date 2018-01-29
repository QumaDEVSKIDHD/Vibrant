package net.cydhra.vibrant.interfaces.entity;

import net.minecraft.entity.EntityLivingBase;

/**
 *
 */
public aspect EntityLivingInterface {
    
    declare parents:(EntityLivingBase)implements net.cydhra.vibrant.api.entity.VibrantEntityLiving;
    
    public double EntityLivingBase.getPosX() {
        return this.posX;
    }
    
    public double EntityLivingBase.getPosY() {
        return this.posY;
    }
    
    public double EntityLivingBase.getPosZ() {
        return this.posZ;
    }
    
    public float EntityLivingBase.getRotationYaw() { return this.rotationYaw; }
    
    public float EntityLivingBase.getRotationPitch() { return this.rotationPitch; }
    
    public boolean EntityLivingBase.isCollidedHorizontally() { return this.isCollidedHorizontally; }
    
    public boolean EntityLivingBase.isCollidedVertically() { return this.isCollidedVertically; }
}
