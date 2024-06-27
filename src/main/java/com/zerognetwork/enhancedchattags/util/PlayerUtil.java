package com.zerognetwork.enhancedchattags.util;

import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import net.minecraft.server.level.ServerPlayer;

public class PlayerUtil {
    public static String formatPlayerName(ServerPlayer player) {
        String format = ConfigManager.TAG_FORMAT.get();
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