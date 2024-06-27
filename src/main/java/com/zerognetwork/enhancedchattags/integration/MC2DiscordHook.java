package com.zerognetwork.enhancedchattags.integration;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import net.minecraftforge.fml.ModList;

public class MC2DiscordHook {
    private boolean isEnabled = false;

    public void init() {
        if (ModList.get().isLoaded("mc2discord")) {
            isEnabled = true;
            EnhancedChatTags.LOGGER.info("MC2Discord detected. Integration enabled.");
            // Initialize your MC2Discord integration here
        } else {
            EnhancedChatTags.LOGGER.info("MC2Discord not found. Integration disabled.");
        }
    }

    public void sendMessageToDiscord(String playerName, String message) {
        if (!isEnabled) return;
        
        // Implement the actual integration with MC2Discord here
        // This is a placeholder and needs to be replaced with actual MC2Discord API calls
        EnhancedChatTags.LOGGER.info("Sending message to Discord: " + playerName + ": " + message);
    }
}