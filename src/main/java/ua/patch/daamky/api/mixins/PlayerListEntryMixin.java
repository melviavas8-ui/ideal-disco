/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.class_2960
 *  net.minecraft.class_640
 *  net.minecraft.class_8685
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.Cape;
import aethereal.MinecraftAccess;
import com.mojang.authlib.GameProfile;
import net.minecraft.class_2960;
import net.minecraft.class_640;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_640.class})
public abstract class PlayerListEntryMixin
implements MinecraftAccess {
    @Shadow
    @Final
    private GameProfile field_3741;
    private static final class_2960 DEFAULT_ELYTRA_TEXTURE = class_2960.method_60656((String)"textures/entity/equipment/wings/elytra.png");

    @Inject(method={"getSkinTextures"}, at={@At(value="RETURN")}, cancellable=true)
    private void onGetSkinTextures(CallbackInfoReturnable<class_8685> cir) {
        if (PlayerListEntryMixin.field0796.field_1724 == null) {
            return;
        }
        Cape capeModule = DaamkyClient.method2004().method1783().method0201(Cape.class);
        if (capeModule == null || !capeModule.method2195()) {
            return;
        }
        GameProfile playerProfile = PlayerListEntryMixin.field0796.field_1724.method_7334();
        if (!this.isCurrentPlayer(playerProfile)) {
            return;
        }
        class_2960 capeTexture = capeModule.method1734();
        if (capeTexture == null) {
            return;
        }
        this.applyCapeTexture(cir, capeTexture);
    }

    private boolean isCurrentPlayer(GameProfile playerProfile) {
        return this.field_3741.getName().equals(playerProfile.getName()) && this.field_3741.getId().equals(playerProfile.getId());
    }

    private void applyCapeTexture(CallbackInfoReturnable<class_8685> cir, class_2960 capeTexture) {
        class_8685 original = (class_8685)cir.getReturnValue();
        class_2960 elytraTexture = original.comp_1628() != null ? original.comp_1628() : DEFAULT_ELYTRA_TEXTURE;
        class_8685 modified = new class_8685(original.comp_1626(), original.comp_1911(), capeTexture, elytraTexture, original.comp_1629(), original.comp_1630());
        cir.setReturnValue((Object)modified);
    }
}

