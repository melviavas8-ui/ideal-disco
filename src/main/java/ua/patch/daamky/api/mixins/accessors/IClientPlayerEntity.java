/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package ua.patch.daamky.api.mixins.accessors;

import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_746.class})
public interface IClientPlayerEntity {
    @Invoker(value="canSprint")
    public boolean invokeCanSprint();

    @Invoker(value="isWalking")
    public boolean invokeIsWalking();
}

