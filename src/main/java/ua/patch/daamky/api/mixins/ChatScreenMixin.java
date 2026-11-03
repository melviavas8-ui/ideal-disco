/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_2561
 *  net.minecraft.class_310
 *  net.minecraft.class_332
 *  net.minecraft.class_338
 *  net.minecraft.class_342
 *  net.minecraft.class_408
 *  net.minecraft.class_437
 *  org.lwjgl.glfw.GLFW
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.ChatCommandSuggestions;
import aethereal.FriendScreen;
import aethereal.HudWidgetManager;
import aethereal.HudWidgetState;
import aethereal.InventoryHudEditor;
import aethereal.MathHelper;
import aethereal.NameTags;
import aethereal.NewHUD;
import aethereal.ScreenLayoutHelper;
import aethereal.ServerAssist;
import aethereal.ServerSearchOverlay;
import java.util.Map;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_338;
import net.minecraft.class_342;
import net.minecraft.class_408;
import net.minecraft.class_437;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_408.class})
public abstract class ChatScreenMixin
extends class_437 {
    @Shadow
    protected class_342 field_2382;
    @Unique
    private final InventoryHudEditor daamky$hudPanel = new InventoryHudEditor();
    @Unique
    private final ServerSearchOverlay daamky$elementPopup = new ServerSearchOverlay();
    @Unique
    private final ChatCommandSuggestions daamky$cmdOverlay = new ChatCommandSuggestions();

    protected ChatScreenMixin(class_2561 title) {
        super(title);
    }

    @Inject(method={"init"}, at={@At(value="RETURN")})
    private void daamky$onInit(CallbackInfo ci) {
        NewHUD.method1735();
    }

    @Inject(method={"onChatFieldUpdate"}, at={@At(value="HEAD")}, cancellable=true)
    private void onChatFieldUpdate(String chatText, CallbackInfo ci) {
        String prefix = DaamkyClient.method2004().method2257().method2067();
        if (chatText != null && chatText.startsWith(prefix) && !this.daamky$cmdOverlay.method0579()) {
            this.daamky$cmdOverlay.method1013(chatText);
            this.field_2382.method_1862(false);
            this.field_2382.method_25365(false);
            ci.cancel();
            return;
        }
        if (this.daamky$cmdOverlay.method0579()) {
            ci.cancel();
        }
    }

    @Inject(method={"keyPressed"}, at={@At(value="HEAD")}, cancellable=true)
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        boolean handled;
        if (keyCode == 256 && this.daamky$elementPopup.method0026()) {
            this.daamky$elementPopup.method0578();
            cir.setReturnValue((Object)true);
            return;
        }
        if (keyCode == 256 && this.daamky$hudPanel.method0026()) {
            this.daamky$hudPanel.method0578();
            cir.setReturnValue((Object)true);
            return;
        }
        if (this.daamky$cmdOverlay.method0579() && (handled = this.daamky$cmdOverlay.method0746(keyCode, scanCode, modifiers))) {
            if (!this.daamky$cmdOverlay.method0579() && this.field_2382 != null) {
                this.field_2382.method_1862(true);
                this.field_2382.method_25365(true);
                this.field_2382.method_1852("");
            }
            cir.setReturnValue((Object)true);
        }
    }

    public boolean method_25400(char chr, int modifiers) {
        if (this.daamky$cmdOverlay.method0579() && this.daamky$cmdOverlay.method0607(chr, modifiers)) {
            return true;
        }
        return super.method_25400(chr, modifiers);
    }

    @Inject(method={"mouseClicked"}, at={@At(value="HEAD")}, cancellable=true)
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        float factor = ScreenLayoutHelper.method0002();
        float efMouseX = (float)(mouseX / (double)factor);
        float efMouseY = (float)(mouseY / (double)factor);
        if (this.daamky$elementPopup.method0026() && this.daamky$elementPopup.method0627(efMouseX, efMouseY, button)) {
            cir.setReturnValue((Object)true);
            return;
        }
        if (this.daamky$hudPanel.method0026() && this.daamky$hudPanel.method0627(efMouseX, efMouseY, button)) {
            cir.setReturnValue((Object)true);
            return;
        }
        if (button == 1) {
            Object bounds;
            class_310 mcClient = class_310.method_1551();
            NameTags nameTagsModule = DaamkyClient.method2004().method1783().method0976(NameTags.class);
            if (nameTagsModule != null && nameTagsModule.method2195()) {
                for (Map.Entry<String, float[]> entry : nameTagsModule.method1730().entrySet()) {
                    bounds = entry.getValue();
                    if (!MathHelper.method0689(bounds[0], (float)bounds[1], (float)bounds[2], (float)bounds[3], efMouseX, efMouseY)) continue;
                    this.daamky$elementPopup.method0578();
                    this.daamky$hudPanel.method0578();
                    mcClient.method_1507((class_437)new FriendScreen(entry.getKey(), efMouseX, efMouseY));
                    cir.setReturnValue((Object)true);
                    return;
                }
            }
            boolean altHeld = GLFW.glfwGetKey((long)mcClient.method_22683().method_4490(), (int)342) == 1;
            NewHUD hudModule = DaamkyClient.method2004().method1783().method0976(NewHUD.class);
            bounds = HudWidgetManager.method1605().method0560().values().iterator();
            while (bounds.hasNext()) {
                HudWidgetState el = (HudWidgetState)bounds.next();
                if (hudModule != null && !hudModule.method1986(el.method1619()) || !el.method0676(efMouseX, efMouseY) || !this.daamky$elementPopup.method1014(el.method1619())) continue;
                if (altHeld && el.method1619().equals("NewArmorHUD")) {
                    cir.setReturnValue((Object)true);
                    return;
                }
                this.daamky$hudPanel.method0578();
                this.daamky$elementPopup.method0703(efMouseX, efMouseY, el.method1619());
                cir.setReturnValue((Object)true);
                return;
            }
            boolean hoveringElement = false;
            for (HudWidgetState el : HudWidgetManager.method1605().method0560().values()) {
                if (hudModule != null && !hudModule.method1986(el.method1619()) || !el.method0676(efMouseX, efMouseY)) continue;
                hoveringElement = true;
                break;
            }
            if (!hoveringElement && this.field_2382 != null && !this.field_2382.method_25405(mouseX, mouseY)) {
                this.daamky$elementPopup.method0578();
                if (this.daamky$hudPanel.method0026()) {
                    this.daamky$hudPanel.method0578();
                } else {
                    this.daamky$hudPanel.method0675(efMouseX, efMouseY);
                }
                cir.setReturnValue((Object)true);
            }
        }
    }

    public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.daamky$elementPopup.method0026()) {
            float factor = ScreenLayoutHelper.method0002();
            this.daamky$elementPopup.method0615(mouseX / (double)factor, mouseY / (double)factor);
        }
        return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
    }

    public boolean method_25406(double mouseX, double mouseY, int button) {
        if (this.daamky$elementPopup.method0026()) {
            float factor = ScreenLayoutHelper.method0002();
            this.daamky$elementPopup.method0111(mouseX / (double)factor, mouseY / (double)factor, button);
        }
        return super.method_25406(mouseX, mouseY, button);
    }

    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true)
    private void onRenderHead(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.daamky$cmdOverlay.method0579()) {
            class_310 mc = class_310.method_1551();
            class_338 chatHud = mc.field_1705.method_1743();
            chatHud.method_1805(context, mc.field_1705.method_1738(), mouseX, mouseY, true);
            this.daamky$cmdOverlay.method1402(context, this.field_22789, this.field_22790);
            this.daamky$renderHudPanels(context, mouseX, mouseY, delta);
            ci.cancel();
            return;
        }
    }

    @Inject(method={"render"}, at={@At(value="TAIL")})
    private void onRenderTail(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.daamky$renderHudPanels(context, mouseX, mouseY, delta);
        this.daamky$renderServerAssistHud(context);
        this.daamky$renderHudHint(context);
    }

    @Unique
    private void daamky$renderHudHint(class_332 context) {
        boolean panelsHidden = !this.daamky$hudPanel.method0026() && !this.daamky$elementPopup.method0026();
        float factor = ScreenLayoutHelper.method0002();
        context.method_51448().method_22903();
        context.method_51448().method_22905(factor, factor, 1.0f);
        NewHUD.method1475(context.method_51448(), (float)this.field_22789 / factor, (float)this.field_22790 / factor, panelsHidden);
        context.method_51448().method_22909();
    }

    @Unique
    private void daamky$renderServerAssistHud(class_332 context) {
        ServerAssist sa = DaamkyClient.method2004().method1783().method0976(ServerAssist.class);
        if (sa == null || !sa.method2195()) {
            return;
        }
        float factor = ScreenLayoutHelper.method0002();
        context.method_51448().method_22903();
        if (factor != 1.0f) {
            context.method_51448().method_22905(factor, factor, 1.0f);
        }
        sa.method1400(context);
        context.method_51448().method_22909();
    }

    @Unique
    private void daamky$renderHudPanels(class_332 context, int mouseX, int mouseY, float delta) {
        float factor = ScreenLayoutHelper.method0002();
        int efMx = (int)((float)mouseX / factor);
        int efMy = (int)((float)mouseY / factor);
        context.method_51448().method_22903();
        context.method_51448().method_22905(factor, factor, 1.0f);
        this.daamky$hudPanel.method1414(context, efMx, efMy, delta);
        this.daamky$elementPopup.method1414(context, efMx, efMy, delta);
        context.method_51448().method_22909();
    }

    @Inject(method={"removed"}, at={@At(value="HEAD")})
    private void onRemoved(CallbackInfo ci) {
        if (this.daamky$elementPopup.method0026()) {
            this.daamky$elementPopup.method0578();
        }
        if (this.daamky$hudPanel.method0026()) {
            this.daamky$hudPanel.method0578();
        }
        if (this.daamky$cmdOverlay.method0579()) {
            this.daamky$cmdOverlay.method0025();
        }
        DaamkyClient.method2004().method2216().method1634();
    }
}

