package net.cydhra.vibrant.aspects;

import net.cydhra.eventsystem.EventManager;
import net.cydhra.vibrant.events.render.RenderWorldEvent;

/**
 * Hook the world rendering directly above the rendering of the arm
 */
public abstract aspect AbstractRenderWorldHook {

    /**
     * The pointcut before executing the renderHand method
     */
    public abstract pointcut renderHand(float partialTicks, int xOffset);

    before(float partialTicks, int xOffset): renderHand(partialTicks, xOffset) {
        EventManager.callEvent(new RenderWorldEvent(partialTicks));
    }
}
