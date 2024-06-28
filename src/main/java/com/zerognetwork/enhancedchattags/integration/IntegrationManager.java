package com.zerognetwork.enhancedchattags.integration;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;

public class IntegrationManager {
    private static LuckPermsHook luckPermsHook;
    private static MC2DiscordHook mc2DiscordHook;

    public static void initLuckPerms() {
        luckPermsHook = new LuckPermsHook();
        luckPermsHook.init();
    }

    public static void initMC2Discord() {
        mc2DiscordHook = new MC2DiscordHook();
        mc2DiscordHook.init();
    }

    public static LuckPermsHook getLuckPermsHook() {
        return luckPermsHook;
    }

    public static MC2DiscordHook getMC2DiscordHook() {
        return mc2DiscordHook;
    }
}