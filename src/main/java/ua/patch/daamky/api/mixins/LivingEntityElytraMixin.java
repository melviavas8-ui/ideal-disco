/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.MinecraftAccess;
import aethereal.Rotation;
import aethereal.RotationManager;
import aethereal.RotationPlan;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1309.class})
public abstract class LivingEntityElytraMixin
implements MinecraftAccess {
    @Shadow
    protected abstract double method_61426();

    @Inject(method={"calcGlidingVelocity"}, at={@At(value="HEAD")}, cancellable=true)
    private void arbuz$fixGlidingVelocity(class_243 oldVelocity, CallbackInfoReturnable<class_243> cir) {
        if (this != LivingEntityElytraMixin.field0796.field_1724) {
            return;
        }
        RotationManager handler = RotationManager.field0618;
        RotationPlan plan = handler.method1606();
        if (plan == null || !plan.method0431()) {
            return;
        }
        Rotation move = handler.method2219();
        if (move == null) {
            return;
        }
        float yaw = move.method2047();
        float pitch = move.method1762();
        class_243 rotationVec = class_243.method_1030((float)pitch, (float)yaw);
        float pitchRad = pitch * ((float)Math.PI / 180);
        double horizontalLength = Math.sqrt(rotationVec.field_1352 * rotationVec.field_1352 + rotationVec.field_1350 * rotationVec.field_1350);
        double oldHorizontalLength = oldVelocity.method_37267();
        double gravity = this.method_61426();
        double cosSquared = class_3532.method_33723((double)Math.cos(pitchRad));
        class_243 v = oldVelocity.method_1031(0.0, gravity * (-1.0 + cosSquared * 0.75), 0.0);
        if (v.field_1351 < 0.0 && horizontalLength > 0.0) {
            double lift = v.field_1351 * -0.1 * cosSquared;
            v = v.method_1031(rotationVec.field_1352 * lift / horizontalLength, lift, rotationVec.field_1350 * lift / horizontalLength);
        }
        if (pitchRad < 0.0f && horizontalLength > 0.0) {
            double accel = oldHorizontalLength * (double)(-class_3532.method_15374((float)pitchRad)) * 0.04;
            v = v.method_1031(-rotationVec.field_1352 * accel / horizontalLength, accel * 3.2, -rotationVec.field_1350 * accel / horizontalLength);
        }
        if (horizontalLength > 0.0) {
            v = v.method_1031((rotationVec.field_1352 / horizontalLength * oldHorizontalLength - v.field_1352) * 0.1, 0.0, (rotationVec.field_1350 / horizontalLength * oldHorizontalLength - v.field_1350) * 0.1);
        }
        v = v.method_18805((double)0.99f, (double)0.98f, (double)0.99f);
        cir.setReturnValue((Object)v);
    }
}

