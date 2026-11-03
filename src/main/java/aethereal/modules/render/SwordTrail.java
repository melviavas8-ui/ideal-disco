/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  meteordevelopment.orbit.EventHandler
 *  net.minecraft.class_1657
 *  net.minecraft.class_1743
 *  net.minecraft.class_1766
 *  net.minecraft.class_1799
 *  net.minecraft.class_1829
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
import aethereal.SwordTrailAnimation;
import aethereal.SwordTrailBuffer;
import aethereal.SwordTrailGlow;
import aethereal.SwordTrailTransform;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1657;
import net.minecraft.class_1743;
import net.minecraft.class_1766;
import net.minecraft.class_1799;
import net.minecraft.class_1829;

public class SwordTrail
extends Module {
    private final BooleanSetting field0034 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("swordtrail.onlymainhand", true).method1007("Only Main Hand")).method0210("Effects only on the main hand")).method2130("\u042d\u0444\u0444\u0435\u043a\u0442\u044b \u0442\u043e\u043b\u044c\u043a\u043e \u043d\u0430 \u0433\u043b\u0430\u0432\u043d\u043e\u0439 \u0440\u0443\u043a\u0435");
    private final EnumSetting<ItemFilter> field1448 = (EnumSetting)((Setting)((Setting)new EnumSetting<ItemFilter>("swordtrail.itemfilter", ItemFilter.field0118).method1007("Item Filter")).method0210("Which held items trigger effects")).method2130("\u041a\u0430\u043a\u0438\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u044b \u0430\u043a\u0442\u0438\u0432\u0438\u0440\u0443\u044e\u0442 \u044d\u0444\u0444\u0435\u043a\u0442\u044b");
    private final BooleanSetting field0970 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("swordtrail.mesh", false).method1007("Mesh Afterimage")).method0210("3D ghost copies of the hand/item along the swing")).method2130("3D-\u043f\u0440\u0438\u0437\u0440\u0430\u043a\u0438 \u0440\u0443\u043a\u0438/\u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430 \u0432\u0434\u043e\u043b\u044c \u0432\u0437\u043c\u0430\u0445\u0430");
    private final FloatSetting field0190 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.mesh.interval", 2.0f, 1.0f, 10.0f, 1.0f, this.field0970::method0492).method1007("Spawn Interval")).method0210("Ticks between ghost spawns while swinging")).method2130("\u0422\u0438\u043a\u043e\u0432 \u043c\u0435\u0436\u0434\u0443 \u043f\u043e\u044f\u0432\u043b\u0435\u043d\u0438\u044f\u043c\u0438 \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u043e\u0432 \u0432\u043e \u0432\u0440\u0435\u043c\u044f \u0443\u0434\u0430\u0440\u0430");
    private final FloatSetting field0470 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.mesh.lifetime", 20.0f, 5.0f, 60.0f, 1.0f, this.field0970::method0492).method1007("Lifetime")).method0210("How long each ghost lives (ticks) before disappearing")).method2130("\u0421\u043a\u043e\u043b\u044c\u043a\u043e \u0442\u0438\u043a\u043e\u0432 \u0436\u0438\u0432\u0451\u0442 \u043a\u0430\u0436\u0434\u044b\u0439 \u043f\u0440\u0438\u0437\u0440\u0430\u043a \u0434\u043e \u0438\u0441\u0447\u0435\u0437\u043d\u043e\u0432\u0435\u043d\u0438\u044f");
    private final FloatSetting field1627 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.mesh.fadein", 0.25f, 0.0f, 0.5f, 0.05f, this.field0970::method0492).method1007("Fade In Ratio")).method0210("Fraction of lifetime used to fade in (appear)")).method2130("\u0414\u043e\u043b\u044f \u0436\u0438\u0437\u043d\u0438 \u043d\u0430 \u043f\u0440\u043e\u044f\u0432\u043b\u0435\u043d\u0438\u0435");
    private final FloatSetting field1553 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.mesh.fadeout", 0.4f, 0.0f, 0.9f, 0.05f, this.field0970::method0492).method1007("Fade Out Ratio")).method0210("Fraction of lifetime used to fade out (disappear)")).method2130("\u0414\u043e\u043b\u044f \u0436\u0438\u0437\u043d\u0438 \u043d\u0430 \u0438\u0441\u0447\u0435\u0437\u043d\u043e\u0432\u0435\u043d\u0438\u0435");
    private final FloatSetting field1716 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.mesh.opacity", 0.5f, 0.1f, 1.0f, 0.05f, this.field0970::method0492).method1007("Mesh Opacity")).method0210("Peak brightness of a ghost at its midlife")).method2130("\u041f\u0438\u043a\u043e\u0432\u0430\u044f \u044f\u0440\u043a\u043e\u0441\u0442\u044c \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u0430 \u0432 \u0441\u0435\u0440\u0435\u0434\u0438\u043d\u0435 \u0436\u0438\u0437\u043d\u0438");
    private final BooleanSetting field1141 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("swordtrail.mesh.perghost", false, this.field0970::method0492).method1007("Per-Ghost Shader Pass")).method0210("Run Shaders effect independently per ghost (more expensive)")).method2130("\u0417\u0430\u043f\u0443\u0441\u043a\u0430\u0442\u044c Shaders-\u044d\u0444\u0444\u0435\u043a\u0442 \u043e\u0442\u0434\u0435\u043b\u044c\u043d\u043e \u043d\u0430 \u043a\u0430\u0436\u0434\u043e\u0433\u043e \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u0430 (\u0434\u043e\u0440\u043e\u0436\u0435)");
    private final BooleanSetting field1093 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("swordtrail.mesh.tint", false, this.field0970::method0492).method1007("Mesh Tint Enabled")).method0210("Overlay tint color on ghosts")).method2130("\u041d\u0430\u043a\u043b\u0430\u0434\u044b\u0432\u0430\u0442\u044c \u0446\u0432\u0435\u0442 \u043f\u043e\u0432\u0435\u0440\u0445 \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u043e\u0432");
    private final ColorSetting field1203 = (ColorSetting)((Setting)((Setting)new ColorSetting("swordtrail.mesh.tintcolor", 255, 150, 255, 128, () -> (Boolean)this.field0970.method0492() != false && (Boolean)this.field1093.method0492() != false).method1007("Mesh Tint Color")).method0210("Tint color for ghosts")).method2130("\u0426\u0432\u0435\u0442 \u043d\u0430\u043b\u043e\u0436\u0435\u043d\u0438\u044f \u043d\u0430 \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u043e\u0432");
    private final BooleanSetting field0875 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("swordtrail.mesh.pulse", false, this.field0970::method0492).method1007("Alpha Pulse")).method0210("Each ghost fades in and out over time (phase-shifted across ghosts)")).method2130("\u041a\u0430\u0436\u0434\u044b\u0439 \u043f\u0440\u0438\u0437\u0440\u0430\u043a \u043f\u043e\u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u0438 \u0438\u0441\u0447\u0435\u0437\u0430\u0435\u0442 \u043f\u043e \u0432\u0440\u0435\u043c\u0435\u043d\u0438 (\u0441 \u0444\u0430\u0437\u043e\u0432\u044b\u043c \u0441\u0434\u0432\u0438\u0433\u043e\u043c)");
    private final FloatSetting field0836 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.mesh.pulsespeed", 2.0f, 0.3f, 6.0f, 0.1f, () -> (Boolean)this.field0970.method0492() != false && (Boolean)this.field0875.method0492() != false).method1007("Pulse Speed")).method0210("Pulsation frequency (Hz)")).method2130("\u0427\u0430\u0441\u0442\u043e\u0442\u0430 \u043f\u0443\u043b\u044c\u0441\u0430\u0446\u0438\u0438 (\u0413\u0446)");
    private final BooleanSetting field0914 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("swordtrail.arc", false).method1007("Arc Trail")).method0210("Glowing ribbon along the tip trajectory")).method2130("\u0421\u0432\u0435\u0442\u044f\u0449\u0430\u044f\u0441\u044f \u043b\u0435\u043d\u0442\u0430 \u0432\u0434\u043e\u043b\u044c \u0442\u0440\u0430\u0435\u043a\u0442\u043e\u0440\u0438\u0438 \u043a\u043e\u043d\u0447\u0438\u043a\u0430");
    private final FloatSetting field1341 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.arc.length", 20.0f, 5.0f, 40.0f, 1.0f, this.field0914::method0492).method1007("Arc Length")).method0210("Segments of trajectory to render")).method2130("\u0421\u0435\u0433\u043c\u0435\u043d\u0442\u043e\u0432 \u0442\u0440\u0430\u0435\u043a\u0442\u043e\u0440\u0438\u0438 \u0434\u043b\u044f \u0440\u0435\u043d\u0434\u0435\u0440\u0430");
    private final FloatSetting field1302 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.arc.width", 0.15f, 0.05f, 0.5f, 0.01f, this.field0914::method0492).method1007("Arc Width")).method0210("Ribbon width in blocks")).method2130("\u0428\u0438\u0440\u0438\u043d\u0430 \u043b\u0435\u043d\u0442\u044b \u0432 \u0431\u043b\u043e\u043a\u0430\u0445");
    private final ColorSetting field1374 = (ColorSetting)((Setting)((Setting)new ColorSetting("swordtrail.arc.start", 120, 220, 255, 255, this.field0914::method0492).method1007("Arc Start Color")).method0210("Color at the tip end of the ribbon")).method2130("\u0426\u0432\u0435\u0442 \u0443 \u0433\u043e\u043b\u043e\u0432\u044b \u043b\u0435\u043d\u0442\u044b");
    private final ColorSetting field0391 = (ColorSetting)((Setting)((Setting)new ColorSetting("swordtrail.arc.end", 120, 220, 255, 0, this.field0914::method0492).method1007("Arc End Color")).method0210("Color at the tail end of the ribbon")).method2130("\u0426\u0432\u0435\u0442 \u0443 \u0445\u0432\u043e\u0441\u0442\u0430 \u043b\u0435\u043d\u0442\u044b");
    private final EnumSetting<GradientMode> field0359 = (EnumSetting)((Setting)((Setting)new EnumSetting<GradientMode>("swordtrail.arc.gradient", GradientMode.field0120, this.field0914::method0492).method1007("Arc Gradient Mode")).method0210("Color mode")).method2130("\u0420\u0435\u0436\u0438\u043c \u043e\u043a\u0440\u0430\u0441\u043a\u0438");
    private final EnumSetting<FadeMode> field0429 = (EnumSetting)((Setting)((Setting)new EnumSetting<FadeMode>("swordtrail.arc.fade", FadeMode.field0119, this.field0914::method0492).method1007("Arc Fade Curve")).method0210("Alpha falloff curve along the ribbon")).method2130("\u041a\u0440\u0438\u0432\u0430\u044f \u0437\u0430\u0442\u0443\u0445\u0430\u043d\u0438\u044f \u043f\u0440\u043e\u0437\u0440\u0430\u0447\u043d\u043e\u0441\u0442\u0438 \u043f\u043e \u043b\u0435\u043d\u0442\u0435");
    private final BooleanSetting field0259 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("swordtrail.particle", false).method1007("Particle Shatter")).method0210("Glow particles spawned along the swing with physics")).method2130("\u0421\u0432\u0435\u0442\u044f\u0449\u0438\u0435\u0441\u044f \u0447\u0430\u0441\u0442\u0438\u0446\u044b \u043f\u043e \u0442\u0440\u0430\u0435\u043a\u0442\u043e\u0440\u0438\u0438 \u0443\u0434\u0430\u0440\u0430 \u0441 \u0444\u0438\u0437\u0438\u043a\u043e\u0439");
    private final FloatSetting field0234 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.particle.rate", 5.0f, 1.0f, 20.0f, 1.0f, this.field0259::method0492).method1007("Particle Rate")).method0210("Particles spawned per tick of swing")).method2130("\u0427\u0430\u0441\u0442\u0438\u0446 \u0437\u0430 \u0442\u0438\u043a \u0441\u0432\u0438\u043d\u0433\u0430");
    private final FloatSetting field0295 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.particle.lifetime", 15.0f, 5.0f, 40.0f, 1.0f, this.field0259::method0492).method1007("Particle Lifetime")).method0210("Ticks before particle dies")).method2130("\u0422\u0438\u043a\u043e\u0432 \u0436\u0438\u0437\u043d\u0438 \u0447\u0430\u0441\u0442\u0438\u0446\u044b");
    private final ColorSetting field0527 = (ColorSetting)((Setting)((Setting)new ColorSetting("swordtrail.particle.color", 255, 220, 140, 200, this.field0259::method0492).method1007("Particle Color")).method0210("Particle tint")).method2130("\u0426\u0432\u0435\u0442 \u0447\u0430\u0441\u0442\u0438\u0446");
    private final FloatSetting field0506 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.particle.size", 0.05f, 0.02f, 0.2f, 0.005f, this.field0259::method0492).method1007("Particle Size")).method0210("Particle size in blocks")).method2130("\u0420\u0430\u0437\u043c\u0435\u0440 \u0447\u0430\u0441\u0442\u0438\u0446 \u0432 \u0431\u043b\u043e\u043a\u0430\u0445");
    private final FloatSetting field0552 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.particle.spread", 15.0f, 0.0f, 45.0f, 1.0f, this.field0259::method0492).method1007("Particle Spread")).method0210("Cone half-angle in degrees")).method2130("\u041f\u043e\u043b\u0443-\u0443\u0433\u043e\u043b \u043a\u043e\u043d\u0443\u0441\u0430 \u0440\u0430\u0437\u043b\u0451\u0442\u0430, \u0433\u0440\u0430\u0434\u0443\u0441\u044b");
    private final FloatSetting field1676 = (FloatSetting)((Setting)((Setting)new FloatSetting("swordtrail.particle.gravity", 0.02f, 0.0f, 0.2f, 0.005f, this.field0259::method0492).method1007("Particle Gravity")).method0210("Downward pull per tick")).method2130("\u0423\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u0435 \u0432\u043d\u0438\u0437 \u0437\u0430 \u0442\u0438\u043a");
    private final BooleanSetting field1659 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("swordtrail.particle.glowtex", true, this.field0259::method0492).method1007("Glow Texture")).method0210("Use glow.png as particle sprite")).method2130("\u0418\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c glow.png \u043a\u0430\u043a \u0441\u043f\u0440\u0430\u0439\u0442 \u0447\u0430\u0441\u0442\u0438\u0446\u044b");
    private final SwordTrailBuffer field1694 = new SwordTrailBuffer();
    private final SwordTrailGlow field1594 = new SwordTrailGlow();
    private final List<SwordTrailAnimation> field1585 = new ArrayList<SwordTrailAnimation>();
    private float field1601 = 0.0f;
    private boolean field1762 = false;
    private int field1740 = 0;

    public SwordTrail() {
        super("SwordTrail", ModuleCategory.field1004, "Arc, afterimage and particle trails for swings");
        this.method1013("\u042d\u0444\u0444\u0435\u043a\u0442\u044b \u0441\u043b\u0435\u0434\u0430 \u043f\u0440\u0438 \u0443\u0434\u0430\u0440\u0435: \u0434\u0443\u0433\u0430, \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u0438 \u0438 \u0447\u0430\u0441\u0442\u0438\u0446\u044b");
    }

    public SwordTrailBuffer method1712() {
        return this.field1694;
    }

    public boolean method1692() {
        return this.field1762;
    }

    public boolean method1755() {
        return (Boolean)this.field0034.method0492();
    }

    public boolean method2030() {
        return this.field0751 && (Boolean)this.field0970.method0492() != false && this.method2278();
    }

    public float method2001() {
        return ((Float)this.field1716.method0492()).floatValue();
    }

    public boolean method2044() {
        return (Boolean)this.field1141.method0492();
    }

    public boolean method0472() {
        return (Boolean)this.field1093.method0492();
    }

    public Color method0453() {
        return this.field1203.method1726();
    }

    public boolean method0480() {
        return this.field0751 && (Boolean)this.field0914.method0492() != false && this.method2278();
    }

    public int method0400() {
        return ((Float)this.field1341.method0492()).intValue();
    }

    public float method0393() {
        return ((Float)this.field1302.method0492()).floatValue();
    }

    public Color method0407() {
        return this.field1374.method1726();
    }

    public Color method0518() {
        return this.field0391.method1726();
    }

    public GradientMode method0514() {
        return (GradientMode)this.field0359.method0492();
    }

    public FadeMode method0525() {
        return (FadeMode)this.field0429.method0492();
    }

    public boolean method2247() {
        return this.field0751 && (Boolean)this.field0259.method0492() != false && this.method2278();
    }

    public int method2240() {
        return ((Float)this.field0234.method0492()).intValue();
    }

    public int method2248() {
        return ((Float)this.field0295.method0492()).intValue();
    }

    public Color method2205() {
        return this.field0527.method1726();
    }

    public float method2200() {
        return ((Float)this.field0506.method0492()).floatValue();
    }

    public float method2209() {
        return ((Float)this.field0552.method0492()).floatValue();
    }

    public float method2273() {
        return ((Float)this.field1676.method0492()).floatValue();
    }

    public boolean method2272() {
        return (Boolean)this.field1659.method0492();
    }

    @Override
    public void method0025() {
        super.method0025();
        this.field1694.method0578();
        this.field1585.clear();
        this.field1740 = 0;
        this.field1601 = 0.0f;
        this.field1762 = false;
    }

    @Override
    public void method2078() {
        super.method2078();
        this.field1694.method0578();
        this.field1594.method0025();
        this.field1585.clear();
    }

    private boolean method1241(class_1799 stack) {
        if (stack == null || stack.method_7960()) {
            return this.field1448.method0492() == ItemFilter.field1018;
        }
        return switch (((ItemFilter)this.field1448.method0492()).ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> false;
            case 1 -> stack.method_7909() instanceof class_1829;
            case 2 -> {
                if (stack.method_7909() instanceof class_1829 || stack.method_7909() instanceof class_1743 || stack.method_7909() instanceof class_1766) {
                    yield true;
                }
                yield false;
            }
            case 3 -> true;
        };
    }

    public boolean method2278() {
        if (SwordTrail.method1974()) {
            return false;
        }
        if (!this.field0751) {
            return false;
        }
        return this.method1241(SwordTrail.field0796.field_1724.method_6047());
    }

    @EventHandler
    public void onTick(PreTickEvent event) {
        if (!this.method2278()) {
            this.field1762 = false;
            this.method1896();
            return;
        }
        float swing = SwordTrail.field0796.field_1724.method_6055(1.0f);
        boolean bl = this.field1762 = SwordTrail.field0796.field_1724.field_6279 > 0 || swing > 0.001f;
        if (this.field1762) {
            this.field1694.method0827(this.field1694.method1187((class_1657)SwordTrail.field0796.field_1724, 1.0f));
            if (((Boolean)this.field0970.method0492()).booleanValue()) {
                int interval = Math.max(1, ((Float)this.field0190.method0492()).intValue());
                if (this.field1740 <= 0) {
                    this.field1585.add(new SwordTrailAnimation(swing, Math.max(2, ((Float)this.field0470.method0492()).intValue())));
                    this.field1740 = interval;
                } else {
                    --this.field1740;
                }
            }
        } else {
            this.field1740 = 0;
        }
        this.method1896();
        if (SwordTrail.field0796.field_1687 != null) {
            this.field1694.method0782(SwordTrail.field0796.field_1687.method_8510(), 60L);
        }
        this.field1594.method0578();
        this.field1601 = swing;
    }

    private void method1896() {
        Iterator<SwordTrailAnimation> it = this.field1585.iterator();
        while (it.hasNext()) {
            SwordTrailAnimation g = it.next();
            ++g.field0004;
            if (!g.method0579()) continue;
            it.remove();
        }
    }

    public List<SwordTrailAnimation> method1924() {
        return this.field1585;
    }

    public float method1919() {
        return ((Float)this.field1627.method0492()).floatValue();
    }

    public float method1925() {
        return ((Float)this.field1553.method0492()).floatValue();
    }

    @EventHandler
    public void onRenderWorld(GlowRenderEvent event) {
        if (!this.method2278()) {
            return;
        }
        SwordTrailTransform.method1452(event.method1808());
        this.field1594.method0665(event.method1603());
    }

    public static enum ItemFilter implements DisplayNamed {
        field0694("None"),
        field0118("Swords Only"),
        field1489("Tools"),
        field1018("Any");

        private final String name;

        private ItemFilter(String n2) {
            this.name = n2;
        }

        @Override
        public String method0557() {
            return this.name;
        }
    }

    public static enum GradientMode implements DisplayNamed {
        field0696("Solid"),
        field0120("Gradient"),
        field1490("Rainbow");

        private final String field1030;

        private GradientMode(String n2) {
            this.field1030 = n2;
        }

        @Override
        public String method0557() {
            return this.field1030;
        }
    }

    public static enum FadeMode implements DisplayNamed {
        field0695("Linear"),
        field0119("EaseOut");

        private final String field1504;

        private FadeMode(String n2) {
            this.field1504 = n2;
        }

        @Override
        public String method0557() {
            return this.field1504;
        }
    }
}