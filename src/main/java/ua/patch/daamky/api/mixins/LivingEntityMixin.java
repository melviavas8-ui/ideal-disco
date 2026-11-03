/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  net.minecraft.class_1297
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.MinecraftAccess;
import aethereal.Rotation;
import aethereal.RotationManager;
import aethereal.RotationPlan;
import aethereal.StaticPlayerEntityAccessor;
import aethereal.SwingDurationEvent;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1309.class})
public abstract class LivingEntityMixin
extends class_1297
implements MinecraftAccess,
StaticPlayerEntityAccessor {
    @Unique
    private boolean arbuz$staticPlayerEntity;

    public LivingEntityMixin(class_1299<?> type, class_1937 world) {
        super(type, world);
    }

    @Override
    public boolean arbuz$isStaticPlayerEntity() {
        return this.arbuz$staticPlayerEntity;
    }

    @Override
    public void arbuz$setStaticPlayerEntity(boolean value) {
        this.arbuz$staticPlayerEntity = value;
    }

    @Inject(method={"getHandSwingDuration"}, at={@At(value="HEAD")}, cancellable=true)
    private void onGetHandSwingDuration(CallbackInfoReturnable<Integer> cir) {
        if (this != LivingEntityMixin.field0796.field_1724) {
            return;
        }
        SwingDurationEvent event = new SwingDurationEvent();
        DaamkyClient.method2004().method2072().post((Object)event);
        if (event.method2079()) {
            cir.setReturnValue((Object)Math.max(1, event.method1763()));
        }
    }

    @Redirect(method={"calcGlidingVelocity"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getPitch()F"))
    private float hookModifyFallFlyingPitchRedirect(class_1309 instance) {
        if (this != LivingEntityMixin.field0796.field_1724) {
            return instance.method_36455();
        }
        RotationManager rotationManager = RotationManager.field0618;
        Rotation rotation = rotationManager.method0545();
        RotationPlan configurable = rotationManager.method1606();
        if (rotation == null || configurable == null || !configurable.method0431() || configurable.method0376()) {
            return instance.method_36455();
        }
        return rotation.method1762();
    }

    @Redirect(method={"calcGlidingVelocity"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;"))
    private class_243 hookModifyFallFlyingRotationVectorRedirect(class_1309 original) {
        if (this != LivingEntityMixin.field0796.field_1724) {
            return original.method_5720();
        }
        RotationManager rotationManager = RotationManager.field0618;
        Rotation rotation = rotationManager.method0545();
        RotationPlan configurable = rotationManager.method1606();
        if (rotation == null || configurable == null || !configurable.method0431() || configurable.method0376()) {
            return original.method_5720();
        }
        return rotation.method0024();
    }

    @ModifyExpressionValue(method={"jump"}, at={@At(value="NEW", target="(DDD)Lnet/minecraft/util/math/Vec3d;")})
    private class_243 hookFixRotation(class_243 original) {
        if (this != LivingEntityMixin.field0796.field_1724) {
            return original;
        }
        float yaw = RotationManager.field0618.method1781().method2047() * ((float)Math.PI / 180);
        return new class_243((double)(-class_3532.method_15374((float)yaw) * 0.2f), 0.0, (double)(class_3532.method_15362((float)yaw) * 0.2f));
    }

    @ModifyExpressionValue(method={"calcGlidingVelocity"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getPitch()F")})
    private float hookModifyFallFlyingPitch(float original) {
        if (this != LivingEntityMixin.field0796.field_1724) {
            return original;
        }
        return RotationManager.field0618.method1781().method1762();
    }

    @ModifyExpressionValue(method={"calcGlidingVelocity"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;")})
    private class_243 hookModifyFallFlyingRotationVector(class_243 original) {
        if (this != LivingEntityMixin.field0796.field_1724) {
            return original;
        }
        return RotationManager.field0618.method1781().method0024();
    }
}

