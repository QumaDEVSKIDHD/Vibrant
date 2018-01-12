@file:Suppress("UNUSED_PARAMETER")

package net.cydhra.vibrant.modules.movement

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.events.minecraft.MinecraftTickEvent
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module

/**
 *
 */
class FlyModule : Module("Fly", DefaultCategories.MOVEMENT) {

    @EventHandler
    fun onTick(e: MinecraftTickEvent) {
        if (VibrantClient.minecraft.thePlayer != null) {
            if (!VibrantClient.minecraft.thePlayer!!.onGround) {
                VibrantClient.minecraft.thePlayer!!.isAllowedFlying = true
                VibrantClient.minecraft.thePlayer!!.isFlying = true
            }
        }
    }
}