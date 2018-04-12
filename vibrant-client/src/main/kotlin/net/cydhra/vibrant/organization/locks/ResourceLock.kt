package net.cydhra.vibrant.organization.locks

import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.GameResourceState

class ResourceLock<G : GameResource<S>, S : GameResourceState>(
        val module: Module,
        val resource: GameResource<S>,
        val isActive: () -> Boolean,
        val priorityGenerator: () -> ResourceRequestPriority,
        val stateGenerator: () -> S,
        val side: Side)