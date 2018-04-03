package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.locks.ResourceLock
import net.cydhra.vibrant.organization.resources.GameResourceState

interface ResourceChannel<G : GameResource<S>, S : GameResourceState> {

    fun registerLock(lock: ResourceLock<G, S>)

    fun releaseLock(lock: ResourceLock<G, S>)

    fun releaseAllLocks(module: Module)
}