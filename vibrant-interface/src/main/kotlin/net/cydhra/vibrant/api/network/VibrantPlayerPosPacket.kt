package net.cydhra.vibrant.api.network

/**
 *
 */
interface VibrantPlayerPosPacket : VibrantPlayerPacket {
    var posX: Double
    var posY: Double
    var posZ: Double
}