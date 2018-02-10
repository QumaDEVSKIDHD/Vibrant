package net.cydhra.vibrant.interfaces.render;

import net.cydhra.vibrant.api.util.VibrantBoundingBox;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.util.AxisAlignedBB;

/**
 *
 */
public aspect FrustumInterface {
    
    declare parents:(net.minecraft.client.renderer.culling.Frustum)implements net.cydhra.vibrant.api.render.VibrantFrustum;
    
    public boolean Frustum.isBoundingBoxInsideFrustum(VibrantBoundingBox boundingBox) {
        return this.isBoundingBoxInFrustum((AxisAlignedBB) boundingBox);
    }
}
