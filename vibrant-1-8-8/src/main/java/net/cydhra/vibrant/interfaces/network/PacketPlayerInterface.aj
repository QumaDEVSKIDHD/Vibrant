package net.cydhra.vibrant.interfaces.network;

import net.minecraft.network.play.client.C03PacketPlayer;

/**
 *
 */
public privileged aspect PacketPlayerInterface {
    
    declare parents:(net.minecraft.network.play.client.C03PacketPlayer)implements net.cydhra.vibrant.api.network.VibrantPlayerPacket;
    
    public boolean C03PacketPlayer.getOnGround() { return this.onGround; }
    
    public void C03PacketPlayer.setOnGround(boolean onGround) { this.onGround = onGround; }
}
