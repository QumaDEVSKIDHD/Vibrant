package net.cydhra.vibrant.modules.movement

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.locks.ResourceLock
import net.cydhra.vibrant.organization.locks.Side
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.FlyingResource
import org.lwjgl.input.Keyboard

/**
 *
 */
class FlyModule : Module("Fly", DefaultCategories.MOVEMENT, Keyboard.KEY_F) {

    override fun onEnable() {
        GameResourceManager.lockResource(
                ResourceLock(
                        this,
                        FlyingResource,
                        { !VibrantClient.minecraft.thePlayer!!.onGround },
                        { ResourceRequestPriority.movement },
                        { FlyingResource.FlyingResourceState(isFlying = true, isAllowedFlying = true) },
                        Side.CLIENT
                )
        )
    }

    override fun onDisable() {
        // this should not be done, but it is the simplest way
        mc.thePlayer?.isFlying = false
    }
}