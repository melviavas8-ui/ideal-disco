/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1921$class_4688
 *  net.minecraft.class_4668$class_4678
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package ua.patch.daamky.api.mixins;

import aethereal.RenderTargetAccessor;
import net.minecraft.class_1921;
import net.minecraft.class_4668;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={class_1921.class_4688.class})
public abstract class RenderLayerMultiPhaseParametersMixin
implements RenderTargetAccessor {
    @Shadow
    @Final
    private class_4668.class_4678 field_21417;

    @Override
    public class_4668.class_4678 arbuz$getTarget() {
        return this.field_21417;
    }
}

