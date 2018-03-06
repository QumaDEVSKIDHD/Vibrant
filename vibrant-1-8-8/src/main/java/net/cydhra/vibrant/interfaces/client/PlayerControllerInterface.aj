package net.cydhra.vibrant.interfaces.client;

import net.cydhra.vibrant.api.entity.VibrantEntity;

public aspect PlayerControllerInterface {

    declare parents:(net.minecraft.client.multiplayer.PlayerControllerMP)implements net.cydhra.vibrant.api.client.VibrantPlayerController;

    public void net.minecraft.client.multiplayer.PlayerControllerMP.attackEntity(VibrantEntity entity) {
        this.attackEntity(net.minecraft.client.Minecraft.getMinecraft().thePlayer, (net.minecraft.entity.Entity) entity);
    }
}
