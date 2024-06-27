package com.zerognetwork.enhancedchattags.mixin;

import com.zerognetwork.enhancedchattags.util.ChatUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.PlayerChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ChatMixin {
    @Shadow public ServerPlayer player;

    @Inject(method = "handleChat", at = @At("HEAD"), cancellable = true)
    private void onHandleChat(String message, CallbackInfo ci) {
        String formattedMessage = ChatUtil.formatChatMessage(player, message);
        Component newMessage = Component.literal(formattedMessage);
        
        PlayerChatMessage playerChatMessage = PlayerChatMessage.system(formattedMessage);
        player.getServer().getPlayerList().broadcastChatMessage(playerChatMessage, player, ChatType.bind(ChatType.CHAT, player));
        
        ci.cancel();
    }
}