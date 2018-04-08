package net.cydhra.vibrant.hooks;

import net.cydhra.eventsystem.EventManager;
import net.cydhra.vibrant.api.render.VibrantScaledResolution;
import net.cydhra.vibrant.aspects.AbstractRenderOverlayHook;
import net.cydhra.vibrant.events.render.RenderOverlayEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

/**
 *
 */
public aspect RenderOverlayHook extends AbstractRenderOverlayHook {

    @Override
    public pointcut renderGameOverlay(float partialTicks):
            call(void net.minecraft.client.gui.GuiIngame.renderGameOverlay(float))
                    && args(partialTicks);

    @Override
    public void renderGameOverlay(float partialTicks) {
        EventManager.callEvent(new RenderOverlayEvent(
                (VibrantScaledResolution) new ScaledResolution(Minecraft.getMinecraft()), partialTicks));
    }
}
