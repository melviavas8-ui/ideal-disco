/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  meteordevelopment.orbit.EventHandler
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 *  org.joml.Vector4f
 */
package aethereal;

import aethereal.AntiBot;
import aethereal.DaamkyClient;
import aethereal.BooleanSetting;
import aethereal.ColorSetting;
import aethereal.FriendManager;
import aethereal.Module;
import aethereal.ModuleCategory;
import aethereal.Setting;
import aethereal.WorldRenderEvent;
import aethereal.WorldRenderHelper;
import java.awt.Color;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector4f;

public class Tracers
extends Module {
    private final ColorSetting field0049 = (ColorSetting)((Setting)((Setting)new ColorSetting("tracers.color", 255, 156, 249, 200).method1882().method1007("Color")).method0210("Tracer line color")).method2130("\u0426\u0432\u0435\u0442 \u043b\u0438\u043d\u0438\u0438");
    private final BooleanSetting field1432 = (BooleanSetting)((Setting)((Setting)new BooleanSetting("tracers.friendcolor", true).method1007("Friend Color")).method0210("Render friends in green")).method2130("\u0414\u0440\u0443\u0437\u0435\u0439 \u043e\u0442\u0440\u0438\u0441\u043e\u0432\u044b\u0432\u0430\u0442\u044c \u0437\u0435\u043b\u0451\u043d\u044b\u043c");

    public Tracers() {
        super("Tracers", ModuleCategory.field1004, "Draws lines from camera to nearby players");
        this.method1013("\u0420\u0438\u0441\u0443\u0435\u0442 \u043b\u0438\u043d\u0438\u0438 \u043a \u0431\u043b\u0438\u0436\u0430\u0439\u0448\u0438\u043c \u0438\u0433\u0440\u043e\u043a\u0430\u043c");
    }

    @EventHandler
    public void onRender3D(WorldRenderEvent.GamePass event) {
        if (Tracers.method1974()) {
            return;
        }
        float tickDelta = event.method1811().method_60637(true);
        class_4587 stack = new class_4587();
        class_243 from = this.method0942(event);
        AntiBot antiBot = AntiBot.method1700();
        FriendManager friends = DaamkyClient.method2004().method1608();
        Color baseColor = this.field0049.method1726();
        for (class_1657 player : Tracers.field0796.field_1687.method_18456()) {
            Color color;
            boolean isFriend;
            if (player == Tracers.field0796.field_1724 || !player.method_5805() || player.method_5477().getString().isEmpty() || antiBot != null && antiBot.method0250(player)) continue;
            class_243 to = Tracers.method1188(player, tickDelta);
            boolean bl = isFriend = (Boolean)this.field1432.method0492() != false && friends != null && friends.method2135(player.method_5477().getString());
            if (isFriend) {
                Color color2;
                color = color2;
                color2 = new Color(66, 245, 149, baseColor.getAlpha());
            } else {
                color = baseColor;
            }
            Color lineColor = color;
            WorldRenderHelper.method1507(stack, from, to, lineColor);
        }
    }

    private class_243 method0942(WorldRenderEvent.GamePass event) {
        class_4184 camera = Tracers.field0796.field_1773.method_19418();
        Matrix4f bobTilt = event.method1629().method_23760().method_23761();
        Matrix4f rotation = new Matrix4f().rotation((Quaternionfc)camera.method_23767().conjugate(new Quaternionf()));
        Matrix4f combined = new Matrix4f((Matrix4fc)bobTilt).mul((Matrix4fc)rotation).invert();
        Vector4f anchor = new Vector4f(0.0f, 0.0f, -1.0f, 1.0f);
        anchor.mul((Matrix4fc)combined);
        return camera.method_19326().method_1031((double)anchor.x, (double)anchor.y, (double)anchor.z);
    }

    private static class_243 method1188(class_1657 player, float tickDelta) {
        double x = class_3532.method_16436((double)tickDelta, (double)player.field_6038, (double)player.method_23317());
        double y = class_3532.method_16436((double)tickDelta, (double)player.field_5971, (double)player.method_23318());
        double z = class_3532.method_16436((double)tickDelta, (double)player.field_5989, (double)player.method_23321());
        return new class_243(x, y + (double)player.method_17682() / 2.0, z);
    }
}

