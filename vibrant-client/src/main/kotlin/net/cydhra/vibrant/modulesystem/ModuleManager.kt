package net.cydhra.vibrant.modulesystem

import net.cydhra.eventsystem.EventManager
import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.events.minecraft.KeyboardEvent
import net.cydhra.vibrant.modules.combat.BowAimbotModule
import net.cydhra.vibrant.modules.combat.Killaura
import net.cydhra.vibrant.modules.gui.ClickGuiModule
import net.cydhra.vibrant.modules.gui.MainMenuModule
import net.cydhra.vibrant.modules.misc.TestModule
import net.cydhra.vibrant.modules.movement.FlyModule
import net.cydhra.vibrant.modules.movement.NoFallModule
import net.cydhra.vibrant.modules.movement.SprintModule
import net.cydhra.vibrant.modules.movement.StepModule
import net.cydhra.vibrant.modules.system.HudModule
import net.cydhra.vibrant.modules.visual.ESPModule
import net.cydhra.vibrant.modules.visual.MinimapModule
import net.cydhra.vibrant.modules.visual.NametagsModule
import net.cydhra.vibrant.modulesystem.ModuleManager.init
import net.cydhra.vibrant.modulesystem.ModuleManager.onKeyEvent

/**
 * A registry for [Module] implementations. They will get registered in [init] and are enabled when [onKeyEvent] handles a fitting [KeyboardEvent]
 */
object ModuleManager {

    private val registeredModules = mutableListOf<Module>()

    val modules: List<Module> = registeredModules

    /**
     * Initialize manager. Registers [KeyboardEvent] handler and register all modules.
     */
    fun init() {
        EventManager.registerListeners(this)

        this.registerModule(FlyModule())
        this.registerModule(MinimapModule())
        this.registerModule(HudModule())
        this.registerModule(ClickGuiModule())
        this.registerModule(SprintModule())
        this.registerModule(NoFallModule())
        this.registerModule(BowAimbotModule())
        this.registerModule(ESPModule())
        this.registerModule(NametagsModule())
        this.registerModule(MainMenuModule())
        this.registerModule(Killaura())
        this.registerModule(TestModule())
        this.registerModule(StepModule())

        registeredModules.sortWith(kotlin.Comparator { m1: Module, m2: Module -> m2.displayName.length - m1.displayName.length })
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
        if (e.type == KeyboardEvent.KeyboardEventType.PRESS) {
            this.modules.filter { it.keycode == e.keycode }.forEach(Module::toggle)
        }
    }
}