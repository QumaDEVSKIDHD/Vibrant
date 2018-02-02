package net.cydhra.vibrant.organization

import net.cydhra.eventsystem.EventManager
import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.eventsystem.listeners.EventType
import net.cydhra.eventsystem.listeners.ListenerPriority
import net.cydhra.vibrant.api.network.VibrantPacket
import net.cydhra.vibrant.events.network.PacketEvent
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.modulesystem.ModuleManager
import net.cydhra.vibrant.organization.channel.IResourceChannel
import net.cydhra.vibrant.organization.channel.ResourceChannel
import net.cydhra.vibrant.organization.priorities.DefaultPriorityComparator
import net.cydhra.vibrant.organization.priorities.ResourceRequestPriority
import net.cydhra.vibrant.organization.resources.FlyingResource
import net.cydhra.vibrant.organization.resources.NoFallResource
import net.cydhra.vibrant.organization.resources.RotationResource
import net.cydhra.vibrant.organization.resources.SprintingResource

/**
 * Manages in-game resources of the player (like for example sprinting). A module can - while the updateResources state - request, require,
 * enforce, disable or otherwise manipulate any resource of the player, that is tracked here. This prevents multiple modules from
 * concurrently setting different player resources to different values thus creating bugs. If a module urgently requires a resource that
 * is currently blocked by another module with lower priority, it can be reassigned to the new requesting module. On the other hand, if
 * a module requires a certain resource, that is disabled at the moment due to urgent work of another module with higher priority, the
 * first requesting module must wait for that resource and cannot perform its work (unless the resource is not mandatory). It is always
 * the module's responsibility to react to missing or suddenly vanishing resources properly.
 */
object GameResourceManager {

    private var canRequestResources: Boolean = false
    private val resources: MutableMap<GameResource<*>, IResourceChannel<in GameResourceState>> = mutableMapOf()

    val resourceRequestPriorityComparator: Comparator<ResourceRequestPriority> = DefaultPriorityComparator()

    private val packetPolicies: MutableList<(VibrantPacket) -> VibrantPacket> = mutableListOf()

    init {
        EventManager.registerListeners(this)

        registerResource(SprintingResource)
        registerResource(FlyingResource)
        registerResource(RotationResource)
        registerResource(NoFallResource)
    }

    @Suppress("UNCHECKED_CAST")
    private fun <S : GameResourceState> registerResource(resource: GameResource<S>) {
        resource.register()(this.resources as MutableMap<GameResource<*>, in IResourceChannel<*>>)
    }

    /**
     * Called pre-tick: Calls all modules and requests for resource updates
     */
    fun updateResources() {
        canRequestResources = true
        ModuleManager.modules.forEach(Module::requestResources)
        canRequestResources = false

        this.resources.values.forEach(IResourceChannel<*>::evaluateNewState)
    }

    /**
     * Request a resource by providing a state the resource shall accept alongside a priority and a side for the channel
     *
     * @param resource the requested game resource
     * @param state the state that shall be applied to the requested resource
     * @param priority the priority which the request has
     * @param side on which side (server or client) the resource shall be updated
     *
     * @throws IllegalStateException if it is currently not the right time to request resources
     * @see Module.requestResources
     */
    fun <T : GameResourceState> requestGameResource(
            resource: GameResource<T>, state: T, priority: ResourceRequestPriority, side: ResourceChannel.Side = ResourceChannel.Side.BOTH) {
        if (canRequestResources)
            this.resources[resource]!!.appendState(state, priority, side)
        else
            throw IllegalStateException("Cannot request game resources outside of ${Module::requestResources.name}")
    }

    /**
     * Add a lock on a game resource
     * @see [IResourceChannel.addLock]
     */
    fun <S : GameResourceState> lockGameResource(
            resource: GameResource<S>, state: S, module: Module, priority: ResourceRequestPriority, side: ResourceChannel.Side) {
        this.resources[resource]!!.addLock(state, module, priority, side)
    }

    /**
     * Remove a lock on the given resource. If there is no lock, nothing happens.
     */
    fun removeLock(module: Module, resource: GameResource<*>) {
        this.resources[resource]!!.removeLock(module)
    }

    /**
     * Remove all resource locks of the module
     */
    fun removeAllLocks(module: Module) {
        this.resources.values.forEach { it.removeLock(module) }
    }

    @Suppress("UNCHECKED_CAST")
    fun <S : GameResourceState> getCurrentState(gameResource: GameResource<S>, side: ResourceChannel.Side): S {
        return this.resources[gameResource]!!.getCurrentState(side) as S
    }

    fun registerPacketManipulation(policy: (VibrantPacket) -> VibrantPacket) {
        packetPolicies.add(policy)
    }

    @EventHandler(priority = ListenerPriority.HIGH)
    @EventType(0 /*RECV*/)
    fun onPacketReceive(e: PacketEvent) {
        this.packetPolicies.forEach {
            e.packet = it.invoke(e.packet)
        }
    }
}
