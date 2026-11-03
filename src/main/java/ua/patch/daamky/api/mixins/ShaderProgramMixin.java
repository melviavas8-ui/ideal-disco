/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_284
 *  net.minecraft.class_5944
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.ModifyGlint;
import java.awt.Color;
import net.minecraft.class_284;
import net.minecraft.class_5944;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_5944.class})
public class ShaderProgramMixin {
    @Shadow
    @Nullable
    public class_284 field_29474;
    @Shadow
    @Nullable
    public class_284 field_42231;
    @Shadow
    @Nullable
    public class_284 field_29472;

    @Inject(method={"bind"}, at={@At(value="RETURN")})
    private void onBind(CallbackInfo ci) {
        ModifyGlint module = ModifyGlint.field0087;
        if (module == null || !module.method2195()) {
            return;
        }
        if (this.field_42231 == null) {
            return;
        }
        if (this.field_29474 != null) {
            Color color = module.method1726();
            float r = (float)color.getRed() / 255.0f;
            float g = (float)color.getGreen() / 255.0f;
            float b = (float)color.getBlue() / 255.0f;
            float a = (float)color.getAlpha() / 255.0f;
            this.field_29474.method_35657(r, g, b, a);
            this.field_29474.method_1300();
        }
        float alpha = module.method1679();
        this.field_42231.method_1251(alpha);
        this.field_42231.method_1300();
    }
}

