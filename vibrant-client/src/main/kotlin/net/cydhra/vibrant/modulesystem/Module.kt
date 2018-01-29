package net.cydhra.vibrant.modulesystem

import net.cydhra.eventsystem.EventManager
import net.cydhra.vibrant.VibrantClient

/**
 * A client module (e.g. a hack). Modules are handled by a [ModuleManager] and therefore must be saved at any position covered by a
 * [ModuleLoader]. Every module should only be instantiated once, though there is technically no restriction, that would prohibit
 * multi-instantiation. Therefore, every module must be written as if there could be multiple instances of it.
 */
abstract class Module(val name: String,
                      val category: ModuleCategory = DefaultCategories.MISCELLANEOUS,
                      var keycode: Int = 0) {

    open var isEnabled = false
        set(enabled) {
            if (field != enabled) {
                if (enabled) {
                    EventManager.registerListeners(this)
                    this.onEnable()
                } else {
                    EventManager.unregisterListeners(this)
                    this.onDisable()
                }
            }

            field = enabled
        }

    val mc
        get() = VibrantClient.minecraft


    /**
     * @return the name that shall be displayed in-game
     */
    open val displayName: String
        get() = this.name

    /**
     * Toggle the module on or off
     */
    fun toggle() {
        this.isEnabled = !isEnabled
    }

    /**
     * Called when the module is disabled
     */
    protected open fun onDisable() {

    }

    /**
     * Called when the module is enabled
     */
    protected open fun onEnable() {

    }

    /**
     * This method is called once per tick before the tick event is called. Every module can overwrite this method to request or
     * manipulate resources of the GameResourceManager.
     */
    open fun requestResources() {

    }
}