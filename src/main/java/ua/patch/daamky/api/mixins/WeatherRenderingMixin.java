/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_4597
 *  net.minecraft.class_9976
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.NoRender;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_4597;
import net.minecraft.class_9976;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_9976.class})
public abstract class WeatherRenderingMixin {
    @Inject(method={"renderPrecipitation(Lnet/minecraft/world/World;Lnet/minecraft/client/render/VertexConsumerProvider;IFLnet/minecraft/util/math/Vec3d;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void daamky$onRenderPrecipitation(class_1937 world, class_4597 vertexConsumers, int ticks, float tickDelta, class_243 cameraPos, CallbackInfo ci) {
        DaamkyClient client = DaamkyClient.method2004();
        if (client == null || client.method1783() == null) {
            return;
        }
        NoRender noRender = client.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method2252()) {
            ci.cancel();
        }
    }
}

