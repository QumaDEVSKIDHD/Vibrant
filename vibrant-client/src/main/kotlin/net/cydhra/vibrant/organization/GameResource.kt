package net.cydhra.vibrant.organization

import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.locks.Side
import net.cydhra.vibrant.organization.resources.GameResourceState

/**
 *
 */
abstract class GameResource<S : GameResourceState> {
    private val stateBySide: MutableMap<Side, S> = mutableMapOf()

    protected val mc
        get() = VibrantClient.minecraft

    abstract val channel: ResourceChannel<GameResource<S>, S>

    fun updateState(side: Side, state: S) {
        if (side == Side.BOTH)
            throw IllegalArgumentException("Cannot update both side's state at the same time")

        if (stateBySide[side] != state) {
            stateBySide[side] = state
            this.onUpdateState(side, state)
        }
    }

    protected abstract fun onUpdateState(side: Side, state: S)
}