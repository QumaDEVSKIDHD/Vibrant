package net.cydhra.vibrant.modules.movement

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.events.minecraft.MinecraftTickEvent
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard

/**
 * A simple not-bypassing step module
 */
class StepModule : Module("Step", DefaultCategories.MOVEMENT, Keyboard.KEY_NONE) {

    override fun onDisable() {
        mc.thePlayer?.stepHeight = 0.5f
    }

    @Suppress("UNUSED_PARAMETER")
    @EventHandler
    fun onTick(e: MinecraftTickEvent) {
        mc.thePlayer?.stepHeight = 1.1f
    }
}