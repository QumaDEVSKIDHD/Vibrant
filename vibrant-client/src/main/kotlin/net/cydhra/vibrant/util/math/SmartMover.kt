package net.cydhra.vibrant.util.math

import kotlin.math.abs
import kotlin.math.sign

/**
 *
 */
class AngleSmartMover(initialConfiguration: Angle) {
    private var components: Pair<Double, Double> = Pair(initialConfiguration.yaw, initialConfiguration.pitch)
    private var prevComponentVector: Pair<Double, Double> = components

    /**
     * Update the current vector state and start interpolating with the previously set state.
     */
    fun update(newConfiguration: Angle) {
        prevComponentVector = this.components
        this.components = Pair(newConfiguration.yaw, newConfiguration.pitch)
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

        var distance = currX - prevX
        if (abs(distance) > 180) distance = (360 - abs(distance)) * -sign(distance)

        return Pair(prevX + (distance) * partialTicks, prevY + (currY - prevY) * partialTicks)
    }
}