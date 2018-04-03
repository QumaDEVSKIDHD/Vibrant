package net.cydhra.vibrant.modules.movement

import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard

/**
 *
 */
class FlyModule : Module("Fly", DefaultCategories.MOVEMENT, Keyboard.KEY_F) {

    override fun onDisable() {
        // this should not be done, but it is the simplest way
        mc.thePlayer?.isFlying = false
    }

    fun doRequestResources() {
//        if (VibrantClient.minecraft.thePlayer != null) {
//            if (!VibrantClient.minecraft.thePlayer!!.onGround) {
//                GameResourceManager.requestGameResource(
//                        FlyingResource,
//                        FlyingResource.FlyingResourceState(isFlying = true, isAllowedFlying = true),
//                        ResourceRequestPriority.movement,
//                        ResourceChannel.Side.CLIENT
//                )
//            }
//        }
    }
}