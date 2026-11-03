/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1921$class_4688
 *  net.minecraft.class_1921$class_4750
 *  net.minecraft.class_4668$class_5939
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package ua.patch.daamky.api.mixins.accessors;

import net.minecraft.class_1921;
import net.minecraft.class_4668;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_1921.class_4688.class})
public interface RenderLayerMultiPhaseParametersAccessor {
    @Accessor(value="outlineMode")
    public class_1921.class_4750 getOutlineMode();

    @Accessor(value="texture")
    public class_4668.class_5939 getTexture();
}

