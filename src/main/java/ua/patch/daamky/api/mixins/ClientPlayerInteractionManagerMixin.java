/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  net.minecraft.class_1268
 *  net.minecraft.class_1269
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_239
 *  net.minecraft.class_3417
 *  net.minecraft.class_3419
 *  net.minecraft.class_3965
 *  net.minecraft.class_3966
 *  net.minecraft.class_636
 *  net.minecraft.class_745
 *  net.minecraft.class_746
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.BlockDestroyEvent;
import aethereal.BlockInteractEvent;
import aethereal.Chams;
import aethereal.FakePlayer;
import aethereal.FakePlayerHelper;
import aethereal.MinecraftAccess;
import aethereal.NoFriendDamage;
import aethereal.NoInteract;
import aethereal.SpeedMine;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_239;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_636;
import net.minecraft.class_745;
import net.minecraft.class_746;
import ua.patch.daamky.api.mixins.accessors.IClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_636.class})
public class ClientPlayerInteractionManagerMixin {
    @Shadow
    private int field_3716;
    @Shadow
    private float field_3715;

    @Inject(method={"tick"}, at={@At(value="HEAD")})
    private void daamky$onTick(CallbackInfo ci) {
        SpeedMine speedMine = SpeedMine.method1719();
        if (speedMine != null && speedMine.method2195()) {
            this.field_3716 = 0;
        }
    }

    @Inject(method={"updateBlockBreakingProgress"}, at={@At(value="HEAD")})
    private void daamky$onUpdateBlockBreakingProgress(class_2338 pos, class_2350 direction, CallbackInfoReturnable<Boolean> cir) {
        SpeedMine speedMine = SpeedMine.method1719();
        if (speedMine != null && speedMine.method1692()) {
            this.field_3715 = 1.0f;
        }
    }

    @Inject(method={"breakBlock"}, at={@At(value="HEAD")})
    private void onBreakBlock(class_2338 pos, CallbackInfoReturnable<Boolean> cir) {
        DaamkyClient.method2004().method2072().post((Object)new BlockDestroyEvent(pos));
    }

    @Inject(method={"interactBlock"}, at={@At(value="HEAD")}, cancellable=true)
    private void onInteractBlock(class_746 player, class_1268 hand, class_3965 hit, CallbackInfoReturnable<class_1269> cir) {
        if (MinecraftAccess.field0796.field_1687 == null) {
            return;
        }
        NoInteract noInteract = DaamkyClient.method2004().method1783().method0976(NoInteract.class);
        if (noInteract == null) {
            return;
        }
        if (noInteract.method1253(MinecraftAccess.field0796.field_1687.method_8320(hit.method_17777()).method_26204())) {
            cir.setReturnValue((Object)class_1269.field_5811);
        }
    }

    @Inject(method={"interactEntity"}, at={@At(value="HEAD")}, cancellable=true)
    private void daamky$onInteractEntity(class_1657 player, class_1297 entity, class_1268 hand, CallbackInfoReturnable<class_1269> cir) {
        NoInteract noInteract = DaamkyClient.method2004().method1783().method0976(NoInteract.class);
        if (noInteract != null && noInteract.method1129(entity)) {
            cir.setReturnValue((Object)class_1269.field_5811);
        }
    }

    @Inject(method={"interactEntityAtLocation"}, at={@At(value="HEAD")}, cancellable=true)
    private void daamky$onInteractEntityAtLocation(class_1657 player, class_1297 entity, class_3966 hit, class_1268 hand, CallbackInfoReturnable<class_1269> cir) {
        NoInteract noInteract = DaamkyClient.method2004().method1783().method0976(NoInteract.class);
        if (noInteract != null && noInteract.method1129(entity)) {
            cir.setReturnValue((Object)class_1269.field_5811);
        }
    }

    @Inject(method={"attackBlock"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAttackBlock(class_2338 pos, class_2350 direction, CallbackInfoReturnable<Boolean> cir) {
        BlockInteractEvent event = new BlockInteractEvent(pos, direction);
        DaamkyClient.method2004().method2072().post((Object)event);
        if (event.method2079()) {
            cir.setReturnValue((Object)true);
        }
    }

    @Inject(method={"attackEntity"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAttackEntityFriend(class_1657 player, class_1297 target, CallbackInfo ci) {
        NoFriendDamage mod = DaamkyClient.method2004().method1783().method0976(NoFriendDamage.class);
        if (mod != null && mod.method2195() && DaamkyClient.method2004().method1608().method1129(target)) {
            ci.cancel();
        }
    }

    @Inject(method={"attackEntity"}, at={@At(value="HEAD")})
    private void onAttackEntityHit(class_1657 player, class_1297 target, CallbackInfo ci) {
        class_3966 ehr;
        if (target == null) {
            return;
        }
        class_239 hr = MinecraftAccess.field0796.field_1765;
        if (hr instanceof class_3966 && (ehr = (class_3966)hr).method_17782() == target) {
            Chams.method1096(target.method_5667(), ehr.method_17784());
        } else {
            Chams.method1096(target.method_5667(), target.method_5829().method_1005());
        }
    }

    @Inject(method={"attackEntity"}, at={@At(value="HEAD")}, cancellable=true)
    private void onAttackEntity(class_1657 player, class_1297 target, CallbackInfo ci) {
        if (!FakePlayerHelper.method1129(target)) {
            return;
        }
        ci.cancel();
        if (MinecraftAccess.field0796.field_1724 == null || MinecraftAccess.field0796.field_1687 == null) {
            return;
        }
        FakePlayer fp = DaamkyClient.method2004().method1783().method0976(FakePlayer.class);
        if (fp == null || !fp.method2195()) {
            return;
        }
        ((IClientPlayerInteractionManager)MinecraftAccess.field0796.field_1761).syncSelectedSlot$drug();
        class_745 fake = (class_745)target;
        float dmg = FakePlayerHelper.method1178((class_1657)MinecraftAccess.field0796.field_1724);
        boolean crit = FakePlayerHelper.method0250((class_1657)MinecraftAccess.field0796.field_1724);
        FakePlayerHelper.method1539(fake, dmg, (class_1657)MinecraftAccess.field0796.field_1724, fp.method2195());
        MinecraftAccess.field0796.field_1724.method_6104(class_1268.field_5808);
        MinecraftAccess.field0796.field_1724.method_7350();
        if (crit) {
            MinecraftAccess.field0796.field_1687.method_43128((class_1657)MinecraftAccess.field0796.field_1724, target.method_23317(), target.method_23318(), target.method_23321(), class_3417.field_15016, class_3419.field_15248, 1.0f, 1.0f);
            MinecraftAccess.field0796.field_1724.method_7277(target);
        } else {
            MinecraftAccess.field0796.field_1687.method_43128((class_1657)MinecraftAccess.field0796.field_1724, target.method_23317(), target.method_23318(), target.method_23321(), class_3417.field_14840, class_3419.field_15248, 1.0f, 1.0f);
        }
        MinecraftAccess.field0796.field_1687.method_43128((class_1657)MinecraftAccess.field0796.field_1724, target.method_23317(), target.method_23318(), target.method_23321(), class_3417.field_15115, class_3419.field_15248, 1.0f, 1.0f);
    }
}

