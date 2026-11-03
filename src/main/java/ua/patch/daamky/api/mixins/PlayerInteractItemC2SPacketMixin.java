/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_2886
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.RotationManager;
import net.minecraft.class_1268;
import net.minecraft.class_2886;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_2886.class})
public class PlayerInteractItemC2SPacketMixin {
    @Mutable
    @Shadow
    @Final
    private float field_51930;
    @Mutable
    @Shadow
    @Final
    private float field_51931;

    @Inject(method={"<init>(Lnet/minecraft/util/Hand;IFF)V"}, at={@At(value="RETURN")})
    private void modifyRotation(class_1268 hand, int sequence, float yaw, float pitch, CallbackInfo ci) {
        this.field_51930 = RotationManager.field0618.method0545().method2047();
        this.field_51931 = RotationManager.field0618.method0545().method1762();
    }
}

