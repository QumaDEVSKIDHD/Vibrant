package net.cydhra.vibrant.util.outline


import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.api.tileentity.VibrantTileEntity
import net.cydhra.vibrant.gui.util.GlStateManager
import net.cydhra.vibrant.gui.util.RenderUtil
import net.cydhra.vibrant.util.enemy.EnemyTracker
import net.cydhra.vibrant.util.enemy.ITrackedEntity
import net.cydhra.vibrant.util.enemy.TrackedTileEntity

/**
 * Outlines multiple object entities
 *
 * @author Flaflo
 */
class MultiOutline(var enabled: Boolean) : Outline() {

    val entities: List<ITrackedEntity>
        get() = EnemyTracker.trackedEntities

    val isEnabled: Boolean
        get() = enabled && this.entities.isNotEmpty()

    override fun startOutline() {
        if (this.isEnabled) {
            super.startOutline()

            GlStateManager.enableStandardItemLighting()

            val wasDebugBoundingBox = VibrantClient.minecraft.getRenderManager().isDebugBoundingBox
            val wasEntityShadows = VibrantClient.minecraft.gameSettings.renderEntityShadows

            VibrantClient.minecraft.getRenderManager().isDebugBoundingBox = false
            VibrantClient.minecraft.gameSettings.renderEntityShadows = false

            val frustum = VibrantClient.factory.newFrustum()
            val renderViewEntity = VibrantClient.minecraft.getRenderViewEntity()

            val cameraPosX = RenderUtil.interpolate(renderViewEntity.posX, renderViewEntity.prevPosX, VibrantClient.minecraft.timer.renderPartialTicks)
            val cameraPosY = RenderUtil.interpolate(renderViewEntity.posY, renderViewEntity.prevPosY, VibrantClient.minecraft.timer.renderPartialTicks)
            val cameraPosZ = RenderUtil.interpolate(renderViewEntity.posZ, renderViewEntity.prevPosZ, VibrantClient.minecraft.timer.renderPartialTicks)

            frustum.setPosition(cameraPosX, cameraPosY, cameraPosZ)

            GlStateManager.pushState()
            this.entities.stream()
                    .filter { entity -> entity !is TrackedTileEntity }
                    .map { entity -> entity.entity as VibrantEntity }
                    .filter { entity ->
                        frustum.isBoundingBoxInsideFrustum(entity.boundingBox)
                    }
                    .forEach { entity ->
                        val entityPosX = RenderUtil.interpolate(entity.posX, entity.prevPosX, VibrantClient.minecraft.timer.renderPartialTicks) - VibrantClient.minecraft.getRenderManager().renderPosX
                        val entityPosY = RenderUtil.interpolate(entity.posY, entity.prevPosY, VibrantClient.minecraft.timer.renderPartialTicks) - VibrantClient.minecraft.getRenderManager().renderPosY
                        val entityPosZ = RenderUtil.interpolate(entity.posZ, entity.prevPosZ, VibrantClient.minecraft.timer.renderPartialTicks) - VibrantClient.minecraft.getRenderManager().renderPosZ

                        VibrantClient.minecraft.getRenderManager().getEntityRenderObj(entity).render(entity, entityPosX, entityPosY, entityPosZ, entity.rotationYaw, VibrantClient.minecraft.timer.renderPartialTicks)
                    }
            GlStateManager.popState()

            this.entities.stream()
                    .filter { entity -> entity is TrackedTileEntity }
                    .map { entity -> entity.entity as VibrantTileEntity }
                    .forEach { entity ->
                        VibrantClient.minecraft.getTileEntityRenderDispatcher().doRenderTileEntity(entity, VibrantClient.minecraft.timer.renderPartialTicks, -1)
                    }

            VibrantClient.minecraft.getRenderManager().isDebugBoundingBox = wasDebugBoundingBox
            VibrantClient.minecraft.gameSettings.renderEntityShadows = wasEntityShadows

            VibrantClient.minecraft.entityRenderer.disableLightmap()
            GlStateManager.disableStandardItemLighting()

            this.executeOutline()
        }
    }

    override fun endOutline() {
        if (this.isEnabled) {
            super.endOutline()
        }
    }
}
