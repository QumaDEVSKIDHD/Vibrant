package net.cydhra.vibrant.util.enemy

import net.cydhra.eventsystem.EventManager
import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.entity.VibrantEntityLiving
import net.cydhra.vibrant.api.entity.VibrantPlayer
import net.cydhra.vibrant.events.minecraft.MinecraftTickEvent

/**
 *
 */
object EnemyTracker {

    private val trackedEntitiesList: MutableList<ITrackedEntity> = mutableListOf()

    init {
        EventManager.registerListeners(this)
    }

    @EventHandler
    fun onTick(e: MinecraftTickEvent) {
        trackedEntitiesList.clear()

        val loadedEntities: MutableList<VibrantEntityLiving> = mutableListOf()
        loadedEntities.addAll(VibrantClient.minecraft.theWorld?.getEntityList()?.filterIsInstance<VibrantEntityLiving>() ?: return)

        val trackedEntityIt = trackedEntitiesList.iterator()
        while (trackedEntityIt.hasNext()) {
            val trackedEntity = trackedEntityIt.next()

            if (!loadedEntities.remove(trackedEntity.entity)) {
                trackedEntityIt.remove()
            }
        }

        for (remainingEntity in loadedEntities) {
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
    private fun classifyEntity(entity: VibrantEntityLiving): ITrackedEntity {
        return TrackedEnemyEntity(entity)
    }
}