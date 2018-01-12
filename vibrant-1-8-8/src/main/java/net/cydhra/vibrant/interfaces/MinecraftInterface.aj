package net.cydhra.vibrant.interfaces;

import net.cydhra.vibrant.api.entity.VibrantPlayerSP;
import net.cydhra.vibrant.api.render.VibrantFrustum;
import net.cydhra.vibrant.api.render.VibrantScaledResolution;
import net.cydhra.vibrant.api.world.VibrantWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.World;

/**
 *
 */
public aspect MinecraftInterface {
    
    declare parents:(Minecraft)implements net.cydhra.vibrant.api.client.VibrantMinecraft;
    
    /**
     * @return the current {@link EntityPlayerSP} instance
     */
    public VibrantPlayerSP Minecraft.getThePlayer() {
        return (VibrantPlayerSP) thePlayer;
    }
    
    /**
     * @return the current {@link World} instance
     */
    public VibrantWorld Minecraft.getTheWorld() { return (VibrantWorld) theWorld; }
    
    /**
     * @return a new {@link ScaledResolution} instance
     */
    public VibrantScaledResolution Minecraft.newScaledResolution() { return (VibrantScaledResolution) new ScaledResolution(this); }
    
    /**
     * @return a new {@link Frustum} instance
     */
    public VibrantFrustum Minecraft.newFrustum() { return (VibrantFrustum) new Frustum(); }
    
    /**
     * @return the current display width
     */
    public int Minecraft.getDisplayWidth() { return this.displayWidth; }
    
    /**
     * @return the current display height
     */
    public int Minecraft.getDisplayHeight() { return this.displayHeight; }
}
