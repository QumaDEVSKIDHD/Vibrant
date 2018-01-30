package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.organization.GameResourceState

/**
 * Configure and then create an instance of [IResourceChannel]
 */
class ChannelBuilder<R : GameResourceState>(private var channel: IResourceChannel<R>) {

    companion object {
        fun <R : GameResourceState> newBuilder(monitor: () -> R, updater: (ResourceChannel.Side, R) -> Unit): ChannelBuilder<R> {
            return ChannelBuilder(ResourceChannel(monitor, updater))
        }
    }

    /**
     * Creates a channel where client and server side cannot diverge
     */
    fun convergent(): ChannelBuilder<R> {
        this.channel = ConvergentResourceChannel(this.channel)
        return this
    }

    /**
     * @return the built [IResourceChannel]
     */
    fun create(): IResourceChannel<R> {
        return this.channel
    }
}