/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandler
 *  io.netty.handler.proxy.ProxyHandler
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package ua.patch.daamky.api.mixins;

import aethereal.ProxyChannelFactory;
import aethereal.ProxyEntry;
import aethereal.ProxyStorage;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.handler.proxy.ProxyHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets={"net/minecraft/network/ClientConnection$1"})
public abstract class ClientConnectionInitMixin {
    @Inject(method={"initChannel(Lio/netty/channel/Channel;)V"}, at={@At(value="HEAD")})
    private void onInitChannel(Channel channel, CallbackInfo ci) {
        ProxyEntry entry = ProxyStorage.method1786().method0547();
        if (entry == null) {
            return;
        }
        ProxyHandler handler = ProxyChannelFactory.method0906(entry);
        if (handler != null) {
            channel.pipeline().addFirst("daamky_proxy", (ChannelHandler)handler);
        }
    }
}

