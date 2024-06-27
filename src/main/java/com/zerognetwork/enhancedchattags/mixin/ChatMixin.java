package com.zerognetwork.enhancedchattags.mixin;

import com.zerognetwork.enhancedchattags.util.ChatUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class ChatMixin {
    @Inject(method = "handleChat", at = @At("HEAD"), cancellable = true)
    private void onHandleChat(String message, CallbackInfo ci) {
        ServerGamePacketListenerImpl thisObj = (ServerGamePacketListenerImpl) (Object) this;
        ServerPlayer player = thisObj.player;
        String formattedMessage = ChatUtil.formatChatMessage(player, message);
        Component newMessage = Component.literal(formattedMessage);
        thisObj.server.getPlayerList().broadcastMessage(newMessage, player);
        ci.cancel();
    }
}