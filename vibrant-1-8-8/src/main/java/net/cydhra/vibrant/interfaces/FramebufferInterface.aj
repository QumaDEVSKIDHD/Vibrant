package net.cydhra.vibrant.interfaces;

import net.minecraft.client.shader.Framebuffer;

/**
 *
 */
public aspect FramebufferInterface {
    
    declare parents: (Framebuffer) implements net.cydhra.vibrant.api.render.VibrantFramebuffer;
    
    public int Framebuffer.getDepthBuffer() { return this.depthBuffer; }
    
    public void Framebuffer.setDepthBuffer(int depthBuffer) { this.depthBuffer = depthBuffer; }
}
