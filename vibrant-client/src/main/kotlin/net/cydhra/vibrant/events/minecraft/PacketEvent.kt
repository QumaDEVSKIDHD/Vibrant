package net.cydhra.vibrant.events.minecraft

import net.cydhra.eventsystem.events.Event
import net.cydhra.eventsystem.events.Typed
import net.cydhra.vibrant.api.network.VibrantPacket

/**
 *
 */
class PacketEvent(private val type: EventType, var packet: VibrantPacket) : Event(), Typed {

    override fun getType(): Int {
        return when (type) {
            EventType.SEND -> 0
            EventType.RECEIVE -> 1
        }
    }

    enum class EventType {
        SEND, RECEIVE
    }
}