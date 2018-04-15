package net.cydhra.vibrant.modules.movement

import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.locks.ResourceLock
import net.cydhra.vibrant.organization.locks.Side
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.SprintingResource
import org.lwjgl.input.Keyboard

/**
 *
 */
class SprintModule : Module("Sprint", DefaultCategories.MOVEMENT, Keyboard.KEY_G) {

    override fun onEnable() {
        GameResourceManager.lockResource(
                ResourceLock(
                        this,
                        SprintingResource,
                        { mc.thePlayer?.isCollidedHorizontally == false && mc.thePlayer!!.movementInput.moveForward > 0 && mc.thePlayer!!.onGround },
                        { ResourceRequestPriority.speed },
                        { SprintingResource.SprintResourceState(isSprinting = true) },
                        Side.CLIENT
                )
        )
    }
}