package net.cydhra.vibrant.hooks;

import net.cydhra.vibrant.aspects.AbstractRenderWorldHook;

public aspect RenderWorldHook extends AbstractRenderWorldHook {

    @Override
    public pointcut renderHand(float partialTicks, int xOffset):
            call(void net.minecraft.client.renderer.EntityRenderer.renderHand(float, int))
                    && args(partialTicks, xOffset);

}
