package net.cydhra.vibrant.modules.movement

import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.SprintingResource

/**
 *
 */
class SprintModule : Module("Sprint", DefaultCategories.MOVEMENT) {

    override fun doRequestResources() {
        if (mc.thePlayer?.isCollidedHorizontally == false && mc.thePlayer!!.movementInput.moveForward > 0 && mc.thePlayer!!.onGround) {
            GameResourceManager.requestGameResource(
                    SprintingResource,
                    SprintingResource.SprintResourceState(isSprinting = true),
                    ResourceRequestPriority.speed) // a speed hack might declare it as movement to override this module
        }
    }
}