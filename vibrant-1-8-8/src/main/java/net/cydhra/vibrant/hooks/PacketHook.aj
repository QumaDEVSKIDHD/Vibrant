package net.cydhra.vibrant.hooks;

import net.cydhra.eventsystem.EventManager;
import net.cydhra.vibrant.api.network.VibrantPacket;
import net.cydhra.vibrant.aspects.AbstractPacketHook;
import net.cydhra.vibrant.events.network.PacketEvent;

/**
 *
 */
public aspect PacketHook extends AbstractPacketHook {

    @Override
    public pointcut sendPacket(VibrantPacket packet):
            call(void net.minecraft.client.network.NetHandlerPlayClient.addToSendQueue(Packet))
                    && args(packet);

    @Override
    public VibrantPacket sendPacket(VibrantPacket packet) {
        PacketEvent packetEvent = new PacketEvent(PacketEvent.EventType.SEND, packet);
        EventManager.callEvent(packetEvent);

        if (!packetEvent.isCancelled()) {
            return packetEvent.getPacket();
        }

        return null;
    }
}
