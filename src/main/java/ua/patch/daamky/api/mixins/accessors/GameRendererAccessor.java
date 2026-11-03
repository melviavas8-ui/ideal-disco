/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_757
 *  net.minecraft.class_759
 *  net.minecraft.class_9920
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package ua.patch.daamky.api.mixins.accessors;

import net.minecraft.class_757;
import net.minecraft.class_759;
import net.minecraft.class_9920;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_757.class})
public interface GameRendererAccessor {
    @Accessor
    public class_9920 getPool();

    @Accessor(value="firstPersonRenderer")
    public class_759 getFirstPersonRenderer();
}

