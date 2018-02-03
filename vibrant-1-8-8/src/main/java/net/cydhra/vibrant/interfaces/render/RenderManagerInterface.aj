package net.cydhra.vibrant.interfaces.render;

import net.cydhra.vibrant.api.entity.VibrantEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;

privileged public aspect RenderManagerInterface {

    declare parents:(net.minecraft.client.renderer.entity.RenderManager)implements net.cydhra.vibrant.api.render.VibrantRenderManager;

    public boolean RenderManager.renderEntitySimple(VibrantEntity entity, float partialTicks, Object unused) {
        return this.renderEntitySimple((Entity) entity, partialTicks);
    }

    public double RenderManager.getRenderPosX() {
        return this.renderPosX;
    }

    public double RenderManager.getRenderPosY() {
        return this.renderPosY;
    }

    public double RenderManager.getRenderPosZ() {
        return this.renderPosZ;
    }

    public void RenderManager.setRenderPosX(double renderPosX) {
        this.renderPosX = renderPosX;
    }

    public void RenderManager.setRenderPosY(double renderPosY) {
        this.renderPosY = renderPosY;
    }

    public void RenderManager.setRenderPosZ(double renderPosZ) {
        this.renderPosZ = renderPosZ;
    }
}
