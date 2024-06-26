package com.zerognetwork.enhancedchattags.util;

import com.zerognetwork.enhancedchattags.config.EnhancedChatTagsConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PlaceholderManager {
    private static final Map<String, Function<Player, String>> placeholders = new HashMap<>();

    static {
        // Register default placeholders
        registerPlaceholder("name", player -> player.getName().getString());
        registerPlaceholder("displayname", p -> p.getDisplayName().getString());
    }

    public static void registerPlaceholder(String key, Function<Player, String> resolver) {
        placeholders.put(key, resolver);
    }

    public static String applyPlaceholders(String text, Player player) {
        for (Map.Entry<String, Function<Player, String>> entry : placeholders.entrySet()) {
            text = text.replace("{" + entry.getKey() + "}", entry.getValue().apply(player));
        }
        return text;
    }

    public static void loadCustomPlaceholders(ForgeConfigSpec config) {
        // Load custom placeholders from config
        Map<String, String> customPlaceholders = EnhancedChatTagsConfig.getCustomPlaceholders();
        for (Map.Entry<String, String> entry : customPlaceholders.entrySet()) {
            registerPlaceholder(entry.getKey(), p -> entry.getValue());
        }
    }

    public static void registerCustomPlaceholder(String key, String value) {
        registerPlaceholder(key, p -> value);
    }
}