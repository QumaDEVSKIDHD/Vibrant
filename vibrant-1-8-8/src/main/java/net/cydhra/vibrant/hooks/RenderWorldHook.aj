package net.cydhra.vibrant.hooks;

import net.cydhra.eventsystem.EventManager;
import net.cydhra.vibrant.api.render.VibrantScaledResolution;
import net.cydhra.vibrant.events.render.RenderOverlayEvent;
import net.cydhra.vibrant.events.render.RenderWorldEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

/**
 *
 */
public aspect RenderWorldHook {

    pointcut renderHand(float partialTicks, int xOffset):
            call(void net.minecraft.client.renderer.EntityRenderer.renderHand(float, int))
                    && args(partialTicks, xOffset);

    before(float partialTicks, int xOffset): renderHand(partialTicks, xOffset) {
        EventManager.callEvent(new RenderWorldEvent(partialTicks));
    }
}
