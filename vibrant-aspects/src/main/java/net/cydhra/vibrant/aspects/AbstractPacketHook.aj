package net.cydhra.vibrant.aspects;

import net.cydhra.eventsystem.EventManager;
import net.cydhra.vibrant.api.network.VibrantPacket;
import net.cydhra.vibrant.events.network.PacketEvent;

public abstract aspect AbstractPacketHook {

    public abstract pointcut sendPacket(VibrantPacket packet);

    void around(VibrantPacket packet): sendPacket(packet) {
        PacketEvent packetEvent = new PacketEvent(PacketEvent.EventType.SEND, packet);
        EventManager.callEvent(packetEvent);

        if (!packetEvent.isCancelled()) {
            proceed(packetEvent.getPacket());
        }
    }
}
