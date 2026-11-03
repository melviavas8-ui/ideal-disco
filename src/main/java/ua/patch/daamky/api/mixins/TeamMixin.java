/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.injector.ModifyReturnValue
 *  net.minecraft.class_2561
 *  net.minecraft.class_268
 *  net.minecraft.class_5250
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 */
package ua.patch.daamky.api.mixins;

import aethereal.NameProtect;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.class_2561;
import net.minecraft.class_268;
import net.minecraft.class_5250;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={class_268.class})
public class TeamMixin {
    @ModifyReturnValue(method={"decorateName(Lnet/minecraft/scoreboard/AbstractTeam;Lnet/minecraft/text/Text;)Lnet/minecraft/text/MutableText;"}, at={@At(value="RETURN")})
    private static class_5250 daamky$decorateName(class_5250 original) {
        if (NameProtect.field0090 == null || !NameProtect.field0090.method2195() || original == null) {
            return original;
        }
        return NameProtect.method1346((class_2561)original);
    }
}

