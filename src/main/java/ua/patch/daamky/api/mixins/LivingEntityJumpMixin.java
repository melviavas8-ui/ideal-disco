/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  net.minecraft.class_1309
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 */
package ua.patch.daamky.api.mixins;

import aethereal.MinecraftAccess;
import aethereal.RotationManager;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={class_1309.class})
public abstract class LivingEntityJumpMixin
implements MinecraftAccess {
    @ModifyExpressionValue(method={"jump"}, at={@At(value="NEW", target="(DDD)Lnet/minecraft/util/math/Vec3d;")})
    private class_243 daamky$fixJumpSprintBoost(class_243 original) {
        if ((Object)this != LivingEntityJumpMixin.field0796.field_1724) {
            return original;
        }
        float yaw = RotationManager.field0618.method1781().method2047() * ((float)Math.PI / 180);
        return new class_243((double)(-class_3532.method_15374((float)yaw) * 0.2f), 0.0, (double)(class_3532.method_15362((float)yaw) * 0.2f));
    }
}

