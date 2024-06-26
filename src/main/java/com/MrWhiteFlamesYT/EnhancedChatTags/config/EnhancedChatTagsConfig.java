package com.MrWhiteFlamesYT.EnhancedChatTags.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class EnhancedChatTagsConfig {
    public static ForgeConfigSpec.ConfigValue<String> CHAT_FORMAT;
    public static ForgeConfigSpec.ConfigValue<String> TAG_FORMAT;

    public static void register() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        builder.push("Chat");
        CHAT_FORMAT = builder
                .comment("Format for chat messages")
                .define("chatFormat", "%prefix%%player%%suffix%: %message%");
        builder.pop();

        builder.push("Tag");
        TAG_FORMAT = builder
                .comment("Format for player tags")
                .define("tagFormat", "%prefix% %player%");
        builder.pop();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, builder.build());
    }
}