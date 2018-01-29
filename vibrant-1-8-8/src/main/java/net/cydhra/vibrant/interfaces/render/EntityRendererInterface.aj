package net.cydhra.vibrant.interfaces.render;

import net.minecraft.client.renderer.EntityRenderer;

/**
 *
 */
privileged public aspect EntityRendererInterface {
    
    declare parents: (EntityRenderer) implements net.cydhra.vibrant.api.render.VibrantEntityRenderer;
    
    public int EntityRenderer.getFrameCount() { return this.frameCount; }
    
    public void EntityRenderer.setFrameCount(int frameCount) { this.frameCount = frameCount; }
}
