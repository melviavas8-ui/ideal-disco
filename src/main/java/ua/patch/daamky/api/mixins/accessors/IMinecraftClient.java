/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package ua.patch.daamky.api.mixins.accessors;

import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_310.class})
public interface IMinecraftClient {
    @Accessor(value="itemUseCooldown")
    public void setItemUseCooldown(int var1);

    @Accessor(value="attackCooldown")
    public void setAttackCooldown(int var1);
}

