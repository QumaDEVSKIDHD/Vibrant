package net.cydhra.vibrant.interfaces;

import net.cydhra.vibrant.adapter.VibrantGuiScreenAdapter;
import net.cydhra.vibrant.api.entity.VibrantPlayerSP;
import net.cydhra.vibrant.api.gui.VibrantGuiScreen;
import net.cydhra.vibrant.api.render.VibrantEntityRenderer;
import net.cydhra.vibrant.api.render.VibrantFrustum;
import net.cydhra.vibrant.api.render.VibrantRenderGlobal;
import net.cydhra.vibrant.api.render.VibrantScaledResolution;
import net.cydhra.vibrant.api.world.VibrantWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderGlobal;
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
    public VibrantPlayerSP Minecraft.getThePlayer() { return (VibrantPlayerSP) thePlayer; }
    
    /**
     * @return the current {@link World} instance
     */
    public VibrantWorld Minecraft.getTheWorld() { return (VibrantWorld) theWorld; }
    
    /**
     * @return the {@link RenderGlobal} instance
     */
    public VibrantRenderGlobal Minecraft.getRenderGlobal() { return (VibrantRenderGlobal) renderGlobal; }
    
    /**
     * @return the {@link EntityRenderer} instance
     */
    public VibrantEntityRenderer Minecraft.getEntityRenderer() { return (VibrantEntityRenderer) entityRenderer; }
    
    /**
     * @return a new {@link ScaledResolution} instance
     */
    public VibrantScaledResolution Minecraft.newScaledResolution() { return (VibrantScaledResolution) new ScaledResolution(this); }
    
    /**
     * @return a new {@link Frustum} instance
     */
    public VibrantFrustum Minecraft.newFrustum() { return (VibrantFrustum) new Frustum(); }
    
    /**
     * Delegate the {@link VibrantGuiScreen} through a {@link VibrantGuiScreenAdapter} to Minecraft
     * @param screen screen implementation
     */
    public void Minecraft.displayGuiScreen(VibrantGuiScreen screen) { this.displayGuiScreen(new VibrantGuiScreenAdapter(screen)); }
    
    /**
     * @return the current display width
     */
    public int Minecraft.getDisplayWidth() { return this.displayWidth; }
    
    /**
     * @return the current display height
     */
    public int Minecraft.getDisplayHeight() { return this.displayHeight; }
    
    /**
     * @return true, if no screen is displayed at the moment
     */
    public boolean Minecraft.isCurrentlyDisplayingScreen() { return this.currentScreen != null; }
}
