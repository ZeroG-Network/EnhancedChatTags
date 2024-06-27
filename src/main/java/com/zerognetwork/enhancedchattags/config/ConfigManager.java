package com.zerognetwork.enhancedchattags.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ConfigManager {
    public static ForgeConfigSpec.ConfigValue<String> CHAT_FORMAT;
    public static ForgeConfigSpec.ConfigValue<String> TAG_FORMAT;
    public static ForgeConfigSpec.BooleanValue ENABLE_TAGS;
    public static ForgeConfigSpec.EnumValue<TagPosition> TAG_POSITION;
    public static ForgeConfigSpec.BooleanValue USE_MC2DISCORD;

    public enum TagPosition {
        ABOVE, BELOW
    }

    public static void register() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        builder.comment("EnhancedChatTags Configuration").push("chat");
        CHAT_FORMAT = builder
                .comment("Format for chat messages. Available placeholders: {prefix}, {name}, {suffix}, {message}")
                .define("format", "&8[&r{prefix}&8] &f{name}&8: &7{message}");
        builder.pop();
        
        builder.push("tags");
        ENABLE_TAGS = builder
                .comment("Enable custom player tags")
                .define("enabled", true);
        TAG_FORMAT = builder
                .comment("Format for player tags. Available placeholders: {prefix}, {name}, {suffix}")
                .define("tag_format", "&8[&r{prefix}&8] &f{name}");
        TAG_POSITION = builder
                .comment("The position of the tag. Options: ABOVE, BELOW")
                .defineEnum("tag_position", TagPosition.ABOVE);
        builder.pop();

        builder.push("integrations");
        USE_MC2DISCORD = builder
                .comment("Enable integration with MC2Discord")
                .define("use_mc2discord", true);
        builder.pop();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, builder.build());
    }
}