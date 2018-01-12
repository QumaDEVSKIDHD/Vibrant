package net.cydhra.vibrant.modulesystem

/**
 *
 */
object ModuleManager {

    private val registeredModules = mutableListOf<Module>()

    val modules: List<Module> = registeredModules

    fun init() {

    }

    fun registerModule(module: Module) {
        this.registeredModules.add(module)
    }
}