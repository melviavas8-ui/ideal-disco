/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_310
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.AntiInvisible;
import aethereal.DaamkyClient;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1297.class})
public class InvisibilityMixin {
    @Inject(method={"isInvisibleTo"}, at={@At(value="HEAD")}, cancellable=true)
    private void onIsInvisibleTo(class_1657 player, CallbackInfoReturnable<Boolean> cir) {
        if (DaamkyClient.method2004() == null || DaamkyClient.method2004().method1783() == null) {
            return;
        }
        AntiInvisible module = DaamkyClient.method2004().method1783().method0976(AntiInvisible.class);
        if (module != null && module.method2195()) {
            if (!module.method1736() && (Object)(Object)this == class_310.method_1551().field_1724) {
                return;
            }
            cir.setReturnValue(false);
        }
    }
}

