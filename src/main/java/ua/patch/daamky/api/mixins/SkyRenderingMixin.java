/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_4587
 *  net.minecraft.class_9975
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.Ambience;
import aethereal.DaamkyClient;
import net.minecraft.class_4587;
import net.minecraft.class_9975;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_9975.class})
public class SkyRenderingMixin {
    @Inject(method={"renderSky(FFF)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderSky(float red, float green, float blue, CallbackInfo ci) {
        if (DaamkyClient.method2004() == null || DaamkyClient.method2004().method1783() == null) {
            return;
        }
        Ambience we = DaamkyClient.method2004().method1783().method0976(Ambience.class);
        if (we == null || !we.method1755()) {
            return;
        }
        we.method2029();
        ci.cancel();
    }

    @Inject(method={"renderSkyDark"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderSkyDark(class_4587 matrices, CallbackInfo ci) {
        if (DaamkyClient.method2004() == null || DaamkyClient.method2004().method1783() == null) {
            return;
        }
        Ambience we = DaamkyClient.method2004().method1783().method0976(Ambience.class);
        if (we == null || !we.method1755()) {
            return;
        }
        we.method2029();
        ci.cancel();
    }

    @Inject(method={"renderCelestialBodies"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderCelestialBodies(CallbackInfo ci) {
        if (DaamkyClient.method2004() == null || DaamkyClient.method2004().method1783() == null) {
            return;
        }
        Ambience we = DaamkyClient.method2004().method1783().method0976(Ambience.class);
        if (we != null && we.method1755()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderStars"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderStars(CallbackInfo ci) {
        if (DaamkyClient.method2004() == null || DaamkyClient.method2004().method1783() == null) {
            return;
        }
        Ambience we = DaamkyClient.method2004().method1783().method0976(Ambience.class);
        if (we != null && we.method1755()) {
            ci.cancel();
        }
    }
}

