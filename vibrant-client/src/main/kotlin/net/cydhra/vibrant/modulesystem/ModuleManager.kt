package net.cydhra.vibrant.modulesystem

import net.cydhra.eventsystem.EventManager
import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.events.minecraft.KeyboardEvent
import net.cydhra.vibrant.modules.movement.FlyModule
import net.cydhra.vibrant.modules.render.MinimapModule
import net.cydhra.vibrant.modules.system.HudModule
import net.cydhra.vibrant.modulesystem.ModuleManager.init
import net.cydhra.vibrant.modulesystem.ModuleManager.onKeyEvent

/**
 * A registry for [Module] implementations. They will get registered in [init] and are enabled when [onKeyEvent] handles a fitting [KeyboardEvent]
 */
object ModuleManager {

    private val registeredModules = mutableListOf<Module>()

    val modules: List<Module> = registeredModules

    /**
     * Initialize manager. Registere [KeyboardEvent] handler and register all modules.
     */
    fun init() {
        EventManager.registerListeners(this)

        this.registerModule(FlyModule())
        this.registerModule(MinimapModule())
        this.registerModule(HudModule())
    }

    /**
     * Register a module
     * @param module module to be registered
     */
    fun registerModule(module: Module) {
        this.registeredModules.add(module)
    }

    @EventHandler
    fun onKeyEvent(e: KeyboardEvent) {
        if (e.type == KeyboardEvent.KeyboardEventType.RELEASE) {
            this.modules.filter { it.keycode == e.keycode }.forEach(Module::toggle)
        }
    }
}