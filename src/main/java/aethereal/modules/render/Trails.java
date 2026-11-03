/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  meteordevelopment.orbit.EventHandler
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  org.joml.Matrix4f
 */
package aethereal;

import aethereal.BooleanSetting;
import aethereal.ColorSetting;
import aethereal.DisplayNamed;
import aethereal.EnumSetting;
import aethereal.FloatSetting;
import aethereal.GlowRenderEvent;
import aethereal.Module;
import aethereal.ModuleCategory;
import aethereal.PreTickEvent;
import aethereal.Setting;
import aethereal.WorldGeometryRenderer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class Trails
extends Module {
    private final EnumSetting<Mode> field0058 = (EnumSetting)((Setting)((Setting)new EnumSetting<Mode>("particletrails.mode", Mode.field0130).method1007("Mode")).method0210("Particle style")).method2130("\u0421\u0442\u0438\u043b\u044c \u0447\u0430\u0441\u0442\u0438\u0446");
    private final FloatSetting field1450 = (FloatSetting)((Setting)((Setting)new FloatSetting("particletrails.amount", 10.0f, 1.0f, 20.0f, 1.0f).method1007("Amount")).method0210("Particles spawned per tick")).method2130("\u041a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e");
    private final FloatSetting field0985 = (FloatSetting)((Setting)((Setting)new FloatSetting("particletrails.size", 0.05f, 0.02f, 0.3f, 0.01f).method1007("Size")).method0210("Base size of particles")).method2130("\u0420\u0430\u0437\u043c\u0435\u0440");
    private final FloatSetting field0190 = (FloatSetting)((Setting)((Setting)new FloatSetting("particletrails.speed", 0.03f, 0.01f, 0.2f, 0.01f).method1007("Speed")).method0210("Particle drift speed")).method2130("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c");
    private final FloatSetting field0470 = (FloatSetting)((Setting)((Setting)new FloatSetting("particletrails.lifetime", 50.0f, 10.0f, 100.0f, 1.0f).method1007("Lifetime")).method0210("How long particles last in ticks")).method2130("\u0414\u043b\u0438\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u044c");
    private final BooleanSetting field1619 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("particletrails.onlymoving", true).method1007("Only Moving")).method0210("Only spawn particles while moving")).method2130("\u0421\u043e\u0437\u0434\u0430\u0432\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u0434\u0432\u0438\u0436\u0435\u043d\u0438\u0438");
    private final BooleanSetting field1544 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("particletrails.thirdpersononly", false).method1007("Third Person Only")).method0210("Only spawn particles in third person")).method2130("\u0421\u043e\u0437\u0434\u0430\u0432\u0430\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u043e\u0442 \u0442\u0440\u0435\u0442\u044c\u0435\u0433\u043e \u043b\u0438\u0446\u0430");
    private final BooleanSetting field1709 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("particletrails.shine", true).method1007("Shine")).method0210("Additive blending for glow")).method2130("\u0421\u0438\u044f\u043d\u0438\u0435");
    private final ColorSetting field1143 = (ColorSetting)((Setting)((Setting)new ColorSetting("particletrails.color", 255, 156, 228, 200).method1882().method1007("Color")).method0210("Particle trail color")).method2130("\u0426\u0432\u0435\u0442");
    private class_243 field1107 = class_243.field_1353;
    private final List<TrailParticle> field1213 = new ArrayList<TrailParticle>();
    private final Random field0886 = new Random();
    private int field0827 = 0;
    private boolean field0931 = true;
    private double field1330 = 0.0;
    private static final ParticleType[] field1313 = ParticleType.values();

    public Trails() {
        super("Trails", ModuleCategory.field1004, "Trails");
        this.method1013("\u0421\u043b\u0435\u0434\u044b \u0447\u0430\u0441\u0442\u0438\u0446 \u0437\u0430 \u0438\u0433\u0440\u043e\u043a\u043e\u043c");
    }

    @Override
    public void method0025() {
        super.method0025();
        this.field1213.clear();
        this.field0827 = 0;
    }

    @Override
    public void method2078() {
        super.method2078();
        this.field1213.clear();
    }

    @EventHandler
    public void onTick(PreTickEvent event) {
        if (Trails.method1974()) {
            return;
        }
        ++this.field0827;
        class_243 pos = Trails.field0796.field_1724.method_19538();
        class_243 vel = pos.method_1020(this.field1107);
        boolean isMoving = vel.method_1027() > 9.999997089662838E-5;
        double speed = vel.method_37267();
        boolean onGround = Trails.field0796.field_1724.method_24828();
        double yaw = Math.toRadians(Trails.field0796.field_1724.method_36454());
        double yawDelta = yaw - this.field1330;
        if (((Boolean)this.field1544.method0492()).booleanValue() && Trails.field0796.field_1690.method_31044().method_31034()) {
            this.field1107 = pos;
            this.field1330 = yaw;
            this.field0931 = onGround;
            this.method1735();
            return;
        }
        if (((Boolean)this.field1619.method0492()).booleanValue() && !isMoving) {
            this.field1107 = pos;
            this.field1330 = yaw;
            this.field0931 = onGround;
            this.method1735();
            return;
        }
        if (this.field0058.method0492() == Mode.field0706) {
            int count = ((Float)this.field1450.method0492()).intValue();
            for (int i = 0; i < count; ++i) {
                double ox = (this.field0886.nextDouble() - 0.5) * 0.5;
                double oy = this.field0886.nextDouble() * 0.5;
                double oz = (this.field0886.nextDouble() - 0.5) * 0.5;
                this.field1213.add(new TrailParticle(pos.field_1352 + ox, pos.field_1351 + oy, pos.field_1350 + oz, (this.field0886.nextDouble() - 0.5) * (double)((Float)this.field0190.method0492()).floatValue(), this.field0886.nextDouble() * (double)((Float)this.field0190.method0492()).floatValue() * 0.5, (this.field0886.nextDouble() - 0.5) * (double)((Float)this.field0190.method0492()).floatValue(), (int)((Float)this.field0470.method0492()).floatValue(), ParticleType.field0705, this.field0886.nextFloat() * 360.0f, (this.field0886.nextFloat() - 0.5f) * 4.0f, ((Float)this.field0985.method0492()).floatValue()));
            }
        } else {
            if (this.field0827 % 3 == 0) {
                double a = (double)this.field0827 * 0.11999995558028098;
                double r = 0.5 + Math.sin((double)this.field0827 * 0.04999999442162316) * 0.2000000451988228;
                double h = 0.7999999405212292 + Math.sin((double)this.field0827 * 0.0800000001783749) * 0.4000000307956725;
                this.field1213.add(new TrailParticle(pos.field_1352 + Math.cos(a) * r, pos.field_1351 + h, pos.field_1350 + Math.sin(a) * r, -Math.sin(a) * 0.020000008426433003, 0.005000000029378958, Math.cos(a) * 0.020000008426433003, (int)(((Float)this.field0470.method0492()).floatValue() * 0.8f), ParticleType.field0705, this.field0886.nextFloat() * 360.0f, 5.0f + this.field0886.nextFloat() * 4.0f, ((Float)this.field0985.method0492()).floatValue() * 1.2f));
            }
            if (isMoving && onGround && this.field0827 % 2 == 0) {
                for (int i = 0; i < 2; ++i) {
                    double ox = (this.field0886.nextDouble() - 0.5) * 0.3000000263176597;
                    double oz = (this.field0886.nextDouble() - 0.5) * 0.3000000263176597;
                    this.field1213.add(new TrailParticle(pos.field_1352 + ox, pos.field_1351 + 0.04999999442162316, pos.field_1350 + oz, (this.field0886.nextDouble() - 0.5) * 0.029999993538952358, 0.020000008426433003 + this.field0886.nextDouble() * 0.020000008426433003, (this.field0886.nextDouble() - 0.5) * 0.029999993538952358, 15 + this.field0886.nextInt(10), ParticleType.field0129, this.field0886.nextFloat() * 360.0f, 15.0f + this.field0886.nextFloat() * 10.0f, ((Float)this.field0985.method0492()).floatValue() * 0.7f));
                }
            }
            if (isMoving && speed > 0.04999999442162316) {
                class_243 dir = vel.method_1029();
                double behind = -0.30000014532136227;
                this.field1213.add(new TrailParticle(pos.field_1352 + dir.field_1352 * behind + (this.field0886.nextDouble() - 0.5) * 0.10000000116859084, pos.field_1351 + 0.3000000263176597 + this.field0886.nextDouble() * 0.5, pos.field_1350 + dir.field_1350 * behind + (this.field0886.nextDouble() - 0.5) * 0.10000000116859084, -dir.field_1352 * 0.010000000500225757, 0.002999999130636981, -dir.field_1350 * 0.010000000500225757, (int)(((Float)this.field0470.method0492()).floatValue() * 0.5f), ParticleType.field1498, (float)Math.toDegrees(Math.atan2(dir.field_1350, dir.field_1352)), 3.0f + this.field0886.nextFloat() * 4.0f, ((Float)this.field0985.method0492()).floatValue() * (1.0f + (float)speed * 5.0f)));
            }
            if (isMoving && speed > 0.15000007152564834 && this.field0827 % 5 == 0) {
                double angle = this.field0886.nextDouble() * 3.1415936109900606 * 2.0;
                this.field1213.add(new TrailParticle(pos.field_1352, pos.field_1351 + 0.5, pos.field_1350, Math.cos(angle) * 0.040000000007288476, 0.0, Math.sin(angle) * 0.040000000007288476, (int)(((Float)this.field0470.method0492()).floatValue() * 0.6f), ParticleType.field1022, this.field0886.nextFloat() * 360.0f, 4.0f + this.field0886.nextFloat() * 5.0f, ((Float)this.field0985.method0492()).floatValue() * 0.5f));
            }
            if (Math.abs(yawDelta) > 0.029999993538952358 && this.field0827 % 3 == 0) {
                double side = yawDelta > 0.0 ? 1.0 : -1.0;
                double perpX = Math.cos(yaw + 1.5707968931794145) * side * 0.4000000307956725;
                double perpZ = Math.sin(yaw + 1.5707968931794145) * side * 0.4000000307956725;
                this.field1213.add(new TrailParticle(pos.field_1352 + perpX, pos.field_1351 + 0.6000000179323369 + this.field0886.nextDouble() * 0.3000000263176597, pos.field_1350 + perpZ, perpX * 0.04999999442162316, 0.010000000500225757, perpZ * 0.04999999442162316, (int)(((Float)this.field0470.method0492()).floatValue() * 0.7f), ParticleType.field0786, this.field0886.nextFloat() * 360.0f, 8.0f + this.field0886.nextFloat() * 6.0f, ((Float)this.field0985.method0492()).floatValue() * 0.9f));
            }
            if (isMoving && speed > 0.10000000116859084 && this.field0827 % 4 == 0) {
                this.field1213.add(new TrailParticle(pos.field_1352 + (this.field0886.nextDouble() - 0.5) * 0.2000000451988228, pos.field_1351 + 0.4000000307956725 + this.field0886.nextDouble() * 0.6000000179323369, pos.field_1350 + (this.field0886.nextDouble() - 0.5) * 0.2000000451988228, (this.field0886.nextDouble() - 0.5) * 0.014999993974599377, 0.00800000035432636, (this.field0886.nextDouble() - 0.5) * 0.014999993974599377, (int)(((Float)this.field0470.method0492()).floatValue() * 1.2f), ParticleType.field1267, (float)this.field0827 * 5.0f, 2.0f + this.field0886.nextFloat() * 3.0f, ((Float)this.field0985.method0492()).floatValue() * 1.1f));
            }
            if (onGround && !this.field0931 && vel.field_1351 < -0.30000014532136227) {
                int count = 6 + this.field0886.nextInt(5);
                for (int i = 0; i < count; ++i) {
                    double angle = 6.283186640837165 * (double)i / (double)count;
                    double sp = 0.04999999442162316 + this.field0886.nextDouble() * 0.040000000007288476;
                    this.field1213.add(new TrailParticle(pos.field_1352, pos.field_1351 + 0.10000000116859084, pos.field_1350, Math.cos(angle) * sp, 0.010000000500225757 + this.field0886.nextDouble() * 0.020000008426433003, Math.sin(angle) * sp, 15 + this.field0886.nextInt(10), ParticleType.field0333, (float)(angle * 57.30000623020847), 8.0f + this.field0886.nextFloat() * 6.0f, ((Float)this.field0985.method0492()).floatValue() * 1.3f));
                }
            }
            if (this.field0827 % 4 == 0) {
                double angle = this.field0886.nextDouble() * 3.1415936109900606 * 2.0;
                double dist = 0.3000000263176597 + this.field0886.nextDouble() * 0.5;
                this.field1213.add(new TrailParticle(pos.field_1352 + Math.cos(angle) * dist, pos.field_1351 + 0.04999999442162316, pos.field_1350 + Math.sin(angle) * dist, 0.0, 0.014999993974599377 + this.field0886.nextDouble() * 0.010000000500225757, 0.0, (int)(((Float)this.field0470.method0492()).floatValue() * 0.9f), ParticleType.field0206, this.field0886.nextFloat() * 360.0f, 4.0f + this.field0886.nextFloat() * 4.0f, ((Float)this.field0985.method0492()).floatValue() * 0.6f));
            }
            if (isMoving) {
                int dotCount = Math.min(3, ((Float)this.field1450.method0492()).intValue() / 3);
                for (int i = 0; i < dotCount; ++i) {
                    this.field1213.add(new TrailParticle(pos.field_1352 + (this.field0886.nextDouble() - 0.5) * 0.4000000307956725, pos.field_1351 + this.field0886.nextDouble() * 0.7999999405212292, pos.field_1350 + (this.field0886.nextDouble() - 0.5) * 0.4000000307956725, (this.field0886.nextDouble() - 0.5) * 0.059999975785620274, (this.field0886.nextDouble() - 0.5) * 0.040000000007288476, (this.field0886.nextDouble() - 0.5) * 0.059999975785620274, 8 + this.field0886.nextInt(8), ParticleType.field0484, 0.0f, 0.0f, ((Float)this.field0985.method0492()).floatValue() * 0.4f));
                }
            }
        }
        this.field1107 = pos;
        this.field1330 = yaw;
        this.field0931 = onGround;
        this.method1735();
    }

    private void method1735() {
        Iterator<TrailParticle> it = this.field1213.iterator();
        while (it.hasNext()) {
            TrailParticle p = it.next();
            p.field0956 = p.field0565;
            p.field0757 = p.field0002;
            p.field1241 = p.field1409;
            switch (p.field1724.ordinal()) {
                case 0: {
                    double a = (double)p.field1615 * 0.10000000116859084 + (double)p.field1136 * 0.020000008426433003;
                    p.field0313 += Math.cos(a) * 5.000000039648302E-4;
                    p.field0457 += Math.sin(a) * 5.000000039648302E-4;
                    p.field0176 *= 0.9900000316832716;
                    p.field0313 *= 0.9799995688342711;
                    p.field0457 *= 0.9799995688342711;
                    break;
                }
                case 1: {
                    p.field0176 *= 0.9199997616689671;
                    p.field0313 *= 0.8799997697452798;
                    p.field0457 *= 0.8799997697452798;
                    break;
                }
                case 2: {
                    p.field0313 *= 0.9000000904037876;
                    p.field0176 *= 0.9000000904037876;
                    p.field0457 *= 0.9000000904037876;
                    break;
                }
                case 3: {
                    p.field0313 *= 0.9500001192116827;
                    p.field0457 *= 0.9500001192116827;
                    break;
                }
                case 4: {
                    p.field0313 *= 0.9400000009323457;
                    p.field0457 *= 0.9400000009323457;
                    p.field0176 += 5.000000039648302E-4;
                    p.field0176 *= 0.9700000656037335;
                    break;
                }
                case 5: {
                    double wave = Math.sin((double)p.field1615 * 0.2000000451988228 + (double)p.field1136 * 0.010000000500225757) * 0.002999999130636981;
                    p.field0313 += wave;
                    p.field0457 += wave * 0.7000000959882013;
                    p.field0176 += 3.0000000048894566E-4;
                    p.field0313 *= 0.9799995688342711;
                    p.field0457 *= 0.9799995688342711;
                    break;
                }
                case 6: {
                    p.field0313 *= 0.930000179411146;
                    p.field0457 *= 0.930000179411146;
                    p.field0176 -= 0.002000000030183859;
                    break;
                }
                case 7: {
                    double sway = Math.sin((double)p.field1615 * 0.0800000001783749) * 9.999994256636507E-4;
                    p.field0313 += sway;
                    p.field0457 += sway * 0.5;
                    p.field0176 *= 0.9950002505257888;
                    break;
                }
                case 8: {
                    p.field0313 *= 0.8500000819719095;
                    p.field0176 *= 0.8500000819719095;
                    p.field0457 *= 0.8500000819719095;
                }
            }
            p.field0565 += p.field0313;
            p.field0002 += p.field0176;
            p.field1409 += p.field0457;
            p.field1136 += p.field1087;
            ++p.field1615;
            if (p.field1615 < p.field1539) continue;
            it.remove();
        }
    }

    @EventHandler
    public void onRenderWorld(GlowRenderEvent event) {
        if (Trails.method1974()) {
            return;
        }
        class_243 camera = Trails.field0796.field_1773.method_19418().method_19326();
        Matrix4f matrix = new class_4587().method_23760().method_23761();
        float partial = event.method1603();
        Color baseColor = this.field1143.method1726();
        int baseRgb = baseColor.getRGB() & 0xFFFFFF;
        boolean additive = (Boolean)this.field1709.method0492();
        if (this.field1213.isEmpty()) {
            return;
        }
        ArrayList<WorldGeometryRenderer.VertexBatch> allLines = new ArrayList<WorldGeometryRenderer.VertexBatch>();
        for (TrailParticle p : this.field1213) {
            float life = 1.0f - (float)p.field1615 / (float)p.field1539;
            if (life <= 0.0f) continue;
            float fadeIn = Math.min(1.0f, (float)p.field1615 / 3.0f);
            float fadeOut = life < 0.3f ? life / 0.3f : 1.0f;
            int a = Math.max(0, Math.min(255, (int)((float)baseColor.getAlpha() * fadeIn * fadeOut)));
            int rgb = (a & 0xFF) << 24 | baseRgb;
            float rx = (float)(class_3532.method_16436((double)partial, (double)p.field0956, (double)p.field0565) - camera.field_1352);
            float ry = (float)(class_3532.method_16436((double)partial, (double)p.field0757, (double)p.field0002) - camera.field_1351);
            float rz = (float)(class_3532.method_16436((double)partial, (double)p.field1241, (double)p.field1409) - camera.field_1350);
            float s = p.field1196;
            switch (p.field1724.ordinal()) {
                case 0: {
                    s *= 0.6f + 0.4f * life;
                    break;
                }
                case 1: {
                    s *= fadeIn * life;
                    break;
                }
                case 2: {
                    s *= 0.8f + 0.2f * (1.0f - life);
                    break;
                }
                case 3: {
                    float mid = (float)p.field1615 < (float)p.field1539 * 0.4f ? (float)p.field1615 / ((float)p.field1539 * 0.4f) : life / 0.6f;
                    s *= mid;
                    break;
                }
                case 4: {
                    s *= 0.5f + 0.5f * life;
                    break;
                }
                case 5: {
                    s *= 0.7f + 0.3f * (float)Math.sin((double)life * 3.1415936109900606);
                    break;
                }
                case 6: {
                    s *= life;
                    break;
                }
                case 7: {
                    s *= 0.4f + 0.6f * (float)Math.sin((double)life * 3.1415936109900606);
                    break;
                }
                case 8: {
                    s *= life * life;
                }
            }
            float rot = p.field1136 + p.field1087 * partial;
            float cos = (float)Math.cos(Math.toRadians(rot));
            float sin = (float)Math.sin(Math.toRadians(rot));
            this.method1563(matrix, allLines, p.field1724, rx, ry, rz, s, cos, sin, rot, rgb);
        }
        WorldGeometryRenderer.method1087(allLines, additive, true);
    }

    private void method1563(Matrix4f m, List<WorldGeometryRenderer.VertexBatch> lines, ParticleType shape, float rx, float ry, float rz, float s, float cos, float sin, float rot, int rgb) {
        switch (shape.ordinal()) {
            case 0: {
                lines.add(this.method1556(m, rx - s, ry, rz, rx + s, ry, rz, rgb));
                lines.add(this.method1556(m, rx, ry - s, rz, rx, ry + s, rz, rgb));
                lines.add(this.method1556(m, rx, ry, rz - s, rx, ry, rz + s, rgb));
                float si = s * 0.55f;
                lines.add(this.method1556(m, rx - si, ry, rz, rx + si, ry, rz, rgb));
                lines.add(this.method1556(m, rx, ry - si, rz, rx, ry + si, rz, rgb));
                break;
            }
            case 1: {
                float d = s * 0.7f;
                lines.add(this.method1556(m, rx - d * cos, ry - d * sin, rz, rx + d * cos, ry + d * sin, rz, rgb));
                lines.add(this.method1556(m, rx + d * sin, ry - d * cos, rz, rx - d * sin, ry + d * cos, rz, rgb));
                break;
            }
            case 2: {
                float len = s * 2.0f;
                float dx = len * cos;
                float dy = len * sin * 0.3f;
                lines.add(this.method1556(m, rx - dx, ry - dy, rz, rx + dx, ry + dy, rz, rgb));
                break;
            }
            case 3: {
                float r = s * 0.7f;
                int segs = 10;
                for (int i = 0; i < segs; ++i) {
                    float a1 = (float)(6.283186640837165 * (double)i / (double)segs);
                    float a2 = (float)(6.283186640837165 * (double)(i + 1) / (double)segs);
                    lines.add(this.method1556(m, rx + r * (float)Math.cos(a1), ry + r * (float)Math.sin(a1), rz, rx + r * (float)Math.cos(a2), ry + r * (float)Math.sin(a2), rz, rgb));
                }
                break;
            }
            case 4: {
                float r = s * 0.7f;
                float rotRad = (float)Math.toRadians(rot);
                float x0 = rx + r * (float)Math.cos(rotRad);
                float y0 = ry + r * (float)Math.sin(rotRad);
                float x1 = rx + r * (float)Math.cos(rotRad + 2.094f);
                float y1 = ry + r * (float)Math.sin(rotRad + 2.094f);
                float x2 = rx + r * (float)Math.cos(rotRad + 4.189f);
                float y2 = ry + r * (float)Math.sin(rotRad + 4.189f);
                lines.add(this.method1556(m, x0, y0, rz, x1, y1, rz, rgb));
                lines.add(this.method1556(m, x1, y1, rz, x2, y2, rz, rgb));
                lines.add(this.method1556(m, x2, y2, rz, x0, y0, rz, rgb));
                break;
            }
            case 5: {
                float seg = s * 0.6f;
                float wave = s * 0.4f;
                float sn1 = (float)Math.sin((double)rot * 0.0800000001783749);
                float sn2 = (float)Math.sin((double)rot * 0.0800000001783749 + 2.0);
                float sn3 = (float)Math.sin((double)rot * 0.0800000001783749 + 4.0);
                lines.add(this.method1556(m, rx - seg, ry + wave * sn1, rz, rx, ry + wave * sn2, rz, rgb));
                lines.add(this.method1556(m, rx, ry + wave * sn2, rz, rx + seg, ry + wave * sn3, rz, rgb));
                break;
            }
            case 6: {
                for (int i = 0; i < 4; ++i) {
                    float angle = (float)Math.toRadians(rot + (float)(i * 90));
                    float ex = rx + s * 0.8f * (float)Math.cos(angle);
                    float ey = ry + s * 0.8f * (float)Math.sin(angle);
                    lines.add(this.method1556(m, rx, ry, rz, ex, ey, rz, rgb));
                }
                break;
            }
            case 7: {
                float h = s * 0.9f;
                float w = s * 0.45f;
                float cR = (float)Math.toRadians(rot);
                float tX = rx + h * (float)Math.cos((double)cR + 1.5707968931794145);
                float tY = ry + h * (float)Math.sin((double)cR + 1.5707968931794145);
                float bX = rx - h * (float)Math.cos((double)cR + 1.5707968931794145);
                float bY = ry - h * (float)Math.sin((double)cR + 1.5707968931794145);
                float lX = rx + w * (float)Math.cos(cR);
                float lY = ry + w * (float)Math.sin(cR);
                float rX = rx - w * (float)Math.cos(cR);
                float rY = ry - w * (float)Math.sin(cR);
                lines.add(this.method1556(m, tX, tY, rz, lX, lY, rz, rgb));
                lines.add(this.method1556(m, lX, lY, rz, bX, bY, rz, rgb));
                lines.add(this.method1556(m, bX, bY, rz, rX, rY, rz, rgb));
                lines.add(this.method1556(m, rX, rY, rz, tX, tY, rz, rgb));
                break;
            }
            case 8: {
                float d = s * 0.3f;
                lines.add(this.method1556(m, rx - d, ry, rz, rx + d, ry, rz, rgb));
                lines.add(this.method1556(m, rx, ry - d, rz, rx, ry + d, rz, rgb));
            }
        }
    }

    private WorldGeometryRenderer.VertexBatch method1556(Matrix4f m, float x1, float y1, float z1, float x2, float y2, float z2, int c) {
        WorldGeometryRenderer.ColoredVertex[] coloredVertexArray = new WorldGeometryRenderer.ColoredVertex[2];
        coloredVertexArray[0] = new WorldGeometryRenderer.ColoredVertex(m, x1, y1, z1, c);
        coloredVertexArray[1] = new WorldGeometryRenderer.ColoredVertex(m, x2, y2, z2, c);
        return new WorldGeometryRenderer.VertexBatch(coloredVertexArray);
    }

    public static enum Mode implements DisplayNamed {
        field0706("Stars"),
        field0130("Harmony");

        private final String field1504;
        private Mode(String name) {
            this.field1504 = name;
        }
    

        @Override
        public String method0557() {
            return this.field1504;
        }
    }

    private static class TrailParticle {
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
        ParticleType field1724;
        float field1136;
        float field1087;
        float field1196;

        TrailParticle(double x, double y, double z, double vx, double vy, double vz, int maxAge, ParticleType shape, float rotation, float rotSpeed, float baseSize) {
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
            this.field1724 = shape;
            this.field1136 = rotation;
            this.field1087 = rotSpeed;
            this.field1196 = baseSize;
        }
    }

    private static enum ParticleType {
        field0705,
        field0129,
        field1498,
        field1022,
        field0786,
        field1267,
        field0333,
        field0206,
        field0484;
    }
}

