package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.resources.GameResourceState

open class ResourceChannelDecorator<G : GameResource<S>, S : GameResourceState>(protected val innerChannel: ResourceChannel<G, S>)
    : ResourceChannel<G, S> by innerChannel