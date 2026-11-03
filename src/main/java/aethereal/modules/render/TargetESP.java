/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.platform.GlStateManager$class_4534
 *  com.mojang.blaze3d.platform.GlStateManager$class_4535
 *  com.mojang.blaze3d.systems.RenderSystem
 *  meteordevelopment.orbit.EventHandler
 *  net.minecraft.class_10142
 *  net.minecraft.class_10156
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_3532
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector4f
 *  org.lwjgl.opengl.GL11
 */
package aethereal;

import aethereal.Aura;
import aethereal.BooleanSetting;
import aethereal.ColorSetting;
import aethereal.DisplayNamed;
import aethereal.EnumSetting;
import aethereal.FloatSetting;
import aethereal.Module;
import aethereal.ModuleCategory;
import aethereal.PreTickEvent;
import aethereal.Setting;
import aethereal.WorldRenderEvent;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;

public class TargetESP
extends Module {
    private static final class_2960 field0160;
    private static final class_2960 field1522;
    private static final class_2960 field1044;
    private final EnumSetting<Mode> field0189 = (EnumSetting)((Setting)((Setting)new EnumSetting<Mode>("targetesp.mode", Mode.field0699).method1007("Mode")).method0210("Visual style of the target indicator")).method2130("\u0421\u0442\u0438\u043b\u044c \u043c\u043e\u0434\u0443\u043b\u044f");
    private final EnumSetting<TextureStyle> field0469 = (EnumSetting)((Setting)((Setting)new EnumSetting<TextureStyle>("targetesp.texture", TextureStyle.field0698, () -> this.field0189.method0492() == Mode.field0123).method1007("Texture")).method0210("Particle texture type")).method2130("\u0422\u0438\u043f \u0442\u0435\u043a\u0441\u0442\u0443\u0440\u044b");
    private final EnumSetting<ShapeType> field1626 = (EnumSetting)((Setting)((Setting)new EnumSetting<ShapeType>("targetesp.shapetype", ShapeType.field0700, () -> this.field0189.method0492() == Mode.field1492).method1007("Shape")).method0210("Geometry shape for Shapes mode")).method2130("\u0422\u0438\u043f \u0444\u0438\u0433\u0443\u0440\u044b");
    private final ColorSetting field1550 = (ColorSetting)((Setting)((Setting)new ColorSetting("targetesp.color", 255, 152, 226, 200).method1882().method1007("Color")).method0210("Color of the target indicator")).method2130("\u0426\u0432\u0435\u0442");
    private final FloatSetting field1716 = (FloatSetting)((Setting)((Setting)new FloatSetting("targetesp.speed", 1.5f, 1.0f, 15.0f, 0.5f).method1007("Speed")).method0210("Animation speed of the indicator")).method2130("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c");
    private final FloatSetting field1144 = (FloatSetting)((Setting)((Setting)new FloatSetting("targetesp.spread", 0.8f, 0.2f, 2.0f, 0.1f).method1007("Spread")).method0210("How far particles spread from target")).method2130("\u041a\u0430\u043a \u0434\u0430\u043b\u0435\u043a\u043e \u0447\u0430\u0441\u0442\u0438\u0446\u044b \u0440\u0430\u0437\u043b\u0435\u0442\u0430\u044e\u0442\u0441\u044f \u043e\u0442 \u0442\u0430\u0440\u0433\u0435\u0442\u0430");
    private final BooleanSetting field1093 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("targetesp.throughwalls", true).method1007("Through Walls")).method0210("Render indicator through blocks")).method2130("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0442\u0430\u0440\u0433\u0435\u0442 \u0441\u043a\u0432\u043e\u0437\u044c \u0431\u043b\u043e\u043a\u0438");
    private final FloatSetting field1206 = (FloatSetting)((Setting)((Setting)new FloatSetting("targetesp.fallbackrange", 12.0f, 2.0f, 20.0f, 0.5f).method1007("Range")).method0210("Max range for target detection")).method2130("\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0434\u0430\u043b\u044c\u043d\u043e\u0441\u0442\u044c \u043e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d\u0438\u044f \u0446\u0435\u043b\u0438");
    private static final int field0872 = 96;
    private static final float field0826 = 0.15f;
    private final EnumSetting<AnimationMode> field0918 = (EnumSetting)((Setting)((Setting)new EnumSetting<AnimationMode>("targetesp.bladeorbit", AnimationMode.field0697, () -> this.field0189.method0492() == Mode.field1019).method1007("Blade Orbit")).method0210("Orbit plane of blades")).method2130("\u041f\u043b\u043e\u0441\u043a\u043e\u0441\u0442\u044c \u043e\u0440\u0431\u0438\u0442\u044b \u043b\u0435\u0437\u0432\u0438\u0439");
    private final FloatSetting field1341 = (FloatSetting)((Setting)((Setting)new FloatSetting("targetesp.bladeradius", 1.3f, 0.5f, 2.5f, 0.1f, () -> this.field0189.method0492() == Mode.field1019).method1007("Blade Spread")).method0210("Orbit radius as multiplier of hitbox width")).method2130("\u0420\u0430\u0434\u0438\u0443\u0441 \u043e\u0440\u0431\u0438\u0442\u044b \u043a\u0430\u043a \u043c\u043d\u043e\u0436\u0438\u0442\u0435\u043b\u044c \u0448\u0438\u0440\u0438\u043d\u044b \u0445\u0438\u0442\u0431\u043e\u043a\u0441\u0430");
    private final FloatSetting field1302 = (FloatSetting)((Setting)((Setting)new FloatSetting("targetesp.bladesize", 1.0f, 0.4f, 1.0f, 0.1f, () -> this.field0189.method0492() == Mode.field1019).method1007("Blade Size")).method0210("Size of each blade")).method2130("\u0420\u0430\u0437\u043c\u0435\u0440 \u043b\u0435\u0437\u0432\u0438\u044f");
    private final FloatSetting field1377 = (FloatSetting)((Setting)((Setting)new FloatSetting("targetesp.bladespeed", 1.0f, 0.2f, 3.0f, 0.1f, () -> this.field0189.method0492() == Mode.field1019).method1007("Blade Speed")).method0210("Rotation speed of blades")).method2130("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0432\u0440\u0430\u0449\u0435\u043d\u0438\u044f \u043b\u0435\u0437\u0432\u0438\u0439");
    private final FloatSetting field0393 = (FloatSetting)((Setting)((Setting)new FloatSetting("targetesp.bladecount", 4.0f, 2.0f, 12.0f, 1.0f, () -> this.field0189.method0492() == Mode.field1019).method1007("Blade Count")).method0210("Number of blades")).method2130("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043b\u0435\u0437\u0432\u0438\u0439");
    private final BooleanSetting field0355 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("targetesp.cubesparks", false, () -> this.field0189.method0492() == Mode.field1266).method1007("Sparks")).method0210("Emit a small spark burst from the cube on each hit")).method2130("\u0418\u0441\u043a\u043e\u0440\u043a\u0438 \u0432\u044b\u043b\u0435\u0442\u0430\u044e\u0442 \u0438\u0437 \u043a\u0443\u0431\u0430 \u043f\u0440\u0438 \u0443\u0434\u0430\u0440\u0435");
    private class_1309 field0440;
    private class_1309 field0272;
    private final List<RenderPoint> field0238 = new ArrayList<RenderPoint>();
    private float field0289;
    private long field0525;
    private float field0501;
    private float field0547;
    private double field1670;
    private double field1656;
    private double field1687;
    private boolean field1597;
    private float field1577;
    private float field1601;
    private long field1755 = -1L;
    private int field1740 = -1;
    private boolean field1774 = false;
    private int field1175 = 0;
    private static final long field1165 = 240L;
    private static final float field1186 = 0.65f;
    private long field1120 = -1L;
    private static final float field1112 = 0.18f;
    private static final float field1129 = 0.3f;
    private static final float field1227 = 0.4f;
    private static final float field1219 = 0.27f;
    private static final float field1234 = 0.6f;
    private static final float field0899 = 0.08f;
    private final List<TargetState> field0896 = new ArrayList<TargetState>();
    private boolean field0907 = false;
    private double field0855;
    private double field0847;
    private double field0862;
    private double field0940;
    private float field0933;
    private float field0948;
    private float field1356;
    private final List<RenderShape> field1353 = new ArrayList<RenderShape>();
    private class_1309 field1366;
    private final List<TargetAnimation> field1323 = new ArrayList<TargetAnimation>();
    private float field1314;
    private static final class_243[][] field1329;
    private static final class_243[][] field1401;
    private static final int field1390 = 6;
    private static final float field1403 = 0.25f;
    private static final float field0412 = 0.5f;
    private static final float field0407 = 0.2f;
    private static final float field0418 = 0.06f;
    private static final float[] field0378;
    private static final float[] field0374;
    private static final float[] field0383;
    private static final float[] field0452;
    private static final float[] field0447;
    private static final float[] field0456;
    private static final float[] field0283;
    private static final float[] field0278;
    private static final float[] field0288;
    private static final float[][] field0250;

    public TargetESP() {
        super("TargetESP", ModuleCategory.field1004, "Renders visual indicators around targets");
        this.method1013("\u0420\u0438\u0441\u0443\u0435\u0442 \u043a\u0440\u0430\u0441\u0438\u0432\u044b\u0435 \u0447\u0430\u0441\u0442\u0438\u0446\u044b \u0432\u043e\u043a\u0440\u0443\u0433 \u0442\u0430\u0440\u0433\u0435\u0442");
    }

    @Override
    public void method0025() {
        super.method0025();
        this.field0440 = null;
        this.field0272 = null;
        this.field0238.clear();
        this.field0289 = 0.0f;
        this.field0525 = System.currentTimeMillis();
        this.field0501 = 0.0f;
        this.field0547 = 0.0f;
        this.field1597 = false;
        this.field1120 = -1L;
        this.field1774 = false;
        this.field1175 = 0;
        this.field0896.clear();
        this.field0907 = false;
        this.field1353.clear();
    }

    @EventHandler
    public void onTick(PreTickEvent event) {
        if (TargetESP.method1974()) {
            return;
        }
        this.method1691();
        this.field1601 = this.field1577;
        this.field1577 += 4.0f;
        boolean isSwinging = TargetESP.field0796.field_1724.field_6252;
        int swingTicks = TargetESP.field0796.field_1724.field_6279;
        boolean newSwing = isSwinging && (!this.field1774 || swingTicks < this.field1175);
        this.field1774 = isSwinging;
        this.field1175 = swingTicks;
        if (newSwing && this.field0272 != null) {
            if (this.field0189.method0492() == Mode.field1019) {
                this.method1735();
            } else if (this.field0189.method0492() == Mode.field1266) {
                this.field1120 = System.currentTimeMillis();
                if (((Boolean)this.field0355.method0492()).booleanValue()) {
                    this.method2043();
                }
            }
        }
    }

    private void method1735() {
        if (this.field0272 == null) {
            return;
        }
        int blades = ((Float)this.field0393.method0492()).intValue();
        if (blades <= 0) {
            return;
        }
        float angle = this.field1577 * ((Float)this.field1377.method0492()).floatValue();
        float bestDistSq = Float.MAX_VALUE;
        int bestIndex = 0;
        float entityWidth = this.field0272.method_17681();
        float baseRadius = Math.max(0.4f, entityWidth) * ((Float)this.field1341.method0492()).floatValue();
        float halfHeight = this.field0272.method_17682() * 0.5f + 0.25f;
        for (int i = 0; i < blades; ++i) {
            float bz;
            float by;
            float bladeAngleDeg = angle + (float)i * (360.0f / (float)blades);
            float rad = (float)Math.toRadians(bladeAngleDeg);
            float bx = (float)this.field1670 + class_3532.method_15362((float)rad) * baseRadius;
            float distSq = bx * bx + (by = (float)this.field1656 + halfHeight) * by + (bz = (float)this.field1687 + class_3532.method_15374((float)rad) * baseRadius) * bz;
            if (!(distSq < bestDistSq)) continue;
            bestDistSq = distSq;
            bestIndex = i;
        }
        this.field1740 = bestIndex;
        this.field1755 = System.currentTimeMillis();
    }

    private void method1691() {
        class_1309 newTarget = this.method1753();
        if (newTarget != this.field0440) {
            if (this.field0440 != null) {
                this.field0238.add(new RenderPoint(this.field0440));
            }
            this.field0440 = newTarget;
            this.field0547 = newTarget != null ? 0.0f : this.field0547;
        }
    }

    private class_1309 method1753() {
        Aura aura = Aura.method1701();
        if (aura == null || !aura.method2195()) {
            return null;
        }
        return aura.method0409();
    }

    @EventHandler
    public void onRender3D(WorldRenderEvent.GamePass event) {
        if (TargetESP.method1974()) {
            return;
        }
        long now = System.currentTimeMillis();
        float delta = (float)(now - this.field0525) / 1000.0f;
        this.field0525 = now;
        float animSpeed = this.field0189.method0492() == Mode.field0123 ? 1.5f : 4.0f;
        Iterator<RenderPoint> iterator = this.field0238.iterator();
        while (iterator.hasNext()) {
            RenderPoint fading = iterator.next();
            fading.field0003 -= delta * 2.0f;
            if (fading.field0003 <= 0.01f) {
                iterator.remove();
                continue;
            }
            fading.field1409 = fading.field0731.method_23317();
            fading.field0956 = fading.field0731.method_23318();
            fading.field0757 = fading.field0731.method_23321();
        }
        if (this.field0440 != null && this.field0440.method_5805()) {
            this.field0272 = this.field0440;
            this.field1597 = true;
            this.field0547 = Math.min(1.0f, this.field0547 + delta * animSpeed);
        } else {
            this.field1597 = false;
            this.field0547 = Math.max(0.0f, this.field0547 - delta * animSpeed);
        }
        this.field0501 = TargetESP.method2092(this.field0547);
        if (this.field0189.method0492() == Mode.field1266) {
            if (this.field0907 && !this.field1597) {
                this.method2015();
                this.field0907 = false;
            }
            if (!this.field0896.isEmpty()) {
                this.method2172(event.method1629(), delta, this.field1550.method1726());
            }
            if (!this.field1353.isEmpty()) {
                this.method1872(event.method1629(), delta, this.field1550.method1726());
            }
        } else {
            if (!this.field0896.isEmpty()) {
                this.field0896.clear();
            }
            if (!this.field1353.isEmpty()) {
                this.field1353.clear();
            }
        }
        if (this.field0501 <= 0.001f && this.field0238.isEmpty()) {
            if (!this.field1597) {
                this.field0272 = null;
            }
            return;
        }
        this.field0289 += ((Float)this.field1716.method0492()).floatValue() * delta * 1.667f;
        float tickDelta = event.method1811().method_60637(false);
        class_243 camPos = TargetESP.field0796.field_1773.method_19418().method_19326();
        if (this.field0272 != null) {
            this.field1670 = class_3532.method_16436((double)tickDelta, (double)this.field0272.field_6014, (double)this.field0272.method_23317()) - camPos.field_1352;
            this.field1656 = class_3532.method_16436((double)tickDelta, (double)this.field0272.field_6036, (double)this.field0272.method_23318()) - camPos.field_1351;
            this.field1687 = class_3532.method_16436((double)tickDelta, (double)this.field0272.field_5969, (double)this.field0272.method_23321()) - camPos.field_1350;
        }
        Color c = this.field1550.method1726();
        float ease = this.field0501;
        if (this.field0189.method0492() == Mode.field0699) {
            if (this.field0272 != null && ease > 0.001f) {
                int fadedAlpha = (int)((float)c.getAlpha() * ease);
                this.method1494(event.method1629(), c, fadedAlpha, ease);
            }
        } else if (this.field0189.method0492() == Mode.field1492) {
            if (this.field0272 != null && ease > 0.001f) {
                this.method1454(event.method1629(), ease);
            }
        } else if (this.field0189.method0492() == Mode.field1019) {
            if (this.field0272 != null && ease > 0.001f) {
                this.method1474(event.method1629(), ease, tickDelta, c);
            }
        } else if (this.field0189.method0492() == Mode.field0783) {
            if (this.field0272 != null && ease > 0.001f) {
                this.method1477(event.method1629(), ease, c);
            }
        } else if (this.field0189.method0492() == Mode.field1266) {
            if (this.field0272 != null && ease > 0.001f) {
                this.method0330(event.method1629(), ease, c);
            }
        } else {
            RenderSystem.setShader((class_10156)class_10142.field_53880);
            RenderSystem.setShaderTexture((int)0, (class_2960)this.method2028());
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate((int)770, (int)1, (int)0, (int)1);
            RenderSystem.disableCull();
            if (((Boolean)this.field1093.method0492()).booleanValue()) {
                RenderSystem.disableDepthTest();
            }
            RenderSystem.depthMask((boolean)false);
            int baseAlpha = c.getAlpha();
            if (this.field0272 != null && ease > 0.001f) {
                int fadedAlpha = (int)((float)baseAlpha * ease);
                Color fadedColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), fadedAlpha);
                float radius = ((Float)this.field1144.method0492()).floatValue() * ease;
                this.method1453(event.method1629(), this.field1670, this.field1656, this.field1687, this.field0289, radius, fadedColor, ease);
            }
            for (RenderPoint fading : this.field0238) {
                float fadeEase = TargetESP.method2092(fading.field0003);
                if (fadeEase <= 0.001f) continue;
                double fx = class_3532.method_16436((double)tickDelta, (double)fading.field0731.field_6038, (double)fading.field1409) - camPos.field_1352;
                double fy = class_3532.method_16436((double)tickDelta, (double)fading.field0731.field_5971, (double)fading.field0956) - camPos.field_1351;
                double fz = class_3532.method_16436((double)tickDelta, (double)fading.field0731.field_5989, (double)fading.field0757) - camPos.field_1350;
                int fadedAlpha = (int)((float)baseAlpha * fadeEase);
                Color fadedColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), fadedAlpha);
                float radius = ((Float)this.field1144.method0492()).floatValue() * fadeEase;
                this.method1453(event.method1629(), fx, fy, fz, this.field0289, radius, fadedColor, fadeEase);
            }
            RenderSystem.enableDepthTest();
            RenderSystem.depthMask((boolean)true);
            RenderSystem.disableBlend();
            RenderSystem.blendFunc((int)770, (int)771);
            RenderSystem.enableCull();
        }
    }

    private void method1453(class_4587 matrices, double x, double y, double z, float elapsed, float radius, Color color, float ease) {
        int layers = 3;
        int particles = 12;
        int total = layers * particles;
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        for (int i = 0; i < total; ++i) {
            int layer = i / particles;
            int idx = i % particles;
            float angle = (float)idx * ((float)Math.PI * 2 / (float)particles);
            float phase = elapsed + angle;
            float drift = 0.3f * ease * class_3532.method_15374((float)(elapsed + angle * 2.0f));
            float yOff = 0.5f + drift + 0.2f * (float)layer;
            float ox = radius * class_3532.method_15374((float)(phase + (float)Math.pow(layer, 2.0)));
            float oz = radius * class_3532.method_15362((float)(phase - (float)Math.pow(layer, 2.0)));
            float baseScale = 0.005f + (float)idx / 2000.0f;
            float scale = baseScale * ease;
            matrices.method_22903();
            matrices.method_22904(x + (double)ox, y + (double)yOff, z + (double)oz);
            matrices.method_22905(scale, scale, scale);
            matrices.method_22907(TargetESP.field0796.field_1773.method_19418().method_23767());
            float half = -25.0f;
            float size = 50.0f;
            int rgba = color.getRGB();
            buffer.method_22918(matrices.method_23760().method_23761(), half, half + size, 0.0f).method_22913(0.0f, 1.0f).method_39415(rgba);
            buffer.method_22918(matrices.method_23760().method_23761(), half + size, half + size, 0.0f).method_22913(1.0f, 1.0f).method_39415(rgba);
            buffer.method_22918(matrices.method_23760().method_23761(), half + size, half, 0.0f).method_22913(1.0f, 0.0f).method_39415(rgba);
            buffer.method_22918(matrices.method_23760().method_23761(), half, half, 0.0f).method_22913(0.0f, 0.0f).method_39415(rgba);
            matrices.method_22909();
        }
        class_286.method_43433((class_9801)buffer.method_60800());
    }

    private class_2960 method2028() {
        return switch (((TextureStyle)this.field0469.method0492()).ordinal()) {
            case 1 -> field1522;
            case 2 -> field1044;
            default -> field0160;
        };
    }

    private void method1477(class_4587 matrices, float ease, Color baseColor) {
        float height = this.field0272.method_17682();
        float radius = Math.max(0.45f, this.field0272.method_17681() * 0.55f) * ((Float)this.field1144.method0492()).floatValue();
        float phase = this.field0289 * 0.6f;
        float t = (class_3532.method_15374((float)phase) + 1.0f) * 0.5f;
        float y = t * height;
        float intensity = 0.5f + 0.5f * Math.abs(class_3532.method_15362((float)phase));
        int alpha = Math.max(0, Math.min(255, (int)((float)baseColor.getAlpha() * ease * intensity)));
        if (alpha < 2) {
            return;
        }
        int rgba = this.method0965(baseColor, alpha);
        class_4184 camera = TargetESP.field0796.field_1773.method_19418();
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        RenderSystem.setShaderTexture((int)0, (class_2960)field1522);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.disableCull();
        if (((Boolean)this.field1093.method0492()).booleanValue()) {
            RenderSystem.disableDepthTest();
        }
        RenderSystem.depthMask((boolean)false);
        for (int i = 0; i < 96; ++i) {
            float angle = (float)Math.PI * 2 * (float)i / 96.0f;
            float cos = class_3532.method_15362((float)angle);
            float sin = class_3532.method_15374((float)angle);
            this.method1512(matrices, camera, cos * radius, y, sin * radius, 0.15f, rgba);
        }
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableCull();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void method1512(class_4587 matrices, class_4184 camera, float px, float py, float pz, float size, int rgba) {
        if (rgba >>> 24 < 2) {
            return;
        }
        matrices.method_22903();
        matrices.method_46416((float)this.field1670 + px, (float)this.field1656 + py, (float)this.field1687 + pz);
        matrices.method_22907(camera.method_23767());
        Matrix4f matrix = matrices.method_23760().method_23761();
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        buffer.method_22918(matrix, -size, -size, 0.0f).method_22913(0.0f, 1.0f).method_39415(rgba);
        buffer.method_22918(matrix, size, -size, 0.0f).method_22913(1.0f, 1.0f).method_39415(rgba);
        buffer.method_22918(matrix, size, size, 0.0f).method_22913(1.0f, 0.0f).method_39415(rgba);
        buffer.method_22918(matrix, -size, size, 0.0f).method_22913(0.0f, 0.0f).method_39415(rgba);
        class_286.method_43433((class_9801)buffer.method_60800());
        matrices.method_22909();
    }

    private void method0330(class_4587 matrices, float ease, Color baseColor) {
        if (!this.field1597) {
            return;
        }
        class_4184 camera = TargetESP.field0796.field_1773.method_19418();
        float width = Math.max(0.4f, this.field0272.method_17681());
        float midY = this.field0272.method_17682() * 0.5f;
        float half = width * 0.5f / 1.7320508f * ((Float)this.field1144.method0492()).floatValue() * ease * this.method0461();
        if (half < 0.001f) {
            return;
        }
        float angleY = this.method0645(this.field0289 * 0.4f);
        float angleX = this.method0645(this.field0289 * 0.27f + 0.5f);
        int rgba = this.method0965(baseColor, (int)((float)baseColor.getAlpha() * ease));
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableCull();
        if (((Boolean)this.field1093.method0492()).booleanValue()) {
            RenderSystem.disableDepthTest();
        } else {
            RenderSystem.enableDepthTest();
        }
        RenderSystem.depthMask((boolean)false);
        matrices.method_22903();
        matrices.method_22904(this.field1670, this.field1656 + (double)midY, this.field1687);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(angleY));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(angleX));
        this.method1558(matrices.method_23760().method_23761(), half, half * 0.08f, rgba);
        matrices.method_22909();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        this.method1513(matrices, camera, angleY, angleX, half, midY, baseColor, ease);
        class_243 cp = camera.method_19326();
        this.field0855 = cp.field_1352 + this.field1670;
        this.field0847 = cp.field_1351 + this.field1656 + (double)midY;
        this.field0862 = cp.field_1350 + this.field1687;
        this.field0940 = cp.field_1351 + this.field1656;
        this.field0933 = half;
        this.field0948 = angleY;
        this.field1356 = angleX;
        this.field0907 = true;
    }

    private float method0645(float stepsFloat) {
        int step = (int)Math.floor(stepsFloat);
        float frac = stepsFloat - (float)step;
        float local = frac < 0.6f ? TargetESP.method0115(frac / 0.6f) : 1.0f;
        return ((float)(step % 4) + local) * 90.0f;
    }

    private void method1558(Matrix4f m, float half, float t, int rgba) {
        class_287 buf = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        float f = 0.3f;
        for (int i = 0; i < 8; ++i) {
            float vx = (i & 1) != 0 ? half : -half;
            float vy = (i & 2) != 0 ? half : -half;
            float vz = (i & 4) != 0 ? half : -half;
            this.method1383(buf, m, vx, vy, vz, vx - 2.0f * vx * f, vy, vz, t, rgba);
            this.method1383(buf, m, vx, vy, vz, vx, vy - 2.0f * vy * f, vz, t, rgba);
            this.method1383(buf, m, vx, vy, vz, vx, vy, vz - 2.0f * vz * f, t, rgba);
        }
        class_286.method_43433((class_9801)buf.method_60800());
    }

    private void method1383(class_287 buf, Matrix4f m, float ax, float ay, float az, float bx, float by, float bz, float t, int rgba) {
        float w2;
        float w1;
        float w0;
        float u2;
        float u1;
        float u0;
        if (ax != bx) {
            u0 = 0.0f;
            u1 = t;
            u2 = 0.0f;
            w0 = 0.0f;
            w1 = 0.0f;
            w2 = t;
        } else if (ay != by) {
            u0 = t;
            u1 = 0.0f;
            u2 = 0.0f;
            w0 = 0.0f;
            w1 = 0.0f;
            w2 = t;
        } else {
            u0 = t;
            u1 = 0.0f;
            u2 = 0.0f;
            w0 = 0.0f;
            w1 = t;
            w2 = 0.0f;
        }
        float a0x = ax - u0 - w0;
        float a0y = ay - u1 - w1;
        float a0z = az - u2 - w2;
        float a1x = ax + u0 - w0;
        float a1y = ay + u1 - w1;
        float a1z = az + u2 - w2;
        float a2x = ax + u0 + w0;
        float a2y = ay + u1 + w1;
        float a2z = az + u2 + w2;
        float a3x = ax - u0 + w0;
        float a3y = ay - u1 + w1;
        float a3z = az - u2 + w2;
        float b0x = bx - u0 - w0;
        float b0y = by - u1 - w1;
        float b0z = bz - u2 - w2;
        float b1x = bx + u0 - w0;
        float b1y = by + u1 - w1;
        float b1z = bz + u2 - w2;
        float b2x = bx + u0 + w0;
        float b2y = by + u1 + w1;
        float b2z = bz + u2 + w2;
        float b3x = bx - u0 + w0;
        float b3y = by - u1 + w1;
        float b3z = bz - u2 + w2;
        this.method1381(buf, m, a0x, a0y, a0z, a1x, a1y, a1z, a2x, a2y, a2z, a3x, a3y, a3z, rgba);
        this.method1381(buf, m, b0x, b0y, b0z, b3x, b3y, b3z, b2x, b2y, b2z, b1x, b1y, b1z, rgba);
        this.method1381(buf, m, a0x, a0y, a0z, b0x, b0y, b0z, b1x, b1y, b1z, a1x, a1y, a1z, rgba);
        this.method1381(buf, m, a1x, a1y, a1z, b1x, b1y, b1z, b2x, b2y, b2z, a2x, a2y, a2z, rgba);
        this.method1381(buf, m, a2x, a2y, a2z, b2x, b2y, b2z, b3x, b3y, b3z, a3x, a3y, a3z, rgba);
        this.method1381(buf, m, a3x, a3y, a3z, b3x, b3y, b3z, b0x, b0y, b0z, a0x, a0y, a0z, rgba);
    }

    private void method1381(class_287 buf, Matrix4f m, float x0, float y0, float z0, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, int rgba) {
        buf.method_22918(m, x0, y0, z0).method_39415(rgba);
        buf.method_22918(m, x1, y1, z1).method_39415(rgba);
        buf.method_22918(m, x2, y2, z2).method_39415(rgba);
        buf.method_22918(m, x3, y3, z3).method_39415(rgba);
    }

    private void method1513(class_4587 matrices, class_4184 camera, float angleY, float angleX, float half, float midY, Color baseColor, float ease) {
        class_4587 rotStack = new class_4587();
        rotStack.method_22907(class_7833.field_40716.rotationDegrees(angleY));
        rotStack.method_22907(class_7833.field_40714.rotationDegrees(angleX));
        Matrix4f rot = new Matrix4f((Matrix4fc)rotStack.method_23760().method_23761());
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        RenderSystem.setShaderTexture((int)0, (class_2960)field1522);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.disableCull();
        if (((Boolean)this.field1093.method0492()).booleanValue()) {
            RenderSystem.disableDepthTest();
        } else {
            RenderSystem.enableDepthTest();
        }
        RenderSystem.depthMask((boolean)false);
        int glowColor = this.method0965(baseColor, (int)((float)baseColor.getAlpha() * ease * 0.3f));
        float glowSize = half * 0.42f;
        float f = 0.3f;
        matrices.method_22903();
        matrices.method_22904(this.field1670, this.field1656 + (double)midY, this.field1687);
        class_287 buf = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        for (int i = 0; i < 8; ++i) {
            float vx = (i & 1) != 0 ? half : -half;
            float vy = (i & 2) != 0 ? half : -half;
            float vz = (i & 4) != 0 ? half : -half;
            this.method1380(buf, matrices, camera, rot, vx, vy, vz, vx - 2.0f * vx * f, vy, vz, glowSize, glowColor);
            this.method1380(buf, matrices, camera, rot, vx, vy, vz, vx, vy - 2.0f * vy * f, vz, glowSize, glowColor);
            this.method1380(buf, matrices, camera, rot, vx, vy, vz, vx, vy, vz - 2.0f * vz * f, glowSize, glowColor);
        }
        class_286.method_43433((class_9801)buf.method_60800());
        matrices.method_22909();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void method1380(class_287 buf, class_4587 matrices, class_4184 camera, Matrix4f rot, float ax, float ay, float az, float bx, float by, float bz, float size, int color) {
        int samples = 3;
        for (int s = 0; s < samples; ++s) {
            float t = (float)s / (float)(samples - 1);
            float lx = ax + (bx - ax) * t;
            float ly = ay + (by - ay) * t;
            float lz = az + (bz - az) * t;
            Vector4f p = new Vector4f(lx, ly, lz, 1.0f);
            rot.transform(p);
            matrices.method_22903();
            matrices.method_46416(p.x, p.y, p.z);
            matrices.method_22907(camera.method_23767());
            Matrix4f m = matrices.method_23760().method_23761();
            buf.method_22918(m, -size, size, 0.0f).method_22913(0.0f, 1.0f).method_39415(color);
            buf.method_22918(m, size, size, 0.0f).method_22913(1.0f, 1.0f).method_39415(color);
            buf.method_22918(m, size, -size, 0.0f).method_22913(1.0f, 0.0f).method_39415(color);
            buf.method_22918(m, -size, -size, 0.0f).method_22913(0.0f, 0.0f).method_39415(color);
            matrices.method_22909();
        }
    }

    private void method2015() {
        this.field0896.clear();
        float half = this.field0933;
        if (half < 0.001f) {
            return;
        }
        float stubLen = 0.6f * half;
        class_4587 rs = new class_4587();
        rs.method_22907(class_7833.field_40716.rotationDegrees(this.field0948));
        rs.method_22907(class_7833.field_40714.rotationDegrees(this.field1356));
        Matrix4f rot = new Matrix4f((Matrix4fc)rs.method_23760().method_23761());
        for (int i = 0; i < 8; ++i) {
            float sx = (i & 1) != 0 ? 1.0f : -1.0f;
            float sy = (i & 2) != 0 ? 1.0f : -1.0f;
            float sz = (i & 4) != 0 ? 1.0f : -1.0f;
            Vector4f corner = new Vector4f(sx * half, sy * half, sz * half, 1.0f);
            rot.transform(corner);
            TargetState f = new TargetState();
            f.field0565 = this.field0855 + (double)corner.x;
            f.field0002 = this.field0847 + (double)corner.y;
            f.field1409 = this.field0862 + (double)corner.z;
            f.field0314 = half;
            f.field0177 = stubLen;
            f.field0458 = sx;
            f.field1614 = sy;
            f.field1538 = sz;
            double len = Math.sqrt(corner.x * corner.x + corner.y * corner.y + corner.z * corner.z);
            if (len < 1.0000000052583687E-4) {
                len = 1.0;
            }
            float burst = 1.7f;
            f.field0956 = (double)corner.x / len * (double)burst + (Math.random() - 0.5) * 0.7000001202807937;
            f.field0757 = 1.299999465574693 + Math.random() * 0.9000000124056896;
            f.field1241 = (double)corner.z / len * (double)burst + (Math.random() - 0.5) * 0.7000001202807937;
            f.field1704 = (float)(Math.random() * 360.0);
            f.field1136 = (float)(Math.random() * 360.0);
            f.field1087 = (float)(Math.random() * 360.0);
            f.field1196 = (float)((Math.random() - 0.5) * 520.0);
            f.field0871 = (float)((Math.random() - 0.5) * 520.0);
            f.field0826 = (float)((Math.random() - 0.5) * 520.0);
            f.field0910 = 0.0f;
            f.field1331 = 1.1f + (float)(Math.random() * 0.5);
            this.field0896.add(f);
        }
    }

    private void method2172(class_4587 matrices, float delta, Color baseColor) {
        float dt = Math.min(delta, 0.1f);
        float gravity = 16.0f;
        Iterator<TargetState> it = this.field0896.iterator();
        while (it.hasNext()) {
            TargetState f = it.next();
            f.field0910 += dt;
            if (f.field0910 >= f.field1331) {
                it.remove();
                continue;
            }
            f.field0757 -= (double)(gravity * dt);
            f.field0565 += f.field0956 * (double)dt;
            f.field0002 += f.field0757 * (double)dt;
            f.field1409 += f.field1241 * (double)dt;
            f.field1704 += f.field1196 * dt;
            f.field1136 += f.field0871 * dt;
            f.field1087 += f.field0826 * dt;
            if (!(f.field0002 <= this.field0940)) continue;
            f.field0002 = this.field0940;
            if (f.field0757 < 0.0) {
                f.field0757 = -f.field0757 * 0.18000000001546174;
            }
            float damp = (float)Math.pow(5.000000474975632E-4, dt);
            f.field0956 *= (double)damp;
            f.field1241 *= (double)damp;
            f.field1196 *= damp;
            f.field0871 *= damp;
            f.field0826 *= damp;
        }
        if (this.field0896.isEmpty()) {
            return;
        }
        class_4184 camera = TargetESP.field0796.field_1773.method_19418();
        class_243 camPos = camera.method_19326();
        int baseAlpha = baseColor.getAlpha();
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableCull();
        if (((Boolean)this.field1093.method0492()).booleanValue()) {
            RenderSystem.disableDepthTest();
        } else {
            RenderSystem.enableDepthTest();
        }
        RenderSystem.depthMask((boolean)false);
        class_287 buf = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1576);
        for (TargetState f : this.field0896) {
            int rgba = this.method0965(baseColor, (int)((float)baseAlpha * TargetESP.method0940(f)));
            float t = f.field0314 * 0.08f;
            matrices.method_22903();
            matrices.method_22904(f.field0565 - camPos.field_1352, f.field0002 - camPos.field_1351, f.field1409 - camPos.field_1350);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(f.field1136));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(f.field1704));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(f.field1087));
            Matrix4f m = matrices.method_23760().method_23761();
            this.method1383(buf, m, 0.0f, 0.0f, 0.0f, -f.field0458 * f.field0177, 0.0f, 0.0f, t, rgba);
            this.method1383(buf, m, 0.0f, 0.0f, 0.0f, 0.0f, -f.field1614 * f.field0177, 0.0f, t, rgba);
            this.method1383(buf, m, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -f.field1538 * f.field0177, t, rgba);
            matrices.method_22909();
        }
        class_286.method_43433((class_9801)buf.method_60800());
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        RenderSystem.setShaderTexture((int)0, (class_2960)field1522);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.disableCull();
        if (((Boolean)this.field1093.method0492()).booleanValue()) {
            RenderSystem.disableDepthTest();
        } else {
            RenderSystem.enableDepthTest();
        }
        RenderSystem.depthMask((boolean)false);
        class_287 gb = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        for (TargetState f : this.field0896) {
            int gc = this.method0965(baseColor, (int)((float)baseAlpha * TargetESP.method0940(f) * 0.4f));
            float gs = f.field0314 * 0.6f;
            matrices.method_22903();
            matrices.method_22904(f.field0565 - camPos.field_1352, f.field0002 - camPos.field_1351, f.field1409 - camPos.field_1350);
            matrices.method_22907(camera.method_23767());
            Matrix4f m = matrices.method_23760().method_23761();
            gb.method_22918(m, -gs, gs, 0.0f).method_22913(0.0f, 1.0f).method_39415(gc);
            gb.method_22918(m, gs, gs, 0.0f).method_22913(1.0f, 1.0f).method_39415(gc);
            gb.method_22918(m, gs, -gs, 0.0f).method_22913(1.0f, 0.0f).method_39415(gc);
            gb.method_22918(m, -gs, -gs, 0.0f).method_22913(0.0f, 0.0f).method_39415(gc);
            matrices.method_22909();
        }
        class_286.method_43433((class_9801)gb.method_60800());
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private static float method0940(TargetState f) {
        float t = f.field0910 / f.field1331;
        if (t < 0.6f) {
            return 1.0f;
        }
        float a = 1.0f - (t - 0.6f) / 0.4f;
        return a < 0.0f ? 0.0f : a;
    }

    private void method2043() {
        float half;
        block6: {
            block5: {
                half = this.field0933;
                if (half < 0.001f) break block5;
                if (this.field1353.size() <= 500) break block6;
            }
            return;
        }
        class_4587 rs = new class_4587();
        rs.method_22907(class_7833.field_40716.rotationDegrees(this.field0948));
        rs.method_22907(class_7833.field_40714.rotationDegrees(this.field1356));
        Matrix4f rot = new Matrix4f((Matrix4fc)rs.method_23760().method_23761());
        int count = 12 + (int)(Math.random() * 5.0);
        for (int i = 0; i < count; ++i) {
            int c = (int)(Math.random() * 8.0);
            float lx = (c & 1) != 0 ? half : -half;
            float ly = (c & 2) != 0 ? half : -half;
            float lz = (c & 4) != 0 ? half : -half;
            Vector4f p = new Vector4f(lx, ly, lz, 1.0f);
            rot.transform(p);
            double len = Math.sqrt(p.x * p.x + p.y * p.y + p.z * p.z);
            if (len < 1.0000000052583687E-4) {
                len = 1.0;
            }
            double sp = 1.6000000484436039 + Math.random() * 2.2000000973542457;
            RenderShape s = new RenderShape();
            s.field0565 = this.field0855 + (double)p.x;
            s.field0002 = this.field0847 + (double)p.y;
            s.field1409 = this.field0862 + (double)p.z;
            s.field0956 = (double)p.x / len * sp + (Math.random() - 0.5) * 1.2000006430216648;
            s.field0757 = (double)p.y / len * sp + (Math.random() - 0.5) * 1.2000006430216648 + 0.6000003159631385;
            s.field1241 = (double)p.z / len * sp + (Math.random() - 0.5) * 1.2000006430216648;
            s.field0314 = 0.0f;
            s.field0177 = 0.32f + (float)(Math.random() * 0.39999995534312804);
            s.field0458 = 0.02f + (float)(Math.random() * 0.035000023406755926);
            this.field1353.add(s);
        }
    }

    private void method1872(class_4587 matrices, float delta, Color baseColor) {
        float dt = Math.min(delta, 0.1f);
        float gravity = 7.0f;
        Iterator<RenderShape> it = this.field1353.iterator();
        while (it.hasNext()) {
            RenderShape s = it.next();
            s.field0314 += dt;
            if (s.field0314 >= s.field0177) {
                it.remove();
                continue;
            }
            s.field0757 -= (double)(gravity * dt);
            s.field0565 += s.field0956 * (double)dt;
            s.field0002 += s.field0757 * (double)dt;
            s.field1409 += s.field1241 * (double)dt;
            double drag = Math.pow(0.3500000596053877, dt);
            s.field0956 *= drag;
            s.field1241 *= drag;
        }
        if (this.field1353.isEmpty()) {
            return;
        }
        class_4184 camera = TargetESP.field0796.field_1773.method_19418();
        class_243 camPos = camera.method_19326();
        int baseAlpha = baseColor.getAlpha();
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        RenderSystem.setShaderTexture((int)0, (class_2960)field1044);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.disableCull();
        if (((Boolean)this.field1093.method0492()).booleanValue()) {
            RenderSystem.disableDepthTest();
        } else {
            RenderSystem.enableDepthTest();
        }
        RenderSystem.depthMask((boolean)false);
        class_287 buf = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        for (RenderShape s : this.field1353) {
            float a = 1.0f - s.field0314 / s.field0177;
            int col = this.method0965(baseColor, (int)((float)baseAlpha * a));
            float sz = s.field0458 * (0.55f + 0.45f * a);
            matrices.method_22903();
            matrices.method_22904(s.field0565 - camPos.field_1352, s.field0002 - camPos.field_1351, s.field1409 - camPos.field_1350);
            matrices.method_22907(camera.method_23767());
            Matrix4f m = matrices.method_23760().method_23761();
            buf.method_22918(m, -sz, sz, 0.0f).method_22913(0.0f, 1.0f).method_39415(col);
            buf.method_22918(m, sz, sz, 0.0f).method_22913(1.0f, 1.0f).method_39415(col);
            buf.method_22918(m, sz, -sz, 0.0f).method_22913(1.0f, 0.0f).method_39415(col);
            buf.method_22918(m, -sz, -sz, 0.0f).method_22913(0.0f, 0.0f).method_39415(col);
            matrices.method_22909();
        }
        class_286.method_43433((class_9801)buf.method_60800());
        RenderSystem.depthMask((boolean)true);
        RenderSystem.enableDepthTest();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.disableBlend();
        RenderSystem.enableCull();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private float method0461() {
        if (this.field1120 < 0L) {
            return 1.0f;
        }
        long e = System.currentTimeMillis() - this.field1120;
        if (e >= 240L) {
            this.field1120 = -1L;
            return 1.0f;
        }
        float t = (float)e / 240.0f;
        float squash = t < 0.5f ? TargetESP.method2092(t * 2.0f) * 0.18f : (1.0f - TargetESP.method2092((t - 0.5f) * 2.0f)) * 0.18f;
        return 1.0f - squash;
    }

    private static float method0115(float t) {
        float c1 = 1.70158f;
        float c3 = c1 + 1.0f;
        float p = t - 1.0f;
        return 1.0f + c3 * p * p * p + c1 * p * p;
    }

    private void method1494(class_4587 matrices, Color c, int fadedAlpha, float ease) {
        float targetHeight = this.field0272.method_17682();
        float radius = ((Float)this.field1144.method0492()).floatValue() * ease;
        int curves = 7;
        int segments = 80;
        this.method0457();
        Matrix4f matrix = matrices.method_23760().method_23761();
        float[][] bloomLayers = new float[][]{{0.06f, 0.18f}, {0.14f, 0.07f}, {0.26f, 0.03f}};
        float[][] bloomDirs = new float[][]{{1.0f, 0.0f}, {-1.0f, 0.0f}, {0.0f, 1.0f}, {0.0f, -1.0f}};
        for (float[] layer : bloomLayers) {
            float br = layer[0];
            float ba = layer[1];
            for (float[] dir : bloomDirs) {
                float ox = dir[0] * br;
                float oz = dir[1] * br;
                for (int curve = 0; curve < curves; ++curve) {
                    float phaseX = (float)curve * 1.7f;
                    float phaseY = (float)curve * 2.3f;
                    float phaseZ = (float)curve * 1.1f;
                    float freqX = 1.0f + (float)curve * 0.3f;
                    float freqY = 0.7f + (float)curve * 0.2f;
                    float freqZ = 1.2f + (float)curve * 0.25f;
                    class_287 bloom = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
                    for (int i = 0; i <= segments; i += 4) {
                        float t = (float)i / (float)segments;
                        float time = this.field0289 * 0.5f;
                        float x = radius * class_3532.method_15374((float)(time * freqX + t * 6.0f + phaseX)) * (0.5f + 0.5f * class_3532.method_15362((float)(t * 3.0f + time * 0.3f)));
                        float y = targetHeight * 0.5f + radius * 0.8f * class_3532.method_15374((float)(time * freqY + t * 4.0f + phaseY));
                        float z = radius * class_3532.method_15362((float)(time * freqZ + t * 5.0f + phaseZ)) * (0.5f + 0.5f * class_3532.method_15374((float)(t * 2.0f + time * 0.4f)));
                        float trailFade = class_3532.method_15374((float)(t * (float)Math.PI));
                        bloom.method_22918(matrix, (float)(this.field1670 + (double)x + (double)ox), (float)(this.field1656 + (double)y), (float)(this.field1687 + (double)z + (double)oz)).method_39415(this.method0965(c, (int)((float)fadedAlpha * trailFade * ba)));
                    }
                    class_286.method_43433((class_9801)bloom.method_60800());
                }
            }
        }
        for (int curve = 0; curve < curves; ++curve) {
            float phaseX = (float)curve * 1.7f;
            float phaseY = (float)curve * 2.3f;
            float phaseZ = (float)curve * 1.1f;
            float freqX = 1.0f + (float)curve * 0.3f;
            float freqY = 0.7f + (float)curve * 0.2f;
            float freqZ = 1.2f + (float)curve * 0.25f;
            class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
            for (int i = 0; i <= segments; ++i) {
                float t = (float)i / (float)segments;
                float time = this.field0289 * 0.5f;
                float x = radius * class_3532.method_15374((float)(time * freqX + t * 6.0f + phaseX)) * (0.5f + 0.5f * class_3532.method_15362((float)(t * 3.0f + time * 0.3f)));
                float y = targetHeight * 0.5f + radius * 0.8f * class_3532.method_15374((float)(time * freqY + t * 4.0f + phaseY));
                float z = radius * class_3532.method_15362((float)(time * freqZ + t * 5.0f + phaseZ)) * (0.5f + 0.5f * class_3532.method_15374((float)(t * 2.0f + time * 0.4f)));
                float trailFade = class_3532.method_15374((float)(t * (float)Math.PI));
                int alpha = (int)((float)fadedAlpha * trailFade * 0.85f);
                buffer.method_22918(matrix, (float)(this.field1670 + (double)x), (float)(this.field1656 + (double)y), (float)(this.field1687 + (double)z)).method_39415(this.method0965(c, alpha));
            }
            class_286.method_43433((class_9801)buffer.method_60800());
            class_287 glow = class_289.method_1348().method_60827(class_293.class_5596.field_29345, class_290.field_1576);
            for (int i = 0; i <= segments; i += 2) {
                float t = (float)i / (float)segments;
                float time = this.field0289 * 0.5f;
                float x = radius * class_3532.method_15374((float)(time * freqX + t * 6.0f + phaseX)) * (0.5f + 0.5f * class_3532.method_15362((float)(t * 3.0f + time * 0.3f)));
                float y = targetHeight * 0.5f + radius * 0.8f * class_3532.method_15374((float)(time * freqY + t * 4.0f + phaseY));
                float z = radius * class_3532.method_15362((float)(time * freqZ + t * 5.0f + phaseZ)) * (0.5f + 0.5f * class_3532.method_15374((float)(t * 2.0f + time * 0.4f)));
                float trailFade = class_3532.method_15374((float)(t * (float)Math.PI));
                glow.method_22918(matrix, (float)(this.field1670 + (double)x + 0.02000000700354576), (float)(this.field1656 + (double)y + 0.02000000700354576), (float)(this.field1687 + (double)z)).method_39415(this.method0965(c, (int)((float)fadedAlpha * trailFade * 0.2f)));
            }
            class_286.method_43433((class_9801)glow.method_60800());
        }
        this.method0479();
    }

    private void method1454(class_4587 matrices, float ease) {
        if (this.field0272 == null) {
            return;
        }
        if (this.field1323.isEmpty() || this.field0272 != this.field1366) {
            this.field1366 = this.field0272;
            this.field1323.clear();
            class_243[] positions = field1329[0];
            class_243[] rotations = field1401[0];
            for (int i = 0; i < positions.length; ++i) {
                this.field1323.add(new TargetAnimation(positions[i], rotations[i]));
            }
        }
        RenderSystem.enableDepthTest();
        this.field1314 = (this.field1314 + 0.5f) % 360.0f;
        class_4184 camera = TargetESP.field0796.field_1773.method_19418();
        matrices.method_22903();
        matrices.method_22904(this.field1670, this.field1656, this.field1687);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(this.field1314));
        Color c = this.field1550.method1726();
        int baseColor = this.method0965(c, (int)((float)c.getAlpha() * ease));
        ShapeType shape = (ShapeType)this.field1626.method0492();
        float sp = ((Float)this.field1144.method0492()).floatValue();
        for (TargetAnimation node : this.field1323) {
            node.method1476(matrices, ease, baseColor, camera, shape, sp);
        }
        matrices.method_22909();
        RenderSystem.enableDepthTest();
    }

    private void method0457() {
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate((int)770, (int)1, (int)0, (int)1);
        RenderSystem.disableCull();
        if (((Boolean)this.field1093.method0492()).booleanValue()) {
            RenderSystem.disableDepthTest();
        }
        RenderSystem.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
    }

    private void method0479() {
        GL11.glDisable((int)2848);
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.disableBlend();
        RenderSystem.blendFunc((int)770, (int)771);
        RenderSystem.enableCull();
    }

    private int method0965(Color c, int alpha) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), Math.max(0, Math.min(255, alpha))).getRGB();
    }

    private static float method2092(float t) {
        return t < 0.5f ? 4.0f * t * t * t : 1.0f - (float)Math.pow(-2.0f * t + 2.0f, 3.0) / 2.0f;
    }

    private void method1474(class_4587 matrices, float ease, float partialTicks, Color baseColor) {
        class_4184 camera = TargetESP.field0796.field_1773.method_19418();
        float rotationSpeed = ((Float)this.field1377.method0492()).floatValue();
        int blades = ((Float)this.field0393.method0492()).intValue();
        boolean shineEnabled = true;
        float angle = class_3532.method_16439((float)partialTicks, (float)this.field1601, (float)this.field1577) * rotationSpeed;
        float targetHalfHeight = this.field0272.method_17682() * 0.5f;
        float entityWidth = this.field0272.method_17681();
        float baseRadius = Math.max(0.4f, entityWidth) * ((Float)this.field1341.method0492()).floatValue();
        float burst = 1.0f - ease;
        float easeBurst = burst * burst * (3.0f - 2.0f * burst);
        float actualRadius = baseRadius + easeBurst * 0.4f;
        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        if (((Boolean)this.field1093.method0492()).booleanValue()) {
            RenderSystem.disableDepthTest();
        } else {
            RenderSystem.enableDepthTest();
        }
        RenderSystem.setShader((class_10156)class_10142.field_53876);
        boolean cameraOrbit = this.field0918.method0492() == AnimationMode.field0697;
        float size = ((Float)this.field1302.method0492()).floatValue();
        matrices.method_22903();
        matrices.method_22904(this.field1670, this.field1656 + (double)targetHalfHeight + 0.25, this.field1687);
        if (cameraOrbit) {
            matrices.method_22907(camera.method_23767());
        }
        class_287 buffer = class_289.method_1348().method_60827(class_293.class_5596.field_27379, class_290.field_1576);
        for (int i = 0; i < blades; ++i) {
            float bladeAngleDeg = angle + (float)i * (360.0f / (float)blades);
            this.method1510(matrices, buffer, bladeAngleDeg, 0.0f, actualRadius, ease, i, angle, baseColor, cameraOrbit, size);
        }
        class_286.method_43433((class_9801)buffer.method_60800());
        if (shineEnabled) {
            RenderSystem.setShaderTexture((int)0, (class_2960)field1522);
            RenderSystem.setShader((class_10156)class_10142.field_53880);
            class_287 glowBuffer = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
            for (int i = 0; i < blades; ++i) {
                float bladeAngleDeg = angle + (float)i * (360.0f / (float)blades);
                this.method1511(matrices, glowBuffer, camera, bladeAngleDeg, 0.0f, actualRadius, ease, i, angle, baseColor, cameraOrbit, size);
            }
            class_286.method_43433((class_9801)glowBuffer.method_60800());
        }
        matrices.method_22909();
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private float method0715(int bladeIndex) {
        if (bladeIndex != this.field1740 || this.field1755 < 0L) {
            return 1.0f;
        }
        long elapsed = System.currentTimeMillis() - this.field1755;
        if (elapsed >= 240L) {
            this.field1755 = -1L;
            this.field1740 = -1;
            return 1.0f;
        }
        float t = (float)elapsed / 240.0f;
        float thrust = t < 0.5f ? TargetESP.method2092(t * 2.0f) * 0.65f : (1.0f - TargetESP.method2092((t - 0.5f) * 2.0f)) * 0.65f;
        return 1.0f - thrust;
    }

    private void method1510(class_4587 matrices, class_287 buffer, float bladeAngleDeg, float yCenter, float orbitRadius, float alpha, int bladeIndex, float globalAngle, Color baseColor, boolean cameraOrbit, float size) {
        float angleRad = (float)Math.toRadians(bladeAngleDeg);
        float radiusFactor = this.method0715(bladeIndex);
        float posA = class_3532.method_15362((float)angleRad) * orbitRadius * radiusFactor;
        float posB = class_3532.method_15374((float)angleRad) * orbitRadius * radiusFactor;
        int idx = bladeIndex % 6;
        float tiltTime = (float)Math.toRadians(globalAngle * field0378[idx]) + field0374[idx];
        float rollTime = (float)Math.toRadians(globalAngle * field0452[idx]) + field0447[idx];
        float bobTime = (float)Math.toRadians(globalAngle * field0283[idx]) + field0278[idx];
        float tiltAngle = class_3532.method_15374((float)tiltTime) * field0383[idx];
        float rollAngle = class_3532.method_15374((float)rollTime) * field0456[idx];
        float bob = class_3532.method_15374((float)bobTime) * field0288[idx];
        matrices.method_22903();
        if (cameraOrbit) {
            matrices.method_46416(posA, yCenter + posB, bob);
            matrices.method_22907(class_7833.field_40718.rotationDegrees(bladeAngleDeg - 90.0f));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(-90.0f));
            matrices.method_22907(class_7833.field_40716.rotationDegrees(tiltAngle));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(rollAngle));
        } else {
            matrices.method_46416(posA, yCenter + bob, posB);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(-bladeAngleDeg - 90.0f));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(tiltAngle));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(rollAngle));
        }
        matrices.method_22905(size, size, size);
        Matrix4f matrix = matrices.method_23760().method_23761();
        int cTip = this.method0965(baseColor, (int)(alpha * 255.0f));
        int cWing = this.method0965(baseColor, (int)(alpha * 240.0f));
        int cEdge = this.method0965(baseColor, (int)(alpha * 200.0f));
        float tipZ = -0.25f;
        float leftOutX = -0.5f;
        float leftOutZ = 0.2f;
        float leftInX = -0.06f;
        float rightOutX = 0.5f;
        float rightOutZ = 0.2f;
        float rightInX = 0.06f;
        buffer.method_22918(matrix, 0.0f, 0.0f, tipZ).method_39415(cTip);
        buffer.method_22918(matrix, leftInX, 0.0f, 0.0f).method_39415(cWing);
        buffer.method_22918(matrix, leftOutX, 0.0f, leftOutZ).method_39415(cEdge);
        buffer.method_22918(matrix, 0.0f, 0.0f, tipZ).method_39415(cTip);
        buffer.method_22918(matrix, 0.0f, 0.0f, 0.0f).method_39415(cWing);
        buffer.method_22918(matrix, leftInX, 0.0f, 0.0f).method_39415(cWing);
        buffer.method_22918(matrix, 0.0f, 0.0f, tipZ).method_39415(cTip);
        buffer.method_22918(matrix, rightOutX, 0.0f, rightOutZ).method_39415(cEdge);
        buffer.method_22918(matrix, rightInX, 0.0f, 0.0f).method_39415(cWing);
        buffer.method_22918(matrix, 0.0f, 0.0f, tipZ).method_39415(cTip);
        buffer.method_22918(matrix, rightInX, 0.0f, 0.0f).method_39415(cWing);
        buffer.method_22918(matrix, 0.0f, 0.0f, 0.0f).method_39415(cWing);
        matrices.method_22909();
    }

    private void method1511(class_4587 matrices, class_287 buffer, class_4184 camera, float bladeAngleDeg, float yCenter, float orbitRadius, float alpha, int bladeIndex, float globalAngle, Color baseColor, boolean cameraOrbit, float size) {
        float angleRad = (float)Math.toRadians(bladeAngleDeg);
        float radiusFactor = this.method0715(bladeIndex);
        float posA = class_3532.method_15362((float)angleRad) * orbitRadius * radiusFactor;
        float posB = class_3532.method_15374((float)angleRad) * orbitRadius * radiusFactor;
        int idx = bladeIndex % 6;
        float tiltTime = (float)Math.toRadians(globalAngle * field0378[idx]) + field0374[idx];
        float rollTime = (float)Math.toRadians(globalAngle * field0452[idx]) + field0447[idx];
        float bobTime = (float)Math.toRadians(globalAngle * field0283[idx]) + field0278[idx];
        float tiltAngle = class_3532.method_15374((float)tiltTime) * field0383[idx];
        float rollAngle = class_3532.method_15374((float)rollTime) * field0456[idx];
        float bob = class_3532.method_15374((float)bobTime) * field0288[idx];
        class_4587 tempStack = new class_4587();
        if (cameraOrbit) {
            tempStack.method_46416(posA, yCenter + posB, bob);
            tempStack.method_22907(class_7833.field_40718.rotationDegrees(bladeAngleDeg - 90.0f));
            tempStack.method_22907(class_7833.field_40714.rotationDegrees(-90.0f));
            tempStack.method_22907(class_7833.field_40716.rotationDegrees(tiltAngle));
            tempStack.method_22907(class_7833.field_40714.rotationDegrees(rollAngle));
        } else {
            tempStack.method_46416(posA, yCenter + bob, posB);
            tempStack.method_22907(class_7833.field_40716.rotationDegrees(-bladeAngleDeg - 90.0f));
            tempStack.method_22907(class_7833.field_40714.rotationDegrees(tiltAngle));
            tempStack.method_22907(class_7833.field_40718.rotationDegrees(rollAngle));
        }
        tempStack.method_22905(size, size, size);
        Matrix4f bladeTransform = new Matrix4f((Matrix4fc)tempStack.method_23760().method_23761());
        for (float[] point : field0250) {
            float localX = point[0];
            float localY = point[1];
            float localZ = point[2];
            float glowSize = point[3];
            float alphaFactor = point[4];
            Vector4f transformed = new Vector4f(localX, localY, localZ, 1.0f);
            bladeTransform.transform(transformed);
            int c = this.method0965(baseColor, (int)(alpha * 22.0f * alphaFactor));
            matrices.method_22903();
            matrices.method_46416(transformed.x, transformed.y, transformed.z);
            if (!cameraOrbit) {
                matrices.method_22907(camera.method_23767());
            }
            Matrix4f matrix = matrices.method_23760().method_23761();
            buffer.method_22918(matrix, -glowSize, glowSize, 0.0f).method_22913(0.0f, 1.0f).method_39415(c);
            buffer.method_22918(matrix, glowSize, glowSize, 0.0f).method_22913(1.0f, 1.0f).method_39415(c);
            buffer.method_22918(matrix, glowSize, -glowSize, 0.0f).method_22913(1.0f, 0.0f).method_39415(c);
            buffer.method_22918(matrix, -glowSize, -glowSize, 0.0f).method_22913(0.0f, 0.0f).method_39415(c);
            matrices.method_22909();
        }
    }

    static {
        float alphaF;
        float size;
        float z;
        float x;
        float t;
        int i;
        field0160 = class_2960.method_60655((String)"daamky", (String)"textures/target.png");
        field1522 = class_2960.method_60655((String)"daamky", (String)"textures/glow.png");
        field1044 = class_2960.method_60655((String)"daamky", (String)"textures/spark.png");
        field1329 = new class_243[][]{{new class_243(0.0, 0.8500003162575299, 0.8000000015134241), new class_243(0.20000001585804067, 0.8500003162575299, -0.6750000685489941), new class_243(0.6000003159631385, 1.3499998137357587, 0.6000003159631385), new class_243(-0.7400002388842586, 1.0499995624093656, 0.39999995534312804), new class_243(0.7400000600814527, 0.950000007956495, -0.40000007458364306), new class_243(-0.475000060783353, 0.8500003162575299, -0.375), new class_243(0.0, 1.3499998137357587, -0.6000002870957047), new class_243(0.8500003162575299, 0.7000001202807937, 0.09999998819572534), new class_243(-0.7000000376259103, 1.3499998137357587, -0.30000000195726123), new class_243(-0.30000000195726123, 1.3499998137357587, 0.550000008949574), new class_243(-0.5, 0.7000001202807937, 0.7000001202807937), new class_243(0.5, 0.7000001202807937, 0.7000001202807937), new class_243(-0.7000000376259103, 0.75, 0.0), new class_243(-0.20000003729295496, 0.6499997319148408, -0.7000000376259103)}};
        field1401 = new class_243[][]{{new class_243(-49.0, 0.0, 40.0), new class_243(35.0, 0.0, -30.0), new class_243(-30.0, 0.0, 35.0), new class_243(-25.0, 0.0, -30.0), new class_243(0.0, 0.0, 0.0), new class_243(30.0, 0.0, -25.0), new class_243(45.0, 0.0, 0.0), new class_243(-30.0, 0.0, 30.0), new class_243(0.0, 0.0, 0.0), new class_243(0.0, 0.0, 0.0), new class_243(0.0, 0.0, 0.0), new class_243(0.0, 0.0, 0.0), new class_243(0.0, 0.0, 0.0), new class_243(0.0, 0.0, 0.0)}};
        field0378 = new float[]{0.55f, 0.72f, 0.48f, 0.65f, 0.58f, 0.8f};
        field0374 = new float[]{0.0f, 1.2f, 2.8f, 4.1f, 5.5f, 0.7f};
        field0383 = new float[]{50.0f, 65.0f, 40.0f, 60.0f, 55.0f, 70.0f};
        field0452 = new float[]{0.35f, 0.5f, 0.42f, 0.6f, 0.38f, 0.55f};
        field0447 = new float[]{1.0f, 3.5f, 0.5f, 2.2f, 4.8f, 1.8f};
        field0456 = new float[]{20.0f, 30.0f, 25.0f, 35.0f, 22.0f, 28.0f};
        field0283 = new float[]{0.4f, 0.55f, 0.35f, 0.5f, 0.45f, 0.62f};
        field0278 = new float[]{0.5f, 2.0f, 3.8f, 1.3f, 4.5f, 0.2f};
        field0288 = new float[]{0.08f, 0.12f, 0.07f, 0.1f, 0.09f, 0.11f};
        int armSamples = 12;
        int total = 3 + armSamples * 2;
        field0250 = new float[total][5];
        int idx = 0;
        TargetESP.field0250[idx++] = new float[]{0.0f, 0.0f, -0.25f, 0.22f, 1.0f};
        for (i = 1; i <= armSamples; ++i) {
            t = (float)i / (float)(armSamples + 1);
            x = -0.5f * t;
            z = class_3532.method_16439((float)t, (float)-0.25f, (float)0.2f);
            size = class_3532.method_16439((float)t, (float)0.18f, (float)0.14f);
            alphaF = class_3532.method_16439((float)t, (float)0.9f, (float)0.7f);
            TargetESP.field0250[idx++] = new float[]{x, 0.0f, z, size, alphaF};
        }
        TargetESP.field0250[idx++] = new float[]{-0.5f, 0.0f, 0.2f, 0.2f, 0.9f};
        for (i = 1; i <= armSamples; ++i) {
            t = (float)i / (float)(armSamples + 1);
            x = 0.5f * t;
            z = class_3532.method_16439((float)t, (float)-0.25f, (float)0.2f);
            size = class_3532.method_16439((float)t, (float)0.18f, (float)0.14f);
            alphaF = class_3532.method_16439((float)t, (float)0.9f, (float)0.7f);
            TargetESP.field0250[idx++] = new float[]{x, 0.0f, z, size, alphaF};
        }
        TargetESP.field0250[idx] = new float[]{0.5f, 0.0f, 0.2f, 0.2f, 0.9f};
    }

    public static enum Mode implements DisplayNamed {
        field0699("Orbit"),
        field0123("Particles"),
        field1492("Shapes"),
        field1019("Blades"),
        field0783("Circle"),
        field1266("Cube");

        private final String field0336;
        private Mode(String name) {
            this.field0336 = name;
        }
    }

    public static enum TextureStyle implements DisplayNamed {
        field0698("Target"),
        field0122("Glow"),
        field1491("Spark");

        private final String field1030;
        private TextureStyle(String name) {
            this.field1030 = name;
        }
    }

    public static enum ShapeType implements DisplayNamed {
        field0700("Octahedron"),
        field0124("Cube"),
        field1493("Icosahedron"),
        field1020("Star");

        private final String field0791;
        private ShapeType(String name) {
            this.field0791 = name;
        }
    }

    public static enum AnimationMode implements DisplayNamed {
        field0697("Camera"),
        field0121("Horizontal");

        private final String field1504;
        private AnimationMode(String name) {
            this.field1504 = name;
        }
    }

    private static class RenderPoint {
        class_1309 field0731;
        float field0003;
        double field1409;
        double field0956;
        double field0757;

        RenderPoint(class_1309 entity) {
            this.field0731 = entity;
            this.field0003 = 1.0f;
            this.field1409 = entity.method_23317();
            this.field0956 = entity.method_23318();
            this.field0757 = entity.method_23321();
        }
    }

    private static class TargetState {
        double field0565;
        double field0002;
        double field1409;
        double field0956;
        double field0757;
        double field1241;
        float field0314;
        float field0177;
        float field0458;
        float field1614;
        float field1538;
        float field1704;
        float field1136;
        float field1087;
        float field1196;
        float field0871;
        float field0826;
        float field0910;
        float field1331;

        private TargetState() {
        }
    }

    private static class RenderShape {
        double field0565;
        double field0002;
        double field1409;
        double field0956;
        double field0757;
        double field1241;
        float field0314;
        float field0177;
        float field0458;

        private RenderShape() {
        }
    }

    private static class TargetAnimation {
        final class_243 field0735;
        final class_243 field0157;
        static final float field1410 = 0.05f;
        final float field0957;

        TargetAnimation(class_243 position, class_243 rotation) {
            this.field0735 = position;
            this.field0157 = rotation;
            this.field0957 = 0.5f + (float)(Math.random() * 1.5);
        }

        void method1476(class_4587 ms, float anim, int baseColor, class_4184 camera, ShapeType shape, float spread) {
            ms.method_22903();
            ms.method_22904(this.field0735.field_1352 * (double)spread, this.field0735.field_1351, this.field0735.field_1350 * (double)spread);
            float pulsation = 1.0f + (float)(Math.sin((double)System.currentTimeMillis() / 500.0) * (double)0.1f);
            ms.method_22905(pulsation, pulsation, pulsation);
            float selfRot = (float)(System.currentTimeMillis() % 36000L) / 100.0f * this.field0957;
            ms.method_22907(class_7833.field_40714.rotationDegrees((float)this.field0157.field_1352));
            ms.method_22907(class_7833.field_40716.rotationDegrees((float)this.field0157.field_1351 + selfRot));
            ms.method_22907(class_7833.field_40718.rotationDegrees((float)this.field0157.field_1350));
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
            TargetAnimation.method1480(ms, baseColor, 0.2f, true, anim, shape);
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
            TargetAnimation.method1480(ms, baseColor, 0.3f, true, anim, shape);
            TargetAnimation.method1480(ms, baseColor, 0.8f, false, anim, shape);
            RenderSystem.depthMask((boolean)false);
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
            ms.method_22903();
            ms.method_22905(1.2f, 1.2f, 1.2f);
            TargetAnimation.method1480(ms, baseColor, 0.3f, true, anim, shape);
            ms.method_22909();
            this.method1479(ms, baseColor, anim, camera);
            RenderSystem.depthMask((boolean)true);
            RenderSystem.disableBlend();
            RenderSystem.enableCull();
            ms.method_22909();
        }

        private void method1479(class_4587 ms, int baseColor, float anim, class_4184 camera) {
            RenderSystem.setShader((class_10156)class_10142.field_53880);
            RenderSystem.setShaderTexture((int)0, (class_2960)field1522);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
            RenderSystem.depthMask((boolean)false);
            int bloomColor = baseColor & 0xFFFFFF | Math.min(255, (int)(10.0f * anim)) << 24;
            float bloomSize = 0.65000004f;
            for (int i = 0; i < 6; ++i) {
                ms.method_22903();
                ms.method_22907(class_7833.field_40716.rotationDegrees(60.0f * (float)i));
                ms.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
                ms.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
                Matrix4f matrix = ms.method_23760().method_23761();
                class_287 buf = class_289.method_1348().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
                buf.method_22918(matrix, -bloomSize / 2.0f, -bloomSize / 2.0f, 0.0f).method_22913(0.0f, 1.0f).method_39415(bloomColor);
                buf.method_22918(matrix, bloomSize / 2.0f, -bloomSize / 2.0f, 0.0f).method_22913(1.0f, 1.0f).method_39415(bloomColor);
                buf.method_22918(matrix, bloomSize / 2.0f, bloomSize / 2.0f, 0.0f).method_22913(1.0f, 0.0f).method_39415(bloomColor);
                buf.method_22918(matrix, -bloomSize / 2.0f, bloomSize / 2.0f, 0.0f).method_22913(0.0f, 0.0f).method_39415(bloomColor);
                class_286.method_43433((class_9801)buf.method_60800());
                ms.method_22909();
            }
            RenderSystem.depthMask((boolean)true);
            RenderSystem.blendFunc((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA);
        }

        private static void method1480(class_4587 ms, int baseColor, float alpha, boolean filled, float anim, ShapeType shape) {
            RenderSystem.setShader((class_10156)class_10142.field_53876);
            int finalColor = baseColor & 0xFFFFFF | Math.min(255, (int)(alpha * 255.0f * anim)) << 24;
            Matrix4f matrix = ms.method_23760().method_23761();
            switch (shape.ordinal()) {
                case 0: {
                    TargetAnimation.method0344(matrix, finalColor, filled);
                    break;
                }
                case 1: {
                    TargetAnimation.method2177(matrix, finalColor, filled);
                    break;
                }
                case 2: {
                    TargetAnimation.method1875(matrix, finalColor, filled);
                    break;
                }
                case 3: {
                    TargetAnimation.method1675(matrix, finalColor, filled);
                }
            }
        }

        private static void method1561(Matrix4f matrix, int color, boolean filled) {
            float s = 0.05f;
            int n = 8;
            ArrayList<class_243> top = new ArrayList<class_243>();
            ArrayList<class_243> bot = new ArrayList<class_243>();
            for (int i = 0; i < n; ++i) {
                float a = (float)(6.283187810787048 * (double)i / (double)n);
                top.add(new class_243((double)s * Math.cos(a), (double)(s / 2.0f), (double)s * Math.sin(a)));
                bot.add(new class_243((double)s * Math.cos(a), (double)(-s / 2.0f), (double)s * Math.sin(a)));
            }
            class_243 vt = new class_243(0.0, (double)(s * 2.0f), 0.0);
            class_243 vb = new class_243(0.0, (double)(-s * 2.0f), 0.0);
            TargetAnimation.method1562(matrix, color, filled, top, bot, vt, vb, n);
        }

        private static void method0344(Matrix4f matrix, int color, boolean filled) {
            float s = 0.075f;
            class_243 t = new class_243(0.0, (double)s, 0.0);
            class_243 b = new class_243(0.0, (double)(-s), 0.0);
            class_243 f = new class_243((double)s, 0.0, 0.0);
            class_243 bk = new class_243((double)(-s), 0.0, 0.0);
            class_243 l = new class_243(0.0, 0.0, (double)s);
            class_243 r = new class_243(0.0, 0.0, (double)(-s));
            class_287 buf = TargetAnimation.method1569(filled);
            if (filled) {
                TargetAnimation.method1388(buf, matrix, t, f, l, color);
                TargetAnimation.method1388(buf, matrix, t, l, bk, color);
                TargetAnimation.method1388(buf, matrix, t, bk, r, color);
                TargetAnimation.method1388(buf, matrix, t, r, f, color);
                TargetAnimation.method1388(buf, matrix, b, l, f, color);
                TargetAnimation.method1388(buf, matrix, b, bk, l, color);
                TargetAnimation.method1388(buf, matrix, b, r, bk, color);
                TargetAnimation.method1388(buf, matrix, b, f, r, color);
            } else {
                TargetAnimation.method1387(buf, matrix, t, f, color);
                TargetAnimation.method1387(buf, matrix, t, bk, color);
                TargetAnimation.method1387(buf, matrix, t, l, color);
                TargetAnimation.method1387(buf, matrix, t, r, color);
                TargetAnimation.method1387(buf, matrix, b, f, color);
                TargetAnimation.method1387(buf, matrix, b, bk, color);
                TargetAnimation.method1387(buf, matrix, b, l, color);
                TargetAnimation.method1387(buf, matrix, b, r, color);
                TargetAnimation.method1387(buf, matrix, f, l, color);
                TargetAnimation.method1387(buf, matrix, l, bk, color);
                TargetAnimation.method1387(buf, matrix, bk, r, color);
                TargetAnimation.method1387(buf, matrix, r, f, color);
            }
            class_286.method_43433((class_9801)buf.method_60800());
        }

        private static void method2177(Matrix4f matrix, int color, boolean filled) {
            float s = 0.05f;
            class_243[] class_243Array = new class_243[8];
            class_243Array[0] = new class_243((double)(-s), (double)(-s), (double)(-s));
            class_243Array[1] = new class_243((double)s, (double)(-s), (double)(-s));
            class_243Array[2] = new class_243((double)s, (double)s, (double)(-s));
            class_243Array[3] = new class_243((double)(-s), (double)s, (double)(-s));
            class_243Array[4] = new class_243((double)(-s), (double)(-s), (double)s);
            class_243Array[5] = new class_243((double)s, (double)(-s), (double)s);
            class_243Array[6] = new class_243((double)s, (double)s, (double)s);
            class_243Array[7] = new class_243((double)(-s), (double)s, (double)s);
            class_243[] v = class_243Array;
            int[][] nArrayArray = new int[6][];
            nArrayArray[0] = new int[]{0, 1, 2, 3};
            int[] nArray = new int[4];
            nArray[0] = 5;
            nArray[1] = 4;
            nArray[2] = 7;
            nArray[3] = 6;
            nArrayArray[1] = nArray;
            int[] nArray2 = new int[4];
            nArray2[0] = 1;
            nArray2[1] = 5;
            nArray2[2] = 6;
            nArray2[3] = 2;
            nArrayArray[2] = nArray2;
            int[] nArray3 = new int[4];
            nArray3[0] = 4;
            nArray3[1] = 0;
            nArray3[2] = 3;
            nArray3[3] = 7;
            nArrayArray[3] = nArray3;
            int[] nArray4 = new int[4];
            nArray4[0] = 3;
            nArray4[1] = 2;
            nArray4[2] = 6;
            nArray4[3] = 7;
            nArrayArray[4] = nArray4;
            nArrayArray[5] = new int[]{4, 5, 1, 0};
            int[][] faces = nArrayArray;
            class_287 buf = TargetAnimation.method1569(filled);
            if (filled) {
                for (int[] f : faces) {
                    TargetAnimation.method1388(buf, matrix, v[f[0]], v[f[1]], v[f[2]], color);
                    TargetAnimation.method1388(buf, matrix, v[f[0]], v[f[2]], v[f[3]], color);
                }
            } else {
                int[][] edges;
                int[][] nArrayArray2 = new int[12][];
                nArrayArray2[0] = new int[]{0, 1};
                nArrayArray2[1] = new int[]{1, 2};
                nArrayArray2[2] = new int[]{2, 3};
                nArrayArray2[3] = new int[]{3, 0};
                nArrayArray2[4] = new int[]{4, 5};
                int[] nArray5 = new int[2];
                nArray5[0] = 5;
                nArray5[1] = 6;
                nArrayArray2[5] = nArray5;
                int[] nArray6 = new int[2];
                nArray6[0] = 6;
                nArray6[1] = 7;
                nArrayArray2[6] = nArray6;
                int[] nArray7 = new int[2];
                nArray7[0] = 7;
                nArray7[1] = 4;
                nArrayArray2[7] = nArray7;
                nArrayArray2[8] = new int[]{0, 4};
                nArrayArray2[9] = new int[]{1, 5};
                int[] nArray8 = new int[2];
                nArray8[0] = 2;
                nArray8[1] = 6;
                nArrayArray2[10] = nArray8;
                int[] nArray9 = new int[2];
                nArray9[0] = 3;
                nArray9[1] = 7;
                nArrayArray2[11] = nArray9;
                for (int[] e : edges = nArrayArray2) {
                    TargetAnimation.method1387(buf, matrix, v[e[0]], v[e[1]], color);
                }
            }
            class_286.method_43433((class_9801)buf.method_60800());
        }

        private static void method1875(Matrix4f matrix, int color, boolean filled) {
            float s = 0.060000002f;
            float phi = (1.0f + (float)Math.sqrt(5.0)) / 2.0f;
            float a = s;
            float b = s * phi;
            class_243[] class_243Array = new class_243[12];
            class_243Array[0] = new class_243((double)(-a), (double)b, 0.0);
            class_243Array[1] = new class_243((double)a, (double)b, 0.0);
            class_243Array[2] = new class_243((double)(-a), (double)(-b), 0.0);
            class_243Array[3] = new class_243((double)a, (double)(-b), 0.0);
            class_243Array[4] = new class_243(0.0, (double)(-a), (double)b);
            class_243Array[5] = new class_243(0.0, (double)a, (double)b);
            class_243Array[6] = new class_243(0.0, (double)(-a), (double)(-b));
            class_243Array[7] = new class_243(0.0, (double)a, (double)(-b));
            class_243Array[8] = new class_243((double)b, 0.0, (double)(-a));
            class_243Array[9] = new class_243((double)b, 0.0, (double)a);
            class_243Array[10] = new class_243((double)(-b), 0.0, (double)(-a));
            class_243Array[11] = new class_243((double)(-b), 0.0, (double)a);
            class_243[] v = class_243Array;
            int[][] nArrayArray = new int[20][];
            int[] nArray = new int[3];
            nArray[0] = 0;
            nArray[1] = 11;
            nArray[2] = 5;
            nArrayArray[0] = nArray;
            nArrayArray[1] = new int[]{0, 5, 1};
            int[] nArray2 = new int[3];
            nArray2[0] = 0;
            nArray2[1] = 1;
            nArray2[2] = 7;
            nArrayArray[2] = nArray2;
            int[] nArray3 = new int[3];
            nArray3[0] = 0;
            nArray3[1] = 7;
            nArray3[2] = 10;
            nArrayArray[3] = nArray3;
            int[] nArray4 = new int[3];
            nArray4[0] = 0;
            nArray4[1] = 10;
            nArray4[2] = 11;
            nArrayArray[4] = nArray4;
            int[] nArray5 = new int[3];
            nArray5[0] = 1;
            nArray5[1] = 5;
            nArray5[2] = 9;
            nArrayArray[5] = nArray5;
            int[] nArray6 = new int[3];
            nArray6[0] = 5;
            nArray6[1] = 11;
            nArray6[2] = 4;
            nArrayArray[6] = nArray6;
            int[] nArray7 = new int[3];
            nArray7[0] = 11;
            nArray7[1] = 10;
            nArray7[2] = 2;
            nArrayArray[7] = nArray7;
            int[] nArray8 = new int[3];
            nArray8[0] = 10;
            nArray8[1] = 7;
            nArray8[2] = 6;
            nArrayArray[8] = nArray8;
            int[] nArray9 = new int[3];
            nArray9[0] = 7;
            nArray9[1] = 1;
            nArray9[2] = 8;
            nArrayArray[9] = nArray9;
            int[] nArray10 = new int[3];
            nArray10[0] = 3;
            nArray10[1] = 9;
            nArray10[2] = 4;
            nArrayArray[10] = nArray10;
            nArrayArray[11] = new int[]{3, 4, 2};
            int[] nArray11 = new int[3];
            nArray11[0] = 3;
            nArray11[1] = 2;
            nArray11[2] = 6;
            nArrayArray[12] = nArray11;
            int[] nArray12 = new int[3];
            nArray12[0] = 3;
            nArray12[1] = 6;
            nArray12[2] = 8;
            nArrayArray[13] = nArray12;
            int[] nArray13 = new int[3];
            nArray13[0] = 3;
            nArray13[1] = 8;
            nArray13[2] = 9;
            nArrayArray[14] = nArray13;
            int[] nArray14 = new int[3];
            nArray14[0] = 4;
            nArray14[1] = 9;
            nArray14[2] = 5;
            nArrayArray[15] = nArray14;
            int[] nArray15 = new int[3];
            nArray15[0] = 2;
            nArray15[1] = 4;
            nArray15[2] = 11;
            nArrayArray[16] = nArray15;
            int[] nArray16 = new int[3];
            nArray16[0] = 6;
            nArray16[1] = 2;
            nArray16[2] = 10;
            nArrayArray[17] = nArray16;
            int[] nArray17 = new int[3];
            nArray17[0] = 8;
            nArray17[1] = 6;
            nArray17[2] = 7;
            nArrayArray[18] = nArray17;
            int[] nArray18 = new int[3];
            nArray18[0] = 9;
            nArray18[1] = 8;
            nArray18[2] = 1;
            nArrayArray[19] = nArray18;
            int[][] faces = nArrayArray;
            class_287 buf = TargetAnimation.method1569(filled);
            if (filled) {
                for (int[] f : faces) {
                    TargetAnimation.method1388(buf, matrix, v[f[0]], v[f[1]], v[f[2]], color);
                }
            } else {
                HashSet<Long> drawn = new HashSet<Long>();
                for (int[] f : faces) {
                    for (int i = 0; i < 3; ++i) {
                        int e0 = Math.min(f[i], f[(i + 1) % 3]);
                        int e1 = Math.max(f[i], f[(i + 1) % 3]);
                        long key = (long)e0 << 32 | (long)e1;
                        if (!drawn.add(key)) continue;
                        TargetAnimation.method1387(buf, matrix, v[e0], v[e1], color);
                    }
                }
            }
            class_286.method_43433((class_9801)buf.method_60800());
        }

        private static void method1675(Matrix4f matrix, int color, boolean filled) {
            float s = 0.05f;
            float tip = 0.125f;
            class_243[] class_243Array = new class_243[6];
            class_243Array[0] = new class_243((double)tip, 0.0, 0.0);
            class_243Array[1] = new class_243((double)(-tip), 0.0, 0.0);
            class_243Array[2] = new class_243(0.0, (double)tip, 0.0);
            class_243Array[3] = new class_243(0.0, (double)(-tip), 0.0);
            class_243Array[4] = new class_243(0.0, 0.0, (double)tip);
            class_243Array[5] = new class_243(0.0, 0.0, (double)(-tip));
            class_243[] tips = class_243Array;
            class_243[] class_243Array2 = new class_243[8];
            class_243Array2[0] = new class_243((double)s, (double)s, (double)s);
            class_243Array2[1] = new class_243((double)s, (double)s, (double)(-s));
            class_243Array2[2] = new class_243((double)s, (double)(-s), (double)s);
            class_243Array2[3] = new class_243((double)s, (double)(-s), (double)(-s));
            class_243Array2[4] = new class_243((double)(-s), (double)s, (double)s);
            class_243Array2[5] = new class_243((double)(-s), (double)s, (double)(-s));
            class_243Array2[6] = new class_243((double)(-s), (double)(-s), (double)s);
            class_243Array2[7] = new class_243((double)(-s), (double)(-s), (double)(-s));
            class_243[] mid = class_243Array2;
            int[][] nArrayArray = new int[24][];
            nArrayArray[0] = new int[]{0, 0, 1};
            nArrayArray[1] = new int[]{0, 1, 3};
            nArrayArray[2] = new int[]{0, 3, 2};
            nArrayArray[3] = new int[]{0, 2, 0};
            nArrayArray[4] = new int[]{1, 4, 5};
            int[] nArray = new int[3];
            nArray[0] = 1;
            nArray[1] = 5;
            nArray[2] = 7;
            nArrayArray[5] = nArray;
            int[] nArray2 = new int[3];
            nArray2[0] = 1;
            nArray2[1] = 7;
            nArray2[2] = 6;
            nArrayArray[6] = nArray2;
            int[] nArray3 = new int[3];
            nArray3[0] = 1;
            nArray3[1] = 6;
            nArray3[2] = 4;
            nArrayArray[7] = nArray3;
            nArrayArray[8] = new int[]{2, 0, 1};
            nArrayArray[9] = new int[]{2, 1, 5};
            nArrayArray[10] = new int[]{2, 5, 4};
            nArrayArray[11] = new int[]{2, 4, 0};
            nArrayArray[12] = new int[]{3, 2, 3};
            int[] nArray4 = new int[3];
            nArray4[0] = 3;
            nArray4[1] = 3;
            nArray4[2] = 7;
            nArrayArray[13] = nArray4;
            int[] nArray5 = new int[3];
            nArray5[0] = 3;
            nArray5[1] = 7;
            nArray5[2] = 6;
            nArrayArray[14] = nArray5;
            int[] nArray6 = new int[3];
            nArray6[0] = 3;
            nArray6[1] = 6;
            nArray6[2] = 2;
            nArrayArray[15] = nArray6;
            nArrayArray[16] = new int[]{4, 0, 2};
            int[] nArray7 = new int[3];
            nArray7[0] = 4;
            nArray7[1] = 2;
            nArray7[2] = 6;
            nArrayArray[17] = nArray7;
            int[] nArray8 = new int[3];
            nArray8[0] = 4;
            nArray8[1] = 6;
            nArray8[2] = 4;
            nArrayArray[18] = nArray8;
            nArrayArray[19] = new int[]{4, 4, 0};
            nArrayArray[20] = new int[]{5, 1, 3};
            int[] nArray9 = new int[3];
            nArray9[0] = 5;
            nArray9[1] = 3;
            nArray9[2] = 7;
            nArrayArray[21] = nArray9;
            int[] nArray10 = new int[3];
            nArray10[0] = 5;
            nArray10[1] = 7;
            nArray10[2] = 5;
            nArrayArray[22] = nArray10;
            nArrayArray[23] = new int[]{5, 5, 1};
            int[][] faces = nArrayArray;
            class_287 buf = TargetAnimation.method1569(filled);
            if (filled) {
                for (int[] f : faces) {
                    TargetAnimation.method1388(buf, matrix, tips[f[0]], mid[f[1]], mid[f[2]], color);
                }
            } else {
                for (class_243 t : tips) {
                    for (class_243 m : mid) {
                        double d = t.method_1022(m);
                        if (!(d < (double)tip * 1.5)) continue;
                        TargetAnimation.method1387(buf, matrix, t, m, color);
                    }
                }
            }
            class_286.method_43433((class_9801)buf.method_60800());
        }

        private static void method1562(Matrix4f matrix, int color, boolean filled, List<class_243> top, List<class_243> bot, class_243 vt, class_243 vb, int n) {
            class_287 buf = TargetAnimation.method1569(filled);
            if (filled) {
                for (int i = 0; i < n; ++i) {
                    int j = (i + 1) % n;
                    TargetAnimation.method1388(buf, matrix, bot.get(i), bot.get(j), top.get(j), color);
                    TargetAnimation.method1388(buf, matrix, bot.get(i), top.get(j), top.get(i), color);
                    TargetAnimation.method1388(buf, matrix, vt, top.get(i), top.get(j), color);
                    TargetAnimation.method1388(buf, matrix, vb, bot.get(j), bot.get(i), color);
                }
            } else {
                for (int i = 0; i < n; ++i) {
                    int j = (i + 1) % n;
                    TargetAnimation.method1387(buf, matrix, top.get(i), top.get(j), color);
                    TargetAnimation.method1387(buf, matrix, bot.get(i), bot.get(j), color);
                    TargetAnimation.method1387(buf, matrix, top.get(i), bot.get(i), color);
                    TargetAnimation.method1387(buf, matrix, vt, top.get(i), color);
                    TargetAnimation.method1387(buf, matrix, vb, bot.get(i), color);
                }
            }
            class_286.method_43433((class_9801)buf.method_60800());
        }

        private static class_287 method1569(boolean filled) {
            return class_289.method_1348().method_60827(filled ? class_293.class_5596.field_27379 : class_293.class_5596.field_29344, class_290.field_1576);
        }

        private static void method1388(class_287 buf, Matrix4f m, class_243 a, class_243 b, class_243 c, int color) {
            buf.method_22918(m, (float)a.field_1352, (float)a.field_1351, (float)a.field_1350).method_39415(color);
            buf.method_22918(m, (float)b.field_1352, (float)b.field_1351, (float)b.field_1350).method_39415(color);
            buf.method_22918(m, (float)c.field_1352, (float)c.field_1351, (float)c.field_1350).method_39415(color);
        }

        private static void method1387(class_287 buf, Matrix4f m, class_243 a, class_243 b, int color) {
            buf.method_22918(m, (float)a.field_1352, (float)a.field_1351, (float)a.field_1350).method_39415(color);
            buf.method_22918(m, (float)b.field_1352, (float)b.field_1351, (float)b.field_1350).method_39415(color);
        }
    }
}

