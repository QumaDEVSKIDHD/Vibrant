package net.cydhra.vibrant.interfaces.render;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

/**
 *
 */
public aspect TextureManagerInterface {
    
    declare parents: (TextureManager) implements net.cydhra.vibrant.api.render.VibrantTextureManager;
    
    public void TextureManager.bindTexture(String location) {
        this.bindTexture(new ResourceLocation(location));
    }
}
