package net.cydhra.vibrant.api.render

import java.nio.ByteBuffer

interface VibrantVertexBuffer {

    val byteBuffer: ByteBuffer
    val vertexCount: Int
    val drawMode: Int
    val vertexFormat: VibrantVertexFormat

    fun markDirty()

    fun beginDrawing(drawMode: Int, vertexFormat: VibrantVertexFormat)

    fun reset()

    fun setTranslation(x: Double, y: Double, z: Double)

    fun finishDrawing()

    fun putColor4(argb: Int)
}