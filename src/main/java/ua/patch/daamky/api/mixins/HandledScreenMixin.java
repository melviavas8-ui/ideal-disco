/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_2561
 *  net.minecraft.class_332
 *  net.minecraft.class_465
 *  net.minecraft.class_5250
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.Event001;
import aethereal.NameProtect;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_465;
import net.minecraft.class_5250;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_465.class})
public abstract class HandledScreenMixin {
    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void arbuz$onRender(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        Event001 event = new Event001((class_465)this, context, mouseX, mouseY);
        DaamkyClient.method2004().method2072().post((Object)event);
    }

    @ModifyArg(method={"drawForeground"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I"), index=1)
    private class_2561 arbuz$maskTitle(class_2561 title) {
        class_5250 masked = NameProtect.method1346(title);
        return masked != null ? masked : title;
    }
}

