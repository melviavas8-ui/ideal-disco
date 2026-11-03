/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.llamalad7.mixinextras.sugar.Local
 *  net.minecraft.class_1268
 *  net.minecraft.class_1306
 *  net.minecraft.class_1309
 *  net.minecraft.class_1799
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_742
 *  net.minecraft.class_746
 *  net.minecraft.class_759
 *  net.minecraft.class_7833
 *  net.minecraft.class_811
 *  org.jetbrains.annotations.NotNull
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.FirstPersonTrailRenderer;
import aethereal.HandAnimationEvent;
import aethereal.HandOffsetEvent;
import aethereal.RenderHandEvent;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_759;
import net.minecraft.class_7833;
import net.minecraft.class_811;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_759.class}, priority=999)
public abstract class HeldItemRendererMixin {
    @Shadow
    private class_1799 field_4048;
    @Shadow
    @Final
    private class_310 field_4050;

    @Shadow
    protected abstract void method_3219(class_4587 var1, class_4597 var2, int var3, float var4, float var5, class_1306 var6);

    @Shadow
    protected abstract void method_3231(class_4587 var1, class_4597 var2, int var3, float var4, float var5, float var6);

    @Shadow
    protected abstract void method_3222(class_4587 var1, class_4597 var2, int var3, float var4, class_1306 var5, float var6, class_1799 var7);

    @Shadow
    protected abstract void method_3224(class_4587 var1, class_1306 var2, float var3);

    @Shadow
    protected abstract void method_3217(class_4587 var1, class_1306 var2, float var3);

    @Shadow
    public abstract void method_3233(class_1309 var1, class_1799 var2, class_811 var3, boolean var4, class_4587 var5, class_4597 var6, int var7);

    @Unique
    private static float getV(class_4587 matrices, float v, float u) {
        float v1 = v;
        if (1.0f < v1) {
            v1 = 1.0f;
        }
        if (0.1f < v1) {
            float w = class_3532.method_15374((float)((u - 0.1f) * 1.3f));
            float x = v1 - 0.1f;
            float y = w * x;
            matrices.method_46416(y * 0.0f, y * 0.004f, y * 0.0f);
        }
        return v1;
    }

    @Unique
    private static float getU(float tickDelta, @NotNull class_1799 item, @NotNull class_4587 matrices, float o, @NotNull class_310 client) {
        matrices.method_22907(class_7833.field_40716.rotationDegrees(o * 35.3f));
        matrices.method_22907(class_7833.field_40718.rotationDegrees(o * -9.785f));
        assert (null != client.field_1724);
        class_746 playerEntity = client.field_1724;
        float u = (float)item.method_7935((class_1309)playerEntity) - ((float)playerEntity.method_6014() - tickDelta + 1.0f);
        return u;
    }

    @Inject(method={"renderFirstPersonItem"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/util/math/MatrixStack;push()V", shift=At.Shift.AFTER)})
    private void renderFirstPersonItem$offset(class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 stack, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
        HandOffsetEvent event = new HandOffsetEvent(matrices, stack, hand);
        DaamkyClient.method2004().method2072().post((Object)event);
    }

    @WrapOperation(method={"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/item/HeldItemRenderer;renderFirstPersonItem(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/util/Hand;FLnet/minecraft/item/ItemStack;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")})
    private void renderItem$renderFirstPersonItem(class_759 instance, class_742 player, float tickDelta, float pitch, class_1268 hand, float swingProgress, class_1799 item, float equipProgress, class_4587 matrices, class_4597 vertexConsumers, int light, Operation<Void> original) {
        RenderHandEvent event = new RenderHandEvent(vertexConsumers, matrices, item, hand);
        DaamkyClient.method2004().method2072().post((Object)event);
        FirstPersonTrailRenderer.method1543(instance, player, tickDelta, pitch, hand, swingProgress, item, equipProgress, matrices, event.method1809(), light, original);
        original.call(new Object[]{instance, player, Float.valueOf(tickDelta), Float.valueOf(pitch), hand, Float.valueOf(swingProgress), item, Float.valueOf(equipProgress), matrices, event.method1809(), light});
    }

    @Inject(method={"renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V"}, at={@At(value="TAIL")})
    private void renderItem$TAIL(float tickDelta, class_4587 matrices, class_4597.class_4598 vertexConsumers, class_746 player, int light, CallbackInfo info) {
        DaamkyClient.method2004().method2072().post((Object)new RenderHandEvent.Pre());
    }

    @WrapOperation(method={"renderFirstPersonItem"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/item/HeldItemRenderer;swingArm(FFLnet/minecraft/client/util/math/MatrixStack;ILnet/minecraft/util/Arm;)V", ordinal=2)})
    private void renderFirstPersonItem$swingAnimation(class_759 instance, float swingProgress, float equipProgress, class_4587 matrices, int armX, class_1306 arm, Operation<Void> original, @Local(ordinal=0, argsOnly=true) class_742 player, @Local(ordinal=0, argsOnly=true) class_1268 hand) {
        HandAnimationEvent event = new HandAnimationEvent(matrices, hand, swingProgress);
        DaamkyClient.method2004().method2072().post((Object)event);
        if (!event.method2079()) {
            original.call(new Object[]{instance, Float.valueOf(swingProgress), Float.valueOf(equipProgress), matrices, armX, arm});
        }
    }
}

