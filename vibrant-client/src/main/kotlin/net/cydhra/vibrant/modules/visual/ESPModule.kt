package net.cydhra.vibrant.modules.visual

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.util.outline.MultiOutline
import org.lwjgl.input.Keyboard

class ESPModule : Module("ESP", DefaultCategories.VISUAL, Keyboard.KEY_B) {

    private val multiOutline: MultiOutline by lazy {
        MultiOutline(true)
    }

    override fun onEnable() {
        multiOutline.setLineWidth(1F)
        multiOutline.setSampleRadius(4)
        multiOutline.setAverageDivisor(80F)
        multiOutline.setMaxSampleRadius(4)
    }

    @EventHandler
    fun onRender2DShaderEsp(e: RenderOverlayEvent) {
        multiOutline.endOutline()
    }

    @EventHandler
    fun onRenderWorldShaderEsp(e: RenderWorldEvent) {
        multiOutline.startOutline()
    }
}