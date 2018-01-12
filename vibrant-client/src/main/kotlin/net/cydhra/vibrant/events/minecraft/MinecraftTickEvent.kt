package net.cydhra.vibrant.events.minecraft

import net.cydhra.eventsystem.events.Event

/**
 * Called once per game tick. Not cancellable.
 */
class MinecraftTickEvent : Event() {

    override fun setCancelled(cancelled: Boolean) {
        if (cancelled)
            throw UnsupportedOperationException("Game tick cannot be cancelled")
    }
}
