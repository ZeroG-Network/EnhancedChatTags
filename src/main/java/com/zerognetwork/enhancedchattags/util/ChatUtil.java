package com.zerognetwork.enhancedchattags.util;

import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import net.minecraft.server.level.ServerPlayer;

public class ChatUtil {
    public static String formatChatMessage(ServerPlayer player, String message) {
        String format = ConfigManager.CHAT_FORMAT.get();
        String prefix = IntegrationManager.getLuckPermsHook().getPrefix(player);
        String suffix = IntegrationManager.getLuckPermsHook().getSuffix(player);
        String name = player.getName().getString();

        String formattedMessage = format
                .replace("{prefix}", prefix != null ? prefix : "")
                .replace("{name}", name)
                .replace("{suffix}", suffix != null ? suffix : "")
                .replace("{message}", message);

        return ColorUtil.translateColorCodes(formattedMessage);
    }

    public static String formatPlayerName(ServerPlayer player) {
        String format = ConfigManager.NAME_FORMAT.get();
        String prefix = IntegrationManager.getLuckPermsHook().getPrefix(player);
        String suffix = IntegrationManager.getLuckPermsHook().getSuffix(player);
        String name = player.getName().getString();

        String formattedName = format
                .replace("{prefix}", prefix != null ? prefix : "")
                .replace("{name}", name)
                .replace("{suffix}", suffix != null ? suffix : "");

        return ColorUtil.translateColorCodes(formattedName);
    }
}