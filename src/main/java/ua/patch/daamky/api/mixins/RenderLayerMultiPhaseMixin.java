/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1921$class_4687
 *  net.minecraft.class_1921$class_4688
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package ua.patch.daamky.api.mixins;

import aethereal.RenderLayerParametersAccessor;
import net.minecraft.class_1921;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={class_1921.class_4687.class})
public class RenderLayerMultiPhaseMixin
implements RenderLayerParametersAccessor {
    @Shadow
    @Final
    private class_1921.class_4688 field_21403;

    @Override
    public class_1921.class_4688 daamky$getParameters() {
        return this.field_21403;
    }
}

