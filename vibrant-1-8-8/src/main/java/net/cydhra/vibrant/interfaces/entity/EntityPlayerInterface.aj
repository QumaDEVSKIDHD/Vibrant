package net.cydhra.vibrant.interfaces.entity;

import net.cydhra.vibrant.api.inventory.VibrantPlayerInventory;
import net.cydhra.vibrant.api.util.VibrantDamageSource;
import net.minecraft.entity.player.EntityPlayer;

/**
 *
 */
public aspect EntityPlayerInterface {
    
    declare parents:(net.minecraft.entity.player.EntityPlayer)implements net.cydhra.vibrant.api.entity.VibrantPlayer;
    
    public double EntityPlayer.getChasingPosX() { return this.chasingPosX; }
    
    public double EntityPlayer.getChasingPosY() { return this.chasingPosY; }
    
    public double EntityPlayer.getChasingPosZ() { return this.chasingPosZ; }

    public VibrantPlayerInventory EntityPlayer.getPlayerInventory() {
        return (VibrantPlayerInventory) this.inventory;
    }

    public VibrantDamageSource EntityPlayer.getDamageSource() {
        return (VibrantDamageSource)
                net.minecraft.util.DamageSource.causePlayerDamage(this);
    }
}
