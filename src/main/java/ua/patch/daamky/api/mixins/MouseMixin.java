/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_310
 *  net.minecraft.class_312
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.FreeLook;
import aethereal.GuiMove;
import aethereal.MouseEvent;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_312.class})
public abstract class MouseMixin {
    @Shadow
    @Final
    private class_310 field_1779;

    @Inject(method={"onMouseButton"}, at={@At(value="HEAD")}, cancellable=true)
    public void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
        MouseEvent event = new MouseEvent(button, action);
        DaamkyClient.method2004().method2072().post((Object)event);
        if (event.method2079()) {
            ci.cancel();
        }
    }

    @Redirect(method={"tick()V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/Mouse;isCursorLocked()Z"))
    private boolean daamky$shouldDriveCamera(class_312 self) {
        if (self.method_1613()) {
            return true;
        }
        DaamkyClient client = DaamkyClient.method2004();
        if (client == null || client.method1783() == null) {
            return false;
        }
        GuiMove guiMove = client.method1783().method0976(GuiMove.class);
        return guiMove != null && guiMove.method1736();
    }

    @Redirect(method={"updateMouse(D)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/network/ClientPlayerEntity;changeLookDirection(DD)V"))
    private void daamky$onChangeLookDirection(class_746 player, double deltaX, double deltaY) {
        DaamkyClient client = DaamkyClient.method2004();
        if (client == null || client.method1783() == null) {
            player.method_5872(deltaX, deltaY);
            return;
        }
        FreeLook freeLook = client.method1783().method0976(FreeLook.class);
        if (freeLook != null && freeLook.method2195() && freeLook.method0616(deltaX, deltaY)) {
            return;
        }
        player.method_5872(deltaX, deltaY);
    }
}

