package net.cydhra.vibrant.util.enemy

import net.cydhra.eventsystem.EventManager
import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.entity.VibrantEntityAlike
import net.cydhra.vibrant.api.entity.VibrantEntityLiving
import net.cydhra.vibrant.api.entity.VibrantPlayer
import net.cydhra.vibrant.api.tileentity.VibrantTileEntity
import net.cydhra.vibrant.events.minecraft.MinecraftTickEvent

/**
 *
 */
object EnemyTracker {

    private val trackedEntitiesList: MutableList<ITrackedEntity> = mutableListOf()

    val trackedEntities: List<ITrackedEntity> = trackedEntitiesList

    init {
        EventManager.registerListeners(this)
    }

    @EventHandler
    fun onTick(e: MinecraftTickEvent) {

        val loadedEntities: MutableList<VibrantEntityAlike> = mutableListOf()
        loadedEntities.addAll(VibrantClient.minecraft.theWorld?.getEntityList() ?: return)
        loadedEntities.addAll(VibrantClient.minecraft.theWorld!!.getTileEntityList())

        val trackedEntityIt = trackedEntitiesList.iterator()
        while (trackedEntityIt.hasNext()) {
            val trackedEntity = trackedEntityIt.next()

            if (!loadedEntities.remove(trackedEntity.entity)) {
                trackedEntityIt.remove()
            }
        }

        for (remainingEntity in loadedEntities) {
            if (remainingEntity !== VibrantClient.minecraft.thePlayer)
                trackedEntitiesList.add(classifyEntity(remainingEntity))
        }
    }

    /**
     * @param centerEntity the entity which is used to center the search range
     *
     * @return the closest entity to the given entity or null if really no enemy is around
     */
    fun getClosestEntity(centerEntity: VibrantEntityLiving, type: Class<*> = VibrantPlayer::class.java): ITrackedEntity? {
        return this.trackedEntitiesList
                .filterIsInstance<TrackedEnemyEntity>()
                .filter { type.isAssignableFrom(it.entity.javaClass) }
                .sortedBy { it.entity.getDistanceSq(centerEntity.posX, centerEntity.posY, centerEntity.posZ) }
                .firstOrNull { it.entity !== centerEntity }
    }

    /**
     * Recognizes the tracked entity as a [TrackedProxyEntity], [TrackedDecoyEntity], [TrackedFriendlyEntity] or a real
     * [TrackedEnemyEntity].
     *
     * @param entity the minecraft entity instance
     *
     * @return any instance of [ITrackedEntity] that matches the type of tracked entity
     */
    private fun classifyEntity(entity: VibrantEntityAlike): ITrackedEntity {
        return when (entity) {
            is VibrantEntityLiving -> TrackedEnemyEntity(entity)
            is VibrantTileEntity -> TrackedTileEntity(entity)
            else -> UnclassifiedEntity(entity)
        }
    }
}