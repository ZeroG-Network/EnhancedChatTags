package com.zerognetwork.enhancedchattags.chat;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import com.zerognetwork.enhancedchattags.util.ColorUtil;
import com.zerognetwork.enhancedchattags.util.PlaceholderUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EnhancedChatTags.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChatManager {

    public static void init() {
        EnhancedChatTags.LOGGER.info("Initializing ChatManager");
    }

    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        String message = event.getMessage().getString();
        
        String formattedMessage = formatMessage(player, message);
        
        event.setMessage(Component.literal(formattedMessage));
        
        if (ConfigManager.USE_MC2DISCORD.get()) {
            IntegrationManager.getMC2DiscordHook().sendMessageToDiscord(player.getName().getString(), formattedMessage);
        }
    }

    private static String formatMessage(ServerPlayer player, String message) {
        String format = ConfigManager.CHAT_FORMAT.get();
        return ColorUtil.translateColorCodes(PlaceholderUtil.replacePlaceholders(format, player, message));
    }
}