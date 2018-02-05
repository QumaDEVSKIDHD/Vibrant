package net.cydhra.vibrant

import net.cydhra.eventsystem.EventManager
import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.eventsystem.listeners.ListenerPriority
import net.cydhra.vibrant.api.client.VibrantFactory
import net.cydhra.vibrant.api.client.VibrantMinecraft
import net.cydhra.vibrant.events.minecraft.MinecraftTickEvent
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.gui.GuiManager
import net.cydhra.vibrant.gui.font.VibrantFontRenderer
import net.cydhra.vibrant.modulesystem.BypassMode
import net.cydhra.vibrant.modulesystem.ModuleManager
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.settings.VibrantSettings
import org.slf4j.LoggerFactory

/**
 *
 */
object VibrantClient {

    const val VERSION = "1.0"

    val logger = LoggerFactory.getLogger("Vibrant")!!

    lateinit var minecraft: VibrantMinecraft
        private set

    lateinit var factory: VibrantFactory
        private set

    var bypassMode: BypassMode = BypassMode.VANILLA

    val fontRenderer: VibrantFontRenderer
        get() = GuiManager.fontRenderer

    fun init(minecraft: VibrantMinecraft, factory: VibrantFactory) {
        logger.info("Vibrant Client successfully hooked.")
        this.minecraft = minecraft
        this.factory = factory

        VibrantSettings.load()

        logger.info("loading modules")
        ModuleManager.init()

        VibrantSettings.save()

        EventManager.registerListeners(this)
    }

    fun tick() {
        GameResourceManager.updateResources()
        EventManager.callEvent(MinecraftTickEvent())
    }

    @EventHandler(priority = ListenerPriority.LOWEST)
    fun afterRenderOverlay(e: RenderOverlayEvent) {
        minecraft.getTextureManager().bindTexture("textures/gui/icons.png")
    }

    @EventHandler(priority = ListenerPriority.LOWEST)
    fun afterRenderWorld(e: RenderWorldEvent) {
        minecraft.getTextureManager().bindTexture("textures/gui/icons.png")
    }
}