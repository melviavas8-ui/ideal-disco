/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1713
 *  net.minecraft.class_1735
 *  net.minecraft.class_465
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 */
package ua.patch.daamky.api.mixins.accessors;

import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_465;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={class_465.class})
public interface IHandledScreen {
    @Accessor(value="focusedSlot")
    public class_1735 getFocusedSlot();

    @Accessor(value="x")
    public int arbuz$getX();

    @Accessor(value="y")
    public int arbuz$getY();

    @Accessor(value="backgroundWidth")
    public int arbuz$getBackgroundWidth();

    @Accessor(value="backgroundHeight")
    public int arbuz$getBackgroundHeight();

    @Invoker(value="onMouseClick")
    public void arbuz$onMouseClick(class_1735 var1, int var2, int var3, class_1713 var4);
}

