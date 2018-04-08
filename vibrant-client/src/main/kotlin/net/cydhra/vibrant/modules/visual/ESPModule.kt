@file:Suppress("UNUSED_PARAMETER")

package net.cydhra.vibrant.modules.visual

import net.cydhra.eventsystem.listeners.EventHandler
import net.cydhra.vibrant.VibrantClient
import net.cydhra.vibrant.api.entity.VibrantEntity
import net.cydhra.vibrant.api.entity.VibrantEntityLiving
import net.cydhra.vibrant.api.entity.VibrantPlayerSP
import net.cydhra.vibrant.api.tileentity.VibrantTileEntity
import net.cydhra.vibrant.events.render.RenderOverlayEvent
import net.cydhra.vibrant.events.render.RenderWorldEvent
import net.cydhra.vibrant.gui.util.RenderUtil
import net.cydhra.vibrant.modulesystem.DefaultCategories
import net.cydhra.vibrant.modulesystem.Module
import net.cydhra.vibrant.util.enemy.EnemyTracker
import net.cydhra.vibrant.util.enemy.TrackedTileEntity
import net.cydhra.vibrant.util.framebuffer.OutlineFramebuffer
import net.cydhra.vibrant.util.shader.ShaderLibrary
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.Display
import org.lwjgl.util.vector.Vector3f
import java.awt.Color
import java.util.*

class ESPModule : Module("ESP", DefaultCategories.VISUAL, Keyboard.KEY_B) {

    //TODO Fix settings to correctly work with enums
    private var mode = Mode.OVERLAY/*by setting("Mode", Mode.OVERLAY) {
        increment cycling Mode.values()
    }*/

    private val outlineFramebuffer: OutlineFramebuffer by lazy {
        OutlineFramebuffer(Display.getWidth(), Display.getHeight())
    }

    private val frustum = VibrantClient.factory.newFrustum()
    private val projectedEntityBoundingBoxes = LinkedList<Pair<VibrantEntity, Array<Vector3f?>>>()

    override fun onEnable() {
        if (mode == Mode.SHADER) {
            ShaderLibrary.reloadShaders()
            ShaderLibrary.outlineShaderProgramProgram.sampleRadius = 8
            ShaderLibrary.outlineShaderProgramProgram.fadeIntensity = 0.01F
            ShaderLibrary.outlineShaderProgramProgram.maxSampleRadius = 8
            ShaderLibrary.outlineShaderProgramProgram.baseColor = Color.RED
            ShaderLibrary.outlineShaderProgramProgram.objectColor = Color.RED
            ShaderLibrary.outlineShaderProgramProgram.debug = false
        }
    }

    @EventHandler
    fun onRender2DShaderEsp(e: RenderOverlayEvent) {
        if (mode == Mode.SHADER) {
            ShaderLibrary.outlineShaderProgramProgram.diffuseSampler = outlineFramebuffer.framebuffer.textureId

            outlineFramebuffer.drawOntoCurrentFramebuffer()
        }

        if (mode == Mode.OVERLAY) {
            projectedEntityBoundingBoxes.forEach {
                val minX = it.second.filterNotNull().stream().map { it.x }.min(Float::compareTo).orElse(0F)
                val minY = it.second.filterNotNull().stream().map { it.y }.min(Float::compareTo).orElse(0F)

                val maxX = it.second.filterNotNull().stream().map { it.x }.max(Float::compareTo).orElse(0F)
                val maxY = it.second.filterNotNull().stream().map { it.y }.max(Float::compareTo).orElse(0F)

                RenderUtil.drawRect(minX, minY, maxX - minX, maxY - minY, Color.BLACK)
            }
        }
    }

    @EventHandler
    fun onRenderWorldShaderEsp(e: RenderWorldEvent) {
        projectedEntityBoundingBoxes.clear()

        if (mode == Mode.SHADER) {
            outlineFramebuffer.bind()
            drawEntities()
            outlineFramebuffer.unbind()
        }

        if (mode == Mode.OVERLAY) {
            for (en: VibrantEntityLiving in mc.theWorld!!.getEntityList().filterIsInstance<VibrantEntityLiving>().filterNot { it is VibrantPlayerSP }) {
                val px = RenderUtil.interpolate(mc.thePlayer!!.posX, mc.thePlayer!!.prevPosX, mc.timer.renderPartialTicks)
                val py = RenderUtil.interpolate(mc.thePlayer!!.posY, mc.thePlayer!!.prevPosY, mc.timer.renderPartialTicks)
                val pz = RenderUtil.interpolate(mc.thePlayer!!.posZ, mc.thePlayer!!.prevPosZ, mc.timer.renderPartialTicks)

                frustum.setPosition(px, py, pz)

                if (frustum.isBoundingBoxInsideFrustum(en.boundingBox)) {
                    val x = (RenderUtil.interpolate(en.posX, en.prevPosX, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosX).toFloat()
                    val y = (RenderUtil.interpolate(en.posY, en.prevPosY, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosY).toFloat()
                    val z = (RenderUtil.interpolate(en.posZ, en.prevPosZ, mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosZ).toFloat()

                    val positions: Array<Vector3f?> = arrayOfNulls(8)

                    val width = ((en.boundingBox.maxX - en.boundingBox.minX) / 2).toFloat()
                    val height = (en.boundingBox.maxY - en.boundingBox.minY).toFloat()
                    val depth = ((en.boundingBox.maxZ - en.boundingBox.minZ) / 2).toFloat()

                    val scaleFactor = VibrantClient.factory.newScaledResolution().getScaleFactor()

                    //Project vertices of the bounding box
                    positions[0] = RenderUtil.project3d(x + width, y + height, z + depth, scaleFactor)
                    positions[1] = RenderUtil.project3d(x - width, y, z + depth, scaleFactor)

                    positions[2] = RenderUtil.project3d(x - width, y + height, z + depth, scaleFactor)
                    positions[3] = RenderUtil.project3d(x + width, y, z + depth, scaleFactor)

                    positions[4] = RenderUtil.project3d(x + width, y + height, z - depth, scaleFactor)
                    positions[5] = RenderUtil.project3d(x - width, y, z - depth, scaleFactor)

                    positions[6] = RenderUtil.project3d(x - width, y + height, z - depth, scaleFactor)
                    positions[7] = RenderUtil.project3d(x + width, y, z - depth, scaleFactor)

                    projectedEntityBoundingBoxes.add(Pair<VibrantEntity, Array<Vector3f?>>(en, positions))
                }
            }
        }
    }

    private fun drawEntities() {

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

        mc.getRenderManager().renderOutlines = true

        mc.glStateManager.enableStandardItemLighting()
        mc.glStateManager.disableLighting()

        EnemyTracker.trackedEntities.stream()
                .filter { entity -> entity !is TrackedTileEntity && entity.entity != mc.thePlayer!! }
                .map { entity -> entity.entity as VibrantEntity }
                .filter { entity ->
                    frustum.isBoundingBoxInsideFrustum(entity.boundingBox)
                }
                .forEach { entity ->
                    VibrantClient.minecraft.getRenderManager().renderEntitySimple(entity, VibrantClient.minecraft.timer.renderPartialTicks, null)
                }

        EnemyTracker.trackedEntities.stream()
                .filter { entity -> entity is TrackedTileEntity }
                .map { entity -> entity.entity as VibrantTileEntity }
                .forEach { entity ->
                    VibrantClient.minecraft.getTileEntityRenderDispatcher().doRenderTileEntity(entity, VibrantClient.minecraft.timer.renderPartialTicks, -1)
                }

        mc.getRenderManager().renderOutlines = false

        VibrantClient.minecraft.getRenderManager().isDebugBoundingBox = wasDebugBoundingBox
        VibrantClient.minecraft.gameSettings.renderEntityShadows = wasEntityShadows

        mc.glStateManager.enableLighting()
    }
}

enum class Mode {
    OVERLAY,
    SHADER
}