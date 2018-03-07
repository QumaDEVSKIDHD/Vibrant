package net.cydhra.vibrant.util.math

/**
 *
 */
class SmartMover2D(x: Double, y: Double) {
    private var components: Pair<Double, Double> = Pair(x, y)
    private var prevComponentVector: Pair<Double, Double> = components

    /**
     * Update the current vector state and start interpolating with the previously set state.
     */
    fun update(x: Double, y: Double) {
        prevComponentVector = this.components
        this.components = Pair(x, y)
    }

    /**
     * The state used for interpolation is reset to the latest known state (so interpolation will always return the latest state until
     * [update] is called again)
     */
    fun reset() {
        this.prevComponentVector = components
    }

    /**
     * Interpolate between the current and the previous state using the [partialTicks] as the time scale
     *
     * @param partialTicks a linear value between 0 and 1 indicating the time until a game tick has fully passed
     */
    fun interpolate(partialTicks: Float): Pair<Double, Double> {
        val (prevX, prevY) = prevComponentVector
        val (currX, currY) = components
        return Pair(prevX + (currX - prevX) * partialTicks, prevY + (currY - prevY) * partialTicks)
    }
}

class SmartMover3D(x: Double, y: Double, z: Double) {
    private var components: Triple<Double, Double, Double> = Triple(x, y, z)
    private var prevComponentVector: Triple<Double, Double, Double> = components

    /**
     * Update the current vector state and start interpolating with the previously set state.
     */
    fun update(x: Double, y: Double, z: Double) {
        prevComponentVector = this.components
        this.components = Triple(x, y, z)
    }

    /**
     * The state used for interpolation is reset to the latest known state (so interpolation will always return the latest state until
     * [update] is called again)
     */
    fun reset() {
        this.prevComponentVector = components
    }

    /**
     * Interpolate between the current and the previous state using the [partialTicks] as the time scale
     *
     * @param partialTicks a linear value between 0 and 1 indicating the time until a game tick has fully passed
     */
    fun interpolate(partialTicks: Float): Triple<Double, Double, Double> {
        val (prevX, prevY, prevZ) = prevComponentVector
        val (currX, currY, currZ) = components
        return Triple(
                prevX + (currX - prevX) * partialTicks,
                prevY + (currY - prevY) * partialTicks,
                prevZ + (currZ - prevZ) * partialTicks)
    }
}