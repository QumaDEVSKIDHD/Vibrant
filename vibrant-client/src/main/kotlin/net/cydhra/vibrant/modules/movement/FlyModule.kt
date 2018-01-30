@file:Suppress("UNUSED_PARAMETER")

package net.cydhra.vibrant.modules.movement

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.FlyingResource
import org.lwjgl.input.Keyboard

/**
 *
 */
class FlyModule : Module("Fly", DefaultCategories.MOVEMENT, Keyboard.KEY_F) {

    override fun onDisable() {
        // this should not be done, but it is the simplest way
        mc.thePlayer?.isFlying = false
    }

    override fun doRequestResources() {
        if (VibrantClient.minecraft.thePlayer != null) {
            if (!VibrantClient.minecraft.thePlayer!!.onGround) {
                GameResourceManager.requestGameResource(
                        FlyingResource,
                        FlyingResource.FlyingResourceState(true, true),
                        ResourceRequestPriority.movement,
                        ResourceChannel.Side.CLIENT
                )
            }
        }
    }
}