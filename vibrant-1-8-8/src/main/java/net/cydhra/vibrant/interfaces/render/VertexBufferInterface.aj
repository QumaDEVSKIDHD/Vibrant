package net.cydhra.vibrant.interfaces.render;

public aspect VertexBufferInterface {

    declare parents:net.minecraft.client.renderer.WorldRenderer implements net.cydhra.vibrant.api.render.VibrantVertexBuffer;
}
