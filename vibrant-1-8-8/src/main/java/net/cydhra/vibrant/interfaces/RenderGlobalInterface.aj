package net.cydhra.vibrant.interfaces;

import net.cydhra.vibrant.api.entity.VibrantEntity;
import net.cydhra.vibrant.api.render.VibrantFrustum;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.entity.Entity;

/**
 *
 */
public aspect RenderGlobalInterface {
    
    declare parents: (RenderGlobal) implements net.cydhra.vibrant.api.render.VibrantRenderGlobal;
    
    public void RenderGlobal
            .setupTerrain(VibrantEntity viewEntity, float partialTicks, VibrantFrustum frustum, int frameCount) {
        this.setupTerrain((Entity) viewEntity, partialTicks, (Frustum) frustum, frameCount, false);
    }
}
