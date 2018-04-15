package net.cydhra.vibrant.organization.network

import net.cydhra.vibrant.api.network.VibrantPacket
import kotlin.reflect.KClass

abstract class PacketManipulation<in T : VibrantPacket>(private val packetClass: KClass<T>) {

    /**
     * Apply this packet manipulation if the given [packet] is an instance of the packet class manipulated through this
     * filter
     */
    fun manipulateIfApplicable(packet: VibrantPacket): VibrantPacket {
        if (packetClass.isInstance(packet)) {
            @Suppress("UNCHECKED_CAST")
            return manipulate(packet as T)
        }

        return packet
    }

    /**
     * Manipulation happening through this filter
     *
     * @param packet the packet to alter
     */
    protected abstract fun manipulate(packet: T): VibrantPacket
}