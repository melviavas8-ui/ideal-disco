/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_332
 *  net.minecraft.class_337
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.NoRender;
import net.minecraft.class_332;
import net.minecraft.class_337;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_337.class})
public abstract class BossBarHudMixin {
    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRender(class_332 context, CallbackInfo ci) {
        DaamkyClient client = DaamkyClient.method2004();
        if (client == null || client.method1783() == null) {
            return;
        }
        NoRender noRender = client.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method2044()) {
            ci.cancel();
        }
    }

    @Inject(method={"render"}, at={@At(value="RETURN")})
    private void afterRender(class_332 context, CallbackInfo ci) {
        context.method_51452();
    }
}

