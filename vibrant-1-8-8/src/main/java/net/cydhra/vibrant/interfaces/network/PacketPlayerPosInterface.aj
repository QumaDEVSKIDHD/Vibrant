package net.cydhra.vibrant.interfaces.network;

/**
 *
 */
public aspect PacketPlayerPosInterface {
    
    declare parents:(net.minecraft.network.play.client.C04PacketPlayerPosition)implements net.cydhra.vibrant.api.network
            .VibrantPlayerPosPacket;
}
