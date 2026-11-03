/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_638$class_5271
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.Ambience;
import aethereal.DaamkyClient;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_638.class_5271.class})
public class ClientWorldPropertiesMixin {
    @Shadow
    private long field_24439;

    @Inject(method={"setTimeOfDay"}, at={@At(value="HEAD")}, cancellable=true)
    private void setTimeOfDay$ambience(long value, CallbackInfo ci) {
        if (DaamkyClient.method2004() == null || DaamkyClient.method2004().method1783() == null) {
            return;
        }
        Ambience ambience = DaamkyClient.method2004().method1783().method0976(Ambience.class);
        if (ambience != null && ambience.method1736()) {
            this.field_24439 = ambience.method1681();
            ci.cancel();
        }
    }
}

