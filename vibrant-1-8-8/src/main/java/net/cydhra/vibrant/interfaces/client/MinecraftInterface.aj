package net.cydhra.vibrant.interfaces.client;

import net.cydhra.vibrant.adapter.VibrantGuiScreenAdapter;
import net.cydhra.vibrant.api.client.VibrantGameSettings;
import net.cydhra.vibrant.api.client.VibrantPlayerController;
import net.cydhra.vibrant.api.client.VibrantTimer;
import net.cydhra.vibrant.api.entity.VibrantEntity;
import net.cydhra.vibrant.api.entity.VibrantPlayerSP;
import net.cydhra.vibrant.api.gui.VibrantGuiScreen;
import net.cydhra.vibrant.api.render.VibrantEntityRenderer;
import net.cydhra.vibrant.api.render.VibrantRenderGlobal;
import net.cydhra.vibrant.api.render.VibrantTileEntityRendererDispatcher;
import net.cydhra.vibrant.api.world.VibrantWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

import java.lang.reflect.Field;

/**
 *
 */
public privileged aspect MinecraftInterface {
    
    declare parents:(Minecraft)implements net.cydhra.vibrant.api.client.VibrantMinecraft;
    
    private Field Minecraft.sessionField;
    
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
    
    public VibrantTileEntityRendererDispatcher Minecraft.getTileEntityRenderDispatcher() {
        return (VibrantTileEntityRendererDispatcher) TileEntityRendererDispatcher.instance;
    }
    
    public VibrantTimer Minecraft.getTimer() { return (VibrantTimer) this.timer; }
    
    public VibrantGameSettings Minecraft.getGameSettings() { return (VibrantGameSettings) this.gameSettings; }

    public VibrantPlayerController Minecraft.getPlayerController() {
        return (VibrantPlayerController) this.playerController;
    }

    /**
     * Delegate the {@link VibrantGuiScreen} through a {@link VibrantGuiScreenAdapter} to Minecraft
     * @param screen screen implementation
     */
    public void Minecraft.displayGuiScreen(VibrantGuiScreen screen) {
        if (screen != null)
            this.displayGuiScreen(new VibrantGuiScreenAdapter(screen));
        else
            this.displayGuiScreen((GuiScreen) null);
    }
    
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
    
    public VibrantEntity Minecraft.getTheRenderViewEntity() { return (VibrantEntity) this.renderViewEntity; }
    
    public void Minecraft.setTheRenderViewEntity(VibrantEntity entity) { this.renderViewEntity = (Entity) entity; }
    
    public net.cydhra.nidhogg.data.Session Minecraft.getMinecraftSession() {
        return new net.cydhra.nidhogg.data.Session(this.session.getUsername(), this.session.getSessionID(), this.session.getPlayerID());
    }
    
    public void Minecraft.setMinecraftSession(net.cydhra.nidhogg.data.Session session) throws IllegalAccessException, NoSuchFieldException {
        if (sessionField == null) {
            sessionField = Minecraft.class.getDeclaredField("session");
            sessionField.setAccessible(true);
        }
        
        sessionField.set(Minecraft.getMinecraft(),
                new net.minecraft.util.Session(session.getAlias(), session.getClientToken(), session.getAccessToken(),
                        "mojang"));
    }
    
    
}
