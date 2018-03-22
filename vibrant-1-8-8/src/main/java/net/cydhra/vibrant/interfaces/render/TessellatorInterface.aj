package net.cydhra.vibrant.interfaces.render;

public aspect TessellatorInterface {

    declare parents:net.minecraft.client.renderer.Tessellator implements net.cydhra.vibrant.api.render.VibrantTessellator;
}
