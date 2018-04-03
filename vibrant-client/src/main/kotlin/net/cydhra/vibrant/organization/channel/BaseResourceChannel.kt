package net.cydhra.vibrant.organization.channel

import net.cydhra.vibrant.organization.GameResource
import net.cydhra.vibrant.organization.resources.GameResourceState

class BaseResourceChannel<G : GameResource<S>, S : GameResourceState> : ResourceChannel<G, S> {

}