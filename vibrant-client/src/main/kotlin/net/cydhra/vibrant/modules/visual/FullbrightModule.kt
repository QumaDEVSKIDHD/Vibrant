package net.cydhra.vibrant.modules.visual

import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard

class FullbrightModule : Module("Fullbright", DefaultCategories.VISUAL, Keyboard.KEY_C) {

    override fun onEnable() {
        mc.gameSettings.gammaSetting = 10f
    }

    override fun onDisable() {
        mc.gameSettings.gammaSetting = 1f
    }
}