package net.cydhra.vibrant.modules.visual

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module

class ESPModule : Module("ESP", DefaultCategories.VISUAL) {

    @EventHandler
    fun onRender2D(e: RenderOverlayEvent) {

    }

    @EventHandler
    fun onRenderWorld(e: RenderWorldEvent) {

    }
}