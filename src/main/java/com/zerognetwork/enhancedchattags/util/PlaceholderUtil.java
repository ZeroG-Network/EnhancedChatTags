package com.zerognetwork.enhancedchattags.util;

import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import net.minecraft.world.entity.player.Player;

public class PlaceholderUtil {
    public static String replacePlaceholders(String text, Player player, String message) {
        String prefix = IntegrationManager.getLuckPermsHook().getPrefix(player);
        String suffix = IntegrationManager.getLuckPermsHook().getSuffix(player);

        return text.replace("{prefix}", prefix != null ? prefix : "")
                   .replace("{suffix}", suffix != null ? suffix : "")
                   .replace("{name}", player.getName().getString())
                   .replace("{message}", message != null ? message : "");
    }
}