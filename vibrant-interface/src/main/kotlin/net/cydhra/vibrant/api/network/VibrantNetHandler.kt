package net.cydhra.vibrant.api.network

interface VibrantNetHandler {

    fun sendPacket(packet: VibrantPacket)
}