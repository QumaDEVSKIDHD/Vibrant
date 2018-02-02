package net.cydhra.vibrant.interfaces.network;

/**
 *
 */
public aspect PacketPlayerLookInterface {
    
    declare parents:(net.minecraft.network.play.client.C05PacketPlayerLook)implements net.cydhra.vibrant.api.network
            .VibrantPlayerLookPacket;
}
