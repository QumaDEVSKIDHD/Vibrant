package net.cydhra.vibrant.events.minecraft

import net.cydhra.eventsystem.events.Event
import net.cydhra.eventsystem.events.Typed

/**
 * Called whenever a key-state on the keyboard changes. Can be cancelled.
 */
class KeyboardEvent(val type: KeyboardEventType, var keycode: Int) : Event(), Typed {

    override fun getType() = type.ordinal;

    enum class KeyboardEventType {
        /**
         * The given key on keyboard is being pressed
         */
        PRESS,

        /**
         * The given key on keyboard is being released
         */
        RELEASE
    }
}