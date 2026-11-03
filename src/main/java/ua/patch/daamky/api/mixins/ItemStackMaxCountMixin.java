/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.ServerAssist;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1799.class})
public abstract class ItemStackMaxCountMixin {
    @Inject(method={"getMaxCount"}, at={@At(value="HEAD")}, cancellable=true)
    private void arbuz$reallyWorldPotionStack(CallbackInfoReturnable<Integer> cir) {
        ServerAssist sa = ServerAssist.method1717();
        if (sa == null || !sa.method1692()) {
            return;
        }
        class_1799 self = (class_1799)this;
        if (self.method_7909() == class_1802.field_8574 || self.method_7909() == class_1802.field_8436 || self.method_7909() == class_1802.field_8150) {
            cir.setReturnValue((Object)7);
        }
    }
}

