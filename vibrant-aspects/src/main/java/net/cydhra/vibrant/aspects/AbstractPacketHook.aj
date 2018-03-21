package net.cydhra.vibrant.aspects;

import net.cydhra.vibrant.api.network.VibrantPacket;

public abstract aspect AbstractPacketHook {

    public abstract pointcut sendPacket(VibrantPacket packet);

    void around(VibrantPacket packet): sendPacket(packet) {
        VibrantPacket alteredPacket = this.sendPacket(packet);
        proceed(alteredPacket);
    }

    /**
     * Process the packet to send
     *
     * @return a packet, that should be used to proceed handling or null if the handling shall be cancelled
     */
    public abstract VibrantPacket sendPacket(VibrantPacket packet);
}
