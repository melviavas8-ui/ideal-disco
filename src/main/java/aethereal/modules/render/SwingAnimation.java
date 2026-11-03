/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  meteordevelopment.orbit.EventHandler
 *  net.minecraft.class_1268
 *  net.minecraft.class_1306
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 */
package aethereal;

import aethereal.Aura;
import aethereal.BooleanSetting;
import aethereal.DisplayNamed;
import aethereal.EnumSetting;
import aethereal.FloatSetting;
import aethereal.HandAnimationEvent;
import aethereal.Module;
import aethereal.ModuleCategory;
import aethereal.Setting;
import aethereal.SwingDurationEvent;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_7833;

public class SwingAnimation
extends Module {
    private static final int field0004 = 6;
    private final EnumSetting<Style> field1448 = (EnumSetting)((Setting)((Setting)new EnumSetting<Style>("swinganimation.style", Style.field0693).method1007("Style")).method0210("Swing animation type")).method2130("\u0422\u0438\u043f \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438");
    private final FloatSetting field0985 = (FloatSetting)((Setting)((Setting)new FloatSetting("swinganimation.strength", 1.0f, 0.5f, 3.0f, 0.1f).method1007("Strength")).method0210("Animation intensity")).method2130("\u0421\u0438\u043b\u0430");
    private final FloatSetting field0190 = (FloatSetting)((Setting)((Setting)new FloatSetting("swinganimation.speed", 1.0f, 0.5f, 2.5f, 0.1f).method1007("Speed")).method0210("Swing duration multiplier")).method2130("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c");
    private final BooleanSetting field0464 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("swinganimation.onauratarget", false).method1007("On Aura Target")).method0210("Animate only while Aura has a target")).method2130("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u0422\u0430\u0440\u0433\u0435\u0442\u0435");

    public SwingAnimation() {
        super("SwingAnimation", ModuleCategory.field1004, "Custom hand swing animation styles");
        this.method1013("\u0410\u043d\u0438\u043c\u0438\u0440\u0443\u0435\u0442 \u0440\u0443\u043a\u0438");
    }

    @EventHandler
    public void onSwingDuration(SwingDurationEvent e) {
        float speed = class_3532.method_15363((float)((Float)this.field0190.method0492()).floatValue(), (float)0.1f, (float)10.0f);
        e.method0729(Math.max(1, Math.round(6.0f / speed)));
        e.method0578();
    }

    @EventHandler
    public void onHandAnimation(HandAnimationEvent e) {
        if (SwingAnimation.method1974()) {
            return;
        }
        if (e.method1624() != class_1268.field_5808) {
            return;
        }
        class_4587 matrix = e.method1808();
        float swingProgress = e.method1946();
        int i = SwingAnimation.field0796.field_1724.method_6068() == class_1306.field_6183 ? 1 : -1;
        float sin1 = class_3532.method_15374((float)(swingProgress * swingProgress * (float)Math.PI));
        float sin2 = class_3532.method_15374((float)(class_3532.method_15355((float)swingProgress) * (float)Math.PI));
        float sinSmooth = (float)(Math.sin((double)swingProgress * 3.1415936072652464) * 0.5);
        float s = ((Float)this.field0985.method0492()).floatValue();
        if (((Boolean)this.field0464.method0492()).booleanValue() && !this.method1736()) {
            return;
        }
        switch (((Style)this.field1448.method0492()).ordinal()) {
            case 6: {
                matrix.method_46416((float)i * 0.56f, -0.36f, -0.72f);
                matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(80 * i)));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -90.0f * s));
                matrix.method_22907(class_7833.field_40718.rotationDegrees((sin1 - sin2) * 60.0f * (float)i * s));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(-30.0f));
                matrix.method_46416(0.0f, -0.1f, 0.05f);
                break;
            }
            case 0: {
                matrix.method_46416(0.56f * (float)i, -0.32f, -0.72f);
                matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(70 * i)));
                matrix.method_22907(class_7833.field_40718.rotationDegrees((float)(-20 * i)));
                matrix.method_22907(class_7833.field_40716.rotationDegrees(sin2 * sin1 * -5.0f * s));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * sin1 * -120.0f * s));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(-70.0f));
                break;
            }
            case 1: {
                matrix.method_46416((float)i * 0.56f, -0.32f, -0.72f);
                matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(76 * i)));
                matrix.method_22907(class_7833.field_40716.rotationDegrees(sin2 * -5.0f * s));
                matrix.method_22907(class_7833.field_40713.rotationDegrees(sin2 * -100.0f * s));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -155.0f * s));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(-100.0f));
                break;
            }
            case 2: {
                matrix.method_46416((float)i * 0.56f, -0.42f, -0.72f);
                matrix.method_22907(class_7833.field_40716.rotationDegrees((float)i * (45.0f + sin1 * -20.0f * s)));
                matrix.method_22907(class_7833.field_40718.rotationDegrees((float)i * sin2 * -20.0f * s));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -80.0f * s));
                matrix.method_22907(class_7833.field_40716.rotationDegrees((float)i * -45.0f));
                matrix.method_22904(0.0, -0.10000002235663849, 0.0);
                break;
            }
            case 3: {
                matrix.method_46416((float)i * 0.56f, -0.42f, -0.72f);
                matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -80.0f * s));
                matrix.method_22904(0.0, -0.10000002235663849, 0.0);
                break;
            }
            case 4: {
                matrix.method_46416((float)i * 0.56f, -0.32f, -0.72f);
                matrix.method_46416(-sinSmooth * sinSmooth * sin1 * (float)i * s, 0.0f, 0.0f);
                matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(61 * i)));
                matrix.method_22907(class_7833.field_40718.rotationDegrees(sin2 * s));
                matrix.method_22907(class_7833.field_40716.rotationDegrees(sin2 * sin1 * -5.0f * s));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * sin1 * -30.0f * s));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(-60.0f));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(sinSmooth * -60.0f * s));
                break;
            }
            case 5: {
                matrix.method_46416((float)i * 0.56f, -0.32f, -0.72f);
                matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(30 * i)));
                matrix.method_22907(class_7833.field_40716.rotationDegrees(sin2 * 75.0f * (float)i * s));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(sin2 * -45.0f * s));
                matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(30 * i)));
                matrix.method_22907(class_7833.field_40714.rotationDegrees(-80.0f));
                matrix.method_22907(class_7833.field_40716.rotationDegrees((float)(35 * i)));
            }
        }
        e.method0578();
    }

    private boolean method1736() {
        Aura aura = Aura.method1701();
        if (aura == null || !aura.method2195()) {
            return false;
        }
        return aura.method0409() != null && aura.method0409().method_5805();
    }

    public static enum Style implements DisplayNamed {
        field0693("Swipe"),
        field0117("Down"),
        field1488("Smooth"),
        field1017("Smooth 2"),
        field0782("Power"),
        field1265("Feast"),
        field0332("Twist");

        private final String field0208;

        private Style(String name) {
            this.field0208 = name;
        }

        @Override
        public String method0557() {
            return this.field0208;
        }
    }
}

