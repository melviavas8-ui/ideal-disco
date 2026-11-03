/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  meteordevelopment.orbit.EventHandler
 *  net.minecraft.class_1297
 *  net.minecraft.class_1665
 *  net.minecraft.class_1682
 *  net.minecraft.class_243
 *  net.minecraft.class_4587
 *  org.joml.Matrix4f
 */
package aethereal;

import aethereal.BooleanSetting;
import aethereal.ColorSetting;
import aethereal.FloatSetting;
import aethereal.GlowRenderEvent;
import aethereal.Module;
import aethereal.ModuleCategory;
import aethereal.PreTickEvent;
import aethereal.Setting;
import aethereal.WorldGeometryRenderer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1297;
import net.minecraft.class_1665;
import net.minecraft.class_1682;
import net.minecraft.class_243;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class ProjectileTrails
extends Module {
    private final FloatSetting field0060 = (FloatSetting)((Setting)((Setting)new FloatSetting("projectiletrails.amount", 5.0f, 1.0f, 15.0f, 1.0f).method1007("Amount")).method0210("Particles spawned per tick per projectile")).method2130("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e");
    private final FloatSetting field1450 = (FloatSetting)((Setting)((Setting)new FloatSetting("projectiletrails.size", 0.04f, 0.01f, 0.2f, 0.01f).method1007("Size")).method0210("Size of trail particles")).method2130("\u0420\u0430\u0437\u043c\u0435\u0440");
    private final FloatSetting field0985 = (FloatSetting)((Setting)((Setting)new FloatSetting("projectiletrails.lifetime", 30.0f, 5.0f, 60.0f, 1.0f).method1007("Lifetime")).method0210("How long particles last in ticks")).method2130("\u0414\u043b\u0438\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u044c");
    private final BooleanSetting field0184 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("projectiletrails.shine", true).method1007("Shine")).method0210("Additive blending for glow")).method2130("\u0421\u0438\u044f\u043d\u0438\u0435");
    private final ColorSetting field0466 = (ColorSetting)((Setting)((Setting)new ColorSetting("projectiletrails.color", 255, 156, 228, 180).method1882().method1007("Color")).method0210("Trail color")).method2130("\u0426\u0432\u0435\u0442");
    private final List<TrailPoint> field1644 = new ArrayList<TrailPoint>();
    private final Map<Integer, class_243> field1566 = new HashMap<Integer, class_243>();
    private final Random field1730 = new Random();

    public ProjectileTrails() {
        super("ProjectileTrails", ModuleCategory.field1004, "Adds particle trails to thrown projectiles");
        this.method1013("\u0414\u043e\u0431\u0430\u0432\u043b\u044f\u0435\u0442 \u0441\u043b\u0435\u0434\u044b \u0447\u0430\u0441\u0442\u0438\u0446 \u043a \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430\u043c \u043a\u043e\u0442\u043e\u0440\u044b\u0435 \u0431\u0440\u043e\u0441\u0430\u044e\u0442 \u0438\u0433\u0440\u043e\u043a\u0438");
    }

    @Override
    public void method0025() {
        super.method0025();
        this.field1644.clear();
        this.field1566.clear();
    }

    @Override
    public void method2078() {
        super.method2078();
        this.field1644.clear();
        this.field1566.clear();
    }

    @EventHandler
    public void onTick(PreTickEvent event) {
        if (ProjectileTrails.method1974()) {
            return;
        }
        HashSet<Integer> alive = new HashSet<Integer>();
        for (class_1297 entity : ProjectileTrails.field0796.field_1687.method_18112()) {
            if (!this.method1129(entity)) continue;
            alive.add(entity.method_5628());
            class_243 pos = entity.method_19538();
            class_243 prev = this.field1566.get(entity.method_5628());
            if (prev != null && pos.method_1025(prev) > 9.999994329400472E-4) {
                int count = ((Float)this.field0060.method0492()).intValue();
                for (int i = 0; i < count; ++i) {
                    float t = (float)i / (float)count;
                    double sx = prev.field_1352 + (pos.field_1352 - prev.field_1352) * (double)t + (this.field1730.nextDouble() - 0.5) * 0.09999998808295149;
                    double sy = prev.field_1351 + (pos.field_1351 - prev.field_1351) * (double)t + (this.field1730.nextDouble() - 0.5) * 0.09999998808295149;
                    double sz = prev.field_1350 + (pos.field_1350 - prev.field_1350) * (double)t + (this.field1730.nextDouble() - 0.5) * 0.09999998808295149;
                    double vx = (this.field1730.nextDouble() - 0.5) * 0.010000003791233567;
                    double vy = (this.field1730.nextDouble() - 0.5) * 0.010000003791233567;
                    double vz = (this.field1730.nextDouble() - 0.5) * 0.010000003791233567;
                    this.field1644.add(new TrailPoint(sx, sy, sz, vx, vy, vz, ((Float)this.field0985.method0492()).intValue()));
                }
            }
            this.field1566.put(entity.method_5628(), pos);
        }
        this.field1566.keySet().retainAll(alive);
        Iterator<TrailPoint> it = this.field1644.iterator();
        while (it.hasNext()) {
            TrailPoint p = it.next();
            p.field0956 = p.field0565;
            p.field0757 = p.field0002;
            p.field1241 = p.field1409;
            p.field0565 += p.field0313;
            p.field0002 += p.field0176;
            p.field1409 += p.field0457;
            ++p.field1615;
            if (p.field1615 < p.field1539) continue;
            it.remove();
        }
    }

    private boolean method1129(class_1297 entity) {
        return entity instanceof class_1682 || entity instanceof class_1665;
    }

    @EventHandler
    public void onRenderWorld(GlowRenderEvent event) {
        if (ProjectileTrails.method1974() || this.field1644.isEmpty()) {
            return;
        }
        class_243 camera = ProjectileTrails.field0796.field_1773.method_19418().method_19326();
        Matrix4f matrix = new class_4587().method_23760().method_23761();
        float partialTick = event.method1603();
        Color baseColor = this.field0466.method1726();
        int baseRgb = baseColor.getRGB() & 0xFFFFFF;
        for (TrailPoint p : this.field1644) {
            float life = 1.0f - (float)p.field1615 / (float)p.field1539;
            if (life <= 0.0f) continue;
            int alpha = Math.max(0, Math.min(255, (int)((float)baseColor.getAlpha() * life)));
            int rgb = (alpha & 0xFF) << 24 | baseRgb;
            float rx = (float)(ProjectileTrails.method0625(p.field0956, p.field0565, partialTick) - camera.field_1352);
            float ry = (float)(ProjectileTrails.method0625(p.field0757, p.field0002, partialTick) - camera.field_1351);
            float rz = (float)(ProjectileTrails.method0625(p.field1241, p.field1409, partialTick) - camera.field_1350);
            float s = ((Float)this.field1450.method0492()).floatValue() * life;
            ArrayList<WorldGeometryRenderer.VertexBatch> lines = new ArrayList<WorldGeometryRenderer.VertexBatch>();
            lines.add(this.method1556(matrix, rx - s, ry, rz, rx + s, ry, rz, rgb));
            lines.add(this.method1556(matrix, rx, ry - s, rz, rx, ry + s, rz, rgb));
            lines.add(this.method1556(matrix, rx, ry, rz - s, rx, ry, rz + s, rgb));
            if (((Boolean)this.field0184.method0492()).booleanValue()) {
                WorldGeometryRenderer.field0209.addAll(lines);
                continue;
            }
            WorldGeometryRenderer.field1508.addAll(lines);
        }
    }

    private WorldGeometryRenderer.VertexBatch method1556(Matrix4f matrix, float x1, float y1, float z1, float x2, float y2, float z2, int color) {
        WorldGeometryRenderer.ColoredVertex[] coloredVertexArray = new WorldGeometryRenderer.ColoredVertex[2];
        coloredVertexArray[0] = new WorldGeometryRenderer.ColoredVertex(matrix, x1, y1, z1, color);
        coloredVertexArray[1] = new WorldGeometryRenderer.ColoredVertex(matrix, x2, y2, z2, color);
        return new WorldGeometryRenderer.VertexBatch(coloredVertexArray);
    }

    private static double method0625(double a, double b, float t) {
        return a + (b - a) * (double)t;
    }

    private static class TrailPoint {
        double field0565;
        double field0002;
        double field1409;
        double field0956;
        double field0757;
        double field1241;
        double field0313;
        double field0176;
        double field0457;
        int field1615;
        int field1539;

        TrailPoint(double x, double y, double z, double vx, double vy, double vz, int maxAge) {
            this.field0565 = x;
            this.field0002 = y;
            this.field1409 = z;
            this.field0956 = x;
            this.field0757 = y;
            this.field1241 = z;
            this.field0313 = vx;
            this.field0176 = vy;
            this.field0457 = vz;
            this.field1615 = 0;
            this.field1539 = maxAge;
        }
    }
}

