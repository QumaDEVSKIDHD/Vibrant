package net.cydhra.vibrant.interfaces.world;

public aspect ChunkInterface {

    declare parents: net.minecraft.world.chunk.Chunk implements net.cydhra.vibrant.api.world.VibrantChunk;
}
