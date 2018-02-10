package net.cydhra.vibrant.gui.util

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.IntBuffer

/**
 *
 */
object AllocationHelper {
    /**
     * Creates and returns a direct byte buffer with the specified capacity. Applies native ordering to speed up access.
     */
    fun createDirectByteBuffer(capacity: Int): ByteBuffer {
        return ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder())
    }

    /**
     * Creates and returns a direct int buffer with the specified capacity. Applies native ordering to speed up access.
     */
    fun createDirectIntBuffer(capacity: Int): IntBuffer {
        return createDirectByteBuffer(capacity shl 2).asIntBuffer()
    }

    /**
     * Creates and returns a direct float buffer with the specified capacity. Applies native ordering to speed up
     * access.
     */
    fun createDirectFloatBuffer(capacity: Int): FloatBuffer {
        return createDirectByteBuffer(capacity shl 2).asFloatBuffer()
    }
}