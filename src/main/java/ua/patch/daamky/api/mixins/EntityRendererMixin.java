/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_10017
 *  net.minecraft.class_10039
 *  net.minecraft.class_10042
 *  net.minecraft.class_1297
 *  net.minecraft.class_2561
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_897
 *  net.minecraft.class_9998
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.NameTags;
import net.minecraft.class_10017;
import net.minecraft.class_10039;
import net.minecraft.class_10042;
import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_897;
import net.minecraft.class_9998;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_897.class})
public abstract class EntityRendererMixin<T extends class_1297, S extends class_10017> {
    @Inject(method={"renderLabelIfPresent"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderLabel(S state, class_2561 text, class_4587 matrices, class_4597 vertexConsumers, int light, CallbackInfo ci) {
        boolean handledByModule;
        NameTags module = DaamkyClient.method2004().method1783().method0976(NameTags.class);
        if (module == null || !module.method2195()) {
            return;
        }
        if (state instanceof class_9998) {
            return;
        }
        boolean bl = handledByModule = state instanceof class_10042 || state instanceof class_10039;
        if (handledByModule) {
            ci.cancel();
        }
    }
}

