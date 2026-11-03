/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_636
 *  net.minecraft.class_638
 *  net.minecraft.class_7204
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package ua.patch.daamky.api.mixins.accessors;

import net.minecraft.class_636;
import net.minecraft.class_638;
import net.minecraft.class_7204;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_636.class})
public interface IClientPlayerInteractionManager {
    @Invoker(value="syncSelectedSlot")
    public void syncSelectedSlot$drug();

    @Accessor(value="blockBreakingCooldown")
    public void setBlockBreakingCooldown(int var1);

    @Invoker(value="sendSequencedPacket")
    public void invokeSendSequencedPacket(class_638 var1, class_7204 var2);
}

