package com.zerognetwork.enhancedchattags.util;

import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import net.minecraft.server.level.ServerPlayer;

public class PlaceholderUtil {
    public static String replacePlaceholders(String text, ServerPlayer player) {
        String prefix = IntegrationManager.getLuckPermsHook().getPrefix(player);
        String suffix = IntegrationManager.getLuckPermsHook().getSuffix(player);

        return text.replace("{prefix}", prefix != null ? prefix : "")
                   .replace("{suffix}", suffix != null ? suffix : "")
                   .replace("{name}", player.getName().getString());
    }
}