package net.cydhra.vibrant.organization.locks

import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.resources.GameResourceState

class ResourceLock<G : GameResource<S>, out S : GameResourceState>(val module: Module, resource: GameResource<S>, state: S) {

}