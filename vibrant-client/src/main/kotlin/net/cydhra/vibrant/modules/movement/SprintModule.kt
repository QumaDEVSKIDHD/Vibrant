package net.cydhra.vibrant.modules.movement

import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import org.lwjgl.input.Keyboard

/**
 *
 */
class SprintModule : Module("Sprint", DefaultCategories.MOVEMENT, Keyboard.KEY_G) {

    fun doRequestResources() {
//        if (mc.thePlayer?.isCollidedHorizontally == false && mc.thePlayer!!.movementInput.moveForward > 0 && mc.thePlayer!!.onGround) {
//            GameResourceManager.requestGameResource(
//                    SprintingResource,
//                    SprintingResource.SprintResourceState(isSprinting = true),
//                    ResourceRequestPriority.speed) // a speed hack might declare it as movement to override this module
//        }
    }
}