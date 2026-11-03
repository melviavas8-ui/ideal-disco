/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.minecraft.class_10017
 *  net.minecraft.class_10042
 *  net.minecraft.class_1309
 *  net.minecraft.class_1921
 *  net.minecraft.class_2350
 *  net.minecraft.class_3532
 *  net.minecraft.class_4050
 *  net.minecraft.class_4587
 *  net.minecraft.class_4588
 *  net.minecraft.class_4597
 *  net.minecraft.class_465
 *  net.minecraft.class_5617$class_5618
 *  net.minecraft.class_572
 *  net.minecraft.class_583
 *  net.minecraft.class_630
 *  net.minecraft.class_897
 *  net.minecraft.class_922
 *  net.minecraft.class_9848
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Vector3f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 */
package ua.patch.daamky.api.mixins;

import aethereal.Aura;
import aethereal.EntityModelRenderHook;
import aethereal.MinecraftAccess;
import aethereal.RotationManager;
import aethereal.StaticPlayerEntityAccessor;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import java.util.Map;
import net.minecraft.class_10017;
import net.minecraft.class_10042;
import net.minecraft.class_1309;
import net.minecraft.class_1921;
import net.minecraft.class_2350;
import net.minecraft.class_3532;
import net.minecraft.class_4050;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_465;
import net.minecraft.class_5617;
import net.minecraft.class_572;
import net.minecraft.class_583;
import net.minecraft.class_630;
import net.minecraft.class_897;
import net.minecraft.class_922;
import net.minecraft.class_9848;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value={class_922.class})
public abstract class LivingEntityRendererMixin<T extends class_1309, S extends class_10042, M extends class_583<? super S>>
extends class_897<T, S>
implements MinecraftAccess,
EntityModelRenderHook {
    @Shadow
    protected M field_4737;

    @Shadow
    public abstract int method_62484(S var1);

    @Shadow
    public abstract float method_23185(S var1);

    @Shadow
    @Nullable
    public abstract class_1921 method_24302(S var1, boolean var2, boolean var3, boolean var4);

    @Shadow
    public abstract boolean method_4056(S var1);

    @Shadow
    public abstract void method_4058(S var1, class_4587 var2, float var3, float var4);

    @Shadow
    public abstract void method_4042(S var1, class_4587 var2);

    public LivingEntityRendererMixin(class_5617.class_5618 context) {
        super(context);
    }

    @ModifyVariable(method={"updateRenderState"}, at=@At(value="HEAD"), argsOnly=true)
    private float daamky$staticEntityTickDelta(float tickDelta, class_1309 entity) {
        StaticPlayerEntityAccessor staticEntity;
        if (entity instanceof StaticPlayerEntityAccessor && (staticEntity = (StaticPlayerEntityAccessor)entity).daamky$isStaticPlayerEntity()) {
            return 1.0f;
        }
        return tickDelta;
    }

    @ModifyExpressionValue(method={"updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/util/math/MathHelper;lerpAngleDegrees(FFF)F")})
    private float lerpAngleDegreesHook(float original, @Local(ordinal=0, argsOnly=true) class_1309 entity, @Local(ordinal=0, argsOnly=true) float delta) {
        RotationManager controller = RotationManager.field0618;
        Aura aura = Aura.method1701();
        if (entity.equals((Object)MinecraftAccess.field0796.field_1724) && controller.method2059().method2047() != MinecraftAccess.field0796.field_1724.method_36454() && controller.method0012().method2047() != MinecraftAccess.field0796.field_1724.method_36454() && !(MinecraftAccess.field0796.field_1755 instanceof class_465)) {
            float currentYaw;
            boolean isLony = Aura.field0169;
            float prevYaw = isLony ? controller.method0012().method2047() : controller.method2059().method2047();
            float f = currentYaw = isLony ? controller.method0012().method2047() : controller.method0545().method2047();
            if (Aura.method1701().method0409() == null) {
                prevYaw = controller.method2059().method2047();
                currentYaw = controller.method0545().method2047();
            }
            return class_3532.method_16439((float)delta, (float)prevYaw, (float)currentYaw);
        }
        return original;
    }

    @ModifyExpressionValue(method={"updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/entity/LivingEntity;getLerpedPitch(F)F")})
    private float getLerpedPitchHook(float original, @Local(ordinal=0, argsOnly=true) class_1309 entity, @Local(ordinal=0, argsOnly=true) float delta) {
        RotationManager controller = RotationManager.field0618;
        Aura aura = Aura.method1701();
        if (entity.equals((Object)MinecraftAccess.field0796.field_1724) && controller.method2059().method1762() != MinecraftAccess.field0796.field_1724.method_36455() && controller.method0012().method1762() != MinecraftAccess.field0796.field_1724.method_36455() && !(MinecraftAccess.field0796.field_1755 instanceof class_465)) {
            float currentPitch;
            boolean isLony = Aura.field0169;
            float prevPitch = isLony ? controller.method0012().method1762() : controller.method2059().method1762();
            float f = currentPitch = isLony ? controller.method0012().method1762() : controller.method0545().method1762();
            if (Aura.method1701().method0409() == null) {
                prevPitch = controller.method2059().method1762();
                currentPitch = controller.method0545().method1762();
            }
            return class_3532.method_16439((float)delta, (float)prevPitch, (float)currentPitch);
        }
        return original;
    }

    public void daamky$render(class_10042 livingEntityRenderState, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i) {
        @SuppressWarnings("unchecked") S state = (S) livingEntityRenderState;
        class_2350 direction;
        matrixStack.method_22903();
        if (livingEntityRenderState.method_62613(class_4050.field_18078) && (direction = livingEntityRenderState.field_53463) != null) {
            float f = livingEntityRenderState.field_53331 - 0.1f;
            matrixStack.method_46416((float)(-direction.method_10148()) * f, 0.0f, (float)(-direction.method_10165()) * f);
        }
        float g = livingEntityRenderState.field_53453;
        matrixStack.method_22905(g, g, g);
        this.method_4058((S)livingEntityRenderState, matrixStack, livingEntityRenderState.field_53446, g);
        matrixStack.method_22905(-1.0f, -1.0f, 1.0f);
        this.method_4042((S)livingEntityRenderState, matrixStack);
        matrixStack.method_46416(0.0f, -1.501f, 0.0f);
        this.field_4737.method_2819((class_10017)(Object)livingEntityRenderState);
        boolean bl = this.method_4056((S)livingEntityRenderState);
        boolean bl2 = !bl && !livingEntityRenderState.field_53461;
        class_1921 renderLayer = this.method_24302((S)livingEntityRenderState, bl, bl2, livingEntityRenderState.field_53462);
        if (renderLayer != null) {
            class_4588 vertexConsumer = vertexConsumerProvider.getBuffer(renderLayer);
            int j = class_922.method_23622((class_10042)livingEntityRenderState, (float)this.method_23185((S)livingEntityRenderState));
            int k = bl2 ? 0x26FFFFFF : -1;
            int l = class_9848.method_61322((int)k, (int)this.method_62484((S)livingEntityRenderState));
            this.field_4737.method_62100(matrixStack, vertexConsumer, i, j, l);
        }
        matrixStack.method_22909();
    }

    public void daamky$collectSkeleton(class_10042 state, class_4587 matrices, Map<String, Vector3f> out) {
        class_2350 direction;
        M m = this.field_4737;
        if (!(m instanceof class_572)) {
            return;
        }
        class_572 biped = (class_572)m;
        matrices.method_22903();
        if (state.method_62613(class_4050.field_18078) && (direction = state.field_53463) != null) {
            float f = state.field_53331 - 0.1f;
            matrices.method_46416((float)(-direction.method_10148()) * f, 0.0f, (float)(-direction.method_10165()) * f);
        }
        float g = state.field_53453;
        matrices.method_22905(g, g, g);
        this.method_4058(state, matrices, state.field_53446, g);
        matrices.method_22905(-1.0f, -1.0f, 1.0f);
        this.method_4042(state, matrices);
        matrices.method_46416(0.0f, -1.501f, 0.0f);
        this.field_4737.method_2819((class_10017)state);
        out.put("headTop", this.daamky$joint(matrices, biped.field_3398, 0.0f, -0.5f, 0.0f));
        out.put("neck", this.daamky$joint(matrices, biped.field_3398, 0.0f, 0.0f, 0.0f));
        out.put("pelvis", this.daamky$joint(matrices, biped.field_3391, 0.0f, 0.75f, 0.0f));
        out.put("shoulderR", this.daamky$joint(matrices, biped.field_3401, 0.0f, 0.0f, 0.0f));
        out.put("handR", this.daamky$joint(matrices, biped.field_3401, 0.0f, 0.625f, 0.0f));
        out.put("shoulderL", this.daamky$joint(matrices, biped.field_27433, 0.0f, 0.0f, 0.0f));
        out.put("handL", this.daamky$joint(matrices, biped.field_27433, 0.0f, 0.625f, 0.0f));
        out.put("hipR", this.daamky$joint(matrices, biped.field_3392, 0.0f, 0.0f, 0.0f));
        out.put("footR", this.daamky$joint(matrices, biped.field_3392, 0.0f, 0.75f, 0.0f));
        out.put("hipL", this.daamky$joint(matrices, biped.field_3397, 0.0f, 0.0f, 0.0f));
        out.put("footL", this.daamky$joint(matrices, biped.field_3397, 0.0f, 0.75f, 0.0f));
        matrices.method_22909();
    }

    @Unique
    private Vector3f daamky$joint(class_4587 matrices, class_630 part, float x, float y, float z) {
        matrices.method_22903();
        part.method_22703(matrices);
        Vector3f v = new Vector3f(x, y, z);
        matrices.method_23760().method_23761().transformPosition(v);
        matrices.method_22909();
        return v;
    }
}

