package net.cydhra.vibrant.modules.gui

import net.cydhra.vibrant.gui.client.VibrantClickGui
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard

/**
 *
 */
class ClickGuiModule : Module("Click GUI", DefaultCategories.SYSTEM, Keyboard.KEY_RSHIFT) {

    private val clickGuiScreen = VibrantClickGui(this)

    override fun onEnable() {
        assert(!mc.isCurrentlyDisplayingScreen)
        mc.displayGuiScreen(clickGuiScreen)
    }
}