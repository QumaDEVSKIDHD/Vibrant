package net.cydhra.vibrant.events.network

import net.cydhra.eventsystem.events.Event
import net.cydhra.eventsystem.events.Typed
import net.cydhra.vibrant.api.network.VibrantPacket

/**
 *
 */
class PacketEvent(private val type: EventType, var packet: VibrantPacket) : Event(), Typed {

    override fun getType(): Int {
        return type.ordinal
    }

    enum class EventType {
        SEND, RECEIVE
    }
}