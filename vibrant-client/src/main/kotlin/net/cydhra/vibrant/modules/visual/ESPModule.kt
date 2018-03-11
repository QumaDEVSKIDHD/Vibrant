package net.cydhra.vibrant.modules.visual

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.api.tileentity.VibrantTileEntity
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.gui.util.GlStateManager
import net.cydhra.vibrant.gui.util.RenderUtil
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.util.enemy.EnemyTracker
import net.cydhra.vibrant.util.enemy.TrackedTileEntity
import net.cydhra.vibrant.util.framebuffer.OutlineFramebuffer
import net.cydhra.vibrant.util.shader.ShaderLibrary
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.Display

class ESPModule : Module("ESP", DefaultCategories.VISUAL, Keyboard.KEY_B) {

    private val outlineFramebuffer: OutlineFramebuffer by lazy {
        OutlineFramebuffer(Display.getWidth(), Display.getHeight())
    }

    override fun onEnable() {
        ShaderLibrary.outlineShaderProgramProgram.sampleRadius = 4
        ShaderLibrary.outlineShaderProgramProgram.averageDivisor = 80F
        ShaderLibrary.outlineShaderProgramProgram.maxSampleRadius = 4
    }

    @EventHandler
    fun onRender2DShaderEsp(e: RenderOverlayEvent) {
        outlineFramebuffer.drawOntoCurrentFramebuffer()
    }

    @EventHandler
    fun onRenderWorldShaderEsp(e: RenderWorldEvent) {
        outlineFramebuffer.bind()
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
        EnemyTracker.trackedEntities.stream()
                .filter { entity -> entity !is TrackedTileEntity && entity.entity != mc.thePlayer!! }
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

        EnemyTracker.trackedEntities.stream()
                .filter { entity -> entity is TrackedTileEntity }
                .map { entity -> entity.entity as VibrantTileEntity }
                .forEach { entity ->
                    VibrantClient.minecraft.getTileEntityRenderDispatcher().doRenderTileEntity(entity, VibrantClient.minecraft.timer.renderPartialTicks, -1)
                }

        VibrantClient.minecraft.getRenderManager().isDebugBoundingBox = wasDebugBoundingBox
        VibrantClient.minecraft.gameSettings.renderEntityShadows = wasEntityShadows

        VibrantClient.minecraft.entityRenderer.disableLightmap()
        GlStateManager.disableStandardItemLighting()

        // rebind minecraft framebuffer
        outlineFramebuffer.unbind()
    }
}