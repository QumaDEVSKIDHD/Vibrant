package net.cydhra.vibrant.organization.channel

/**
 * Configure and then create an instance of [IResourceChannel]
 */
class ChannelBuilder<R : Any>(private var channel: IResourceChannel<R>) {

    companion object {
        fun <R : Any> newBuilder(monitor: () -> R): ChannelBuilder<R> {
            return ChannelBuilder(ResourceChannel(monitor))
        }
    }

    /**
     * Creates a channel where client and server side cannot diverge
     */
    fun convergent() {
        this.channel = ConvergentResourceChannel(this.channel)
    }

    /**
     * @return the built [IResourceChannel]
     */
    fun create(): IResourceChannel<R> {
        return this.channel
    }
}