package net.cydhra.vibrant.gui.util

import java.nio.FloatBuffer

/**
 *
 */
interface IStateManager {
    fun pushAttrib()

    fun popAttrib()

    fun disableAlpha()

    fun enableAlpha()

    fun alphaFunc(func: Int, ref: Float)

    fun enableLighting()

    fun disableLighting()

    fun enableLight(light: Int)

    fun disableLight(light: Int)

    fun enableColorMaterial()

    fun disableColorMaterial()

    fun colorMaterial(face: Int, mode: Int)

    fun disableDepth()

    fun enableDepth()

    fun depthFunc(depthFunc: Int)

    fun depthMask(flagIn: Boolean)

    fun disableBlend()

    fun enableBlend()

    fun blendFunc(srcFactor: Int, dstFactor: Int)

    fun tryBlendFuncSeparate(srcFactor: Int, dstFactor: Int, srcFactorAlpha: Int, dstFactorAlpha: Int)

    fun enableFog()

    fun disableFog()

    fun setFog(param: Int)

    fun setFogDensity(param: Float)

    fun setFogStart(param: Float)

    fun setFogEnd(param: Float)

    fun enableCull()

    fun disableCull()

    fun cullFace(mode: Int)

    fun enablePolygonOffset()

    fun disablePolygonOffset()

    fun doPolygonOffset(factor: Float, units: Float)

    fun enableColorLogic()

    fun disableColorLogic()

    fun colorLogicOp(opcode: Int)

    fun setActiveTexture(texture: Int)

    fun enableTexture2D()

    fun disableTexture2D()

    fun generateTexture()

    fun deleteTexture(texture: Int)

    fun bindTexture(texture: Int)

    fun enableNormalize()

    fun disableNormalize()

    fun shadeModel(mode: Int)

    fun enableRescaleNormal()

    fun disableRescaleNormal()

    fun viewport(x: Int, y: Int, width: Int, height: Int)

    fun colorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean)

    fun clearDepth(depth: Double)

    fun clearColor(red: Float, green: Float, blue: Float, alpha: Float)

    fun clear(mask: Int)

    fun matrixMode(mode: Int)

    fun loadIdentity()

    fun pushMatrix()

    fun popMatrix()

    fun getFloat(pname: Int, params: FloatBuffer)

    fun ortho(left: Double, right: Double, bottom: Double, top: Double, zNear: Double, zFar: Double)

    fun rotate(angle: Float, x: Float, y: Float, z: Float)

    fun scale(x: Float, y: Float, z: Float)

    fun scale(x: Double, y: Double, z: Double)

    fun translate(x: Float, y: Float, z: Float)

    fun translate(x: Double, y: Double, z: Double)

    fun multMatrix(matrix: FloatBuffer)

    fun color(colorRed: Float, colorGreen: Float, colorBlue: Float, colorAlpha: Float)

    fun color(colorRed: Float, colorGreen: Float, colorBlue: Float)

    fun resetColor()

    fun callList(list: Int)
}