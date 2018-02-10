package net.cydhra.vibrant.interfaces.render;

import net.cydhra.vibrant.api.entity.VibrantEntity;
import net.minecraft.client.renderer.entity.Render;

/**
 *
 */
public aspect RenderInterface {
    
    declare parents:(net.minecraft.client.renderer.entity.Render)implements net.cydhra.vibrant.api.render.VibrantRender;
    
    public void Render.render(VibrantEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.doRender((T) entity, x, y, z, entityYaw, partialTicks);
    }
}
