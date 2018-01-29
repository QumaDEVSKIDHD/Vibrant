package net.cydhra.vibrant.interfaces.render;

/**
 *
 */
public aspect FrustumInterface {
    
    declare parents: (net.minecraft.client.renderer.culling.Frustum) implements net.cydhra.vibrant.api.render.VibrantFrustum;
}
