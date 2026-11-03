/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  meteordevelopment.orbit.EventHandler
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 */
package aethereal;

import aethereal.BooleanSetting;
import aethereal.ColorSetting;
import aethereal.FloatSetting;
import aethereal.GlowRenderEvent;
import aethereal.Module;
import aethereal.ModuleCategory;
import aethereal.Setting;
import aethereal.StaticPlayerEntity;
import aethereal.TotemPopEvent;
import java.awt.Color;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Generated;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1657;
import net.minecraft.class_243;

public class PopChams
extends Module {
    private final FloatSetting field0060 = (FloatSetting)((Setting)((Setting)new FloatSetting("popchams.duration", 1500.0f, 500.0f, 5000.0f, 100.0f).method1007("Duration")).method0210("How long the ghost model stays visible")).method2130("\u0412\u0440\u0435\u043c\u044f \u0436\u0438\u0437\u043d\u0438");
    private final FloatSetting field1450 = (FloatSetting)((Setting)((Setting)new FloatSetting("popchams.fadetime", 500.0f, 100.0f, 2000.0f, 50.0f).method1007("Fade Time")).method0210("Time for the ghost model to fade out")).method2130("\u0412\u0440\u0435\u043c\u044f \u0437\u0430\u0442\u0443\u0445\u0430\u043d\u0438\u044f");
    private final BooleanSetting field0970 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("popchams.fill", true).method1007("Fill")).method0210("Render filled ghost model")).method2130("\u041e\u0442\u0440\u0438\u0441\u043e\u0432\u043a\u0430 \u0437\u0430\u043f\u043e\u043b\u043d\u0435\u043d\u043d\u043e\u0439 \u043c\u043e\u0434\u0435\u043b\u0438");
    private final ColorSetting field0185 = (ColorSetting)((Setting)((Setting)new ColorSetting("popchams.fillcolor", 255, 0, 0, 100, this.field0970::method0492).method1882().method1007("Fill Color")).method0210("Color of the filled ghost model")).method2130("\u0426\u0432\u0435\u0442");
    private final BooleanSetting field0464 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("popchams.outline", true).method1007("Outline")).method0210("Render outline around ghost model")).method2130("\u041e\u0431\u0432\u043e\u0434\u043a\u0430 \u0432\u043e\u043a\u0440\u0443\u0433 \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u0430");
    private final ColorSetting field1623 = (ColorSetting)((Setting)((Setting)new ColorSetting("popchams.outlinecolor", 255, 0, 0, 255, this.field0464::method0492).method1882().method1007("Outline Color")).method0210("Color of the ghost model outline")).method2130("\u0426\u0432\u0435\u0442 \u043e\u0431\u0432\u043e\u0434\u043a\u0438 \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u0430");
    private final BooleanSetting field1544 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("popchams.shine", false).method1007("Shine")).method0210("Add shine effect to ghost model")).method2130("\u0421\u0438\u044f\u043d\u0438\u0435");
    private final BooleanSetting field1709 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("popchams.self", false).method1007("Self")).method0210("Show pop chams on yourself")).method2130("\u041f\u043e\u043a\u0430\u0437\u044b\u0432\u0430\u0442\u044c \u0447\u0430\u043c\u0441\u044b \u043d\u0430 \u0441\u0435\u0431\u0435");
    private final BooleanSetting field1141 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("popchams.rising", true).method1007("Rising")).method0210("Ghost model rises upward over time")).method2130("\u041f\u0440\u0438\u0437\u0440\u0430\u0447\u043d\u0430\u044f \u0443\u043b\u0435\u0442\u0430\u0435\u0442 \u043d\u0430 \u043d\u0435\u0431\u0435\u0441\u0430");
    private final FloatSetting field1097 = (FloatSetting)((Setting)((Setting)new FloatSetting("popchams.risespeed", 0.5f, 0.1f, 2.0f, 0.1f, this.field1141::method0492).method1007("Rise Speed")).method0210("Speed of the rising animation")).method2130("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c \u0430\u043d\u0438\u043c\u0430\u0446\u0438\u0438 \u043f\u043e\u0434\u044a\u0451\u043c\u0430");
    private final Map<Integer, GhostEntity> field1214 = new ConcurrentHashMap<Integer, GhostEntity>();

    public PopChams() {
        super("PopChams", ModuleCategory.field1004, "Renders ghost model when a totem pops");
        this.method1013("\u0420\u0438\u0441\u0443\u0435\u0442 \u043f\u0440\u0438\u0437\u0440\u0430\u043a\u0430 \u043f\u0440\u0438 \u0441\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u043d\u0438\u0438 \u0442\u043e\u0442\u0435\u043c\u0430");
    }

    @Override
    public void method2078() {
        super.method2078();
        this.field1214.clear();
    }

    @EventHandler
    public void onPopTotem(TotemPopEvent event) {
        if (PopChams.method1974()) {
            return;
        }
        class_1657 player = event.method1800();
        if (player == null) {
            return;
        }
        if (player == PopChams.field0796.field_1724 && !((Boolean)this.field1709.method0492()).booleanValue()) {
            return;
        }
        StaticPlayerEntity model = new StaticPlayerEntity(player);
        GhostEntity data = new GhostEntity(model, player.method_19538(), System.currentTimeMillis());
        this.field1214.put(player.method_5628(), data);
    }

    @EventHandler
    public void onRenderWorld(GlowRenderEvent event) {
        if (PopChams.method1974()) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        float durationMs = ((Float)this.field0060.method0492()).floatValue();
        float fadeMs = ((Float)this.field1450.method0492()).floatValue();
        this.field1214.entrySet().removeIf(entry -> {
            GhostEntity data = (GhostEntity)entry.getValue();
            long elapsed = currentTime - data.field1412;
            if ((float)elapsed > durationMs) {
                return true;
            }
            float alpha = 1.0f;
            if ((float)elapsed > durationMs - fadeMs) {
                alpha = 1.0f - ((float)elapsed - (durationMs - fadeMs)) / fadeMs;
            }
            alpha = Math.max(0.0f, Math.min(1.0f, alpha));
            float riseOffset = 0.0f;
            if (((Boolean)this.field1141.method0492()).booleanValue()) {
                float progress = (float)elapsed / durationMs;
                riseOffset = progress * ((Float)this.field1097.method0492()).floatValue();
            }
            this.method0904(data, alpha, event.method1603(), riseOffset);
            return false;
        });
    }

    private void method0904(GhostEntity data, float alpha, float tickDelta, float riseOffset) {
        class_243 renderPos = data.field0157.method_1031(0.0, (double)riseOffset, 0.0);
        data.field0608.method1300(renderPos);
        Color fillC = new Color(this.field0185.method1742(), this.field0185.method2019(), this.field0185.method2002(), (int)((float)this.field0185.method2036() * alpha));
        Color outlineC = new Color(this.field1623.method1742(), this.field1623.method2019(), this.field1623.method2002(), (int)((float)this.field1623.method2036() * alpha));
        try {
            data.field0608.method0713(tickDelta, (Boolean)this.field0970.method0492(), fillC, (Boolean)this.field0464.method0492(), outlineC, (Boolean)this.field1544.method0492());
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private static class GhostEntity {
        private final StaticPlayerEntity field0608;
        private final class_243 field0157;
        private final long field1412;

        public GhostEntity(StaticPlayerEntity model, class_243 position, long startTime) {
            this.field0608 = model;
            this.field0157 = position;
            this.field1412 = startTime;
        }

        @Generated
        public StaticPlayerEntity method0534() {
            return this.field0608;
        }

        @Generated
        public class_243 method0024() {
            return this.field0157;
        }

        @Generated
        public long method2049() {
            return this.field1412;
        }
    }
}

