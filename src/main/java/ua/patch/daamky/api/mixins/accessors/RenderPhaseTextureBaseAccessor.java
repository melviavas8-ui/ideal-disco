/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_4668$class_5939
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package ua.patch.daamky.api.mixins.accessors;

import java.util.Optional;
import net.minecraft.class_2960;
import net.minecraft.class_4668;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_4668.class_5939.class})
public interface RenderPhaseTextureBaseAccessor {
    @Invoker(value="getId")
    public Optional<class_2960> invokeGetId();
}

