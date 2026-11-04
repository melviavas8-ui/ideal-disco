/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  aethereal.ClientContext
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  com.llamalad7.mixinextras.injector.ModifyReturnValue
 *  net.minecraft.class_310
 *  net.minecraft.class_437
 *  net.minecraft.class_442
 *  net.minecraft.class_542
 *  net.minecraft.class_636
 *  net.minecraft.class_746
 *  net.minecraft.class_757
 *  org.jetbrains.annotations.Nullable
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
import aethereal.ClientContext;
import aethereal.ClientTickEvent;
import aethereal.GameLoopEvent;
import aethereal.DaamkyMainMenu;
import aethereal.MultiTask;
import aethereal.WindowHandleUtil;
import aethereal.WindowIconManager;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_542;
import net.minecraft.class_636;
import net.minecraft.class_746;
import net.minecraft.class_757;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_310.class})
public class MinecraftClientMixin {
    @Shadow
    @Nullable
    public class_636 field_1761;
    @Shadow
    @Nullable
    public class_746 field_1724;
    @Shadow
    @Final
    public class_757 field_1773;
    @Unique
    private static boolean daamky$replacingScreen = false;

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    private void onClientTick(CallbackInfo ci) {
        if (DaamkyClient.method2004() != null && DaamkyClient.method2004().method2072() != null) {
            DaamkyClient.method2004().method2072().post((Object)new ClientTickEvent());
            DaamkyClient.method2004().method2072().post((Object)new GameLoopEvent());
        }
    }

    @Inject(method={"<init>"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/MinecraftClient;setOverlay(Lnet/minecraft/client/gui/screen/Overlay;)V", shift=At.Shift.BEFORE)})
    private void init(class_542 args, CallbackInfo info) {
        DaamkyClient.method0025();
    }

    @Inject(method={"<init>"}, at={@At(value="TAIL")})
    private void onInit(class_542 args, CallbackInfo ci) {
        class_310 self = (class_310)(Object)this;
        try {
            Thread.sleep(50L);
            WindowIconManager.method0578();
            WindowHandleUtil.method0778(self.method_22683().method_4490());
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @Inject(method={"setScreen"}, at={@At(value="HEAD")}, cancellable=true)
    private void onSetScreen(class_437 screen, CallbackInfo ci) {
        if (daamky$replacingScreen) {
            return;
        }
        class_310 self = (class_310)(Object)this;
        if (screen instanceof class_442) {
            daamky$replacingScreen = true;
            self.method_1507((class_437)new DaamkyMainMenu());
            daamky$replacingScreen = false;
            ci.cancel();
            return;
        }
        if (screen == null && self.field_1687 == null) {
            daamky$replacingScreen = true;
            self.method_1507((class_437)new DaamkyMainMenu());
            daamky$replacingScreen = false;
            ci.cancel();
        }
    }

    @ModifyReturnValue(method={"getWindowTitle"}, at={@At(value="RETURN")})
    private String daamky$modifyWindowTitle(String original) {
        ClientContext api = DaamkyClient.method2004().method1744();
        return "Daamky 1.21.4 #" + api.method0423() + " | " + api.method0557();
    }

    @ModifyExpressionValue(method={"handleBlockBreaking"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z")})
    private boolean daamky$handleBlockBreaking(boolean original) {
        if (this.daamky$isMultiTaskEnabled()) {
            return false;
        }
        return original;
    }

    @ModifyExpressionValue(method={"doItemUse"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerInteractionManager;isBreakingBlock()Z")})
    private boolean daamky$doItemUse(boolean original) {
        if (this.daamky$isMultiTaskEnabled()) {
            return false;
        }
        return original;
    }

    @Unique
    private boolean daamky$isMultiTaskEnabled() {
        DaamkyClient client = DaamkyClient.method2004();
        if (client == null || client.method1783() == null) {
            return false;
        }
        MultiTask multiTask = client.method1783().method0976(MultiTask.class);
        return multiTask != null && multiTask.method2195();
    }
}

