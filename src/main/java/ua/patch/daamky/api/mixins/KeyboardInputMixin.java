/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  com.llamalad7.mixinextras.injector.ModifyExpressionValue
 *  net.minecraft.class_10185
 *  net.minecraft.class_743
 *  net.minecraft.class_744
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.InventoryComponent;
import aethereal.KeyboardEvent;
import aethereal.KeyboardInputEvent;
import aethereal.MoveInputEvent;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.class_10185;
import net.minecraft.class_743;
import net.minecraft.class_744;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={class_743.class})
public abstract class KeyboardInputMixin
extends class_744 {
    @ModifyExpressionValue(method={"tick"}, at={@At(value="NEW", target="(ZZZZZZZ)Lnet/minecraft/util/PlayerInput;")})
    private class_10185 tickHook(class_10185 original) {
        float rawForward = this.daamky$getMovementMultiplier(original.comp_3159(), original.comp_3160());
        float rawStrafe = this.daamky$getMovementMultiplier(original.comp_3161(), original.comp_3162());
        KeyboardEvent keyboardEvent = new KeyboardEvent(rawForward, rawStrafe, original.comp_3163(), original.comp_3164(), original.comp_3165());
        DaamkyClient.method2004().method2072().post((Object)keyboardEvent);
        class_10185 afterKeyboard = keyboardEvent.method2079() ? new class_10185(false, false, false, false, original.comp_3163(), original.comp_3164(), original.comp_3165()) : new class_10185(keyboardEvent.method1762() > 0.0f, keyboardEvent.method1762() < 0.0f, keyboardEvent.method1603() > 0.0f, keyboardEvent.method1603() < 0.0f, keyboardEvent.method1974(), keyboardEvent.method0431(), keyboardEvent.method0376());
        MoveInputEvent moveEvent = new MoveInputEvent(afterKeyboard, keyboardEvent.method1762(), keyboardEvent.method1603());
        DaamkyClient.method2004().method2072().post((Object)moveEvent);
        class_10185 afterMove = afterKeyboard;
        if (moveEvent.method2079()) {
            afterMove = new class_10185(false, false, false, false, afterKeyboard.comp_3163(), afterKeyboard.comp_3164(), afterKeyboard.comp_3165());
        } else if (moveEvent.method1603() != keyboardEvent.method1762() || moveEvent.method1946() != keyboardEvent.method1603()) {
            afterMove = new class_10185(moveEvent.method1603() > 0.0f, moveEvent.method1603() < 0.0f, moveEvent.method1946() > 0.0f, moveEvent.method1946() < 0.0f, afterKeyboard.comp_3163(), afterKeyboard.comp_3164(), afterKeyboard.comp_3165());
        }
        KeyboardInputEvent event = new KeyboardInputEvent(afterMove);
        DaamkyClient.method2004().method2072().post((Object)event);
        InventoryComponent.method0888(event);
        return event.method0427();
    }

    @Unique
    private float daamky$getMovementMultiplier(boolean positive, boolean negative) {
        if (positive == negative) {
            return 0.0f;
        }
        return positive ? 1.0f : -1.0f;
    }
}

