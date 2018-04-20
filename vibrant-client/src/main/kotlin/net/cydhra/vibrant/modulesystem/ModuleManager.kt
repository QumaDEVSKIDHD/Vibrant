package net.cydhra.vibrant.modulesystem

import net.cydhra.eventsystem.EventManager
import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.events.minecraft.KeyboardEvent
import net.cydhra.vibrant.modulesystem.ModuleManager.init
import net.cydhra.vibrant.modulesystem.ModuleManager.onKeyEvent

/**
 * A registry for [Module] implementations. They will get registered in [init] and are enabled when [onKeyEvent] handles a fitting [KeyboardEvent]
 */
object ModuleManager {

    private val registeredModuleLoaders = mutableListOf<ModuleLoader>()
    private val registeredModules = mutableListOf<Module>()

    val modules: List<Module> = registeredModules

    /**
     * Initialize manager. Registers [KeyboardEvent] handler and register all modules.
     */
    fun init() {
        EventManager.registerListeners(this)

        this.registeredModuleLoaders.forEach { this.registerModules(it.loadModules()) }

        registeredModules.sortWith(kotlin.Comparator { m1: Module, m2: Module -> m2.displayName.length - m1.displayName.length })
    }

    private fun registerModules(modules: Collection<Module>) {
        this.registeredModules += modules
    }

    /**
     * Register a module
     * @param module module to be registered
     */
    private fun registerModule(module: Module) {
        this.registeredModules += module
    }

    fun registerModuleLoader(moduleLoader: ModuleLoader) {
        this.registeredModuleLoaders += moduleLoader
    }

    @EventHandler
    fun onKeyEvent(e: KeyboardEvent) {
        if (e.type == KeyboardEvent.KeyboardEventType.PRESS) {
            this.modules.filter { it.keycode == e.keycode }.forEach(Module::toggle)
        }
    }
}