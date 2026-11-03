/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1533
 *  net.minecraft.class_3695
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4604
 *  net.minecraft.class_757
 *  net.minecraft.class_761
 *  net.minecraft.class_898
 *  net.minecraft.class_9779
 *  net.minecraft.class_9909
 *  net.minecraft.class_9922
 *  net.minecraft.class_9958
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.BlockOverlay;
import aethereal.NoRender;
import aethereal.RenderEntityEvent;
import aethereal.RenderShaderEvent;
import aethereal.ShaderCache;
import aethereal.WorldProjectionHelper;
import aethereal.WorldRenderEvent;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1297;
import net.minecraft.class_1533;
import net.minecraft.class_3695;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4604;
import net.minecraft.class_757;
import net.minecraft.class_761;
import net.minecraft.class_898;
import net.minecraft.class_9779;
import net.minecraft.class_9909;
import net.minecraft.class_9922;
import net.minecraft.class_9958;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_761.class})
public abstract class WorldRendererMixin {
    @Shadow
    @Final
    private class_898 field_4109;

    @Shadow
    protected abstract void method_62202(class_9909 var1, class_4604 var2, class_4184 var3, Matrix4f var4, Matrix4f var5, class_9958 var6, boolean var7, boolean var8, class_9779 var9, class_3695 var10);

    @Inject(method={"render"}, at={@At(value="HEAD")})
    public void render(class_9922 allocator, class_9779 tickCounter, boolean renderBlockOutline, class_4184 camera, class_757 gameRenderer, Matrix4f positionMatrix, Matrix4f projectionMatrix, CallbackInfo ci) {
        WorldRenderEvent.WorldPass event = new WorldRenderEvent.WorldPass(camera, positionMatrix, tickCounter);
        DaamkyClient.method2004().method2072().post((Object)event);
        WorldProjectionHelper.field0750.set((Matrix4fc)positionMatrix);
        WorldProjectionHelper.field0168.set((Matrix4fc)projectionMatrix);
        WorldProjectionHelper.field1526.set((Matrix4fc)RenderSystem.getModelViewMatrix());
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/render/WorldRenderer;renderMain(Lnet/minecraft/client/render/FrameGraphBuilder;Lnet/minecraft/client/render/Frustum;Lnet/minecraft/client/render/Camera;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/client/render/Fog;ZZLnet/minecraft/client/render/RenderTickCounter;Lnet/minecraft/util/profiler/Profiler;)V"))
    private void render$blockOverlay(class_761 instance, class_9909 frameGraphBuilder, class_4604 frustum, class_4184 camera, Matrix4f positionMatrix, Matrix4f projectionMatrix, class_9958 fog, boolean renderBlockOutline, boolean hasEntitiesToRender, class_9779 renderTickCounter, class_3695 profiler) {
        BlockOverlay blockOverlay;
        boolean drawOutline = renderBlockOutline;
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz != null && arbuz.method1783() != null && (blockOverlay = arbuz.method1783().method0976(BlockOverlay.class)) != null && blockOverlay.method2195()) {
            drawOutline = false;
        }
        this.method_62202(frameGraphBuilder, frustum, camera, positionMatrix, projectionMatrix, fog, drawOutline, hasEntitiesToRender, renderTickCounter, profiler);
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", ordinal=0, shift=At.Shift.BEFORE)})
    private void render$swap(class_9922 allocator, class_9779 tickCounter, boolean renderBlockOutline, class_4184 camera, class_757 gameRenderer, Matrix4f positionMatrix, Matrix4f projectionMatrix, CallbackInfo info) {
        RenderShaderEvent event = new RenderShaderEvent();
        DaamkyClient.method2004().method2072().post((Object)event);
    }

    @WrapOperation(method={"renderEntity"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/entity/EntityRenderDispatcher;render(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")})
    private <E extends class_1297> void renderEntity$render(class_898 instance, E entity, double x, double y, double z, float tickDelta, class_4587 matrices, class_4597 vertexConsumers, int light, Operation<Void> original) {
        NoRender noRender;
        DaamkyClient arbuz;
        if (entity instanceof class_1533 && (arbuz = DaamkyClient.method2004()) != null && arbuz.method1783() != null && (noRender = arbuz.method1783().method0976(NoRender.class)) != null && noRender.method2208()) {
            return;
        }
        RenderEntityEvent event = new RenderEntityEvent(entity, vertexConsumers, x, y, z, tickDelta, matrices);
        DaamkyClient.method2004().method2072().post((Object)event);
        original.call(new Object[]{instance, entity, x, y, z, Float.valueOf(tickDelta), matrices, event.method1630(), light});
    }

    @Inject(method={"onResized"}, at={@At(value="TAIL")})
    private void onResized(int width, int height, CallbackInfo info) {
        ShaderCache.method0550().method0738(width, height);
    }

    @Inject(method={"hasBlindnessOrDarkness(Lnet/minecraft/client/render/Camera;)Z"}, at={@At(value="HEAD")}, cancellable=true)
    private void onHasBlindnessOrDarkness(class_4184 camera, CallbackInfoReturnable<Boolean> cir) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        NoRender noRender = arbuz.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method0480()) {
            cir.setReturnValue(false);
        }
    }
}

