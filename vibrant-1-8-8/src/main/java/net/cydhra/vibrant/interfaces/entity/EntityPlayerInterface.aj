package net.cydhra.vibrant.interfaces.entity;

import net.minecraft.entity.player.EntityPlayer;

/**
 *
 */
public aspect EntityPlayerInterface {
    
    declare parents:(net.minecraft.entity.player.EntityPlayer)implements net.cydhra.vibrant.api.entity.VibrantPlayer;
    
    public double EntityPlayer.getChasingPosX() { return this.chasingPosX; }
    
    public double EntityPlayer.getChasingPosY() { return this.chasingPosY; }
    
    public double EntityPlayer.getChasingPosZ() { return this.chasingPosZ; }
}
