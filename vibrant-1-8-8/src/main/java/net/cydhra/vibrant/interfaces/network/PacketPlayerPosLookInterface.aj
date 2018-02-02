package net.cydhra.vibrant.interfaces.network;

/**
 *
 */
public aspect PacketPlayerPosLookInterface {
    
    declare parents:(net.minecraft.network.play.client.C06PacketPlayerPosLook)implements net.cydhra.vibrant.api.network
            .VibrantPlayerPosLookPacket;
}
