package com.zerognetwork.enhancedchattags.integration;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import net.creeperhost.polylib.chat.ChatHandler;
import net.minecraftforge.fml.ModList;

public class MC2DiscordHook {
    private boolean isEnabled = false;

    public void init() {
        if (ModList.get().isLoaded("mc2discord")) {
            isEnabled = true;
            EnhancedChatTags.LOGGER.info("MC2Discord detected. Integration enabled.");
        } else {
            EnhancedChatTags.LOGGER.info("MC2Discord not found. Integration disabled.");
        }
    }

    public void sendMessageToDiscord(String playerName, String message) {
        if (!isEnabled) return;
        
        try {
            ChatHandler.sendMessageToChannel(playerName + ": " + message);
            EnhancedChatTags.LOGGER.info("Sent message to Discord: " + playerName + ": " + message);
        } catch (Exception e) {
            EnhancedChatTags.LOGGER.error("Failed to send message to Discord", e);
        }
    }
}