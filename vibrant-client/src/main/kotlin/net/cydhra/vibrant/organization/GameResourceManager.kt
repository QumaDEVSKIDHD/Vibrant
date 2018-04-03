package net.cydhra.vibrant.organization

import net.cydhra.eventsystem.EventManager
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.locks.ResourceLock
import net.cydhra.vibrant.organization.resources.FlyingResource

/**
 * Manages in-game resources of the player (like for example sprinting). A module can - while the updateResources state - request, require,
 * enforce, disable or otherwise manipulate any resource of the player, that is tracked here. This prevents multiple modules from
 * concurrently setting different player resources to different values thus creating bugs. If a module urgently requires a resource that
 * is currently blocked by another module with lower priority, it can be reassigned to the new requesting module. On the other hand, if
 * a module requires a certain resource, that is disabled at the moment due to urgent work of another module with higher priority, the
 * first requesting module must wait for that resource and cannot perform its work (unless the resource is not mandatory). It is always
 * the module's responsibility to react to missing or suddenly vanishing resources properly.
 */
object GameResourceManager {

    private val resourceChannels: MutableList<ResourceChannel> = mutableListOf()

    init {
        EventManager.registerListeners(this)

        this.registerResourceChannel(FlyingResource)
    }

    fun registerResourceChannel(resource: GameResource<*>) {
        resource.register()
    }

    fun lockResource(lock: ResourceLock<*>) {
        TODO()
    }
}
