package net.cydhra.vibrant.modulesystem

import net.cydhra.eventsystem.EventManager
import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.events.minecraft.KeyboardEvent
import net.cydhra.vibrant.modules.movement.FlyModule

/**
 *
 */
object ModuleManager {

    private val registeredModules = mutableListOf<Module>()

    val modules: List<Module> = registeredModules

    fun init() {
        EventManager.registerListeners(this)

        this.registerModule(FlyModule())
    }

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