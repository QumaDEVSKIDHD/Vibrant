package net.cydhra.vibrant.modules.movement

import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.locks.ResourceLock
import net.cydhra.vibrant.organization.locks.Side
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.OnGroundResource
import org.lwjgl.input.Keyboard

class NoFallModule : Module("NoFall", DefaultCategories.MOVEMENT, Keyboard.KEY_N) {

    override fun onEnable() {
        GameResourceManager.lockResource(
                ResourceLock(
                        this,
                        OnGroundResource,
                        { mc.thePlayer!!.fallDistance > 2.5 },
                        { ResourceRequestPriority.defense },
                        { OnGroundResource.OnGroundResourceState(true) },
                        Side.SERVER
                ).andThen {
                    mc.thePlayer!!.fallDistance = 0f
                }
        )
    }
}