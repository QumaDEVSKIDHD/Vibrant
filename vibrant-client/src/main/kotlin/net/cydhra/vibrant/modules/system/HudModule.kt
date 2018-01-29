package net.cydhra.vibrant.modules.system

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.gui.font.VibrantFontRenderer
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard
import java.awt.Font

class HudModule : Module("Hud", DefaultCategories.SYSTEM, Keyboard.KEY_H) {

    private val fontRenderer = VibrantFontRenderer(Font("Arial", Font.BOLD, 16))

    @EventHandler
    fun onRenderOverlay(e: RenderOverlayEvent) {
        fontRenderer.drawString("Vibrant ${this.javaClass.`package`.implementationVersion}", 2, 2, -1)
    }
}
