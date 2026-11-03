/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_10529
 *  net.minecraft.class_2338
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_8242
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.NoRender;
import net.minecraft.class_10529;
import net.minecraft.class_2338;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_8242;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_10529.class})
public abstract class SignBlockEntityRendererMixin {
    @Inject(method={"renderText"}, at={@At(value="HEAD")}, cancellable=true, require=0)
    private void onRenderText(class_2338 pos, class_8242 signText, class_4587 matrices, class_4597 vertexConsumers, int light, int lineHeight, int lineWidth, boolean front, CallbackInfo ci) {
        DaamkyClient client = DaamkyClient.method2004();
        if (client == null || client.method1783() == null) {
            return;
        }
        NoRender noRender = client.method1783().method0976(NoRender.class);
        if (noRender != null && noRender.method0517()) {
            ci.cancel();
        }
    }
}

