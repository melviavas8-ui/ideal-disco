/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  meteordevelopment.orbit.EventHandler
 *  net.minecraft.class_243
 *  net.minecraft.class_2960
 *  net.minecraft.class_332
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 */
package aethereal;

import aethereal.BooleanSetting;
import aethereal.FontSize;
import aethereal.Fonts;
import aethereal.GuiRenderHelper;
import aethereal.Module;
import aethereal.ModuleCategory;
import aethereal.ProjectionHelper;
import aethereal.Render2DEvent;
import aethereal.ScreenLayoutHelper;
import aethereal.Setting;
import aethereal.ThemePalette;
import aethereal.WaypointManager;
import java.awt.Color;
import java.util.ArrayList;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4184;
import net.minecraft.class_4587;

public class Waypoint
extends Module {
    private final BooleanSetting field0034 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("waypoint.showdistance", true).method1007("Show Distance")).method0210("Show the distance")).method2130("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c \u0434\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044e");
    private static final class_2960 field1522 = class_2960.method_60655((String)"daamky", (String)"textures/glow.png");
    private static final float field0957 = 6.0f;
    private static final float field0177 = 3.0f;
    private static final float field0458 = 6.0f;
    private static final float field1614 = 7.0f;
    private static final float field1538 = 16.0f;
    private static final Color field1726 = new Color(255, 255, 255, 80);
    private static final Color field1153 = new Color(227, 227, 227);
    private static final Color field1104 = new Color(255, 255, 255, 184);

    public Waypoint() {
        super("Waypoint", ModuleCategory.field1004, "Renders for saved waypoints");
        this.method1013("\u041c\u0435\u0442\u043a\u0438 \u0441\u043e\u0445\u0440\u0430\u043d\u0451\u043d\u043d\u044b\u0445 \u0432\u0435\u0439\u043f\u043e\u0439\u043d\u0442\u043e\u0432");
        this.method0213("W");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @EventHandler
    public void onRender(Render2DEvent event) {
        if (Waypoint.method1974()) {
            return;
        }
        ArrayList<WaypointManager.Waypoint> active = new ArrayList<WaypointManager.Waypoint>(WaypointManager.method0552().method0424());
        WaypointManager.Waypoint gps = WaypointManager.method0552().method0014();
        if (gps != null) {
            active.add(gps);
        }
        if (active.isEmpty()) {
            return;
        }
        class_332 ctx = event.method1806();
        class_4587 stack = ctx.method_51448();
        class_4184 camera = Waypoint.field0796.field_1773.method_19418();
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
            for (WaypointManager.Waypoint wp : active) {
                boolean onScreen;
                class_243 worldPos = new class_243(wp.method0001() + 0.5, wp.method2046() + 1.0, wp.method1761() + 0.5);
                double distance = camPos.method_1022(worldPos);
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
                this.method1493(stack, wp, sx, sy, distScale, distance);
            }
        }
        finally {
            GuiRenderHelper.method0025();
        }
    }

    private void method1493(class_4587 stack, WaypointManager.Waypoint wp, float x, float y, float distScale, double distance) {
        FontSize font = Fonts.field0075.method0654(6.0f);
        String name = wp.method0557();
        boolean hasName = name != null && !name.isEmpty();
        String coordsText = "X " + (int)Math.floor(wp.method0001()) + ", Y " + (int)Math.floor(wp.method2046()) + ", Z " + (int)Math.floor(wp.method1761());
        boolean drawDistance = this.field0034.method1938();
        String distText = drawDistance ? Math.round(distance) + "m" : "";
        Color circleColor = new Color(wp.method1604() & 0xFFFFFF);
        float sepGap = 5.0f;
        float innerPad = 4.0f;
        float smallSepH = 7.0f;
        float nameWidth = hasName ? font.method0998(name) : 0.0f;
        float coordsWidth = font.method0998(coordsText);
        float distWidth = drawDistance ? font.method0998(distText) : 0.0f;
        float totalW = innerPad + 7.0f + sepGap + 1.0f + sepGap + coordsWidth;
        if (hasName) {
            totalW += sepGap + 1.0f + sepGap + nameWidth;
        }
        if (drawDistance) {
            totalW += sepGap + 1.0f + sepGap + distWidth;
        }
        float bgHeight = font.method0530() + 7.5f;
        float bgWidth = totalW += innerPad;
        float bgX = -bgWidth / 2.0f;
        float bgY = -bgHeight;
        float scaledBlur = ThemePalette.field0367.get().floatValue();
        stack.method_22903();
        stack.method_46416(x, y - 4.0f, 0.0f);
        stack.method_22905(distScale, distScale, 1.0f);
        Color bgColor = ThemePalette.field0930.get();
        Color borderColor = ThemePalette.field1564;
        GuiRenderHelper.method1462(stack, bgX, bgY, bgWidth, bgHeight, 6.0f, scaledBlur, ThemePalette.field0134);
        GuiRenderHelper.method1463(stack, bgX, bgY, bgWidth, bgHeight, 6.0f, bgColor);
        GuiRenderHelper.method1461(stack, bgX, bgY, bgWidth, bgHeight, 6.0f, 0.5f, 0.5f, borderColor);
        float centerY = bgY + bgHeight / 2.0f;
        float textY = centerY - font.method0530() / 2.0f;
        float smallSepY = centerY - smallSepH / 2.0f;
        float currentX = bgX + innerPad;
        Color glowColor = new Color(circleColor.getRed(), circleColor.getGreen(), circleColor.getBlue(), 200);
        GuiRenderHelper.method1466(stack, currentX + 3.5f - 8.0f, centerY - 8.0f, 16.0f, 16.0f, 8.0f, field1522, glowColor);
        GuiRenderHelper.method0326(stack, currentX, centerY - 3.5f, 7.0f, 7.0f, 2.5f, circleColor);
        currentX += 7.0f;
        if (hasName) {
            GuiRenderHelper.method0326(stack, currentX += sepGap, smallSepY, 1.0f, smallSepH, 1.0f, field1726);
            GuiRenderHelper.method1491(stack, font, name, currentX += 1.0f + sepGap, textY, field1153);
            currentX += nameWidth;
        }
        GuiRenderHelper.method0326(stack, currentX += sepGap, smallSepY, 1.0f, smallSepH, 1.0f, field1726);
        GuiRenderHelper.method1491(stack, font, coordsText, currentX += 1.0f + sepGap, textY, field1104);
        currentX += coordsWidth;
        if (drawDistance) {
            GuiRenderHelper.method0326(stack, currentX += sepGap, smallSepY, 1.0f, smallSepH, 1.0f, field1726);
            GuiRenderHelper.method1491(stack, font, distText, currentX += 1.0f + sepGap, textY, field1104);
        }
        stack.method_22909();
    }
}

