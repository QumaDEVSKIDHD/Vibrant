package net.cydhra.vibrant.adapter;

import net.cydhra.vibrant.api.render.VibrantGlStateManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.nio.FloatBuffer;

/**
 *
 */
public class VibrantGlStateManagerImpl implements VibrantGlStateManager {
    @Override
    public void alphaFunc(final int func, final float ref) {
        GlStateManager.alphaFunc(func, ref);
    }
    
    @Override
    public void pushAttrib() {
        GlStateManager.pushAttrib();
    }
    
    @Override
    public void popAttrib() {
        GlStateManager.popAttrib();
    }
    
    @Override
    public void disableAlpha() {
        GlStateManager.disableAlpha();
    }
    
    @Override
    public void enableAlpha() {
        GlStateManager.enableAlpha();
    }
    
    @Override
    public void enableLighting() {
        GlStateManager.enableLighting();
    }
    
    @Override
    public void disableLighting() {
        GlStateManager.disableLighting();
    }
    
    @Override
    public void enableLight(final int light) {
        GlStateManager.enableLight(light);
    }
    
    @Override
    public void disableLight(final int light) {
        GlStateManager.disableLight(light);
    }
    
    @Override
    public void enableColorMaterial() {
        GlStateManager.enableColorMaterial();
    }
    
    @Override
    public void disableColorMaterial() {
        GlStateManager.disableColorMaterial();
    }
    
    @Override
    public void colorMaterial(final int face, final int mode) {
        GlStateManager.colorMaterial(face, mode);
    }
    
    @Override
    public void disableDepth() {
        GlStateManager.disableDepth();
    }
    
    @Override
    public void enableDepth() {
        GlStateManager.enableDepth();
    }
    
    @Override
    public void depthFunc(final int depthFunc) {
        GlStateManager.depthFunc(depthFunc);
    }
    
    @Override
    public void depthMask(final boolean flagIn) {
        GlStateManager.depthMask(flagIn);
    }
    
    @Override
    public void disableBlend() {
        GlStateManager.disableBlend();
    }
    
    @Override
    public void enableBlend() {
        GlStateManager.enableBlend();
    }
    
    @Override
    public void blendFunc(final int srcFactor, final int dstFactor) {
        GlStateManager.blendFunc(srcFactor, dstFactor);
    }
    
    @Override
    public void tryBlendFuncSeparate(final int srcFactor, final int dstFactor, final int srcFactorAlpha, final int dstFactorAlpha) {
        GlStateManager.tryBlendFuncSeparate(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
    }
    
    @Override
    public void enableFog() {
        GlStateManager.enableFog();
    }
    
    @Override
    public void disableFog() {
        GlStateManager.disableFog();
    }
    
    @Override
    public void setFog(final int param) {
        GlStateManager.setFog(param);
    }
    
    @Override
    public void setFogDensity(final float param) {
        GlStateManager.setFogDensity(param);
    }
    
    @Override
    public void setFogStart(final float param) {
        GlStateManager.setFogStart(param);
    }
    
    @Override
    public void setFogEnd(final float param) {
        GlStateManager.setFogEnd(param);
    }
    
    @Override
    public void enableCull() {
        GlStateManager.enableCull();
    }
    
    @Override
    public void disableCull() {
        GlStateManager.disableCull();
    }
    
    @Override
    public void cullFace(final int mode) {
        GlStateManager.cullFace(mode);
    }
    
    @Override
    public void enablePolygonOffset() {
        GlStateManager.enablePolygonOffset();
    }
    
    @Override
    public void disablePolygonOffset() {
        GlStateManager.disablePolygonOffset();
    }
    
    @Override
    public void doPolygonOffset(final float factor, final float units) {
        GlStateManager.doPolygonOffset(factor, units);
    }
    
    @Override
    public void enableColorLogic() {
        GlStateManager.enableColorLogic();
    }
    
    @Override
    public void disableColorLogic() {
        GlStateManager.disableColorLogic();
    }
    
    @Override
    public void colorLogicOp(final int opcode) {
        GlStateManager.colorLogicOp(opcode);
    }
    
    @Override
    public void setActiveTexture(final int texture) {
        GlStateManager.setActiveTexture(texture);
    }
    
    @Override
    public void enableTexture2D() {
        GlStateManager.enableTexture2D();
    }
    
    @Override
    public void disableTexture2D() {
        GlStateManager.disableTexture2D();
    }
    
    @Override
    public void generateTexture() {
        GlStateManager.generateTexture();
    }
    
    @Override
    public void deleteTexture(final int texture) {
        GlStateManager.deleteTexture(texture);
    }
    
    @Override
    public void bindTexture(final int texture) {
        GlStateManager.bindTexture(texture);
    }
    
    @Override
    public void enableNormalize() {
        GlStateManager.enableNormalize();
    }
    
    @Override
    public void disableNormalize() {
        GlStateManager.disableNormalize();
    }
    
    @Override
    public void shadeModel(final int mode) {
        GlStateManager.shadeModel(mode);
    }
    
    @Override
    public void enableRescaleNormal() {
        GlStateManager.enableRescaleNormal();
    }
    
    @Override
    public void disableRescaleNormal() {
        GlStateManager.disableRescaleNormal();
    }
    
    @Override
    public void viewport(final int x, final int y, final int width, final int height) {
        GlStateManager.viewport(x, y, width, height);
    }
    
    @Override
    public void colorMask(final boolean red, final boolean green, final boolean blue, final boolean alpha) {
        GlStateManager.colorMask(red, green, blue, alpha);
    }
    
    @Override
    public void clearDepth(final double depth) {
        GlStateManager.clearDepth(depth);
    }
    
    @Override
    public void clearColor(final float red, final float green, final float blue, final float alpha) {
        GlStateManager.clearColor(red, green, blue, alpha);
    }
    
    @Override
    public void clear(final int mask) {
        GlStateManager.clear(mask);
    }
    
    @Override
    public void matrixMode(final int mode) {
        GlStateManager.matrixMode(mode);
    }
    
    @Override
    public void loadIdentity() {
        GlStateManager.loadIdentity();
    }
    
    @Override
    public void pushMatrix() {
        GlStateManager.pushMatrix();
    }
    
    @Override
    public void popMatrix() {
        GlStateManager.popMatrix();
    }
    
    @Override
    public void getFloat(final int pname, @NotNull final FloatBuffer params) {
        GlStateManager.getFloat(pname, params);
    }
    
    @Override
    public void ortho(final double left, final double right, final double bottom, final double top, final double zNear, final double zFar) {
        GlStateManager.ortho(left, right, bottom, top, zNear, zFar);
    }
    
    @Override
    public void rotate(final float angle, final float x, final float y, final float z) {
        GlStateManager.rotate(angle, x, y, z);
    }
    
    @Override
    public void scale(final float x, final float y, final float z) {
        GlStateManager.scale(x, y, z);
    }
    
    @Override
    public void scale(final double x, final double y, final double z) {
        GlStateManager.scale(x, y, z);
    }
    
    @Override
    public void translate(final float x, final float y, final float z) {
        GlStateManager.translate(x, y, z);
    }
    
    @Override
    public void translate(final double x, final double y, final double z) {
        GlStateManager.translate(x, y, z);
    }
    
    @Override
    public void multMatrix(@NotNull final FloatBuffer matrix) {
        GlStateManager.multMatrix(matrix);
    }
    
    @Override
    public void color(final float colorRed, final float colorGreen, final float colorBlue, final float colorAlpha) {
        GlStateManager.color(colorRed, colorGreen, colorBlue, colorAlpha);
    }
    
    @Override
    public void color(final float colorRed, final float colorGreen, final float colorBlue) {
        GlStateManager.color(colorRed, colorGreen, colorBlue);
    }
    
    @Override
    public void resetColor() {
        GlStateManager.resetColor();
    }
    
    @Override
    public void callList(final int list) {
        GlStateManager.callList(list);
    }
    
    @Override
    public void disableLineSmooth() {
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
    }
    
    @Override
    public void enableLineSmooth() {
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
    }
    
    @Override
    public void disableStandardItemLighting() {
        RenderHelper.disableStandardItemLighting();
    }
    
    @Override
    public void enableStandardItemLighting() {
        RenderHelper.enableStandardItemLighting();
    }
    
    @Override
    public void color(@NotNull Color color) {
        this.color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
    }
}
