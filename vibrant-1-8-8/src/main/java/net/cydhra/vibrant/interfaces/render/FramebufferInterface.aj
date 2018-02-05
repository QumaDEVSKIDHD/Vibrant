package net.cydhra.vibrant.interfaces.render;

import net.minecraft.client.shader.Framebuffer;

/**
 *
 */
public aspect FramebufferInterface {
    
    declare parents: (Framebuffer) implements net.cydhra.vibrant.api.render.VibrantFramebuffer;
    
    public int Framebuffer.getDepthBuffer() { return this.depthBuffer; }
    
    public void Framebuffer.setDepthBuffer(int depthBuffer) { this.depthBuffer = depthBuffer; }
    
    public int Framebuffer.getWidth() { return this.framebufferWidth; }
    
    public int Framebuffer.getHeight() { return this.framebufferHeight; }
}
