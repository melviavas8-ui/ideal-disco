/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  com.llamalad7.mixinextras.injector.wrapoperation.Operation
 *  com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation
 *  com.llamalad7.mixinextras.sugar.Local
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1675
 *  net.minecraft.class_238
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_3966
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_757
 *  net.minecraft.class_9779
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
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
import aethereal.EmberHands;
import aethereal.EntityCollisionEvent;
import aethereal.FovEvent;
import aethereal.GlowRenderEvent;
import aethereal.MinecraftAccess;
import aethereal.NoRender;
import aethereal.NoTrace;
import aethereal.RaycastHelper;
import aethereal.RenderEntityEvent;
import aethereal.RenderShaderEvent;
import aethereal.RotationManager;
import aethereal.WorldGeometryRenderer;
import aethereal.WorldRenderEvent;
import aethereal.WorldRenderHelper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1297;
import net.minecraft.class_1675;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_3966;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_757.class})
public abstract class GameRendererMixin
implements MinecraftAccess {
    @Shadow
    private float field_4005;
    @Shadow
    private float field_3988;
    @Shadow
    private float field_4004;

    @Shadow
    public abstract float method_32796();

    @Inject(method={"renderWorld"}, at={@At(value="HEAD")})
    public void renderWorld(class_9779 renderTickCounter, CallbackInfo ci) {
        WorldRenderHelper.method0578();
    }

    @WrapOperation(method={"renderWorld"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/GameRenderer;renderHand(Lnet/minecraft/client/render/Camera;FLorg/joml/Matrix4f;)V")})
    private void renderWorld$emberHands(class_757 instance, class_4184 camera, float tickDelta, Matrix4f matrix, Operation<Void> original) {
        EmberHands ember = DaamkyClient.method2004().method1783().method0976(EmberHands.class);
        if (ember == null || !ember.method2195()) {
            original.call(new Object[]{instance, camera, Float.valueOf(tickDelta), matrix});
            return;
        }
        ember.method0991(() -> original.call(new Object[]{instance, camera, Float.valueOf(tickDelta), matrix}));
    }

    @Inject(at={@At(value="FIELD", target="Lnet/minecraft/client/render/GameRenderer;renderHand:Z", opcode=180, ordinal=0)}, method={"renderWorld"})
    public void renderWorld(class_9779 renderTickCounter, CallbackInfo info, @Local(ordinal=2) Matrix4f matrix4f3, @Local(ordinal=1) float tickDelta, @Local class_4587 matrixStack) {
        RenderSystem.getModelViewStack().pushMatrix();
        RenderSystem.getModelViewStack().mul((Matrix4fc)matrix4f3);
        class_4587 cleanMatrixStack = new class_4587();
        RenderSystem.getModelViewStack().mul((Matrix4fc)cleanMatrixStack.method_23760().method_23761());
        WorldRenderEvent.GamePass event = new WorldRenderEvent.GamePass(renderTickCounter, matrixStack);
        DaamkyClient.method2004().method2072().post((Object)event);
        WorldRenderHelper.method1081(WorldRenderHelper.field0139, WorldRenderHelper.field1508, false);
        WorldRenderHelper.method1081(WorldRenderHelper.field1032, WorldRenderHelper.field0209, true);
        WorldRenderHelper.method1079(WorldRenderHelper.field0488, WorldRenderHelper.field1644);
        RenderSystem.getModelViewStack().popMatrix();
    }

    @Inject(method={"renderWorld"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/render/WorldRenderer;render(Lnet/minecraft/client/util/ObjectAllocator;Lnet/minecraft/client/render/RenderTickCounter;ZLnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/GameRenderer;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;)V", shift=At.Shift.AFTER)})
    private void renderWorld$swap(class_9779 tickCounter, CallbackInfo info, @Local(ordinal=2) Matrix4f matrix4f3, @Local(ordinal=1) float tickDelta, @Local class_4587 matrixStack) {
        RenderSystem.getModelViewStack().pushMatrix();
        RenderSystem.getModelViewStack().mul((Matrix4fc)matrix4f3);
        WorldGeometryRenderer.method0578();
        DaamkyClient.method2004().method2072().post((Object)new GlowRenderEvent(matrixStack, tickDelta));
        WorldGeometryRenderer.method1081(WorldGeometryRenderer.field0139, WorldGeometryRenderer.field1508, false);
        WorldGeometryRenderer.method1081(WorldGeometryRenderer.field1032, WorldGeometryRenderer.field0209, true);
        WorldGeometryRenderer.method1079(WorldGeometryRenderer.field0488, WorldGeometryRenderer.field1644);
        WorldGeometryRenderer.field0751 = false;
        DaamkyClient.method2004().method2072().post((Object)new GlowRenderEvent.Pre(matrixStack, tickDelta));
        DaamkyClient.method2004().method2072().post((Object)new RenderEntityEvent.Pre());
        RenderSystem.getModelViewStack().popMatrix();
    }

    @Inject(method={"findCrosshairTarget"}, at={@At(value="HEAD")})
    private void findCrosshairTarget$hitboxOn(class_1297 camera, double blockInteractionRange, double entityInteractionRange, float tickDelta, CallbackInfoReturnable<class_239> cir) {
        EntityCollisionEvent.method0345(true);
    }

    @Inject(method={"findCrosshairTarget"}, at={@At(value="RETURN")})
    private void findCrosshairTarget$hitboxOff(class_1297 camera, double blockInteractionRange, double entityInteractionRange, float tickDelta, CallbackInfoReturnable<class_239> cir) {
        EntityCollisionEvent.method0345(false);
    }

    @Redirect(method={"findCrosshairTarget"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;raycast(DFZ)Lnet/minecraft/util/hit/HitResult;"))
    private class_239 hookRaycast(class_1297 instance, double maxDistance, float tickDelta, boolean includeFluids) {
        if (instance != GameRendererMixin.field0796.field_1724) {
            return instance.method_5745(maxDistance, tickDelta, includeFluids);
        }
        return RaycastHelper.method0640(maxDistance, RotationManager.field0618.method0545(), includeFluids);
    }

    @Redirect(method={"findCrosshairTarget"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;getRotationVec(F)Lnet/minecraft/util/math/Vec3d;"))
    private class_243 hookRotationVector(class_1297 instance, float tickDelta) {
        return RotationManager.field0618.method0545().method0024();
    }

    @Inject(method={"findCrosshairTarget"}, at={@At(value="HEAD")}, cancellable=true)
    private void findCrosshairTarget$noTrace(class_1297 camera, double blockInteractionRange, double entityInteractionRange, float tickDelta, CallbackInfoReturnable<class_239> cir) {
        double entityDistSq;
        double blockDistSq;
        class_238 box;
        class_239 blockHit;
        DaamkyClient client = DaamkyClient.method2004();
        if (client == null || client.method1783() == null) {
            return;
        }
        NoTrace noTrace = client.method1783().method0976(NoTrace.class);
        if (noTrace == null) {
            return;
        }
        boolean ignoreEntity = noTrace.method1736();
        boolean ignoreWeb = noTrace.method1692();
        if (!ignoreEntity && !ignoreWeb) {
            return;
        }
        double range = Math.max(blockInteractionRange, entityInteractionRange);
        Object object = blockHit = ignoreWeb ? noTrace.method1131(camera, range, tickDelta, false) : camera.method_5745(range, tickDelta, false);
        if (ignoreEntity) {
            cir.setReturnValue(blockHit);
            return;
        }
        class_243 cameraPos = camera.method_5836(tickDelta);
        class_243 look = camera.method_5828(tickDelta);
        class_243 end = cameraPos.method_1031(look.field_1352 * range, look.field_1351 * range, look.field_1350 * range);
        class_3966 entityHit = class_1675.method_18075((class_1297)camera, (class_243)cameraPos, (class_243)end, (class_238)(box = camera.method_5829().method_18804(look.method_1021(range)).method_1009(1.0, 1.0, 1.0)), e -> !e.method_7325() && e.method_5863(), (double)(blockDistSq = blockHit.method_17783() == class_239.class_240.field_1333 ? range * range : cameraPos.method_1025(blockHit.method_17784())));
        if (entityHit != null && ((entityDistSq = cameraPos.method_1025(entityHit.method_17784())) < blockDistSq || blockHit.method_17783() == class_239.class_240.field_1333)) {
            cir.setReturnValue(entityHit);
            return;
        }
        cir.setReturnValue(blockHit);
    }

    @Inject(method={"getBasicProjectionMatrix"}, at={@At(value="TAIL")}, cancellable=true)
    private void getBasicProjectionMatrix$aspect(float fovDegrees, CallbackInfoReturnable<Matrix4f> cir) {
        FovEvent event = new FovEvent();
        DaamkyClient.method2004().method2072().post((Object)event);
        if (!event.method2079()) {
            return;
        }
        Matrix4f matrix4f = new Matrix4f();
        if (this.field_4005 != 1.0f) {
            matrix4f.translate(this.field_3988, -this.field_4004, 0.0f);
            matrix4f.scale(this.field_4005, this.field_4005, 1.0f);
        }
        matrix4f.perspective(fovDegrees * ((float)Math.PI / 180), event.method1762(), 0.05f, this.method_32796());
        cir.setReturnValue(matrix4f);
    }

    @Inject(method={"renderWorld"}, at={@At(value="TAIL")})
    private void renderWorld$TAIL(class_9779 renderTickCounter, CallbackInfo info) {
        DaamkyClient.method2004().method2072().post((Object)new RenderShaderEvent.Pre());
    }

    @Inject(method={"tiltViewWhenHurt"}, at={@At(value="HEAD")}, cancellable=true)
    private void onTiltViewWhenHurt(CallbackInfo ci) {
        DaamkyClient client = DaamkyClient.method2004();
        if (client == null || client.method1783() == null) {
            return;
        }
        NoRender noRender = client.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method1692()) {
            ci.cancel();
        }
    }
}

