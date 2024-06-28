package com.zerognetwork.enhancedchattags.mixin;

import com.zerognetwork.enhancedchattags.util.ChatUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.network.chat.ChatType;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ChatMixin {
    @Shadow public ServerPlayer player;

    @Inject(method = "handleChat", at = @At("HEAD"), cancellable = true)
    private void onHandleChat(CallbackInfo ci) {
        ServerChatEvent event = new ServerChatEvent(player, "", Component.literal(""));
        MinecraftForge.EVENT_BUS.post(event);
        
        if (event.isCanceled()) {
            ci.cancel();
            return;
        }

        String message = event.getMessage().getString();
        String formattedMessage = ChatUtil.formatChatMessage(player, message);
        Component newMessage = Component.literal(formattedMessage);

        player.getServer().getPlayerList().broadcastMessage(newMessage, ChatType.CHAT, player.getUUID());

        ci.cancel();
    }
}