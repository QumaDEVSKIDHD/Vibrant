package net.cydhra.vibrant.api.render

import java.nio.ByteBuffer

interface VibrantVertexBuffer {

    val byteBuffer: ByteBuffer
    val vertexCount: Int
    val drawMode: Int
    val vertexFormat: VibrantVertexFormat

    fun markDirty()

    fun beginDrawing(drawMode: Int, vertexFormat: VibrantVertexFormat)

    fun tex(x: Double, y: Double): VibrantVertexBuffer

    fun lightmap(x: Int, y: Int): VibrantVertexBuffer

    fun putPosition(x: Double, y: Double, z: Double)

    fun reset()

    fun setTranslation(x: Double, y: Double, z: Double)

    fun finishDrawing()

    fun putColor4(argb: Int)
}