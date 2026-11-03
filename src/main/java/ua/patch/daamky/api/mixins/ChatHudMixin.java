/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_2561
 *  net.minecraft.class_2583
 *  net.minecraft.class_303$class_7590
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_338
 *  net.minecraft.class_3532
 *  net.minecraft.class_5250
 *  net.minecraft.class_5251
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArgs
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.invoke.arg.Args
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.BetterChat;
import aethereal.NameProtect;
import aethereal.ThemeColorManager;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_303;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_338;
import net.minecraft.class_3532;
import net.minecraft.class_5250;
import net.minecraft.class_5251;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value={class_338.class})
public abstract class ChatHudMixin {
    @Shadow
    @Final
    private List<class_303.class_7590> field_2064;
    @Unique
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    @Unique
    private static final DateTimeFormatter TIME_FORMAT_SECONDS = DateTimeFormatter.ofPattern("HH:mm:ss");
    @Unique
    private boolean daamky$animationEnabled = false;
    @Unique
    private float daamky$animationSpeed = 6.0f;
    @Unique
    private int daamky$currentLineIndex = 0;
    @Unique
    private int daamky$currentTick = 0;
    @Unique
    private float daamky$partialTick = 0.0f;

    @ModifyArgs(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V", ordinal=0))
    private void modifyChatBackground(Args args) {
        BetterChat bc = DaamkyClient.method2004().method1783().method0976(BetterChat.class);
        if (bc != null && bc.method2195() && ((Boolean)bc.field0034.method0492()).booleanValue()) {
            args.set(4, (Object)0);
        }
    }

    @ModifyVariable(method={"addMessage(Lnet/minecraft/text/Text;)V"}, at=@At(value="HEAD"), argsOnly=true)
    private class_2561 addTimestamp(class_2561 message) {
        return this.daamky$prependTimestamp(message);
    }

    @Unique
    private class_2561 daamky$prependTimestamp(class_2561 message) {
        BetterChat bc;
        class_5250 masked = NameProtect.method1346(message);
        if (masked != null) {
            message = masked;
        }
        if ((bc = DaamkyClient.method2004().method1783().method0976(BetterChat.class)) != null && bc.method2195() && ((Boolean)bc.field1432.method0492()).booleanValue()) {
            String time = LocalTime.now().format((Boolean)bc.field0970.method0492() != false ? TIME_FORMAT_SECONDS : TIME_FORMAT);
            int themeColor = ThemeColorManager.method1604() & 0xFFFFFF;
            class_2583 timeStyle = class_2583.field_24360.method_27703(class_5251.method_27717((int)themeColor));
            class_5250 root = class_2561.method_43473();
            root.method_10852((class_2561)class_2561.method_43470((String)("[" + time + "] ")).method_10862(timeStyle));
            root.method_10852(message);
            return root;
        }
        return message;
    }

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void onRenderHead(class_332 context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        BetterChat bc = DaamkyClient.method2004().method1783().method0976(BetterChat.class);
        if (bc != null && bc.method2195() && ((Boolean)bc.field0184.method0492()).booleanValue()) {
            this.daamky$animationEnabled = true;
            this.daamky$animationSpeed = ((Float)bc.field0470.method0492()).floatValue();
        } else {
            this.daamky$animationEnabled = false;
        }
        this.daamky$currentLineIndex = 0;
        this.daamky$currentTick = currentTick;
        this.daamky$partialTick = class_310.method_1551().method_61966().method_60637(true);
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;III)I")})
    private void beforeDrawLine(class_332 context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        if (!this.daamky$animationEnabled) {
            return;
        }
        int idx = this.daamky$currentLineIndex;
        if (idx >= 0 && idx < this.field_2064.size()) {
            class_303.class_7590 line = this.field_2064.get(idx);
            float age = (float)(this.daamky$currentTick - line.comp_895()) + this.daamky$partialTick;
            float progress = class_3532.method_15363((float)(age * this.daamky$animationSpeed / 60.0f), (float)0.0f, (float)1.0f);
            float eased = 1.0f - (1.0f - progress) * (1.0f - progress);
            float offsetX = (1.0f - eased) * -50.0f;
            context.method_51448().method_22903();
            context.method_51448().method_46416(offsetX, 0.0f, 0.0f);
        }
        ++this.daamky$currentLineIndex;
    }

    @Inject(method={"render"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/OrderedText;III)I", shift=At.Shift.AFTER)})
    private void afterDrawLine(class_332 context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        if (!this.daamky$animationEnabled) {
            return;
        }
        context.method_51448().method_22909();
    }
}

