/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10151
 *  net.minecraft.class_10156
 *  net.minecraft.class_5944
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.ShaderRegistry;
import net.minecraft.class_10151;
import net.minecraft.class_10156;
import net.minecraft.class_5944;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_10151.class})
public class ShaderLoaderMixin {
    @Inject(method={"getOrCreateProgram"}, at={@At(value="HEAD")}, cancellable=true)
    private void daamky$intercept(class_10156 key, CallbackInfoReturnable<class_5944> cir) {
        class_5944 preloaded = ShaderRegistry.method1112(key);
        if (preloaded != null) {
            cir.setReturnValue((Object)preloaded);
        }
    }
}

