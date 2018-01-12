package net.cydhra.vibrant.interfaces;

import net.cydhra.vibrant.api.entity.VibrantPlayerSP;
import net.cydhra.vibrant.api.render.VibrantFrustum;
import net.cydhra.vibrant.api.render.VibrantScaledResolution;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.culling.Frustum;

/**
 *
 */
public aspect MinecraftInterface {
    
    declare parents:(Minecraft)implements net.cydhra.vibrant.api.client.VibrantMinecraft;
    
    public VibrantPlayerSP Minecraft.getThePlayer() {
        return (VibrantPlayerSP) thePlayer;
    }
    
    public VibrantScaledResolution Minecraft.newScaledResolution() { return (VibrantScaledResolution) new ScaledResolution(this); }
    
    public VibrantFrustum Minecraft.newFrustum() { return (VibrantFrustum) new Frustum(); }
    
    public int Minecraft.getDisplayWidth() { return this.displayWidth; }
    
    public int Minecraft.getDisplayHeight() { return this.displayHeight; }
}
