package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.locks.ResourceLock
import net.cydhra.vibrant.organization.locks.Side
import net.cydhra.vibrant.organization.resources.GameResourceState

class SymmetricResourceChannel<G : GameResource<S>, S : GameResourceState>(innerChannel: ResourceChannel<G, S>)
    : ResourceChannelDecorator<G, S>(innerChannel) {

    override fun registerLock(lock: ResourceLock<G, S>) {
        super.registerLock(ResourceLock(lock.module, lock.resource, lock.isActive,
                lock.priorityGenerator, lock.stateGenerator, Side.BOTH))
    }
}