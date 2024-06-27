package com.zerognetwork.enhancedchattags.chat;

import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import com.zerognetwork.enhancedchattags.util.ColorUtil;
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

        String format = ConfigManager.CHAT_FORMAT.get();
        String prefix = IntegrationManager.getLuckPermsHook().isAvailable() ? 
            IntegrationManager.getLuckPermsHook().getPrefix(player) : "";
        String suffix = IntegrationManager.getLuckPermsHook().isAvailable() ? 
            IntegrationManager.getLuckPermsHook().getSuffix(player) : "";
        String name = player.getName().getString();

        String formattedMessage = format
                .replace("{prefix}", prefix != null ? prefix : "")
                .replace("{name}", name)
                .replace("{suffix}", suffix != null ? suffix : "")
                .replace("{message}", message);

        formattedMessage = ColorUtil.translateColorCodes(formattedMessage);

        event.setMessage(Component.literal(formattedMessage));

        if (ConfigManager.USE_MC2DISCORD.get()) {
            IntegrationManager.getMC2DiscordHook().sendMessageToDiscord(name, message);
        }
    }
}