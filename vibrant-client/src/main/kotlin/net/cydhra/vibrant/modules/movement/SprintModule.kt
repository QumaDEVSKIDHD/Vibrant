package net.cydhra.vibrant.modules.movement

import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.SprintingResource
import org.lwjgl.input.Keyboard

/**
 *
 */
class SprintModule : Module("Sprint", DefaultCategories.MOVEMENT, Keyboard.KEY_G) {

    override fun doRequestResources() {
        if (mc.thePlayer?.isCollidedHorizontally == false) {
            GameResourceManager.requestGameResource(
                    SprintingResource,
                    SprintingResource.SprintResourceState(isSprinting = true),
                    ResourceRequestPriority.speed) // a speed hack might declare it as movement to override this module
        }
    }
}