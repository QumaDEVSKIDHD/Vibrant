package net.cydhra.vibrant.interfaces;

public aspect DynamicTextureInterface {

    declare parents:(net.minecraft.client.renderer.texture.DynamicTexture)implements net.cydhra.vibrant.api.render.VibrantDynamicTexture;

}
