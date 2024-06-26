package com.zerognetwork.enhancedchattags.chat;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import com.zerognetwork.enhancedchattags.config.EnhancedChatTagsConfig;
import com.zerognetwork.enhancedchattags.util.ColorUtil;
import com.zerognetwork.enhancedchattags.util.LuckPermsCache;
import com.zerognetwork.enhancedchattags.util.PlaceholderManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EnhancedChatTags.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChatHandler {

    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        String message = event.getMessage().getString();
        
        String formattedMessage = EnhancedChatTagsConfig.CHAT_FORMAT.get();
        
        if (EnhancedChatTags.isLuckPermsLoaded) {
            LuckPermsCache.UserData userData = LuckPermsCache.getUserData(player.getUUID());
            if (userData != null) {
                formattedMessage = formattedMessage.replace("{prefix}", userData.prefix != null ? userData.prefix : "")
                                                   .replace("{suffix}", userData.suffix != null ? userData.suffix : "");
            }
        }
        
        formattedMessage = formattedMessage.replace("{message}", message);
        formattedMessage = PlaceholderManager.applyPlaceholders(formattedMessage, player);
        formattedMessage = ColorUtil.translateColorCodes(formattedMessage);

        event.setMessage(Component.literal(formattedMessage));
    }
}