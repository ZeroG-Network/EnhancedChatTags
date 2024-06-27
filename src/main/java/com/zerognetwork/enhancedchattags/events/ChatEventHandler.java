package com.zerognetwork.enhancedchattags.events;

import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import com.zerognetwork.enhancedchattags.util.PlayerUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "enhancedchattags", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChatEventHandler {

    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        Component originalMessage = event.getMessage();

        String formattedName = PlayerUtil.formatPlayerName(player);
        Component newMessage = Component.literal(formattedName + ": ").append(originalMessage);

        event.setMessage(newMessage);

        if (ConfigManager.USE_MC2DISCORD.get()) {
            String fullMessage = formattedName + ": " + originalMessage.getString();
            IntegrationManager.getMC2DiscordHook().sendMessageToDiscord(player.getName().getString(), fullMessage);
        }
    }
}