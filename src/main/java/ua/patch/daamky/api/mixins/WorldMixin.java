/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_1937
 *  net.minecraft.class_638
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.Ambience;
import aethereal.DaamkyClient;
import net.minecraft.class_1937;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1937.class})
public abstract class WorldMixin {
    @Inject(method={"getTimeOfDay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetTimeOfDay(CallbackInfoReturnable<Long> cir) {
        if (!((Object)this instanceof class_638)) {
            return;
        }
        if (DaamkyClient.method2004() == null || DaamkyClient.method2004().method1783() == null) {
            return;
        }
        Ambience ambience = DaamkyClient.method2004().method1783().method0976(Ambience.class);
        if (ambience != null && ambience.method1736()) {
            cir.setReturnValue(ambience.method1681());
        }
    }

    @Inject(method={"getSkyAngleRadians"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetSkyAngleRadians(float tickDelta, CallbackInfoReturnable<Float> cir) {
        if (!((Object)this instanceof class_638)) {
            return;
        }
        if (DaamkyClient.method2004() == null || DaamkyClient.method2004().method1783() == null) {
            return;
        }
        Ambience ambience = DaamkyClient.method2004().method1783().method0976(Ambience.class);
        if (ambience != null && ambience.method1736()) {
            float angle = (float)(ambience.method1681() % 24000L) / 24000.0f;
            cir.setReturnValue(Float.valueOf(angle * ((float)Math.PI * 2)));
        }
    }
}

