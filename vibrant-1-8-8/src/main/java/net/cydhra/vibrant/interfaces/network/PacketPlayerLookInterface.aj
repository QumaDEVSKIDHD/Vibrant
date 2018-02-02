package net.cydhra.vibrant.interfaces.network;

import net.minecraft.network.play.client.C03PacketPlayer;

/**
 *
 */
public privileged aspect PacketPlayerLookInterface {
    
    declare parents:(net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook)implements net.cydhra.vibrant.api.network
            .VibrantPlayerLookPacket;
    
    public void C03PacketPlayer.C05PacketPlayerLook.setYaw(float yaw) { this.yaw = yaw; }
    
    public void C03PacketPlayer.C05PacketPlayerLook.setPitch(float pitch) { this.pitch = pitch; }
    
    public float C03PacketPlayer.C05PacketPlayerLook.getYaw() { return this.yaw; }
    
    public float C03PacketPlayer.C05PacketPlayerLook.getPitch() { return this.pitch; }
}
