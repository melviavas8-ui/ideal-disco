/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_1309
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_490
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.Interface;
import net.minecraft.class_1309;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_490;
import net.minecraft.class_746;
import ua.patch.daamky.api.mixins.accessors.IHandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_490.class})
public abstract class InventoryScreenMixin {
    @Inject(method={"drawBackground"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDrawBackground(class_332 context, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        Interface iface = arbuz.method1783().method0976(Interface.class);
        if (iface == null || !iface.method1692()) {
            return;
        }
        IHandledScreen acc = (IHandledScreen)((Object)this);
        int x = acc.arbuz$getX();
        int y = acc.arbuz$getY();
        iface.method1415(context, x, y, acc.arbuz$getBackgroundWidth(), acc.arbuz$getBackgroundHeight(), "Inventory");
        class_746 player = class_310.method_1551().field_1724;
        if (player != null) {
            class_490.method_2486((class_332)context, (int)(x + 26), (int)(y + 8), (int)(x + 75), (int)(y + 78), (int)30, (float)0.0625f, (float)mouseX, (float)mouseY, (class_1309)player);
        }
        ci.cancel();
    }

    @Inject(method={"drawForeground"}, at={@At(value="HEAD")}, cancellable=true)
    private void onDrawForeground(class_332 context, int mouseX, int mouseY, CallbackInfo ci) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        Interface iface = arbuz.method1783().method0976(Interface.class);
        if (iface != null && iface.method1692()) {
            ci.cancel();
        }
    }
}

