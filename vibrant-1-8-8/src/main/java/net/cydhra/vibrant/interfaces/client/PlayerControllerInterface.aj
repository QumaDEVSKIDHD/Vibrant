package net.cydhra.vibrant.interfaces.client;

import net.cydhra.vibrant.api.entity.VibrantEntity;
import net.cydhra.vibrant.api.network.VibrantNetHandler;
import net.minecraft.client.multiplayer.PlayerControllerMP;

public privileged aspect PlayerControllerInterface {

    declare parents:(net.minecraft.client.multiplayer.PlayerControllerMP)implements net.cydhra.vibrant.api.client.VibrantPlayerController;

    public void PlayerControllerMP.attackEntity(VibrantEntity entity) {
        this.attackEntity(net.minecraft.client.Minecraft.getMinecraft().thePlayer, (net.minecraft.entity.Entity) entity);
    }

    public VibrantNetHandler PlayerControllerMP.getNetHandler() {
        return (VibrantNetHandler) this.netClientHandler;
    }
}
