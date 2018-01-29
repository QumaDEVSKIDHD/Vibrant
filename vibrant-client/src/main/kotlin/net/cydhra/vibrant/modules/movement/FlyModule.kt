@file:Suppress("UNUSED_PARAMETER")

package net.cydhra.vibrant.modules.movement

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.events.minecraft.MinecraftTickEvent
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard

/**
 *
 */
class FlyModule : Module("Fly", DefaultCategories.MOVEMENT, Keyboard.KEY_F) {

    override fun onDisable() {
        VibrantClient.minecraft.thePlayer?.isFlying = false
    }

    @EventHandler
    fun onTick(e: MinecraftTickEvent) {
        if (VibrantClient.minecraft.thePlayer != null) {
            val grounded = !VibrantClient.minecraft.thePlayer!!.onGround

            VibrantClient.minecraft.thePlayer!!.isAllowedFlying = grounded
            VibrantClient.minecraft.thePlayer!!.isFlying = grounded
        }
    }
}