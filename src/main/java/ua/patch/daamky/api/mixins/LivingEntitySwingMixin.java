/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_1309
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.MinecraftAccess;
import aethereal.SwingDurationEvent;
import net.minecraft.class_1309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1309.class})
public abstract class LivingEntitySwingMixin
implements MinecraftAccess {
    @Inject(method={"getHandSwingDuration"}, at={@At(value="HEAD")}, cancellable=true)
    private void daamky$swingDuration(CallbackInfoReturnable<Integer> cir) {
        if (this != LivingEntitySwingMixin.field0796.field_1724) {
            return;
        }
        SwingDurationEvent event = new SwingDurationEvent();
        DaamkyClient.method2004().method2072().post((Object)event);
        if (event.method2079()) {
            cir.setReturnValue((Object)Math.max(1, event.method1763()));
        }
    }
}

