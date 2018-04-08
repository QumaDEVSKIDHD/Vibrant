package net.cydhra.vibrant.api.world

interface VibrantBlockPosition {

    val posX: Int
    val posY: Int
    val posZ: Int

    fun add(x: Double, y: Double, z: Double): VibrantBlockPosition

    fun add(x: Int, y: Int, z: Int): VibrantBlockPosition

    fun up(): VibrantBlockPosition

    fun up(count: Int): VibrantBlockPosition

    fun down(): VibrantBlockPosition

    fun down(count: Int): VibrantBlockPosition

    fun north(): VibrantBlockPosition

    fun north(count: Int): VibrantBlockPosition

    fun east(): VibrantBlockPosition

    fun east(count: Int): VibrantBlockPosition

    fun south(): VibrantBlockPosition

    fun south(count: Int): VibrantBlockPosition

    fun west(): VibrantBlockPosition

    fun west(count: Int): VibrantBlockPosition

    fun offsetSide(facing: VibrantBlockFacing): VibrantBlockPosition

    fun offsetSide(facing: VibrantBlockFacing, count: Int): VibrantBlockPosition
}

enum class VibrantBlockFacing(oppositeIndex: Int, horizontalIndex: Int, axisDirection: VibrantAxisDirection, axis: VibrantAxis,
                              offsetVec: Triple<Int, Int, Int>) {
    DOWN(1, -1, VibrantAxisDirection.NEGATIVE, VibrantAxis.Y, Triple(0, -1, 0)),
    UP(0, -1, VibrantAxisDirection.POSITIVE, VibrantAxis.Y, Triple(0, 1, 0)),
    NORTH(3, 2, VibrantAxisDirection.NEGATIVE, VibrantAxis.Z, Triple(0, 0, -1)),
    SOUTH(2, 0, VibrantAxisDirection.POSITIVE, VibrantAxis.Z, Triple(0, 0, 1)),
    WEST(5, 1, VibrantAxisDirection.NEGATIVE, VibrantAxis.X, Triple(-1, 0, 0)),
    EAST(4, 3, VibrantAxisDirection.POSITIVE, VibrantAxis.X, Triple(1, 0, 0));

    enum class VibrantAxis {
        X, Y, Z
    }
    
    enum class VibrantAxisDirection(val directionOffset: Int) {
        NEGATIVE(-1),
        POSITIVE(1)
    }
}