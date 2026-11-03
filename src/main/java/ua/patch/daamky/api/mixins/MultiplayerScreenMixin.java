/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_364
 *  net.minecraft.class_4185
 *  net.minecraft.class_437
 *  net.minecraft.class_500
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.ProxyAddressHelper;
import aethereal.ProxyEntry;
import aethereal.ProxyScreen;
import aethereal.ProxyStorage;
import net.minecraft.class_2561;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_500;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_500.class})
public abstract class MultiplayerScreenMixin
extends class_437 {
    protected MultiplayerScreenMixin(class_2561 title) {
        super(title);
    }

    @Inject(method={"init"}, at={@At(value="TAIL")})
    private void onInitAddProxyButton(CallbackInfo ci) {
        class_500 self = (class_500)(Object)this;
        ProxyEntry active = ProxyStorage.method1786().method0547();
        String base = ProxyAddressHelper.method1044("Proxy", "\u041f\u0440\u043e\u043a\u0441\u0438");
        String label = active == null ? base : base + ": " + active.method2060().method0557();
        class_4185 btn = class_4185.method_46430((class_2561)class_2561.method_43470((String)label), b -> this.field_22787.method_1507((class_437)new ProxyScreen((class_437)self))).method_46434(5, 5, 85, 20).method_46431();
        this.method_37063(btn);
    }
}

