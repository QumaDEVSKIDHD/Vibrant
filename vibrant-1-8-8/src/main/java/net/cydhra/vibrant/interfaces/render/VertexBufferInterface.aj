package net.cydhra.vibrant.interfaces.render;

import net.cydhra.vibrant.api.render.VibrantVertexFormat;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.VertexFormat;

public aspect VertexBufferInterface {

    declare parents:net.minecraft.client.renderer.WorldRenderer implements net.cydhra.vibrant.api.render.VibrantVertexBuffer;

    public void WorldRenderer.beginDrawing(int drawMode, VibrantVertexFormat vertexFormat) {
        this.begin(drawMode, (VertexFormat) vertexFormat);
    }
}
