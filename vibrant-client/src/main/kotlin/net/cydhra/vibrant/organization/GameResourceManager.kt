package net.cydhra.vibrant.organization

import net.cydhra.eventsystem.EventManager
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.locks.ResourceLock
import net.cydhra.vibrant.organization.priorities.DefaultPriorityComparator
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.*

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

    private val resources: MutableList<GameResource<*>> = mutableListOf()

    val resourceRequestPriorityComparator: Comparator<ResourceRequestPriority> = DefaultPriorityComparator()

    init {
        EventManager.registerListeners(this)

        resources += FlyingResource
        resources += OnGroundResource
        resources += RotationResource
        resources += SprintingResource
    }

    fun <S : GameResourceState> lockResource(lock: ResourceLock<GameResource<S>, S>) {
        lock.resource.channel.registerLock(lock)
    }

    fun removeAllLocks(module: Module) {
        resources.forEach { it.channel.releaseAllLocks(module) }
    }

    fun updateChannelsTick() {

    }
}
