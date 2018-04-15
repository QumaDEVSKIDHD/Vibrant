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
        val side: Side) {

    private var andThen: (S) -> Unit = {}

    fun andThen(block: (S) -> Unit): ResourceLock<G, S> {
        this.andThen = block
        return this
    }

    /**
     * Called when the lock is fulfilled
     */
    fun fulfilled(state: S) {
        andThen(state)
    }
}