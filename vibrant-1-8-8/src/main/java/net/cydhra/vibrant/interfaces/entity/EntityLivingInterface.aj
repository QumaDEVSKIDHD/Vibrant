package net.cydhra.vibrant.interfaces.entity;

import net.cydhra.vibrant.api.util.VibrantDamageSource;
import net.minecraft.entity.EntityLivingBase;

/**
 *
 */
public aspect EntityLivingInterface {
    
    declare parents:(EntityLivingBase)implements net.cydhra.vibrant.api.entity.VibrantEntityLiving;
    
    public boolean EntityLivingBase.isCollidedHorizontally() { return this.isCollidedHorizontally; }
    
    public boolean EntityLivingBase.isCollidedVertically() { return this.isCollidedVertically; }

    public VibrantDamageSource EntityLivingBase.getDamageSource() {
        return (VibrantDamageSource)
                net.minecraft.util.DamageSource.causeMobDamage(this);
    }
}
