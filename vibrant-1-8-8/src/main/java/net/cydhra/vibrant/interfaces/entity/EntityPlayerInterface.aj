package net.cydhra.vibrant.interfaces.entity;

/**
 *
 */
public aspect EntityPlayerInterface {
    
    declare parents:(net.minecraft.entity.player.EntityPlayer)implements net.cydhra.vibrant.api.entity.VibrantPlayer;
}
