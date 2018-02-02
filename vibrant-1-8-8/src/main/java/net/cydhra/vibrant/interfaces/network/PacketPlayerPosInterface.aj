package net.cydhra.vibrant.interfaces.network;

import net.minecraft.network.play.client.C03PacketPlayer;

/**
 *
 */
public privileged aspect PacketPlayerPosInterface {
    
    declare parents:(net.minecraft.network.play.client.C03PacketPlayer$C04PacketPlayerPosition)implements net.cydhra.vibrant.api.network
            .VibrantPlayerPosPacket;
    
    public void C03PacketPlayer.C04PacketPlayerPosition.setPosX(double posX) { this.x = posX; }
    
    public void C03PacketPlayer.C04PacketPlayerPosition.setPosY(double posY) { this.y = posY; }
    
    public void C03PacketPlayer.C04PacketPlayerPosition.setPosZ(double posZ) { this.z = posZ; }
    
    public double C03PacketPlayer.C04PacketPlayerPosition.getPosX() { return x; }
    
    public double C03PacketPlayer.C04PacketPlayerPosition.getPosY() { return y; }
    
    public double C03PacketPlayer.C04PacketPlayerPosition.getPosZ() { return z; }
    
}
