package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.resources.GameResourceState

class ChannelBuilder<G : GameResource<S>, S : GameResourceState>(resource: G, monitor: () -> S) {

    var channel: ResourceChannel<G, S> = BaseResourceChannel(resource, monitor)

    fun symmetric(): ChannelBuilder<G, S> {
        channel = SymmetricResourceChannel(channel)
        return this
    }

    fun create(): ResourceChannel<G, S> {
        return channel
    }
}