package net.cydhra.vibrant.hooks;

import net.cydhra.vibrant.api.network.VibrantPacket;
import net.cydhra.vibrant.aspects.AbstractPacketHook;

/**
 *
 */
public aspect PacketHook extends AbstractPacketHook {

    @Override
    public pointcut sendPacket(VibrantPacket packet):
            call(void net.minecraft.client.network.NetHandlerPlayClient.addToSendQueue(Packet))
                    && args(packet);
}
