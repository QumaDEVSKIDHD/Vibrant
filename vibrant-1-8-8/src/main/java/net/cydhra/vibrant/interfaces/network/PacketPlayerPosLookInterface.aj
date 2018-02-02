package net.cydhra.vibrant.interfaces.network;

import net.minecraft.network.play.client.C03PacketPlayer;

/**
 *
 */
public privileged aspect PacketPlayerPosLookInterface {
    
    declare parents:(net.minecraft.network.play.client.C03PacketPlayer$C06PacketPlayerPosLook)implements net.cydhra.vibrant.api.network
            .VibrantPlayerPosLookPacket;
    
    public void C03PacketPlayer.C06PacketPlayerPosLook.setPosX(double posX) { this.x = posX; }
    
    public void C03PacketPlayer.C06PacketPlayerPosLook.setPosY(double posY) { this.y = posY; }
    
    public void C03PacketPlayer.C06PacketPlayerPosLook.setPosZ(double posZ) { this.z = posZ; }
    
    public double C03PacketPlayer.C06PacketPlayerPosLook.getPosX() { return x; }
    
    public double C03PacketPlayer.C06PacketPlayerPosLook.getPosY() { return y; }
    
    public double C03PacketPlayer.C06PacketPlayerPosLook.getPosZ() { return z; }
    
    public void C03PacketPlayer.C06PacketPlayerPosLook.setYaw(float yaw) { this.yaw = yaw; }
    
    public void C03PacketPlayer.C06PacketPlayerPosLook.setPitch(float pitch) { this.pitch = pitch; }
    
    public float C03PacketPlayer.C06PacketPlayerPosLook.getYaw() { return this.yaw; }
    
    public float C03PacketPlayer.C06PacketPlayerPosLook.getPitch() { return this.pitch; }
}
