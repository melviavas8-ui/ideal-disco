/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aethereal.DaamkyClient
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.class_2535
 *  net.minecraft.class_2596
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.DaamkyClient;
import aethereal.PacketEvent;
import aethereal.PacketQueue;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.class_2535;
import net.minecraft.class_2596;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_2535.class})
public abstract class ClientConnectionMixin {
    @Inject(method={"channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/packet/Packet;)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void channelRead0(ChannelHandlerContext channelHandlerContext, class_2596<?> packet, CallbackInfo ci) {
        PacketEvent.Inbound eventReceive = new PacketEvent.Inbound(packet);
        PacketEvent.Transfer eventAll = new PacketEvent.Transfer(packet);
        DaamkyClient.method2004().method2072().post((Object)eventReceive);
        DaamkyClient.method2004().method2072().post((Object)eventAll);
        if (eventReceive.method2079() || eventAll.method2079()) {
            ci.cancel();
        }
    }

    @Inject(method={"send(Lnet/minecraft/network/packet/Packet;)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void send(class_2596<?> packet, CallbackInfo ci) {
        if (PacketQueue.method0559().contains(packet)) {
            PacketQueue.method0559().remove(packet);
            return;
        }
        PacketEvent.Outbound eventSend = new PacketEvent.Outbound(packet);
        PacketEvent.Transfer eventAll = new PacketEvent.Transfer(packet);
        DaamkyClient.method2004().method2072().post((Object)eventSend);
        DaamkyClient.method2004().method2072().post((Object)eventAll);
        if (eventSend.method2079() || eventAll.method2079()) {
            ci.cancel();
        }
    }
}

