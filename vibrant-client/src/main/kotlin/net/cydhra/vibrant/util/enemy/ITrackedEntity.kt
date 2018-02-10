package net.cydhra.vibrant.util.enemy

import net.cydhra.vibrant.api.entity.VibrantEntityAlike
import net.cydhra.vibrant.api.entity.VibrantEntityLiving
import net.cydhra.vibrant.api.tileentity.VibrantTileEntity

/**
 *
 */
sealed class ITrackedEntity {
    abstract val entity: VibrantEntityAlike
}

class TrackedEnemyEntity(override val entity: VibrantEntityLiving) : ITrackedEntity()

class TrackedFriendlyEntity(override val entity: VibrantEntityLiving) : ITrackedEntity()

class TrackedProxyEntity(override val entity: VibrantEntityLiving) : ITrackedEntity()

class TrackedDecoyEntity(override val entity: VibrantEntityLiving) : ITrackedEntity()

class TrackedTileEntity(override val entity: VibrantTileEntity) : ITrackedEntity()

class UnclassifiedEntity(override val entity: VibrantEntityAlike) : ITrackedEntity()