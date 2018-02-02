package net.cydhra.vibrant.interfaces.entity;

import net.minecraft.entity.EntityLivingBase;

/**
 *
 */
public aspect EntityLivingInterface {
    
    declare parents:(EntityLivingBase)implements net.cydhra.vibrant.api.entity.VibrantEntityLiving;
    
    public boolean EntityLivingBase.isCollidedHorizontally() { return this.isCollidedHorizontally; }
    
    public boolean EntityLivingBase.isCollidedVertically() { return this.isCollidedVertically; }
}
