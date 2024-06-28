package com.zerognetwork.enhancedchattags.integration;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.server.level.ServerPlayer;

public class LuckPermsHook {
    private static LuckPerms api;
    private static boolean initialized = false;

    public void init() {
        try {
            api = LuckPermsProvider.get();
            initialized = true;
            EnhancedChatTags.LOGGER.info("LuckPerms integration initialized successfully");
        } catch (IllegalStateException e) {
            EnhancedChatTags.LOGGER.error("Failed to initialize LuckPerms integration", e);
        }
    }

    public static boolean isAvailable() {
        return initialized && api != null;
    }

    public String getPrefix(ServerPlayer player) {
        if (!isAvailable()) return "";
        try {
            return api.getPlayerAdapter(ServerPlayer.class).getMetaData(player).getPrefix();
        } catch (Exception e) {
            EnhancedChatTags.LOGGER.error("Error getting LuckPerms prefix", e);
            return "";
        }
    }

    public String getSuffix(ServerPlayer player) {
        if (!isAvailable()) return "";
        try {
            return api.getPlayerAdapter(ServerPlayer.class).getMetaData(player).getSuffix();
        } catch (Exception e) {
            EnhancedChatTags.LOGGER.error("Error getting LuckPerms suffix", e);
            return "";
        }
    }
}