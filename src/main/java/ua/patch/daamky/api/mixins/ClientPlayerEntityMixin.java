/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_1313
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_634
 *  net.minecraft.class_638
 *  net.minecraft.class_742
 *  net.minecraft.class_744
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.InventoryComponent;
import aethereal.InventoryManager;
import aethereal.ItemUseEvent;
import aethereal.MovementEvent;
import aethereal.MovementState;
import aethereal.NoPush;
import aethereal.NoRender;
import aethereal.NoSlow;
import aethereal.NoSlowEvent;
import aethereal.PlayerDeathEvent;
import aethereal.PlayerTickEvent;
import aethereal.PlayerUpdateEvent;
import aethereal.PostMovementEvent;
import aethereal.PostMovementTickEvent;
import aethereal.PreTickEvent;
import aethereal.Rotation;
import aethereal.RotationManager;
import aethereal.ScreenCloseEvent;
import aethereal.Sprint;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import net.minecraft.class_1313;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_744;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_746.class})
public abstract class ClientPlayerEntityMixin
extends class_742 {
    @Final
    @Shadow
    protected class_310 field_3937;
    @Final
    @Shadow
    public class_634 field_3944;
    @Shadow
    public class_744 field_3913;
    @Shadow
    public float field_44911;
    @Shadow
    public float field_44912;
    private double bodyYawPrevX = 0.0;
    private double bodyYawPrevZ = 0.0;
    private float prevBodyYaw = 0.0f;
    private boolean initialized = false;
    @Unique
    private boolean daamky$wasDead = false;

    public ClientPlayerEntityMixin(class_638 world, GameProfile profile) {
        super(world, profile);
    }

    @Shadow
    protected abstract void method_46742();

    @Shadow
    protected abstract void method_3148(float var1, float var2);

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    private void onTick(CallbackInfo ci) {
        InventoryManager.method2078();
        PlayerTickEvent playerTickEvent = new PlayerTickEvent();
        DaamkyClient.method2004().method2072().post((Object)playerTickEvent);
    }

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    public void onSilentRotationInit(CallbackInfo ci) {
        if (!this.initialized && this.field_3937.field_1724 != null) {
            this.bodyYawPrevX = this.field_3937.field_1724.method_23317();
            this.bodyYawPrevZ = this.field_3937.field_1724.method_23321();
            this.prevBodyYaw = this.field_3937.field_1724.method_43078();
            this.initialized = true;
        }
    }

    @ModifyExpressionValue(method={"sendMovementPackets", "tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;getYaw()F")})
    private float hookSilentRotationYaw(float original) {
        if (this.field_3937.field_1724 == null) {
            return original;
        }
        boolean hasPlan = RotationManager.field0618.method1606() != null;
        float currentYaw = hasPlan ? RotationManager.field0618.method0545().method2047() : original;
        Rotation serverAngle = RotationManager.field0618.method2258();
        if (serverAngle != null) {
            currentYaw = serverAngle.method2047() + class_3532.method_15393((float)(currentYaw - serverAngle.method2047()));
        }
        if (hasPlan) {
            float newBodyYaw;
            this.prevBodyYaw = newBodyYaw = MovementState.method0679(currentYaw, this.prevBodyYaw, this.bodyYawPrevX, this.bodyYawPrevZ, this.field_3937.field_1724.method_23317(), this.field_3937.field_1724.method_23321(), this.field_3937.field_1724.field_6251);
            this.bodyYawPrevX = this.field_3937.field_1724.method_23317();
            this.bodyYawPrevZ = this.field_3937.field_1724.method_23321();
            this.field_3937.field_1724.method_5636(newBodyYaw);
        }
        return currentYaw;
    }

    @ModifyExpressionValue(method={"sendMovementPackets", "tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;getPitch()F")})
    private float hookSilentRotationPitch(float original) {
        if (RotationManager.field0618.method1606() == null) {
            return original;
        }
        return RotationManager.field0618.method0545().method1762();
    }

    @Inject(method={"sendMovementPackets"}, at={@At(value="RETURN")})
    private void onPostMotion(CallbackInfo ci) {
        InventoryComponent.method0025();
        DaamkyClient.method2004().method2072().post((Object)new PostMovementTickEvent());
    }

    @ModifyExpressionValue(method={"tickMovement"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z")})
    private boolean usingItemHook(boolean original) {
        if (original) {
            ItemUseEvent event = new ItemUseEvent(1);
            DaamkyClient.method2004().method2072().post((Object)event);
            if (event.method2079()) {
                return false;
            }
            Sprint.field0004 = 1;
        }
        return original;
    }

    @Inject(method={"tick"}, at={@At(value="TAIL")})
    private void onTickPost(CallbackInfo ci) {
        PreTickEvent tickEvent = new PreTickEvent();
        DaamkyClient.method2004().method2072().post((Object)tickEvent);
        if (((class_746)this).method_6032() <= 0.0f && !this.daamky$wasDead) {
            this.daamky$wasDead = true;
            DaamkyClient.method2004().method2072().post((Object)new PlayerDeathEvent());
        } else if (((class_746)this).method_6032() > 0.0f) {
            this.daamky$wasDead = false;
        }
    }

    @Inject(method={"tickMovement"}, at={@At(value="TAIL")})
    private void onTickMovementPost(CallbackInfo ci) {
        DaamkyClient.method2004().method2072().post((Object)new PostMovementEvent());
    }

    @Inject(method={"closeHandledScreen"}, at={@At(value="HEAD")}, cancellable=true)
    private void onCloseHandledScreen(CallbackInfo ci) {
        if (this.field_7512 != null) {
            ScreenCloseEvent event = new ScreenCloseEvent(this.field_7512.field_7763);
            DaamkyClient.method2004().method2072().post((Object)event);
            if (event.method2079()) {
                ci.cancel();
            }
        }
    }

    @Inject(method={"tickNausea"}, at={@At(value="HEAD")}, cancellable=true)
    private void onTickNausea(boolean fromPortal, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        NoRender noRender = arbuz.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method0406()) {
            this.field_44911 = 0.0f;
            this.field_44912 = 0.0f;
            ci.cancel();
        }
    }

    @Inject(method={"pushOutOfBlocks"}, at={@At(value="HEAD")}, cancellable=true)
    public void pushOutOfBlocks(double x, double z, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        NoPush noPush = arbuz.method1783().method0976(NoPush.class);
        if (noPush != null && noPush.method2195() && noPush.method1692()) {
            ci.cancel();
        }
    }

    private boolean checkNoSlowCancel() {
        NoSlowEvent event = new NoSlowEvent();
        DaamkyClient.method2004().method2072().post((Object)event);
        NoSlowEvent.method0345(event.method2079());
        return event.method2079();
    }

    @Redirect(method={"tickMovement"}, at=@At(value="FIELD", target="Lnet/minecraft/client/input/Input;movementSideways:F", opcode=181, ordinal=0))
    private void redirectMovementSideways(class_744 input, float value) {
        if (!this.checkNoSlowCancel()) {
            input.field_3907 = value;
        }
    }

    @Redirect(method={"tickMovement"}, at=@At(value="FIELD", target="Lnet/minecraft/client/input/Input;movementForward:F", opcode=181, ordinal=0))
    private void redirectMovementForward(class_744 input, float value) {
        if (!this.checkNoSlowCancel()) {
            input.field_3905 = value;
        }
    }

    @Redirect(method={"tickMovement"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;setSprinting(Z)V", ordinal=0))
    private void redirectSetSprinting(class_746 player, boolean sprinting) {
        if (!this.checkNoSlowCancel()) {
            player.method_5728(sprinting);
        }
    }

    @Redirect(method={"sendMovementPackets"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;sendSprintingPacket()V"))
    public void invokeSprintUpdate(class_746 instance) {
        PlayerUpdateEvent event = new PlayerUpdateEvent();
        DaamkyClient.method2004().method2072().post((Object)event);
        if (!event.method2079()) {
            this.method_46742();
        }
    }

    @Inject(method={"shouldStopSprinting"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z")}, cancellable=true)
    public void shouldStopSprintingHook(CallbackInfoReturnable<Boolean> cir) {
        Sprint sprint = Sprint.method1720();
        NoSlow noSlow = NoSlow.method1715();
        if (sprint != null && sprint.method2195() && noSlow != null && noSlow.method2195()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"canStartSprinting"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z")}, cancellable=true)
    public void canStartSprintingHook(CallbackInfoReturnable<Boolean> cir) {
        Sprint sprint = Sprint.method1720();
        NoSlow noSlow = NoSlow.method1715();
        if (sprint != null && sprint.method2195() && noSlow != null && noSlow.method2195()) {
            cir.setReturnValue((Object)false);
        }
    }

    @Inject(method={"move"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/AbstractClientPlayerEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V")}, cancellable=true)
    public void onMoveHook(class_1313 type, class_243 movement, CallbackInfo ci) {
        MovementEvent event = new MovementEvent(movement);
        DaamkyClient.method2004().method2072().post((Object)event);
        double dx = this.method_23317();
        double dz = this.method_23321();
        super.method_5784(type, event.method1803());
        this.method_3148((float)(this.method_23317() - dx), (float)(this.method_23321() - dz));
        ci.cancel();
    }
}

