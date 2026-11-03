/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  net.minecraft.class_10017
 *  net.minecraft.class_1297
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_897
 *  net.minecraft.class_898
 *  org.joml.Quaternionf
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.Chams;
import aethereal.MinecraftAccess;
import aethereal.Shaders;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_10017;
import net.minecraft.class_1297;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_897;
import net.minecraft.class_898;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_898.class})
public class EntityRenderDispatcherMixin
implements MinecraftAccess {
    @Inject(method={"render(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/EntityRenderer;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void render$pixelScaleSkip(class_1297 entity, double x, double y, double z, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light, class_897<?, ?> renderer, CallbackInfo ci) {
        Chams chams = Chams.method1705();
        if (chams != null && chams.method1129(entity)) {
            ci.cancel();
        }
    }

    @WrapOperation(method={"render(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/EntityRenderer;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/EntityRenderDispatcher;renderFire(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/render/entity/state/EntityRenderState;Lorg/joml/Quaternionf;)V")})
    private void render$renderFire(class_898 instance, class_4587 matrices, class_4597 vertexConsumers, class_10017 renderState, Quaternionf rotation, Operation<Void> original) {
        Shaders shaders;
        class_4597 vcp = vertexConsumers;
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz != null && arbuz.method1783() != null && (shaders = arbuz.method1783().method0976(Shaders.class)) != null && shaders.method2195()) {
            vcp = field0796.method_22940().method_23000();
        }
        original.call(new Object[]{instance, matrices, vcp, renderState, rotation});
    }
}

