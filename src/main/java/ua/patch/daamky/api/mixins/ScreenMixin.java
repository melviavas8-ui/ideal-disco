/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_437
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.ClickGuiDashboard;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_437.class})
public abstract class ScreenMixin {
    @Inject(method={"applyBlur"}, at={@At(value="HEAD")}, cancellable=true)
    protected void onApplyBlur(CallbackInfo ci) {
        if (((Boolean)ClickGuiDashboard.field1432.method0492()).booleanValue()) {
            ci.cancel();
        }
    }
}

