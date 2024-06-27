package com.zerognetwork.enhancedchattags.integration;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.world.entity.player.Player;

public class LuckPermsHook {
    private LuckPerms api;

    public void init() {
        try {
            api = LuckPermsProvider.get();
            EnhancedChatTags.LOGGER.info("LuckPerms integration initialized successfully");
        } catch (IllegalStateException e) {
            EnhancedChatTags.LOGGER.error("Failed to initialize LuckPerms integration", e);
        }
    }

    public String getPrefix(Player player) {
        if (api == null) return "";
        return api.getPlayerAdapter(Player.class).getMetaData(player).getPrefix();
    }

    public String getSuffix(Player player) {
        if (api == null) return "";
        return api.getPlayerAdapter(Player.class).getMetaData(player).getSuffix();
    }
}