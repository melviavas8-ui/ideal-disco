/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 *  net.minecraft.class_332
 *  net.minecraft.class_465
 */
package aethereal;

import aethereal.CancellableEvent;
import lombok.Generated;
import net.minecraft.class_332;
import net.minecraft.class_465;

public class Event001
extends CancellableEvent {
    private final class_465<?> field0746;
    private final class_332 field0162;
    private final int field1411;
    private final int field0958;

    @Generated
    public Event001(class_465<?> screen, class_332 context, int mouseX, int mouseY) {
        this.field0746 = screen;
        this.field0162 = context;
        this.field1411 = mouseX;
        this.field0958 = mouseY;
    }

    @Generated
    public class_465<?> method1810() {
        return this.field0746;
    }

    @Generated
    public class_332 method1628() {
        return this.field0162;
    }

    @Generated
    public int method1947() {
        return this.field1411;
    }

    @Generated
    public int method0414() {
        return this.field0958;
    }
}

