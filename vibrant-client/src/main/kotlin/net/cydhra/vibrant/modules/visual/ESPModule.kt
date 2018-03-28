@file:Suppress("UNUSED_PARAMETER")

package net.cydhra.vibrant.modules.visual

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.api.tileentity.VibrantTileEntity
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.gui.util.RenderUtil
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.util.enemy.EnemyTracker
import net.cydhra.vibrant.util.enemy.TrackedTileEntity
import net.cydhra.vibrant.util.framebuffer.Framebuffer
import net.cydhra.vibrant.util.framebuffer.OutlineFramebuffer
import net.cydhra.vibrant.util.shader.ShaderLibrary
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.Display
import org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT
import org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT
import java.awt.Color

class ESPModule : Module("ESP", DefaultCategories.VISUAL, Keyboard.KEY_B) {

    private val outlineFramebuffer: OutlineFramebuffer by lazy {
        OutlineFramebuffer(Display.getWidth(), Display.getHeight())
    }

    private val objectMapFramebuffer: Framebuffer by lazy {
        Framebuffer(Display.getWidth(), Display.getHeight(), true)
    }

    override fun onEnable() {
        ShaderLibrary.reloadShaders()
        ShaderLibrary.outlineShaderProgramProgram.sampleRadius = 8
        ShaderLibrary.outlineShaderProgramProgram.fadeIntensity = 0.01F
        ShaderLibrary.outlineShaderProgramProgram.maxSampleRadius = 8
        ShaderLibrary.outlineShaderProgramProgram.baseColor = Color.RED
        ShaderLibrary.outlineShaderProgramProgram.objectColor = Color.RED
        ShaderLibrary.outlineShaderProgramProgram.debug = false
    }

    @EventHandler
    fun onRender2DShaderEsp(e: RenderOverlayEvent) {
        ShaderLibrary.outlineShaderProgramProgram.objectMapSampler = objectMapFramebuffer.framebuffer.textureId
        ShaderLibrary.outlineShaderProgramProgram.diffuseSampler = outlineFramebuffer.framebuffer.textureId

        outlineFramebuffer.drawOntoCurrentFramebuffer()
    }

    @EventHandler
    fun onRenderWorldShaderEsp(e: RenderWorldEvent) {
        objectMapFramebuffer.bind()

        mc.glStateManager.clearColor(1F, 1F, 0F, 0F)
        mc.glStateManager.clear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        objectMapFramebuffer.unbind()

        outlineFramebuffer.bind()
        drawEntities()
        outlineFramebuffer.unbind()
    }

    fun drawEntities() {
        mc.glStateManager.enableStandardItemLighting()

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

        mc.glStateManager.pushAttrib()
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

        EnemyTracker.trackedEntities.stream()
                .filter { entity -> entity is TrackedTileEntity }
                .map { entity -> entity.entity as VibrantTileEntity }
                .forEach { entity ->
                    VibrantClient.minecraft.getTileEntityRenderDispatcher().doRenderTileEntity(entity, VibrantClient.minecraft.timer.renderPartialTicks, -1)
                }
        mc.glStateManager.popAttrib()

        VibrantClient.minecraft.getRenderManager().isDebugBoundingBox = wasDebugBoundingBox
        VibrantClient.minecraft.gameSettings.renderEntityShadows = wasEntityShadows

        VibrantClient.minecraft.entityRenderer.disableLightmap()
        mc.glStateManager.disableStandardItemLighting()
    }
}