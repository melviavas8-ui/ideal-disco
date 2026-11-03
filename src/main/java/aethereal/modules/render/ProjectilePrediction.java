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
 *  net.minecraft.class_1297
 *  net.minecraft.class_1306
 *  net.minecraft.class_1309
 *  net.minecraft.class_1542
 *  net.minecraft.class_1665
 *  net.minecraft.class_1667
 *  net.minecraft.class_1676
 *  net.minecraft.class_1680
 *  net.minecraft.class_1681
 *  net.minecraft.class_1683
 *  net.minecraft.class_1684
 *  net.minecraft.class_1685
 *  net.minecraft.class_1686
 *  net.minecraft.class_1753
 *  net.minecraft.class_1764
 *  net.minecraft.class_1771
 *  net.minecraft.class_1776
 *  net.minecraft.class_1779
 *  net.minecraft.class_1792
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1823
 *  net.minecraft.class_1828
 *  net.minecraft.class_1835
 *  net.minecraft.class_1937
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2678
 *  net.minecraft.class_286
 *  net.minecraft.class_287
 *  net.minecraft.class_290
 *  net.minecraft.class_293$class_5596
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_3486
 *  net.minecraft.class_3532
 *  net.minecraft.class_3857
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_7833
 *  net.minecraft.class_811
 *  net.minecraft.class_9278
 *  net.minecraft.class_9334
 *  net.minecraft.class_9801
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 */
package aethereal;

import aethereal.BooleanSetting;
import aethereal.ColorMath;
import aethereal.ColorSetting;
import aethereal.FontSize;
import aethereal.Fonts;
import aethereal.GlowRenderEvent;
import aethereal.GuiRenderHelper;
import aethereal.HudRenderEvent;
import aethereal.MathHelper;
import aethereal.Module;
import aethereal.ModuleCategory;
import aethereal.OutlineShaderRenderer;
import aethereal.PacketEvent;
import aethereal.ProjectionHelper;
import aethereal.ScreenLayoutHelper;
import aethereal.Setting;
import aethereal.ThemeColorManager;
import aethereal.ThemePalette;
import aethereal.WorldRenderEvent;
import aethereal.WorldRenderHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Color;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_10142;
import net.minecraft.class_10156;
import net.minecraft.class_1297;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1542;
import net.minecraft.class_1665;
import net.minecraft.class_1667;
import net.minecraft.class_1676;
import net.minecraft.class_1680;
import net.minecraft.class_1681;
import net.minecraft.class_1683;
import net.minecraft.class_1684;
import net.minecraft.class_1685;
import net.minecraft.class_1686;
import net.minecraft.class_1753;
import net.minecraft.class_1764;
import net.minecraft.class_1771;
import net.minecraft.class_1776;
import net.minecraft.class_1779;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1823;
import net.minecraft.class_1828;
import net.minecraft.class_1835;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2678;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_290;
import net.minecraft.class_293;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_3486;
import net.minecraft.class_3532;
import net.minecraft.class_3857;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import net.minecraft.class_9278;
import net.minecraft.class_9334;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.patch.daamky.api.mixins.accessors.GameRendererAccessor;

public class ProjectilePrediction
extends Module {
    private static ProjectilePrediction field0103;
    private final BooleanSetting field1432 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("projectileprediction.renderhand", true).method1007("Render Hand")).method0210("Show trajectory of your currently held item")).method2130("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u0442\u0440\u0430\u0435\u043a\u0442\u043e\u0440\u0438\u044e \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430 \u0432 \u0440\u0443\u043a\u0435");
    private final BooleanSetting field0970 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("projectileprediction.handline", true, () -> (Boolean)this.field1432.method0492()).method1007("Hand Line")).method0210("Draw a fading line from your hand to the predicted landing spot")).method2130("\u041b\u0438\u043d\u0438\u044f \u043e\u0442 \u0440\u0443\u043a\u0438 \u0434\u043e \u0442\u043e\u0447\u043a\u0438 \u043f\u0440\u0438\u0437\u0435\u043c\u043b\u0435\u043d\u0438\u044f, \u0441 \u0444\u0435\u0439\u0434\u043e\u043c");
    private final BooleanSetting field0184 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("projectileprediction.itemmarker", true, () -> (Boolean)this.field1432.method0492()).method1007("3D Item Marker")).method0210("Show a 3D outline of the predicted item at the landing spot instead of the ring")).method2130("3D-\u043a\u043e\u043d\u0442\u0443\u0440 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430 \u0432 \u0442\u043e\u0447\u043a\u0435 \u043f\u0440\u0438\u0437\u0435\u043c\u043b\u0435\u043d\u0438\u044f \u0432\u043c\u0435\u0441\u0442\u043e \u043a\u043e\u043b\u044c\u0446\u0430");
    private final OutlineShaderRenderer field0465 = new OutlineShaderRenderer();
    private static final class_4588 field1651;
    private static final class_4597 field1572;
    private final BooleanSetting field1709 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("projectileprediction.renderincoming", true).method1007("Render Incoming")).method0210("Show trajectories of incoming projectiles")).method2130("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u0442\u0440\u0430\u0435\u043a\u0442\u043e\u0440\u0438\u0438 \u043e\u0442 \u0438\u0433\u0440\u043e\u043a\u043e\u0432");
    private final BooleanSetting field1141 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("projectileprediction.render2d", true).method1007("2D HUD")).method0210("Show 2D HUD with item icon and time-to-impact")).method2130("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0432\u0440\u0435\u043c\u044f \u0434\u043e \u043e\u043a\u043e\u043d\u0447\u0430\u043d\u0438\u044f \u043f\u0435\u0440\u0435\u0434 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u0435\u043c");
    private final BooleanSetting field1093 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("projectileprediction.crosshair", true).method1007("Crosshair Indicator")).method0210("Show time-to-impact indicator near crosshair")).method2130("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0432\u0440\u0435\u043c\u044f \u0434\u043e \u043e\u043a\u043e\u043d\u0447\u0430\u043d\u0438\u044f \u043f\u043e\u0441\u043b\u0435 \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f");
    private final ColorSetting field1203 = (ColorSetting)((Setting)((Setting)new ColorSetting("projectileprediction.linecolor", 255, 156, 228, 200).method1882().method1007("Line Color")).method0210("Trajectory line color")).method2130("\u0426\u0432\u0435\u0442 \u0442\u0440\u0430\u0435\u043a\u0442\u043e\u0440\u0438\u0438");
    private final List<PathMesh> field0885 = new ArrayList<PathMesh>();
    private final List<class_243> field0843 = new ArrayList<class_243>();
    private final List<class_2350> field0928 = new ArrayList<class_2350>();
    private final List<Boolean> field1347 = new ArrayList<Boolean>();
    private final List<class_243> field1308 = new ArrayList<class_243>();
    private final List<class_243> field1384 = new ArrayList<class_243>();
    private boolean field0404 = false;
    private float field0351 = -1.0f;
    private class_1799 field0441 = class_1799.field_8037;
    private float field0255 = -1.0f;
    private float field0226 = 0.0f;
    private float field0289 = 0.0f;
    private String field0534 = "";
    private String field0513 = "";
    private long field0549 = 0L;
    private long field1673 = 0L;
    private static final int field1658 = 150;
    private class_1297 field1698 = null;
    private final List<PathPoint> field1595 = new ArrayList<PathPoint>();
    private static final long field1579 = 220L;
    private static final long field1603 = 200L;
    private class_1799 field1761 = class_1799.field_8037;
    private float field1739 = -1.0f;
    private long field1767 = 0L;
    private static final class_2960 field1181;

    public static ProjectilePrediction method1716() {
        return field0103;
    }

    public ProjectilePrediction() {
        super("ProjectilePrediction", ModuleCategory.field1004, "Predicts projectile trajectories (incoming + hand item)");
        this.method1013("\u041f\u0440\u0435\u0434\u0441\u043a\u0430\u0437\u044b\u0432\u0430\u0435\u0442 \u0442\u0440\u0430\u0435\u043a\u0442\u043e\u0440\u0438\u0438 \u043f\u0430\u0434\u0430\u044e\u0449\u0438\u0445 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432");
        field0103 = this;
    }

    private ProjectilePath method1747() {
        if (ProjectilePrediction.field0796.field_1724 == null) {
            return new ProjectilePath(0.0f, 0.0f);
        }
        return new ProjectilePath(ProjectilePrediction.field0796.field_1724.method_36454(), ProjectilePrediction.field0796.field_1724.method_36455());
    }

    private Stream<class_1297> method2026() {
        if (ProjectilePrediction.field0796.field_1687 == null) {
            return Stream.empty();
        }
        return StreamSupport.stream(ProjectilePrediction.field0796.field_1687.method_18112().spliterator(), false);
    }

    public List<class_239> method1199(class_1676 entity, double velocity, ProjectilePath angle) {
        return new ArrayList<class_239>(Collections.singletonList(this.method1323(angle.method2077(), entity, velocity)));
    }

    public class_239 method1323(class_243 lookVec, class_1676 entity, double velocity) {
        class_1667 arrow;
        float sqrt = class_3532.method_15355((float)((float)lookVec.method_1027()));
        class_243 motion = entity instanceof class_1667 && (arrow = (class_1667)entity).method_54759().method_7909().equals(class_1802.field_8399) ? class_243.field_1353 : ProjectilePrediction.field0796.field_1724.method_18798();
        return this.method1335(ProjectilePrediction.field0796.field_1724.method_33571().method_1019(MathHelper.method1127((class_1297)ProjectilePrediction.field0796.field_1724).method_1020(ProjectilePrediction.field0796.field_1724.method_19538())), lookVec.method_1021(velocity / (double)sqrt).method_1019(motion), entity);
    }

    public class_239 method1198(class_1676 entity) {
        return this.method1335(entity.method_19538(), entity.method_18798(), entity);
    }

    public class_239 method1335(class_243 pos, class_243 motion, class_1676 entity) {
        this.field1384.clear();
        this.field1384.add(pos);
        for (int i = 0; i < 300; ++i) {
            class_243 prevPos = pos;
            pos = pos.method_1019(motion);
            motion = this.method1147((class_1297)entity, prevPos, motion);
            class_3965 result = ProjectilePrediction.field0796.field_1687.method_17742(new class_3959(prevPos, pos, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)entity));
            if (!result.method_17783().equals((Object)class_239.class_240.field_1333)) {
                this.field1384.add(result.method_17784());
                return result;
            }
            class_243 finalPos = pos;
            class_243 finalPrevPos = prevPos;
            boolean entityHit = this.method2026().filter(ent -> {
                class_1309 living;
                return ent instanceof class_1309 && (living = (class_1309)ent) != entity.method_24921() && living.method_5805();
            }).anyMatch(ent -> this.method1293(ent.method_5829().method_1014(0.3000001804584443), finalPrevPos, finalPos));
            if (entityHit) {
                this.field1384.add(pos);
                return new class_239(this, pos){

                    public class_239.class_240 method_17783() {
                        return class_239.class_240.field_1331;
                    }
                };
            }
            this.field1384.add(pos);
            if (pos.field_1351 < -128.0) break;
        }
        return null;
    }

    private boolean method1293(class_238 box, class_243 from, class_243 to) {
        double minX = Math.min(from.field_1352, to.field_1352) - 0.010000003747132445;
        double minY = Math.min(from.field_1351, to.field_1351) - 0.010000003747132445;
        double minZ = Math.min(from.field_1350, to.field_1350) - 0.010000003747132445;
        double maxX = Math.max(from.field_1352, to.field_1352) + 0.010000003747132445;
        double maxY = Math.max(from.field_1351, to.field_1351) + 0.010000003747132445;
        double maxZ = Math.max(from.field_1350, to.field_1350) + 0.010000003747132445;
        return maxX >= box.field_1323 && minX <= box.field_1320 && maxY >= box.field_1322 && minY <= box.field_1325 && maxZ >= box.field_1321 && minZ <= box.field_1324;
    }

    private void method2015() {
        try {
            class_243 camPos = ProjectilePrediction.field0796.field_1773.method_19418().method_19326();
            Color col = this.field1203.method1726();
            this.field0465.method0578();
            class_4597 wrapped = this.field0465.method1517(field1572, col);
            float spin = (float)(System.currentTimeMillis() % 3600L) / 10.0f;
            for (int i = 0; i < this.field0843.size(); ++i) {
                class_243 p = this.field0843.get(i);
                class_4587 ms = new class_4587();
                ms.method_22904(p.field_1352 - camPos.field_1352, p.field_1351 - camPos.field_1351 + 0.2800001835305881, p.field_1350 - camPos.field_1350);
                ms.method_22905(0.7f, 0.7f, 0.7f);
                ms.method_22907(class_7833.field_40716.rotationDegrees(spin));
                ((GameRendererAccessor)ProjectilePrediction.field0796.field_1773).getFirstPersonRenderer().method_3233((class_1309)ProjectilePrediction.field0796.field_1724, this.field0441, class_811.field_4318, false, ms, wrapped, 0xF000F0);
            }
            this.field0465.method2058().method0578();
            this.field0465.method0764(1, col, false, false, 0.0f, 0.0f, 0.0f, col, false, 0.0f, 0.0f, 0.0f, col, false, 0.0f, 0.0f, col);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @EventHandler
    public void onGlowPass(GlowRenderEvent.Pre event) {
        if (ProjectilePrediction.method1974()) {
            return;
        }
        if (((Boolean)this.field0184.method0492()).booleanValue() && ((Boolean)this.field1432.method0492()).booleanValue() && !this.field0843.isEmpty() && !this.field0441.method_7960()) {
            this.method2015();
        }
        if (this.field0843.isEmpty() && this.field0885.isEmpty()) {
            return;
        }
        class_243 camera = ProjectilePrediction.field0796.field_1773.method_19418().method_19326();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((int)770, (int)32772);
        RenderSystem.enableDepthTest();
        RenderSystem.depthMask((boolean)false);
        RenderSystem.disableCull();
        RenderSystem.setShaderTexture((int)0, (class_2960)field1181);
        RenderSystem.setShader((class_10156)class_10142.field_53880);
        class_287 buffer = RenderSystem.renderThreadTesselator().method_60827(class_293.class_5596.field_27382, class_290.field_1575);
        Matrix4f m = new Matrix4f();
        Color lineCol = this.field1203.method1726();
        int[] vertexCount = new int[]{0};
        class_4184 cam = ProjectilePrediction.field0796.field_1773.method_19418();
        float camYawRad = (float)Math.toRadians(-cam.method_19330());
        float camPitchRad = (float)Math.toRadians(cam.method_19329());
        float cy = (float)Math.cos(camYawRad);
        float sy = (float)Math.sin(camYawRad);
        float cp = (float)Math.cos(camPitchRad);
        float sp = (float)Math.sin(camPitchRad);
        float rightX = cy;
        float rightY = 0.0f;
        float rightZ = -sy;
        float upX = sy * sp;
        float upY = cp;
        float upZ = cy * sp;
        for (int i = 0; i < this.field0843.size(); ++i) {
            class_243 p = this.field0843.get(i);
            class_2350 face = this.field0928.get(i);
            if (!this.field0404) continue;
            vertexCount[0] = vertexCount[0] + this.method1386(buffer, m, p, face, camera, 2.0f, lineCol, 180, rightX, rightY, rightZ, upX, upY, upZ);
        }
        for (PathMesh pt : this.field0885) {
            class_1297 owner;
            boolean isOwn;
            class_1297 class_12972;
            class_1297 class_12973 = pt.entity;
            if (class_12973 instanceof class_1676) {
                class_1676 pe = (class_1676)class_12973;
                class_12972 = pe.method_24921();
            } else {
                class_12972 = null;
            }
            if ((isOwn = (owner = class_12972) == ProjectilePrediction.field0796.field_1724) || !pt.isPotion) continue;
            vertexCount[0] = vertexCount[0] + this.method1386(buffer, m, pt.pos, pt.facing, camera, 4.0f, lineCol, 180, rightX, rightY, rightZ, upX, upY, upZ);
        }
        if (vertexCount[0] > 0) {
            class_286.method_43433((class_9801)buffer.method_60800());
        } else {
            buffer.method_60794();
        }
        RenderSystem.setShaderTexture((int)0, (int)0);
        RenderSystem.enableCull();
        RenderSystem.depthMask((boolean)true);
        RenderSystem.blendFuncSeparate((GlStateManager.class_4535)GlStateManager.class_4535.SRC_ALPHA, (GlStateManager.class_4534)GlStateManager.class_4534.ONE_MINUS_SRC_ALPHA, (GlStateManager.class_4535)GlStateManager.class_4535.ZERO, (GlStateManager.class_4534)GlStateManager.class_4534.ONE);
        RenderSystem.disableBlend();
    }

    private void method1506(class_4587 matrix, class_243 pos, class_2350 surface, Color color) {
        class_243 axisU;
        class_243 axisV = switch (surface) {
            case class_2350.field_11043, class_2350.field_11035 -> {
                axisU = new class_243(1.0, 0.0, 0.0);
                yield new class_243(0.0, 1.0, 0.0);
            }
            case class_2350.field_11039, class_2350.field_11034 -> {
                axisU = new class_243(0.0, 0.0, 1.0);
                yield new class_243(0.0, 1.0, 0.0);
            }
            default -> {
                axisU = new class_243(1.0, 0.0, 0.0);
                yield new class_243(0.0, 0.0, 1.0);
            }
        };
        double width = 0.3000001804584443;
        double offset = 0.015000000495704477;
        class_243 center = new class_243(pos.field_1352 + (double)surface.method_10148() * offset, pos.field_1351 + (double)surface.method_10164() * offset, pos.field_1350 + (double)surface.method_10165() * offset);
        int size = 90;
        for (int i = 0; i < size; ++i) {
            double a1 = 6.283185314844992 * (double)i / (double)size;
            double a2 = 6.283185314844992 * (double)(i + 1) / (double)size;
            class_243 p1 = center.method_1019(axisU.method_1021(Math.cos(a1) * width)).method_1019(axisV.method_1021(Math.sin(a1) * width));
            class_243 p2 = center.method_1019(axisU.method_1021(Math.cos(a2) * width)).method_1019(axisV.method_1021(Math.sin(a2) * width));
            WorldRenderHelper.method1507(matrix, p1, p2, color);
        }
        WorldRenderHelper.method1507(matrix, center.method_1020(axisU.method_1021(width)), center.method_1019(axisU.method_1021(width)), color);
        WorldRenderHelper.method1507(matrix, center.method_1020(axisV.method_1021(width)), center.method_1019(axisV.method_1021(width)), color);
    }

    private void method1382(class_287 buf, Matrix4f m, float cx, float cy, float cz, float halfSize, float rX, float rY, float rZ, float uX, float uY, float uZ, Color col, int alpha) {
        int r = col.getRed();
        int g = col.getGreen();
        int b = col.getBlue();
        float rX2 = rX * halfSize;
        float rY2 = rY * halfSize;
        float rZ2 = rZ * halfSize;
        float uX2 = uX * halfSize;
        float uY2 = uY * halfSize;
        float uZ2 = uZ * halfSize;
        buf.method_22918(m, cx - rX2 - uX2, cy - rY2 - uY2, cz - rZ2 - uZ2).method_22913(0.0f, 1.0f).method_1336(r, g, b, alpha);
        buf.method_22918(m, cx + rX2 - uX2, cy + rY2 - uY2, cz + rZ2 - uZ2).method_22913(1.0f, 1.0f).method_1336(r, g, b, alpha);
        buf.method_22918(m, cx + rX2 + uX2, cy + rY2 + uY2, cz + rZ2 + uZ2).method_22913(1.0f, 0.0f).method_1336(r, g, b, alpha);
        buf.method_22918(m, cx - rX2 + uX2, cy - rY2 + uY2, cz - rZ2 + uZ2).method_22913(0.0f, 0.0f).method_1336(r, g, b, alpha);
    }

    private int method1386(class_287 buf, Matrix4f m, class_243 pos, class_2350 surface, class_243 camera, float radius, Color color, int alpha, float rX, float rY, float rZ, float uX, float uY, float uZ) {
        float cx = (float)(pos.field_1352 - camera.field_1352);
        float cy = (float)(pos.field_1351 - camera.field_1351) + 1.0f;
        float cz = (float)(pos.field_1350 - camera.field_1350);
        int segments = 96;
        float spacing = (float)(6.283185314844992 * (double)radius / (double)segments);
        float dotHalf = spacing * 2.0f;
        for (int i = 0; i < segments; ++i) {
            double angle = 6.283185314844992 * (double)i / (double)segments;
            float px = cx + (float)Math.cos(angle) * radius;
            float py = cy;
            float pz = cz + (float)Math.sin(angle) * radius;
            this.method1382(buf, m, px, py, pz, dotHalf, rX, rY, rZ, uX, uY, uZ, color, alpha);
        }
        return segments * 4;
    }

    @EventHandler
    public void onPacketReceive(PacketEvent.Inbound event) {
        if (event.method1970() instanceof class_2678) {
            this.method2043();
        }
    }

    private void method2043() {
        this.field0885.clear();
        this.field0843.clear();
        this.field0928.clear();
        this.field1347.clear();
        this.field0404 = false;
        this.field0351 = -1.0f;
        this.field0441 = class_1799.field_8037;
        this.field0255 = -1.0f;
        this.field1698 = null;
        this.field1595.clear();
        this.field1761 = class_1799.field_8037;
        this.field1739 = -1.0f;
        this.field1767 = 0L;
        this.field0226 = 0.0f;
        this.field0289 = 0.0f;
        this.field0534 = "";
        this.field0513 = "";
        this.field0549 = 0L;
        this.field1673 = 0L;
    }

    @EventHandler
    public void onWorldRender(WorldRenderEvent.GamePass e) {
        if (ProjectilePrediction.method1974()) {
            return;
        }
        this.field0885.clear();
        this.field0843.clear();
        this.field0928.clear();
        this.field1347.clear();
        this.field1308.clear();
        this.field0404 = false;
        this.field0351 = -1.0f;
        this.field0441 = class_1799.field_8037;
        this.field0255 = -1.0f;
        Color lineCol = this.field1203.method1726();
        int baseColor = lineCol.getRGB();
        if (((Boolean)this.field1432.method0492()).booleanValue()) {
            this.method1495(e.method1629(), ProjectilePrediction.field0796.field_1724.method_5877(), this.method1747());
        }
        long nowMs = System.currentTimeMillis();
        for (PathPoint t2 : this.field1595) {
            if (t2.field1244 != 0L) continue;
            if (!t2.field0730.method_31481() && t2.field0730.method_5805()) {
                if (t2.field0730.field_6012 <= 200) continue;
            }
            t2.method1634();
        }
        this.field1595.removeIf(t -> t.method0161(nowMs));
        for (class_1297 ent : this.method1689()) {
            HitResult tr;
            class_1676 proj;
            if (!(ent instanceof class_1676) || (proj = (class_1676)ent).method_24921() != ProjectilePrediction.field0796.field_1724 || ent.field_6012 >= 40) continue;
            boolean already = false;
            for (PathPoint t3 : this.field1595) {
                if (t3.method0563() != ent) continue;
                already = true;
                break;
            }
            if (already || (tr = this.method0292(ent.method_19538(), ent.method_18798(), proj)) == null) continue;
            class_1799 stk = class_1799.field_8037;
            if (ent instanceof class_3857) {
                class_3857 ti = (class_3857)ent;
                stk = ti.method_7495();
            } else if (ent instanceof class_1665) {
                class_1665 pp = (class_1665)ent;
                stk = pp.method_54759();
            } else if (ent instanceof class_1542) {
                class_1542 ie = (class_1542)ent;
                stk = ie.method_6983();
            }
            if (stk.method_7960()) continue;
            this.field1595.add(new PathPoint(ent, tr.method0568().method_17784(), tr.method0003(), stk));
        }
        class_1297 class_12972 = this.field1698 = this.field1595.isEmpty() ? null : this.field1595.get(0).method0563();
        if (((Boolean)this.field1709.method0492()).booleanValue()) {
            block3: for (class_1297 entity : this.method1689()) {
                class_243 motion = entity.method_18798();
                class_243 pos = entity.method_19538();
                for (int i = 0; i < 300; ++i) {
                    class_243 prevPos = pos;
                    pos = pos.method_1019(motion);
                    motion = this.method1147(entity, prevPos, motion);
                    class_3965 result = ProjectilePrediction.field0796.field_1687.method_17742(new class_3959(prevPos, pos, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, entity));
                    if (!result.method_17783().equals((Object)class_239.class_240.field_1333)) {
                        pos = result.method_17784();
                    }
                    int fadeAlpha = (int)(255.0f * class_3532.method_15363((float)((float)i / 25.0f), (float)0.0f, (float)1.0f));
                    int lineRgb = ColorMath.method1828(baseColor, (float)fadeAlpha / 255.0f);
                    WorldRenderHelper.method1507(e.method1629(), prevPos, pos, new Color(lineRgb, true));
                    if (result.method_17783().equals((Object)class_239.class_240.field_1333) && !(pos.field_1351 < -128.0)) continue;
                    this.method1146(entity, pos, i, (class_239)result);
                    continue block3;
                }
            }
        }
        Color clientCol = this.field1203.method1726();
        Color entityCol = Color.RED;
        if (!((Boolean)this.field0184.method0492()).booleanValue()) {
            for (int i = 0; i < this.field0843.size(); ++i) {
                boolean entHit = i < this.field1347.size() && this.field1347.get(i) != false;
                this.method1506(e.method1629(), this.field0843.get(i), this.field0928.get(i), entHit ? entityCol : clientCol);
            }
        }
        if (((Boolean)this.field1432.method0492()).booleanValue() && ((Boolean)this.field0970.method0492()).booleanValue() && this.field1308.size() >= 2) {
            boolean entHit = !this.field1347.isEmpty() && this.field1347.get(this.field1347.size() - 1) != false;
            this.method1503(e.method1629(), this.method0470(), this.field1308, entHit ? entityCol : clientCol);
        }
        for (PathMesh pt : this.field0885) {
            class_1297 owner;
            class_1297 class_12973 = pt.entity;
            if (class_12973 instanceof class_1676) {
                class_1676 pe = (class_1676)class_12973;
                v1 = pe.method_24921();
            } else {
                v1 = owner = null;
            }
            if (owner == ProjectilePrediction.field0796.field_1724) continue;
            this.method1506(e.method1629(), pt.pos, pt.facing, pt.isEntityHit ? entityCol : clientCol);
        }
    }

    private class_243 method0470() {
        class_4184 cam = ProjectilePrediction.field0796.field_1773.method_19418();
        class_243 base = cam.method_19326();
        double yawR = Math.toRadians(cam.method_19330());
        double pitchR = Math.toRadians(cam.method_19329());
        class_243 look = new class_243(-Math.sin(yawR) * Math.cos(pitchR), -Math.sin(pitchR), Math.cos(yawR) * Math.cos(pitchR));
        class_243 right = new class_243(-look.field_1350, 0.0, look.field_1352);
        right = right.method_1027() < 1.0000000010018248E-6 ? new class_243(1.0, 0.0, 0.0) : right.method_1029();
        class_243 up = right.method_1036(look).method_1029();
        double side = ProjectilePrediction.field0796.field_1690.method_42552().method_41753() == class_1306.field_6182 ? -1.0 : 1.0;
        return base.method_1019(right.method_1021(0.32000000096362474 * side)).method_1019(up.method_1021(-0.16000005268583464)).method_1019(look.method_1021(0.44999991806726064));
    }

    private void method1503(class_4587 matrix, class_243 origin, List<class_243> path, Color baseColor) {
        int n = path.size();
        if (n < 2) {
            return;
        }
        int rgb = baseColor.getRGB();
        float baseFrac = (float)baseColor.getAlpha() / 255.0f;
        class_243 off = origin.method_1020(path.get(0));
        int blend = Math.max(2, (int)((double)n * 0.44999991806726064));
        class_243 prev = null;
        for (int i = 0; i < n; ++i) {
            float w = i >= blend ? 0.0f : 1.0f - (float)i / (float)blend;
            float ws = w * w * (3.0f - 2.0f * w);
            class_243 pt = path.get(i).method_1019(off.method_1021((double)ws));
            if (prev != null) {
                float t = (float)i / (float)(n - 1);
                float fadeIn = class_3532.method_15363((float)(t / 0.3f), (float)0.0f, (float)1.0f);
                int col = ColorMath.method1828(rgb, baseFrac * fadeIn);
                WorldRenderHelper.method1507(matrix, prev, pt, new Color(col, true));
            }
            prev = pt;
        }
    }

    /*
     * Unable to fully structure code
     */
    private void method1495(class_4587 matrix, Iterable<class_1799> stacks, ProjectilePath angle) {
        block18: {
            block20: {
                block21: {
                    block19: {
                        if (ProjectilePrediction.field0796.field_1724 == null) {
                            return;
                        }
                        activeItem = ProjectilePrediction.field0796.field_1724.method_6030().method_7909();
                        var5_5 = stacks.iterator();
                        if (!var5_5.hasNext()) break block18;
                        stack = var5_5.next();
                        results = null;
                        item = stack.method_7909();
                        if (!(item instanceof class_1779)) break block19;
                        results = this.method1199((class_1676)new class_1683((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack), 0.7999999493743812, angle);
                        break block20;
                    }
                    if (!(item instanceof class_1828)) break block21;
                    results = this.method1199((class_1676)new class_1686((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack), 0.5500000601931105, angle);
                    break block20;
                }
                if (!(item instanceof class_1835) || !item.equals(activeItem)) ** GOTO lbl-1000
                if (ProjectilePrediction.field0796.field_1724.method_6048() >= 10) {
                    results = this.method1199((class_1676)new class_1685((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack), 2.5, angle);
                } else if (item instanceof class_1823) {
                    results = this.method1199((class_1676)new class_1680((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack), 1.5, angle);
                } else if (item instanceof class_1771) {
                    results = this.method1199((class_1676)new class_1681((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack), 1.5, angle);
                } else if (item instanceof class_1776) {
                    results = this.method1199((class_1676)new class_1684((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack), 1.5, angle);
                } else if (item instanceof class_1753 && item.equals(activeItem) && ProjectilePrediction.field0796.field_1724.method_6115()) {
                    tickDelta = ProjectilePrediction.field0796.method_61966() != null ? ProjectilePrediction.field0796.method_61966().method_60637(false) : 0.0f;
                    velocity = 3.0f * class_3532.method_15363((float)(((float)ProjectilePrediction.field0796.field_1724.method_6048() + tickDelta) / 20.0f), (float)0.0f, (float)1.0f);
                    results = this.method1199((class_1676)new class_1667((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack, stack), velocity, angle);
                } else if (item instanceof class_1764 && class_1764.method_7781((class_1799)stack)) {
                    component = (class_9278)stack.method_57824(class_9334.field_49649);
                    list = new ArrayList<class_239>();
                    if (component != null && !component.method_57437().isEmpty()) {
                        velocity = ((class_1799)component.method_57437().getFirst()).method_31574(class_1802.field_8639) != false ? 100.0f : 3.0f;
                        list.add(this.method1323(angle.method2077(), (class_1676)new class_1667((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack, stack), velocity));
                        if (component.method_57437().size() > 2) {
                            pitchAbs = angle.method0002() / 90.0f;
                            delta = pitchAbs * pitchAbs * pitchAbs * pitchAbs * pitchAbs;
                            yawSpread = class_3532.method_48781((float)Math.abs(delta), (int)10, (int)90);
                            pitchSpread = class_3532.method_48781((float)delta, (int)0, (int)10);
                            list.add(this.method1323(angle.method0659(-yawSpread).method0123(-pitchSpread).method2077(), (class_1676)new class_1667((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack, stack), velocity));
                            list.add(this.method1323(angle.method0659(yawSpread).method0123(-pitchSpread).method2077(), (class_1676)new class_1667((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack, stack), velocity));
                        }
                    }
                    results = list;
                }
            }
            if (results != null && !(results = results.stream().filter((Predicate<class_239>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, nonNull(java.lang.Object ), (Lnet/minecraft/class_239;)Z)()).toList()).isEmpty()) {
                this.method1496(matrix, results);
                this.field1308.clear();
                this.field1308.addAll(this.field1384);
                if (item instanceof class_1828) {
                    this.field0404 = true;
                }
                this.field0441 = stack;
                lookVec = angle.method2077();
                vel = this.method1234(item, stack, activeItem);
                if (vel > 0.0) {
                    sqrt = class_3532.method_15355((float)((float)lookVec.method_1027()));
                    motion = lookVec.method_1021(vel / (double)sqrt).method_1019(ProjectilePrediction.field0796.field_1724.method_18798());
                    dummy = this.method1232(item, stack);
                    if (dummy != null && (tr = this.method0292(ProjectilePrediction.field0796.field_1724.method_33571().method_1019(MathHelper.method1127((class_1297)ProjectilePrediction.field0796.field_1724).method_1020(ProjectilePrediction.field0796.field_1724.method_19538())), motion, dummy)) != null) {
                        this.field0441 = stack;
                        tickDelta = ProjectilePrediction.field0796.method_61966() != null ? ProjectilePrediction.field0796.method_61966().method_60637(false) : 0.0f;
                        this.field0351 = this.field0255 = Math.max(0.0f, ((float)tr.ticks - tickDelta) * 0.05f);
                    }
                }
            }
            return;
        }
    }

    private void method1496(class_4587 matrix, List<class_239> results) {
        for (class_239 result : results) {
            class_2350 direction = this.method1294(result);
            this.field0843.add(result.method_17784());
            this.field0928.add(direction);
            this.field1347.add(result.method_17783() == class_239.class_240.field_1331);
        }
    }

    private void method0336(class_4587 matrix, class_243 pos, class_2350 surface, Color color) {
        Quaternionf quaternionf;
        float offset = 0.015f;
        class_243 lifted = pos.method_1031((double)((float)surface.method_10148() * offset), (double)((float)surface.method_10164() * offset), (double)((float)surface.method_10165() * offset));
        switch (surface) {
            case field_11039: 
            case field_11034: {
                quaternionf = class_7833.field_40718.rotationDegrees(90.0f);
                break;
            }
            case field_11043: 
            case field_11035: {
                quaternionf = class_7833.field_40714.rotationDegrees(90.0f);
                break;
            }
            default: {
                Quaternionf quaternionf2;
                quaternionf = quaternionf2;
                quaternionf2 = new Quaternionf();
            }
        }
        Quaternionf quat = quaternionf;
        float size = 0.15f;
        float gap = 0.18f;
        float baseHalf = 0.06f;
        matrix.method_22903();
        matrix.method_22904(lifted.field_1352, lifted.field_1351, lifted.field_1350);
        matrix.method_22907(quat);
        this.method1463(matrix, gap, gap + size, 0.0f, 0.0f, baseHalf, color);
        this.method1463(matrix, -gap, -(gap + size), 0.0f, 0.0f, baseHalf, color);
        this.method1463(matrix, 0.0f, 0.0f, gap, gap + size, baseHalf, color);
        this.method1463(matrix, 0.0f, 0.0f, -gap, -(gap + size), baseHalf, color);
        matrix.method_22909();
    }

    private void method1463(class_4587 matrix, float bx, float tx, float bz, float tz, float baseHalf, Color color) {
        float dx = tx - bx;
        float dz = tz - bz;
        float len = (float)Math.sqrt(dx * dx + dz * dz);
        if (len < 0.001f) {
            return;
        }
        float nx = -dz / len;
        float nz = dx / len;
        class_243 tip = new class_243((double)tx, 0.0, (double)tz);
        class_243 bLeft = new class_243((double)(bx + nx * baseHalf), 0.0, (double)(bz + nz * baseHalf));
        class_243 bRight = new class_243((double)(bx - nx * baseHalf), 0.0, (double)(bz - nz * baseHalf));
        WorldRenderHelper.method0337(matrix, tip, bLeft, color);
        WorldRenderHelper.method0337(matrix, bLeft, bRight, color);
        WorldRenderHelper.method0337(matrix, bRight, tip, color);
    }

    private Quaternionf method1280(class_2350 surface) {
        Quaternionf quaternionf;
        switch (surface) {
            case field_11039: 
            case field_11034: {
                quaternionf = class_7833.field_40718.rotationDegrees(90.0f);
                break;
            }
            case field_11043: 
            case field_11035: {
                quaternionf = class_7833.field_40714.rotationDegrees(90.0f);
                break;
            }
            default: {
                Quaternionf quaternionf2;
                quaternionf = quaternionf2;
                quaternionf2 = new Quaternionf();
            }
        }
        return quaternionf;
    }

    private void method1505(class_4587 matrix, class_243 pos, class_2350 surface, float radius, Color color) {
        matrix.method_22903();
        matrix.method_22904(pos.field_1352, pos.field_1351 + 0.02000001037642601, pos.field_1350);
        matrix.method_22907(this.method1280(surface));
        int segments = 48;
        for (int i = 0; i < segments; ++i) {
            double a1 = 6.283185314844992 * (double)i / (double)segments;
            double a2 = 6.283185314844992 * (double)(i + 1) / (double)segments;
            class_243 p1 = new class_243(Math.cos(a1) * (double)radius, 0.0, -Math.sin(a1) * (double)radius);
            class_243 p2 = new class_243(Math.cos(a2) * (double)radius, 0.0, -Math.sin(a2) * (double)radius);
            WorldRenderHelper.method0337(matrix, p1, p2, color);
        }
        matrix.method_22909();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    public void onRender2D(HudRenderEvent event) {
        if (ProjectilePrediction.method1974()) {
            return;
        }
        class_332 ctx = event.method1806();
        class_4587 stack = ctx.method_51448();
        if (((Boolean)this.field1141.method0492()).booleanValue()) {
            class_4184 camera = ProjectilePrediction.field0796.field_1773.method_19418();
            class_243 camPos = camera.method_19326();
            float yawRad = (float)Math.toRadians(camera.method_19330());
            float pitchRad = (float)Math.toRadians(camera.method_19329());
            double cosP = Math.cos(pitchRad);
            double fwdX = -Math.sin(yawRad) * cosP;
            double fwdY = -Math.sin(pitchRad);
            double fwdZ = Math.cos(yawRad) * cosP;
            float screenW = ScreenLayoutHelper.method2047();
            float screenH = ScreenLayoutHelper.method1762();
            float screenCx = screenW / 2.0f;
            float screenCy = screenH / 2.0f;
            float edgeMargin = 30.0f;
            GuiRenderHelper.method0578();
            try {
                String ownNick = ProjectilePrediction.field0796.field_1724 != null ? ProjectilePrediction.field0796.field_1724.method_5477().getString() : null;
                float tickDelta = field0796.method_61966() != null ? field0796.method_61966().method_60637(false) : 0.0f;
                long now2D = System.currentTimeMillis();
                for (PathPoint tag : this.field1595) {
                    class_1297 ent = tag.method0563();
                    float anim = tag.method0774(now2D);
                    if (anim < 0.01f) continue;
                    float remaining = Math.max(0.0f, ((float)(tag.method2048() - ent.field_6012) - tickDelta) * 0.05f);
                    this.method1443(ctx, stack, tag.method0024(), tag.method1801(), ownNick, remaining, anim, camPos, fwdX, fwdY, fwdZ, screenW, screenH, screenCx, screenCy, edgeMargin);
                }
                for (PathMesh point : this.field0885) {
                    class_1297 owner;
                    class_1297 remaining = point.entity;
                    if (remaining instanceof class_1676) {
                        class_1676 pe = (class_1676)remaining;
                        v0 = pe.method_24921();
                    } else {
                        v0 = owner = null;
                    }
                    if (owner == ProjectilePrediction.field0796.field_1724) continue;
                    String nick = null;
                    if (owner instanceof class_1309) {
                        class_1309 le = (class_1309)owner;
                        if (!owner.method_5767()) {
                            nick = le.method_5477().getString();
                        }
                    }
                    this.method1443(ctx, stack, point.pos, point.stack, nick, point.remainingTime, 1.0f, camPos, fwdX, fwdY, fwdZ, screenW, screenH, screenCx, screenCy, edgeMargin);
                }
            }
            finally {
                GuiRenderHelper.method0025();
            }
        }
        if (((Boolean)this.field1093.method0492()).booleanValue()) {
            this.method1432(ctx, stack);
        }
    }

    private void method1443(class_332 ctx, class_4587 stack, class_243 worldPos, class_1799 itemStack, String nick, float remainingTime, float anim, class_243 camPos, double fwdX, double fwdY, double fwdZ, float screenW, float screenH, float screenCx, float screenCy, float edgeMargin) {
        boolean onScreen;
        double dx = worldPos.field_1352 - camPos.field_1352;
        double dy = worldPos.field_1351 - camPos.field_1351;
        double dz = worldPos.field_1350 - camPos.field_1350;
        double dot = dx * fwdX + dy * fwdY + dz * fwdZ;
        class_243 projected = ProjectionHelper.method1299(worldPos);
        float sx = (float)projected.field_1352;
        float sy = (float)projected.field_1351;
        if (dot <= 0.0) {
            sx = 2.0f * screenCx - sx;
            sy = 2.0f * screenCy - sy;
        }
        boolean bl = onScreen = dot > 0.0 && sx >= 0.0f && sx <= screenW && sy >= 0.0f && sy <= screenH;
        if (!onScreen) {
            float dirX = sx - screenCx;
            float dirY = sy - screenCy;
            float len = (float)Math.sqrt(dirX * dirX + dirY * dirY);
            if (len < 0.001f) {
                dirX = 0.0f;
                dirY = -1.0f;
            } else {
                dirX /= len;
                dirY /= len;
            }
            float halfW = screenCx - edgeMargin;
            float halfH = screenCy - edgeMargin;
            float tx = Math.abs(dirX) > 0.001f ? halfW / Math.abs(dirX) : Float.POSITIVE_INFINITY;
            float ty = Math.abs(dirY) > 0.001f ? halfH / Math.abs(dirY) : Float.POSITIVE_INFINITY;
            float t = Math.min(tx, ty);
            sx = screenCx + dirX * t;
            sy = screenCy + dirY * t;
        }
        float distScale = 0.9116279f;
        this.method1442(ctx, stack, itemStack, nick, remainingTime, sx, sy, distScale, anim);
    }

    private static Color method0964(Color c, float mult) {
        int a = Math.max(0, Math.min(255, (int)((float)c.getAlpha() * mult)));
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), a);
    }

    private void method1442(class_332 ctx, class_4587 stack, class_1799 itemStack, String nick, float remainingTime, float x, float y, float distScale, float anim) {
        FontSize font = Fonts.field0075.method0654(6.0f);
        float padding = 3.0f;
        float radius = 6.0f;
        float innerPad = 4.0f;
        float sepGap = 5.0f;
        float smallSepH = 7.0f;
        float itemSize = 9.0f;
        String timeText = String.format("%.1f", Float.valueOf(Math.max(0.0f, remainingTime))) + "s";
        boolean hasNick = nick != null && !nick.isEmpty();
        float nickWidth = hasNick ? font.method0998(nick) : 0.0f;
        float timeWidth = font.method0998(timeText);
        float totalW = innerPad + itemSize + sepGap + 1.0f + sepGap + timeWidth;
        if (hasNick) {
            totalW += sepGap + 1.0f + sepGap + nickWidth;
        }
        float bgHeight = font.method0530() + padding * 2.5f;
        float bgWidth = totalW += innerPad;
        float bgX = -bgWidth / 2.0f;
        float bgY = -bgHeight;
        float scaledBlur = ThemePalette.field0367.get().floatValue();
        stack.method_22903();
        stack.method_46416(x, y - 4.0f, 0.0f);
        float popScale = 0.55f + 0.45f * anim;
        stack.method_22905(distScale * popScale, distScale * popScale, 1.0f);
        Color bgColor = ProjectilePrediction.method0964(ThemePalette.field0930.get(), anim);
        Color borderColor = ProjectilePrediction.method0964(ThemePalette.field1564, anim);
        Color textPrimary = ProjectilePrediction.method0964(new Color(227, 227, 227), anim);
        Color textSecondary = ProjectilePrediction.method0964(new Color(255, 255, 255, 184), anim);
        Color separator = ProjectilePrediction.method0964(new Color(255, 255, 255, 80), anim);
        GuiRenderHelper.method1462(stack, bgX, bgY, bgWidth, bgHeight, radius, scaledBlur, ProjectilePrediction.method0964(new Color(255, 255, 255), anim));
        GuiRenderHelper.method1463(stack, bgX, bgY, bgWidth, bgHeight, radius, bgColor);
        GuiRenderHelper.method1461(stack, bgX, bgY, bgWidth, bgHeight, radius, 0.5f, 0.5f, borderColor);
        float centerY = bgY + bgHeight / 2.0f;
        float textY = centerY - font.method0530() / 2.0f;
        float smallSepY = centerY - smallSepH / 2.0f;
        float currentX = bgX + innerPad;
        stack.method_22903();
        stack.method_46416(currentX, centerY - itemSize / 2.0f, 200.0f);
        float itemScale = itemSize / 16.0f;
        stack.method_22905(itemScale, itemScale, 1.0f);
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)anim);
        ctx.method_51427(itemStack, 0, 0);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        stack.method_22909();
        currentX += itemSize;
        if (hasNick) {
            GuiRenderHelper.method0326(stack, currentX += sepGap, smallSepY, 1.0f, smallSepH, 1.0f, separator);
            GuiRenderHelper.method1491(stack, font, nick, currentX += 1.0f + sepGap, textY, textPrimary);
            currentX += nickWidth;
        }
        GuiRenderHelper.method0326(stack, currentX += sepGap, smallSepY, 1.0f, smallSepH, 1.0f, separator);
        GuiRenderHelper.method1491(stack, font, timeText, currentX += 1.0f + sepGap, textY, textSecondary);
        stack.method_22909();
    }

    private void method1432(class_332 ctx, class_4587 stack) {
        class_1297 ent;
        long now = System.currentTimeMillis();
        float dt = this.field1673 == 0L ? 0.016f : (float)(now - this.field1673) / 1000.0f;
        this.field1673 = now;
        dt = Math.min(dt, 0.1f);
        class_1799 displayItem = this.field0441;
        float displayTime = this.field0255;
        if (displayItem.method_7960() && this.field1698 != null && (ent = this.field1698) instanceof class_1676) {
            class_1676 proj = (class_1676)ent;
            if (ent.method_5805() && !ent.method_31481()) {
                HitResult tr = this.method0292(ent.method_19538(), ent.method_18798(), proj);
                float tickDelta = field0796.method_61966() != null ? field0796.method_61966().method_60637(false) : 0.0f;
                displayTime = tr != null ? Math.max(0.0f, ((float)tr.ticks - tickDelta) * 0.05f) : 0.0f;
                if (ent instanceof class_3857) {
                    class_3857 ti = (class_3857)ent;
                    displayItem = ti.method_7495();
                } else if (ent instanceof class_1665) {
                    class_1665 pp = (class_1665)ent;
                    displayItem = pp.method_54759();
                }
            }
        }
        if (!displayItem.method_7960() && displayTime >= 0.0f) {
            this.field1761 = displayItem;
            this.field1739 = displayTime;
            this.field1767 = now;
        } else if (now - this.field1767 < 400L && !this.field1761.method_7960()) {
            float elapsed = (float)(now - this.field1767) / 1000.0f;
            displayItem = this.field1761;
            displayTime = Math.max(0.0f, this.field1739 - elapsed);
        }
        boolean hasData = displayTime >= 0.0f && !displayItem.method_7960();
        float targetVis = hasData ? 1.0f : 0.0f;
        float visSpeed = dt / 0.25f;
        this.field0226 += Math.signum(targetVis - this.field0226) * Math.min(Math.abs(targetVis - this.field0226), visSpeed);
        if (this.field0226 < 0.01f && !hasData) {
            this.field0226 = 0.0f;
            return;
        }
        float vis = this.field0226;
        String timeText = String.format("%.1f", Float.valueOf(Math.max(0.0f, displayTime)));
        if (!timeText.equals(this.field0513)) {
            this.field0534 = this.field0513;
            this.field0513 = timeText;
            this.field0549 = System.currentTimeMillis();
        }
        FontSize labelFont = Fonts.field0075.method0654(6.5f);
        FontSize secondaryFont = Fonts.field0075.method0654(6.5f);
        float itemSize = 10.0f;
        float sepH = 7.0f;
        float padding = 6.0f;
        float gap = 5.0f;
        String suffix = "s";
        float timeW = labelFont.method0998(this.field0513);
        float suffixW = secondaryFont.method0998(suffix);
        float contentW = itemSize + gap + 1.0f + gap + timeW + suffixW;
        float totalW = padding + contentW + padding;
        float totalH = 18.0f;
        float targetW = totalW;
        float wSpeed = dt / 0.15f * Math.abs(targetW - this.field0289);
        this.field0289 += Math.signum(targetW - this.field0289) * Math.min(Math.abs(targetW - this.field0289), wSpeed);
        if (Math.abs(targetW - this.field0289) < 0.5f) {
            this.field0289 = targetW;
        }
        float animW = this.field0289 * vis;
        float screenCX = ScreenLayoutHelper.method2047() / 2.0f;
        float screenCY = ScreenLayoutHelper.method1762() / 2.0f;
        float x = screenCX + 10.0f;
        float y = screenCY - totalH / 2.0f;
        Color bgBase = ThemeColorManager.method1908().method0141(224);
        Color bg = new Color(bgBase.getRed(), bgBase.getGreen(), bgBase.getBlue(), (int)((float)bgBase.getAlpha() * vis));
        Color textPrimary = new Color(255, 255, 255, (int)(255.0f * vis));
        Color textSecondary = new Color(255, 255, 255, (int)(184.0f * vis));
        Color sepColor = new Color(255, 255, 255, (int)(80.0f * vis));
        float scaleX = Math.max(0.3f, vis);
        float scaleY = Math.max(0.3f, vis * vis);
        float cx = x + animW / 2.0f;
        float cy = y + totalH / 2.0f;
        stack.method_22903();
        stack.method_46416(cx, cy, 0.0f);
        stack.method_22905(scaleX, scaleY, 1.0f);
        stack.method_46416(-cx, -cy, 0.0f);
        if (vis > 0.01f) {
            GuiRenderHelper.method1462(stack, x, y, animW, totalH, 8.0f, 14.0f, new Color(255, 255, 255, (int)(255.0f * vis)));
            GuiRenderHelper.method1463(stack, x, y, animW, totalH, 8.0f, bg);
        }
        GuiRenderHelper.method1404(ctx, x, y, animW, totalH);
        float drawX = x + padding;
        float centerY = y + totalH / 2.0f;
        float itemY = centerY - itemSize / 2.0f;
        stack.method_22903();
        stack.method_46416(drawX, itemY, 200.0f);
        float itemScale = itemSize / 16.0f;
        stack.method_22905(itemScale, itemScale, 1.0f);
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)vis);
        ctx.method_51427(displayItem, 0, 0);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        stack.method_22909();
        float sepY = centerY - sepH / 2.0f;
        GuiRenderHelper.method0326(stack, drawX += itemSize + gap, sepY, 1.0f, sepH, 1.0f, sepColor);
        drawX += 1.0f + gap;
        float textY = centerY - labelFont.method0530() / 2.0f;
        long elapsed = System.currentTimeMillis() - this.field0549;
        float textProgress = Math.min(1.0f, (float)elapsed / 150.0f);
        float eased = 1.0f - (1.0f - textProgress) * (1.0f - textProgress);
        if (textProgress < 1.0f && !this.field0534.isEmpty()) {
            int newAlpha;
            float slide = labelFont.method0530() * 0.8f;
            int oldAlpha = (int)(255.0f * (1.0f - eased) * vis);
            if (oldAlpha > 0) {
                GuiRenderHelper.method1491(stack, labelFont, this.field0534, drawX, textY + slide * eased, new Color(255, 255, 255, oldAlpha));
            }
            if ((newAlpha = (int)(255.0f * eased * vis)) > 0) {
                GuiRenderHelper.method1491(stack, labelFont, this.field0513, drawX, textY - slide * (1.0f - eased), new Color(255, 255, 255, newAlpha));
            }
        } else {
            GuiRenderHelper.method1491(stack, labelFont, this.field0513, drawX, textY, textPrimary);
        }
        GuiRenderHelper.method1491(stack, secondaryFont, suffix, drawX += timeW, textY, textSecondary);
        GuiRenderHelper.method1400(ctx);
        stack.method_22909();
    }

    public List<class_1297> method1689() {
        ArrayList<class_1297> result = new ArrayList<class_1297>();
        this.method2026().filter(e -> (e instanceof class_1665 || e instanceof class_3857 || e instanceof class_1542) && !this.method1129((class_1297)e)).forEach(result::add);
        return result;
    }

    public class_243 method1147(class_1297 entity, class_243 prevPos, class_243 motion) {
        boolean isInWater = ProjectilePrediction.field0796.field_1687.method_8316(class_2338.method_49638((class_2374)prevPos)).method_15767(class_3486.field_15517);
        double multiply = entity instanceof class_1685 ? 0.9900000190921701 : (entity instanceof class_1665 ? (isInWater ? 0.6000003606674028 : 0.9900000190921701) : (isInWater ? 0.7999999493743812 : 0.9900000190921701));
        return motion.method_1021(multiply).method_1031(0.0, -entity.method_56989(), 0.0);
    }

    private void method1146(class_1297 entity, class_243 pos, int ticks, class_239 result) {
        class_2350 class_23502;
        float tickDelta = field0796.method_61966() != null ? field0796.method_61966().method_60637(false) : 0.0f;
        float remaining = Math.max(0.0f, ((float)ticks - tickDelta) * 0.05f);
        if (result instanceof class_3965) {
            class_3965 bh = (class_3965)result;
            class_23502 = bh.method_17780();
        } else {
            class_23502 = class_2350.field_11036;
        }
        class_2350 facing = class_23502;
        boolean isEntityHit = result != null && result.method_17783() == class_239.class_240.field_1331;
        class_1799 stack = null;
        boolean isPotion = false;
        if (entity instanceof class_1542) {
            class_1542 item = (class_1542)entity;
            stack = item.method_6983();
        } else if (entity instanceof class_3857) {
            class_3857 thrown = (class_3857)entity;
            stack = thrown.method_7495();
            isPotion = entity instanceof class_1686;
        } else if (entity instanceof class_1665) {
            class_1665 persistent = (class_1665)entity;
            stack = persistent.method_54759();
        }
        if (stack != null) {
            this.field0885.add(new PathMesh(stack, pos, remaining, entity, isPotion, facing, isEntityHit));
        }
    }

    private class_2350 method1294(class_239 result) {
        if (result instanceof class_3965) {
            class_3965 blockHit = (class_3965)result;
            return blockHit.method_17780();
        }
        return class_2350.method_58251((class_243)result.method_17784().method_1020(ProjectilePrediction.field0796.field_1724.method_33571()).method_1029());
    }

    private boolean method1129(class_1297 entity) {
        boolean posSame = entity.method_23317() == entity.field_6014 && entity.method_23318() == entity.field_6036 && entity.method_23321() == entity.field_5969;
        boolean itemOnGround = entity instanceof class_1542 && (entity.method_24828() || this.method0235(entity));
        return posSame || itemOnGround;
    }

    private boolean method0235(class_1297 entity) {
        return ProjectilePrediction.field0796.field_1687.method_8316(class_2338.method_49638((class_2374)entity.method_19538())).method_15767(class_3486.field_15517);
    }

    private double method1234(class_1792 item, class_1799 stack, class_1792 activeItem) {
        class_9278 component;
        if (item instanceof class_1779) {
            return 0.7999999493743812;
        }
        if (item instanceof class_1828) {
            return 0.5500000601931105;
        }
        if (item instanceof class_1835 && item.equals(activeItem)) {
            if (ProjectilePrediction.field0796.field_1724.method_6048() >= 10) {
                return 2.5;
            }
        }
        if (item instanceof class_1823 || item instanceof class_1771 || item instanceof class_1776) {
            return 1.5;
        }
        if (item instanceof class_1753 && item.equals(activeItem) && ProjectilePrediction.field0796.field_1724.method_6115()) {
            float tickDelta = field0796.method_61966() != null ? field0796.method_61966().method_60637(false) : 0.0f;
            return 3.0f * class_3532.method_15363((float)(((float)ProjectilePrediction.field0796.field_1724.method_6048() + tickDelta) / 20.0f), (float)0.0f, (float)1.0f);
        }
        if (item instanceof class_1764 && class_1764.method_7781((class_1799)stack) && (component = (class_9278)stack.method_57824(class_9334.field_49649)) != null && !component.method_57437().isEmpty()) {
            return ((class_1799)component.method_57437().getFirst()).method_31574(class_1802.field_8639) ? 100.0 : 3.0;
        }
        return -1.0;
    }

    private class_1676 method1232(class_1792 item, class_1799 stack) {
        if (item instanceof class_1779) {
            return new class_1683((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack);
        }
        if (item instanceof class_1828) {
            return new class_1686((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack);
        }
        if (item instanceof class_1835) {
            return new class_1685((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack);
        }
        if (item instanceof class_1823) {
            return new class_1680((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack);
        }
        if (item instanceof class_1771) {
            return new class_1681((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack);
        }
        if (item instanceof class_1776) {
            return new class_1684((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack);
        }
        if (item instanceof class_1753 || item instanceof class_1764) {
            return new class_1667((class_1937)ProjectilePrediction.field0796.field_1687, (class_1309)ProjectilePrediction.field0796.field_1724, stack, stack);
        }
        return null;
    }

    private HitResult method0292(class_243 pos, class_243 motion, class_1676 entity) {
        for (int i = 0; i < 300; ++i) {
            class_243 prevPos = pos;
            pos = pos.method_1019(motion);
            motion = this.method1147((class_1297)entity, prevPos, motion);
            class_3965 result = ProjectilePrediction.field0796.field_1687.method_17742(new class_3959(prevPos, pos, class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)entity));
            if (!result.method_17783().equals((Object)class_239.class_240.field_1333)) {
                return new HitResult((class_239)result, i);
            }
            class_243 finalPos = pos;
            class_243 finalPrevPos = prevPos;
            boolean entityHit = this.method2026().filter(ent -> {
                class_1309 living;
                return ent instanceof class_1309 && (living = (class_1309)ent) != entity.method_24921() && living.method_5805();
            }).anyMatch(ent -> this.method1293(ent.method_5829().method_1014(0.3000001804584443), finalPrevPos, finalPos));
            if (entityHit) {
                return new HitResult(new class_239(this, pos){

                    public class_239.class_240 method_17783() {
                        return class_239.class_240.field_1331;
                    }
                }, i);
            }
            if (pos.field_1351 < -128.0) break;
        }
        return null;
    }

    static {
        field1651 = new class_4588(){

            public class_4588 method_22912(float x, float y, float z) {
                return this;
            }

            public class_4588 method_1336(int r, int g, int b, int a) {
                return this;
            }

            public class_4588 method_22913(float u, float v) {
                return this;
            }

            public class_4588 method_60796(int u, int v) {
                return this;
            }

            public class_4588 method_22921(int u, int v) {
                return this;
            }

            public class_4588 method_22914(float x, float y, float z) {
                return this;
            }
        };
        field1572 = layer -> field1651;
        field1181 = class_2960.method_60655((String)"daamky", (String)"textures/glow.png");
    }

    private static final class ProjectilePath {
        final float field0566;
        final float field0003;

        ProjectilePath(float yaw, float pitch) {
            this.field0566 = yaw;
            this.field0003 = pitch;
        }

        float method0530() {
            return this.field0566;
        }

        float method0002() {
            return this.field0003;
        }

        ProjectilePath method0659(float delta) {
            return new ProjectilePath(this.field0566 + delta, this.field0003);
        }

        ProjectilePath method0123(float delta) {
            return new ProjectilePath(this.field0566, this.field0003 + delta);
        }

        class_243 method2077() {
            float p = (float)Math.toRadians(this.field0003);
            float y = (float)Math.toRadians(-this.field0566);
            float cp = class_3532.method_15362((float)p);
            return new class_243((double)(class_3532.method_15374((float)y) * cp), (double)(-class_3532.method_15374((float)p)), (double)(class_3532.method_15362((float)y) * cp));
        }
    }

    private static final class PathMesh
    extends Record {
        private final class_1799 stack;
        private final class_243 pos;
        private final float remainingTime;
        private final class_1297 entity;
        private final boolean isPotion;
        private final class_2350 facing;
        private final boolean isEntityHit;

        private PathMesh(class_1799 stack, class_243 pos, float remainingTime, class_1297 entity, boolean isPotion, class_2350 facing, boolean isEntityHit) {
            this.stack = stack;
            this.pos = pos;
            this.remainingTime = remainingTime;
            this.entity = entity;
            this.isPotion = isPotion;
            this.facing = facing;
            this.isEntityHit = isEntityHit;
        }

        @Override
        public final String toString() {
            return ObjectMethods.bootstrap("toString", new MethodHandle[]{PathMesh.class, "stack;pos;remainingTime;entity;isPotion;facing;isEntityHit", "stack", "pos", "remainingTime", "entity", "isPotion", "facing", "isEntityHit"}, this);
        }

        @Override
        public final int hashCode() {
            return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{PathMesh.class, "stack;pos;remainingTime;entity;isPotion;facing;isEntityHit", "stack", "pos", "remainingTime", "entity", "isPotion", "facing", "isEntityHit"}, this);
        }

        @Override
        public final boolean equals(Object o) {
            return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{PathMesh.class, "stack;pos;remainingTime;entity;isPotion;facing;isEntityHit", "stack", "pos", "remainingTime", "entity", "isPotion", "facing", "isEntityHit"}, this, o);
        }

        public class_1799 method0567() {
            return this.stack;
        }

        public class_243 method0024() {
            return this.pos;
        }

        public float method2047() {
            return this.remainingTime;
        }

        public class_1297 method1798() {
            return this.entity;
        }

        public boolean method1635() {
            return this.isPotion;
        }

        public class_2350 method1969() {
            return this.facing;
        }

        public boolean method0431() {
            return this.isEntityHit;
        }
    }

    private static class PathPoint {
        final class_1297 field0730;
        final class_243 field0157;
        final int field1411;
        final class_1799 field1040;
        final long field0760;
        long field1244 = 0L;

        PathPoint(class_1297 entity, class_243 impactPos, int initialTicks, class_1799 stack) {
            this.field0730 = entity;
            this.field0157 = impactPos;
            this.field1411 = initialTicks;
            this.field1040 = stack;
            this.field0760 = System.currentTimeMillis();
        }

        class_1297 method0563() {
            return this.field0730;
        }

        class_243 method0024() {
            return this.field0157;
        }

        int method2048() {
            return this.field1411;
        }

        class_1799 method1801() {
            return this.field1040;
        }

        void method1634() {
            if (this.field1244 == 0L) {
                this.field1244 = System.currentTimeMillis();
            }
        }

        float method0774(long now) {
            if (this.field1244 != 0L) {
                float t = (float)(now - this.field1244) / 200.0f;
                t = Math.min(1.0f, Math.max(0.0f, t));
                return 1.0f - t * t;
            }
            float t = (float)(now - this.field0760) / 220.0f;
            t = Math.min(1.0f, Math.max(0.0f, t));
            return 1.0f - (1.0f - t) * (1.0f - t);
        }

        boolean method0161(long now) {
            return this.field1244 != 0L && now - this.field1244 > 200L;
        }
    }

    private static final class HitResult
    extends Record {
        private final class_239 hit;
        private final int ticks;

        private HitResult(class_239 hit, int ticks) {
            this.hit = hit;
            this.ticks = ticks;
        }

        @Override
        public final String toString() {
            return ObjectMethods.bootstrap("toString", new MethodHandle[]{HitResult.class, "hit;ticks", "hit", "ticks"}, this);
        }

        @Override
        public final int hashCode() {
            return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{HitResult.class, "hit;ticks", "hit", "ticks"}, this);
        }

        @Override
        public final boolean equals(Object o) {
            return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{HitResult.class, "hit;ticks", "hit", "ticks"}, this, o);
        }

        public class_239 method0568() {
            return this.hit;
        }

        public int method0003() {
            return this.ticks;
        }
    }
}

