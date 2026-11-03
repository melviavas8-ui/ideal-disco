/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_276
 *  net.minecraft.class_4604
 *  net.minecraft.class_761
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package ua.patch.daamky.api.mixins.accessors;

import net.minecraft.class_276;
import net.minecraft.class_4604;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_761.class})
public interface IWorldRenderer {
    @Accessor(value="frustum")
    public class_4604 getFrustum();

    @Accessor(value="entityOutlineFramebuffer")
    public class_276 getEntityOutlineFramebuffer();

    @Accessor(value="entityOutlineFramebuffer")
    public void setEntityOutlineFramebuffer(class_276 var1);
}

