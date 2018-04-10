package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.locks.ResourceLock
import net.cydhra.vibrant.organization.resources.GameResourceState

class ResourceChannelDecorator<G : GameResource<S>, S : GameResourceState> : ResourceChannel<G, S> {
    override fun registerLock(lock: ResourceLock<G, S>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun releaseLock(lock: ResourceLock<G, S>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun releaseAllLocks(module: Module) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun evaluateState(locks: List<ResourceLock<G, S>>): S {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}