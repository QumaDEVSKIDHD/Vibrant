package net.cydhra.vibrant.events.render

import net.cydhra.eventsystem.events.Event
import net.cydhra.vibrant.api.render.VibrantScaledResolution

/**
 *
 */
class RenderOverlayEvent(val scaledResolution: VibrantScaledResolution, val partialTicks: Float) : Event()