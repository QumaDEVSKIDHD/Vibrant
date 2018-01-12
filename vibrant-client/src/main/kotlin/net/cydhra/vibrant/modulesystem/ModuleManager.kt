package net.cydhra.vibrant.modulesystem

import net.cydhra.vibrant.modules.movement.FlyModule

/**
 *
 */
object ModuleManager {

    private val registeredModules = mutableListOf<Module>()

    val modules: List<Module> = registeredModules

    fun init() {
        this.registerModule(FlyModule())
    }

    fun registerModule(module: Module) {
        this.registeredModules.add(module)
    }
}