package net.cydhra.vibrant.interfaces.render;

/**
 *
 */
public aspect RenderInterface {
    
    declare parents:(net.minecraft.client.renderer.entity.Render)implements net.cydhra.vibrant.api.render.VibrantRender;
}
