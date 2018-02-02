package net.cydhra.vibrant.hooks;

import net.cydhra.eventsystem.EventManager;
import net.cydhra.vibrant.api.network.VibrantPacket;
import net.cydhra.vibrant.events.network.PacketEvent;
import net.minecraft.network.Packet;

/**
 *
 */
public aspect PacketHook {
    
    pointcut sendPacket(Packet packet):
            call(void net.minecraft.client.network.NetHandlerPlayClient.addToSendQueue(Packet))
                    && args(packet);
    
    void around(Packet packet): sendPacket(packet) {
        PacketEvent packetEvent = new PacketEvent(PacketEvent.EventType.SEND, (VibrantPacket) packet);
        EventManager.callEvent(packetEvent);
        
        if (!packetEvent.isCancelled()) {
            proceed((Packet) packetEvent.getPacket());
        }
    }
}
