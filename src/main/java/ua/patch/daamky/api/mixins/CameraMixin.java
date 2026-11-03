/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_1297
 *  net.minecraft.class_1922
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_4184
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.CameraEvent;
import aethereal.MinecraftAccess;
import aethereal.MovementState;
import aethereal.NoRender;
import aethereal.Rotation;
import aethereal.RotationManager;
import aethereal.RotationPlan;
import net.minecraft.class_1297;
import net.minecraft.class_1922;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_4184.class})
public abstract class CameraMixin
implements MinecraftAccess {
    @Shadow
    private class_243 field_18712;
    @Shadow
    private float field_18718;
    @Shadow
    private float field_18717;
    @Unique
    private float daamky$lastTickDelta;
    @Unique
    private float daamky$currentYaw;
    @Unique
    private float daamky$currentPitch;

    @Shadow
    protected abstract void method_19322(class_243 var1);

    @Shadow
    protected abstract void method_19325(float var1, float var2);

    @Inject(method={"clipToSpace"}, at={@At(value="HEAD")}, cancellable=true)
    private void daamky$noCameraClip(float desiredCameraDistance, CallbackInfoReturnable<Float> cir) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        NoRender noRender = arbuz.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method2203()) {
            cir.setReturnValue((Object)Float.valueOf(desiredCameraDistance));
        }
    }

    @ModifyArg(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;clipToSpace(F)F"), index=0)
    private float modifyCameraDistance(float distance) {
        CameraEvent event = new CameraEvent((class_4184)this, distance, this.field_18712.field_1352, this.field_18712.field_1351, this.field_18712.field_1350, this.field_18718, this.field_18717, this.daamky$lastTickDelta);
        DaamkyClient.method2004().method2072().post((Object)event);
        if (event.method2195()) {
            this.method_19325(event.method0483(), event.method2213());
        }
        return event.method1603();
    }

    @Inject(method={"update"}, at={@At(value="HEAD")})
    private void onCameraUpdateHead(class_1922 area, class_1297 focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        this.daamky$lastTickDelta = tickDelta;
        this.daamky$currentYaw = this.field_18718;
        this.daamky$currentPitch = this.field_18717;
    }

    @Inject(method={"update"}, at={@At(value="TAIL")})
    private void onCameraUpdate(class_1922 area, class_1297 focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        CameraEvent event = new CameraEvent((class_4184)this, 4.0f, this.field_18712.field_1352, this.field_18712.field_1351, this.field_18712.field_1350, this.field_18718, this.field_18717, tickDelta);
        DaamkyClient.method2004().method2072().post((Object)event);
        if (event.method1945() != this.field_18712.field_1352 || event.method0412() != this.field_18712.field_1351 || event.method0354() != this.field_18712.field_1350) {
            this.method_19322(new class_243(event.method1945(), event.method0412(), event.method0354()));
        }
        if (event.method2195() && !thirdPerson) {
            this.method_19325(event.method0483(), event.method2213());
        }
    }

    @Inject(method={"update"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/Camera;setPos(DDD)V", shift=At.Shift.AFTER)})
    private void injectQuickPerspectiveSwap(class_1922 area, class_1297 focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        boolean shouldModifyRotation;
        RotationManager rotationController = RotationManager.field0618;
        RotationPlan rotationPlan = rotationController.method1606();
        Rotation previousAngle = rotationController.method2188();
        Rotation currentAngle = rotationController.method2219();
        boolean bl = shouldModifyRotation = rotationPlan != null && rotationPlan.method0376();
        if (currentAngle == null || previousAngle == null || !shouldModifyRotation) {
            return;
        }
        this.method_19325((float)class_3532.method_16436((double)(MovementState.method1945() == 1488.0 ? (double)tickDelta : MovementState.method1945()), (double)previousAngle.method2047(), (double)currentAngle.method2047()), (float)class_3532.method_16436((double)(MovementState.method1945() == 1488.0 ? (double)tickDelta : MovementState.method1945()), (double)previousAngle.method1762(), (double)currentAngle.method1762()));
    }
}

