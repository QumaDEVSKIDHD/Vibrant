package net.cydhra.vibrant.api.network

/**
 *
 */
interface VibrantPlayerLookPacket : VibrantPlayerPacket {
    var yaw: Float
    var pitch: Float
}