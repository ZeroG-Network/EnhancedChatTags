package com.zerognetwork.enhancedchattags.integration;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.server.level.ServerPlayer;

public class LuckPermsHook {
    private static LuckPerms api;
    private static boolean initialized = false;

    public static void init() {
        // Do nothing here, we'll initialize later
    }

    public static boolean isAvailable() {
        if (!initialized) {
            try {
                api = LuckPermsProvider.get();
                initialized = true;
            } catch (IllegalStateException e) {
                // LuckPerms is not available yet
                return false;
            }
        }
        return initialized;
    }

    public static String getPrefix(ServerPlayer player) {
        if (!isAvailable()) return "";
        try {
            return api.getPlayerAdapter(ServerPlayer.class).getMetaData(player).getPrefix();
        } catch (Exception e) {
            EnhancedChatTags.LOGGER.error("Error getting LuckPerms prefix", e);
            return "";
        }
    }

    public static String getSuffix(ServerPlayer player) {
        if (!isAvailable()) return "";
        try {
            return api.getPlayerAdapter(ServerPlayer.class).getMetaData(player).getSuffix();
        } catch (Exception e) {
            EnhancedChatTags.LOGGER.error("Error getting LuckPerms suffix", e);
            return "";
        }
    }
}