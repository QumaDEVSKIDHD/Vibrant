@file:Suppress("UNUSED_PARAMETER")

package net.cydhra.vibrant.modules.gui

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.api.gui.VibrantGuiMainMenu
import net.cydhra.vibrant.events.minecraft.GuiScreenChangeEvent
import net.cydhra.vibrant.events.minecraft.MinecraftTickEvent
import net.cydhra.vibrant.gui.menu.MainMenuScreen
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module

/**
 *
 */
class MainMenuModule : Module("MainMenu", DefaultCategories.SYSTEM) {

    private var init = false

    init {
        isEnabled = true
    }

    @EventHandler
    fun onTick(e: MinecraftTickEvent) {
        if (!init) {
            mc.displayGuiScreen(MainMenuScreen(factory.newGuiMainMenu()))
            init = true
        }
    }

    @EventHandler
    fun onGuiScreenChange(e: GuiScreenChangeEvent) {
        if ((e.screen == null && mc.theWorld == null) || e.screen is VibrantGuiMainMenu) {
            e.isCancelled = true
            mc.displayGuiScreen(MainMenuScreen(factory.newGuiMainMenu()))
        }
    }
}