/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  com.llamalad7.mixinextras.injector.v2.WrapWithCondition
 *  net.minecraft.class_1291
 *  net.minecraft.class_1293
 *  net.minecraft.class_1297
 *  net.minecraft.class_1799
 *  net.minecraft.class_1812
 *  net.minecraft.class_1842
 *  net.minecraft.class_1844
 *  net.minecraft.class_266
 *  net.minecraft.class_2960
 *  net.minecraft.class_329
 *  net.minecraft.class_332
 *  net.minecraft.class_6880
 *  net.minecraft.class_9334
 *  net.minecraft.class_9779
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.Crosshair;
import aethereal.HudRenderEvent;
import aethereal.Interface;
import aethereal.NoRender;
import aethereal.Render2DEvent;
import aethereal.ScreenLayoutHelper;
import aethereal.StatusEffectsRenderEvent;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1812;
import net.minecraft.class_1842;
import net.minecraft.class_1844;
import net.minecraft.class_266;
import net.minecraft.class_2960;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_6880;
import net.minecraft.class_9334;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_329.class})
public abstract class InGameHudMixin {
    @Inject(method={"render"}, at={@At(value="HEAD")})
    public void renderPre(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        float factor = ScreenLayoutHelper.method0002();
        if (factor != 1.0f) {
            context.method_51448().method_22903();
            context.method_51448().method_22905(factor, factor, 1.0f);
        }
        Render2DEvent preEvent = new Render2DEvent(context, tickCounter);
        DaamkyClient.method2004().method2072().post((Object)preEvent);
        if (factor != 1.0f) {
            context.method_51448().method_22909();
        }
    }

    @Inject(method={"render"}, at={@At(value="RETURN")})
    public void render(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        float factor = ScreenLayoutHelper.method0002();
        if (factor != 1.0f) {
            context.method_51448().method_22903();
            context.method_51448().method_22905(factor, factor, 1.0f);
        }
        HudRenderEvent event = new HudRenderEvent(context, tickCounter);
        DaamkyClient.method2004().method2072().post((Object)event);
        if (factor != 1.0f) {
            context.method_51448().method_22909();
        }
    }

    @Inject(method={"renderStatusEffectOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderStatusEffectOverlay(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        NoRender noRender;
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz != null && arbuz.method1783() != null && (noRender = arbuz.method1783().method0976(NoRender.class)) != null && noRender.method2030()) {
            ci.cancel();
            return;
        }
        StatusEffectsRenderEvent event = new StatusEffectsRenderEvent(context, tickCounter);
        DaamkyClient.method2004().method2072().post((Object)event);
        if (event.method2079()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderCrosshair"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderCrosshair(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        Crosshair crosshair = DaamkyClient.method2004().method1783().method0976(Crosshair.class);
        if (crosshair != null && crosshair.method2195()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderScoreboardSidebar(class_332 context, class_266 objective, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        NoRender noRender = arbuz.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method2016()) {
            ci.cancel();
            return;
        }
        Interface iface = arbuz.method1783().method0976(Interface.class);
        if (iface != null && iface.method1755()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderHotbar"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderHotbar(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        Interface iface = arbuz.method1783().method0976(Interface.class);
        if (iface != null && iface.method1736()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderExperienceBar"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderExperienceBar(class_332 context, int x, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        Interface iface = arbuz.method1783().method0976(Interface.class);
        if (iface != null && iface.method1736()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderExperienceLevel"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderExperienceLevel(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        Interface iface = arbuz.method1783().method0976(Interface.class);
        if (iface != null && iface.method1736()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderStatusBars"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderStatusBars(class_332 context, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        Interface iface = arbuz.method1783().method0976(Interface.class);
        if (iface != null && iface.method1736()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderHeldItemTooltip"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderHeldItemTooltip(class_332 context, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        Interface iface = arbuz.method1783().method0976(Interface.class);
        if (iface != null && iface.method1736()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderPortalOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderPortalOverlay(class_332 context, float nauseaStrength, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        NoRender noRender = arbuz.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method0472()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderNauseaOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderNauseaOverlay(class_332 context, float nauseaStrength, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        NoRender noRender = arbuz.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method0406()) {
            ci.cancel();
        }
    }

    @Inject(method={"renderVignetteOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderVignetteOverlay(class_332 context, class_1297 entity, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        NoRender noRender = arbuz.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method0528()) {
            ci.cancel();
        }
    }

    @WrapWithCondition(method={"renderMiscOverlays"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V", ordinal=0)})
    private boolean onRenderPumpkinOverlay(class_329 instance, class_332 context, class_2960 texture, float opacity) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return true;
        }
        NoRender noRender = arbuz.method1783().method0976(NoRender.class);
        return noRender == null || !noRender.method2247();
    }

    @WrapWithCondition(method={"renderMiscOverlays"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/hud/InGameHud;renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V", ordinal=1)})
    private boolean onRenderSnowOverlay(class_329 instance, class_332 context, class_2960 texture, float opacity) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return true;
        }
        NoRender noRender = arbuz.method1783().method0976(NoRender.class);
        return noRender == null || !noRender.method2244();
    }

    @Unique
    private boolean isPotion(class_1799 stack, class_1291 status) {
        if (!(stack.method_7909() instanceof class_1812)) {
            return false;
        }
        class_1844 component = (class_1844)stack.method_57824(class_9334.field_49651);
        if (component == null) {
            return false;
        }
        if (component.comp_2378().isEmpty()) {
            return false;
        }
        for (class_1293 effect : ((class_1842)((class_6880)component.comp_2378().get()).comp_349()).method_8049()) {
            if (effect.method_5579().comp_349() != status) continue;
            return true;
        }
        return false;
    }
}

