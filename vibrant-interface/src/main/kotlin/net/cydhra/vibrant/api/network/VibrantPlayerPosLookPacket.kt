package net.cydhra.vibrant.api.network

/**
 *
 */
interface VibrantPlayerPosLookPacket : VibrantPlayerPacket {
    var posX: Double
    var posY: Double
    var posZ: Double

    var yaw: Float
    var pitch: Float
}