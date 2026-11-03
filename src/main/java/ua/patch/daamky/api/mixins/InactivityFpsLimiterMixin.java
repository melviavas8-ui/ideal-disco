/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_9919
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import net.minecraft.class_310;
import net.minecraft.class_9919;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_9919.class})
public class InactivityFpsLimiterMixin {
    @Inject(method={"update"}, at={@At(value="HEAD")}, cancellable=true)
    private void unlockMenuFps(CallbackInfoReturnable<Integer> cir) {
        class_310 client = class_310.method_1551();
        if (client.field_1755 != null && client.field_1687 == null) {
            cir.setReturnValue(((Integer)client.field_1690.method_42524().method_41753()));
        }
    }
}

