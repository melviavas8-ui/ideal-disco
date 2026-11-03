/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_124
 *  net.minecraft.class_2561
 *  net.minecraft.class_266
 *  net.minecraft.class_268
 *  net.minecraft.class_269
 *  net.minecraft.class_270
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_355
 *  net.minecraft.class_5250
 *  net.minecraft.class_5348
 *  net.minecraft.class_640
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.Interface;
import aethereal.NameProtect;
import aethereal.TabExpert;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_266;
import net.minecraft.class_268;
import net.minecraft.class_269;
import net.minecraft.class_270;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_355;
import net.minecraft.class_5250;
import net.minecraft.class_5348;
import net.minecraft.class_640;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_355.class})
public abstract class PlayerListHudMixin {
    @Shadow
    @Final
    private class_310 field_2155;
    @Shadow
    @Final
    private static Comparator<class_640> field_2156;
    @Shadow
    private class_2561 field_2153;
    @Shadow
    private class_2561 field_2154;

    @Shadow
    protected abstract class_2561 method_27538(class_640 var1, class_5250 var2);

    @Shadow
    protected abstract List<class_640> method_48213();

    @Shadow
    public abstract class_2561 method_1918(class_640 var1);

    @Inject(method={"render"}, at={@At(value="HEAD")})
    private void daamky$drawTabBg(class_332 context, int scaledWindowWidth, class_269 scoreboard, class_266 objective, CallbackInfo ci) {
        if (!this.daamky$tabReplaceEnabled()) {
            return;
        }
        if (this.field_2155.field_1724 == null || this.field_2155.field_1724.field_3944 == null) {
            return;
        }
        List<class_640> entries = this.method_48213();
        int n = entries.size();
        if (n == 0) {
            return;
        }
        class_327 tr = this.field_2155.field_1772;
        int maxNameW = 0;
        for (class_640 e : entries) {
            maxNameW = Math.max(maxNameW, tr.method_27525((class_5348)this.method_1918(e)));
        }
        int rows = n;
        int columns = 1;
        while (rows > 20) {
            rows = (int)Math.ceil((double)n / (double)(++columns));
        }
        int columnW = maxNameW + 13;
        int totalW = columns * columnW + Math.max(columns - 1, 0) * 5;
        int headerLineCount = 0;
        if (this.field_2153 != null) {
            List hl = tr.method_1728((class_5348)this.field_2153, scaledWindowWidth - 50);
            headerLineCount = hl.size();
        }
        int footerLineCount = 0;
        if (this.field_2154 != null) {
            List fl = tr.method_1728((class_5348)this.field_2154, scaledWindowWidth - 50);
            footerLineCount = fl.size();
        }
        Objects.requireNonNull(tr);
        int fh = 9;
        int headerH = headerLineCount * fh + (headerLineCount > 0 ? 1 : 0);
        int bodyH = rows * (fh + 1);
        int footerH = footerLineCount * fh + (footerLineCount > 0 ? fh + 1 : 0);
        int bodyTop = 10;
        int panelTop = bodyTop - headerH - 1;
        int panelLeft = scaledWindowWidth / 2 - totalW / 2 - 1;
        int panelW = totalW + 2;
        int panelH = headerH + bodyH + footerH + 2;
        Interface iface = DaamkyClient.method2004().method1783().method0976(Interface.class);
        iface.method1404(context, (float)panelLeft - 4.0f, (float)panelTop - 4.0f, (float)panelW + 8.0f, (float)panelH + 8.0f);
    }

    @Redirect(method={"render"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawContext;fill(IIIII)V"))
    private void daamky$skipVanillaFill(class_332 context, int x1, int y1, int x2, int y2, int color) {
        if (this.daamky$tabReplaceEnabled()) {
            return;
        }
        context.method_25294(x1, y1, x2, y2, color);
    }

    private boolean daamky$tabReplaceEnabled() {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return false;
        }
        Interface iface = arbuz.method1783().method0976(Interface.class);
        return iface != null && iface.method2030();
    }

    @Inject(method={"collectPlayerEntries"}, at={@At(value="HEAD")}, cancellable=true)
    private void daamky$collectPlayerEntries(CallbackInfoReturnable<List<class_640>> info) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        TabExpert tabExpert = arbuz.method1783().method0976(TabExpert.class);
        if (tabExpert == null || !tabExpert.method2195()) {
            return;
        }
        info.setReturnValue(this.field_2155.field_1724.field_3944.method_45732().stream().sorted(field_2156).limit((long)((Float)tabExpert.field0060.method0492()).floatValue()).toList());
    }

    @Inject(method={"getPlayerName"}, at={@At(value="HEAD")}, cancellable=true)
    private void daamky$getPlayerName(class_640 entry, CallbackInfoReturnable<class_2561> info) {
        DaamkyClient arbuz = DaamkyClient.method2004();
        if (arbuz == null || arbuz.method1783() == null) {
            return;
        }
        String profileName = entry.method_2966().getName();
        String masked = NameProtect.method2131(profileName);
        if (!masked.equals(profileName)) {
            class_2561 displayName = entry.method_2971();
            class_5250 base = displayName != null ? NameProtect.method1346(displayName) : class_268.method_1142((class_270)entry.method_2955(), (class_2561)class_2561.method_43470((String)masked));
            info.setReturnValue((Object)this.method_27538(entry, base));
            return;
        }
        TabExpert tabExpert = arbuz.method1783().method0976(TabExpert.class);
        if (tabExpert == null || !tabExpert.method2195() || !((Boolean)tabExpert.field1432.method0492()).booleanValue()) {
            return;
        }
        if (arbuz.method1608() == null) {
            return;
        }
        String name = entry.method_2966().getName();
        if (!arbuz.method1608().method2135(name)) {
            return;
        }
        class_2561 display = entry.method_2971();
        String fullText = display != null ? display.getString() : name;
        int idx = fullText.indexOf(name);
        if (idx < 0) {
            info.setReturnValue((Object)this.method_27538(entry, class_268.method_1142((class_270)entry.method_2955(), (class_2561)class_2561.method_43470((String)name).method_27692(class_124.field_1075))));
            return;
        }
        class_5250 rebuilt = class_2561.method_43473();
        if (idx > 0) {
            rebuilt.method_10852((class_2561)class_2561.method_43470((String)fullText.substring(0, idx)).method_27692(class_124.field_1068));
        }
        rebuilt.method_10852((class_2561)class_2561.method_43470((String)name).method_27692(class_124.field_1075));
        int after = idx + name.length();
        if (after < fullText.length()) {
            rebuilt.method_10852((class_2561)class_2561.method_43470((String)fullText.substring(after)).method_27692(class_124.field_1068));
        }
        info.setReturnValue((Object)this.method_27538(entry, rebuilt));
    }
}

