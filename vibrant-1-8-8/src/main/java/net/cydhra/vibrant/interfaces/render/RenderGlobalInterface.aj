package net.cydhra.vibrant.interfaces.render;

import net.cydhra.vibrant.api.entity.VibrantEntity;
import net.cydhra.vibrant.api.render.VibrantFrustum;
import net.cydhra.vibrant.api.render.VibrantRenderGlobal;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumWorldBlockLayer;


/**
 *
 */
public aspect RenderGlobalInterface {
    
    declare parents: (RenderGlobal) implements net.cydhra.vibrant.api.render.VibrantRenderGlobal;
    
    public void RenderGlobal
            .setupTerrain(VibrantEntity viewEntity, float partialTicks, VibrantFrustum frustum, int frameCount) {
        this.setupTerrain((Entity) viewEntity, partialTicks, (Frustum) frustum, frameCount, false);
    }
    
    public void RenderGlobal.renderBlockLayer(VibrantRenderGlobal.VibrantBlockLayerType type, VibrantEntity viewEntity) {
        EnumWorldBlockLayer layerType = null;
        
        switch (type) {
            case SOLID:
                layerType = EnumWorldBlockLayer.SOLID;
                break;
            case TRANSLUCENT:
                layerType = EnumWorldBlockLayer.TRANSLUCENT;
                break;
            case CUTOUT:
                layerType = EnumWorldBlockLayer.CUTOUT;
                break;
            case CUTOUT_MIPPED:
                layerType = EnumWorldBlockLayer.CUTOUT_MIPPED;
                break;
        }
        
        this.renderBlockLayer(layerType, 0, 0, (Entity) viewEntity);
    }
}
