package net.cydhra.vibrant.modules.movement

import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.OnGroundResource
import org.lwjgl.input.Keyboard

class NoFallModule : Module("NoFall", DefaultCategories.MOVEMENT, Keyboard.KEY_N) {

    override fun onEnable() {
        GameResourceManager.lockGameResource(
                OnGroundResource,
                {
                    OnGroundResource.OnGroundResourceState((mc.thePlayer!!.fallDistance > 2.5).also { if (it) mc.thePlayer!!.fallDistance = 0f })
                },
                this,
                ResourceRequestPriority.movement,
                ResourceChannel.Side.SERVER
        )
    }
}