package net.cydhra.vibrant.interfaces.network;

/**
 *
 */
public aspect PacketInterface {
    
    declare parents:(net.minecraft.network.Packet)implements net.cydhra.vibrant.api.network.VibrantPacket;
}
