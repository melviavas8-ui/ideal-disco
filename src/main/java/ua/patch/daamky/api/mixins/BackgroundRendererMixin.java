/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_1297
 *  net.minecraft.class_4184
 *  net.minecraft.class_5636
 *  net.minecraft.class_638
 *  net.minecraft.class_6854
 *  net.minecraft.class_758
 *  net.minecraft.class_758$class_4596
 *  net.minecraft.class_758$class_7286
 *  net.minecraft.class_9958
 *  org.joml.Vector4f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.FogRenderEvent;
import aethereal.NoRender;
import net.minecraft.class_1297;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import net.minecraft.class_638;
import net.minecraft.class_6854;
import net.minecraft.class_758;
import net.minecraft.class_9958;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_758.class})
public class BackgroundRendererMixin {
    @Inject(method={"getFogColor"}, at={@At(value="HEAD")}, cancellable=true)
    private static void getFogColorHook(class_4184 camera, float tickDelta, class_638 world, int clampedViewDistance, float skyDarkness, CallbackInfoReturnable<Vector4f> cir) {
        FogRenderEvent event = new FogRenderEvent();
        DaamkyClient.method2004().method2072().post((Object)event);
        if (event.method2079()) {
            int color = event.method1604();
            float r = (float)(color >> 16 & 0xFF) / 255.0f;
            float g = (float)(color >> 8 & 0xFF) / 255.0f;
            float b = (float)(color & 0xFF) / 255.0f;
            float a = (float)(color >> 24 & 0xFF) / 255.0f;
            cir.setReturnValue(new Vector4f(r, g, b, a));
        }
    }

    @Inject(method={"applyFog"}, at={@At(value="HEAD")}, cancellable=true)
    private static void modifyFog(class_4184 camera, class_758.class_4596 fogType, Vector4f color, float viewDistance, boolean thickenFog, float tickDelta, CallbackInfoReturnable<class_9958> cir) {
        NoRender noRender;
        DaamkyClient client = DaamkyClient.method2004();
        if (client != null && client.method1783() != null && (noRender = client.method1783().method0976(NoRender.class)) != null && noRender.method0458() && camera.method_19334() == class_5636.field_27886) {
            cir.setReturnValue(new class_9958(0.0f, viewDistance, class_6854.field_36350, color.x, color.y, color.z, color.w));
            return;
        }
        FogRenderEvent event = new FogRenderEvent();
        DaamkyClient.method2004().method2072().post((Object)event);
        if (event.method2079()) {
            int color1 = event.method1604();
            float r = (float)(color1 >> 16 & 0xFF) / 255.0f;
            float g = (float)(color1 >> 8 & 0xFF) / 255.0f;
            float b = (float)(color1 & 0xFF) / 255.0f;
            float a = (float)(color1 >> 24 & 0xFF) / 255.0f;
            cir.setReturnValue(new class_9958(2.0f, event.method1762(), class_6854.field_36351, r, g, b, a));
        }
    }

    @Inject(method={"getFogModifier"}, at={@At(value="HEAD")}, cancellable=true)
    private static void norender$getFogModifier(class_1297 entity, float tickDelta, CallbackInfoReturnable<class_758.class_7286> cir) {
        DaamkyClient client = DaamkyClient.method2004();
        if (client == null || client.method1783() == null) {
            return;
        }
        NoRender noRender = client.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method0480()) {
            cir.setReturnValue(null);
        }
    }
}

