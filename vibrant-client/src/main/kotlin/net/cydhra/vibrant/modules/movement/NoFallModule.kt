package net.cydhra.vibrant.modules.movement

import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResourceManager
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.NoFallResource
import org.lwjgl.input.Keyboard

class NoFallModule : Module("NoFall", DefaultCategories.MOVEMENT, Keyboard.KEY_N) {

    override fun onEnable() {
        GameResourceManager.lockGameResource(
                NoFallResource,
                {
                    NoFallResource.NoFallResourceState((mc.thePlayer!!.fallDistance > 2.5).also { if (it) mc.thePlayer!!.fallDistance = 0f })
                },
                this,
                ResourceRequestPriority.movement,
                ResourceChannel.Side.SERVER
        )
    }
}