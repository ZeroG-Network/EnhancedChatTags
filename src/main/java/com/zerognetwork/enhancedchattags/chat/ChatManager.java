package com.zerognetwork.enhancedchattags.chat;

import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import com.zerognetwork.enhancedchattags.util.ChatUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "enhancedchattags", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChatManager {
    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        String message = event.getMessage().getString();

        String formattedMessage = ChatUtil.formatChatMessage(player, message);
        event.setMessage(Component.literal(formattedMessage));

        if (ConfigManager.USE_MC2DISCORD.get()) {
            IntegrationManager.getMC2DiscordHook().sendMessageToDiscord(player.getName().getString(), message);
        }
    }
}