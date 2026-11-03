/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_10141
 *  net.minecraft.class_10151
 *  net.minecraft.class_10151$class_10152
 *  net.minecraft.class_10156
 *  net.minecraft.class_10157
 *  net.minecraft.class_5944
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package ua.patch.daamky.api.mixins.accessors;

import net.minecraft.class_10141;
import net.minecraft.class_10151;
import net.minecraft.class_10156;
import net.minecraft.class_10157;
import net.minecraft.class_5944;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_10151.class})
public interface ShaderLoaderInvoker {
    @Invoker(value="createProgram")
    public static class_5944 invokeCreateProgram(class_10156 key, class_10157 def, class_10141 vertex, class_10141 fragment) throws class_10151.class_10152 {
        throw new AssertionError();
    }
}

