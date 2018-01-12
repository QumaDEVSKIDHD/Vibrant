package net.cydhra.vibrant.interfaces;

import net.cydhra.vibrant.api.entity.VibrantPlayerSP;
import net.minecraft.client.Minecraft;

/**
 *
 */
public aspect MinecraftInterface {

    declare parents: (Minecraft) implements net.cydhra.vibrant.api.client.VibrantMinecraft;
    
    public VibrantPlayerSP Minecraft.getThePlayer() {
        return (VibrantPlayerSP) thePlayer;
    }
}
