package net.cydhra.vibrant.organization

import net.cydhra.eventsystem.EventManager
import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.events.minecraft.MinecraftTickEvent
import net.cydhra.vibrant.organization.priorities.DefaultPriorityComparator
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority

/**
 * Manages in-game resources of the player (like for example sprinting). A module can - at any time - request, require, enforce, disable
 * or otherwise manipulate any resource of the player, that is tracked here. This prevents multiple modules from concurrently setting
 * different player resources to different values thus creating bugs. If a module urgently requires a resource that is currently blocked
 * by another module with lower priority, it can be reassigned to the new requesting module. On the other hand, if a module requires a
 * certain resource, that is disabled at the moment due to urgent work of another module with higher priority, the first requesting
 * module must wait for that resource and cannot perform its work (unless the resource is not mandatory). It is always the module's
 * responsibility to react to missing or suddenly vanishing resources properly.
 */
object GameResourceManager {

    private val resources: MutableList<GameResource> = mutableListOf()

    val resourceRequestPriorityComparator: Comparator<ResourceRequestPriority> = DefaultPriorityComparator()

    init {
        EventManager.registerListeners(this)
    }

    /**
     * Update the resources' state by calling [GameResource.updateResourceState]
     */
    @EventHandler
    fun onTick(e: MinecraftTickEvent) {
        resources.forEach(GameResource::updateResourceState)
    }
}