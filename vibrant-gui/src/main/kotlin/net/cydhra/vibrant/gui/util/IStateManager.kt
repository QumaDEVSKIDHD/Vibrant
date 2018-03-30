package net.cydhra.vibrant.gui.util

import java.awt.Color
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

    fun blendFunc(srcFactor: SourceFactor, dstFactor: DestinationFactor)

    fun tryBlendFuncSeparate(srcFactor: SourceFactor, dstFactor: DestinationFactor, srcFactorAlpha: SourceFactor, dstFactorAlpha: DestinationFactor)

    fun enableFog()

    fun disableFog()

    fun setFog(param: FogMode)

    fun setFogDensity(param: Float)

    fun setFogStart(param: Float)

    fun setFogEnd(param: Float)

    fun enableCull()

    fun disableCull()

    fun cullFace(mode: CullFace)

    fun enablePolygonOffset()

    fun disablePolygonOffset()

    fun doPolygonOffset(factor: Float, units: Float)

    fun enableColorLogic()

    fun disableColorLogic()

    fun colorLogicOp(opcode: LogicOperation)

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

    fun color(color: Color)

    fun resetColor()

    fun callList(list: Int)

    fun disableLineSmooth()

    fun enableLineSmooth()

    fun enableStandardItemLighting()

    fun disableStandardItemLighting()

    fun setLightmapTextureCoords(target: Int, s: Float, t: Float)
}

enum class CullFace(val mode: Int) {
    FRONT(1028),
    BACK(1029),
    FRONT_AND_BACK(1032)
}

enum class FogMode(val mode: Int) {
    LINEAR(9729),
    EXP(2048),
    EXP2(2049)
}

enum class SourceFactor(val factor: Int) {
    CONSTANT_ALPHA(32771),
    CONSTANT_COLOR(32769),
    DST_ALPHA(772),
    DST_COLOR(774),
    ONE(1),
    ONE_MINUS_CONSTANT_ALPHA(32772),
    ONE_MINUS_CONSTANT_COLOR(32770),
    ONE_MINUS_DST_ALPHA(773),
    ONE_MINUS_DST_COLOR(775),
    ONE_MINUS_SRC_ALPHA(771),
    ONE_MINUS_SRC_COLOR(769),
    SRC_ALPHA(770),
    SRC_ALPHA_SATURATE(776),
    SRC_COLOR(768),
    ZERO(0)
}

enum class DestinationFactor(val factor: Int) {
    CONSTANT_ALPHA(32771),
    CONSTANT_COLOR(32769),
    DST_ALPHA(772),
    DST_COLOR(774),
    ONE(1),
    ONE_MINUS_CONSTANT_ALPHA(32772),
    ONE_MINUS_CONSTANT_COLOR(32770),
    ONE_MINUS_DST_ALPHA(773),
    ONE_MINUS_DST_COLOR(775),
    ONE_MINUS_SRC_ALPHA(771),
    ONE_MINUS_SRC_COLOR(769),
    SRC_ALPHA(770),
    SRC_COLOR(768),
    ZERO(0)
}

enum class LogicOperation(val operator: Int) {
    AND(5377),
    AND_INVERTED(5380),
    AND_REVERSE(5378),
    CLEAR(5376),
    COPY(5379),
    COPY_INVERTED(5388),
    EQUIV(5385),
    INVERT(5386),
    NAND(5390),
    NOOP(5381),
    NOR(5384),
    OR(5383),
    OR_INVERTED(5389),
    OR_REVERSE(5387),
    SET(5391),
    XOR(5382)
}