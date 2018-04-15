package net.cydhra.vibrant.organization.network

import net.cydhra.eventsystem.EventManager
import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.events.network.PacketEvent

object NetworkManager {

    private val packetManipulations: MutableList<PacketManipulation<*>> = mutableListOf()

    init {
        EventManager.registerListeners(this)
    }

    /**
     * Register a manipulation of packets
     */
    fun registerPacketManipulation(packetManipulation: PacketManipulation<*>) {
        packetManipulations += packetManipulation
    }

    fun unregisterPacketManipulation(packetManipulation: PacketManipulation<*>) {
        packetManipulations -= packetManipulation
    }

    @EventHandler
    fun onPacketSend(e: PacketEvent) {
        packetManipulations.forEach { it.manipulateIfApplicable(e.packet) }
    }
}