/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_2874
 *  net.minecraft.class_765
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.FullBright;
import net.minecraft.class_2874;
import net.minecraft.class_765;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={class_765.class})
public class LightmapTextureManagerMixin {
    @Redirect(method={"update"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/dimension/DimensionType;ambientLight()F"))
    private float redirectAmbientLight(class_2874 type) {
        if (DaamkyClient.method2004() == null || DaamkyClient.method2004().method1783() == null) {
            return type.comp_656();
        }
        FullBright fb = DaamkyClient.method2004().method1783().method0976(FullBright.class);
        if (fb != null && fb.method2195() && fb.method1736()) {
            return 1.0f;
        }
        return type.comp_656();
    }
}

