package net.cydhra.vibrant.interfaces.network;

import net.cydhra.vibrant.api.network.VibrantPacket;
import net.minecraft.client.network.NetHandlerPlayClient;

public privileged aspect NetHandlerInterface {

    declare parents:NetHandlerPlayClient implements net.cydhra.vibrant.api.network.VibrantNetHandler;

    public void NetHandlerPlayClient.sendPacket(VibrantPacket packet) {
        this.netManager.sendPacket((net.minecraft.network.Packet) packet);
    }
}
