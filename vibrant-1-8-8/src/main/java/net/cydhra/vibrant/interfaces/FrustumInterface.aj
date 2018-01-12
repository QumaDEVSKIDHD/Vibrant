package net.cydhra.vibrant.interfaces;

/**
 *
 */
public aspect FrustumInterface {
    
    declare parents: (net.minecraft.client.renderer.culling.Frustum) implements net.cydhra.vibrant.api.render.VibrantFrustum;
}
