package net.cydhra.vibrant.api.render

import java.nio.ByteBuffer

interface VibrantVertexBuffer {

    val byteBuffer: ByteBuffer
    val vertexCount: Int
    val drawMode: Int

    fun markDirty()

//    fun begin(drawMode: Int)

    fun reset()

    fun setTranslation(x: Double, y: Double, z: Double)

    fun finishDrawing()

    fun putColor4(argb: Int)
}