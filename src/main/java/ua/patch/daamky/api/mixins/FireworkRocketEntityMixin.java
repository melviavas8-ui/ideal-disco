/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  net.minecraft.class_1309
 *  net.minecraft.class_1671
 *  net.minecraft.class_243
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 */
package ua.patch.daamky.api.mixins;

import aethereal.MinecraftAccess;
import aethereal.RotationManager;
import aethereal.SuperFirework;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_1309;
import net.minecraft.class_1671;
import net.minecraft.class_243;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={class_1671.class})
public class FireworkRocketEntityMixin
implements MinecraftAccess {
    @Shadow
    @Nullable
    private class_1309 field_7616;

    @WrapOperation(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getRotationVector()Lnet/minecraft/util/math/Vec3d;")})
    public class_243 getRotationVectorHook(class_1309 instance, Operation<class_243> original) {
        if (this.field_7616 == FireworkRocketEntityMixin.field0796.field_1724) {
            return RotationManager.field0618.method1781().method0024();
        }
        return (class_243)original.call(new Object[]{instance});
    }

    @WrapOperation(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getVelocity()Lnet/minecraft/util/math/Vec3d;", ordinal=0)})
    public class_243 getVelocityHook(class_1309 instance, Operation<class_243> original) {
        boolean yAcceleration;
        if (this.field_7616 != FireworkRocketEntityMixin.field0796.field_1724) {
            return (class_243)original.call(new Object[]{instance});
        }
        SuperFirework module = SuperFirework.method1722();
        if (module == null || !module.method2195()) {
            return (class_243)original.call(new Object[]{instance});
        }
        class_243 originalVelocity = (class_243)original.call(new Object[]{instance});
        class_243 rot = RotationManager.field0618.method1781().method0024();
        boolean bl = yAcceleration = Math.abs(RotationManager.field0618.method1781().method1762()) > 60.0f;
        if (module.field0058.method0492() == SuperFirework.Mode.field0692) {
            double speedXZ = module.method0614(rot.field_1352, rot.field_1350);
            double speedY = module.method0608(rot.field_1351);
            return new class_243(originalVelocity.field_1352 + rot.field_1352 * (speedXZ - 1.5), originalVelocity.field_1351 + rot.field_1351 * (speedY - 1.5), originalVelocity.field_1350 + rot.field_1350 * (speedXZ - 1.5));
        }
        float yaw = RotationManager.field0618.method0545().method2047();
        int ff = yaw > 0.0f ? 45 : -45;
        double acceleration = (double)Math.abs((yaw + (float)ff) % 90.0f - (float)ff) / 45.0;
        double rotationBoost = 1.0 + 0.3 * acceleration * acceleration;
        class_243 direction = RotationManager.field0618.method1781().method0024();
        float speed = ((Float)module.field1450.method0492()).floatValue() / 20.0f;
        double finalSpeed = (double)speed * rotationBoost;
        return new class_243(direction.field_1352 * finalSpeed, yAcceleration ? direction.field_1351 * finalSpeed : direction.field_1351 * (double)speed, direction.field_1350 * finalSpeed);
    }
}

