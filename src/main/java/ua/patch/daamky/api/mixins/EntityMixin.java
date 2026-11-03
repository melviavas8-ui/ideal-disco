/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  net.minecraft.class_1297
 *  net.minecraft.class_238
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.EntityCollisionEvent;
import aethereal.MinecraftAccess;
import aethereal.NoPush;
import aethereal.RotationManager;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1297.class})
public abstract class EntityMixin
implements MinecraftAccess {
    @Shadow
    private class_238 field_6005;

    @Inject(method={"pushAwayFrom"}, at={@At(value="HEAD")}, cancellable=true)
    public void pushAwayFrom(CallbackInfo ci) {
        if ((Object)this != EntityMixin.field0796.field_1724) {
            return;
        }
        DaamkyClient client = DaamkyClient.method2004();
        if (client == null || client.method1783() == null) {
            return;
        }
        NoPush noPush = client.method1783().method0976(NoPush.class);
        if (noPush != null && noPush.method2195() && noPush.method1736()) {
            ci.cancel();
        }
    }

    @Inject(method={"isPushedByFluids"}, at={@At(value="RETURN")}, cancellable=true)
    public void isPushedByFluids(CallbackInfoReturnable<Boolean> cir) {
        if ((Object)this != EntityMixin.field0796.field_1724) {
            return;
        }
        DaamkyClient client = DaamkyClient.method2004();
        if (client == null || client.method1783() == null) {
            return;
        }
        NoPush noPush = client.method1783().method0976(NoPush.class);
        if (noPush != null && noPush.method2195() && noPush.method1755()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method={"getBoundingBox"}, at={@At(value="HEAD")}, cancellable=true)
    private void getBoundingBox$hitbox(CallbackInfoReturnable<class_238> cir) {
        class_1297 self = (class_1297)(Object)this;
        if (self == EntityMixin.field0796.field_1724) {
            return;
        }
        if (!EntityCollisionEvent.method1813()) {
            return;
        }
        EntityCollisionEvent event = new EntityCollisionEvent(this.field_6005, self);
        DaamkyClient.method2004().method2072().post((Object)event);
        cir.setReturnValue(event.method1627());
    }

    @ModifyVariable(method={"getRotationVector(FF)Lnet/minecraft/util/math/Vec3d;"}, at=@At(value="HEAD"), ordinal=0, argsOnly=true)
    private float modifyPitch(float pitch) {
        if ((Object)this instanceof class_746 && RotationManager.field0618.method2219() != null) {
            return RotationManager.field0618.method2219().method1762();
        }
        return pitch;
    }

    @ModifyExpressionValue(method={"move"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;isControlledByPlayer()Z")})
    public boolean isControlledByPlayerHook(boolean original) {
        if ((Object)this == EntityMixin.field0796.field_1724) {
            return false;
        }
        return original;
    }

    @ModifyExpressionValue(method={"updateVelocity"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;getYaw()F")})
    private float updateVelocityYaw(float original) {
        class_1297 self = (class_1297)(Object)this;
        if (self != EntityMixin.field0796.field_1724) {
            return original;
        }
        return RotationManager.field0618.method1781().method2047();
    }
}

