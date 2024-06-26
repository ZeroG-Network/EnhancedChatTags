package com.zerognetwork.enhancedchattags.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class EnhancedChatTagsConfig {
    private static ForgeConfigSpec SPEC;
    public static ForgeConfigSpec.ConfigValue<String> CHAT_FORMAT;
    public static ForgeConfigSpec.ConfigValue<String> TAG_FORMAT;
    public static ForgeConfigSpec.BooleanValue ENABLE_TAGS;
    public static ForgeConfigSpec.EnumValue<TagPosition> TAG_POSITION;

    public enum TagPosition {
        ABOVE, BELOW
    }

    public static void register() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        builder.comment("EnhancedChatTags Configuration").push("chat");
        CHAT_FORMAT = builder
                .comment("Format for chat messages. Available placeholders: {prefix}, {name}, {suffix}, {message}")
                .define("format", "{prefix} {name}: {message} {suffix}");
        builder.pop();
        
        builder.push("tags");
        ENABLE_TAGS = builder
                .comment("Enable custom player tags")
                .define("enabled", true);
        TAG_FORMAT = builder
                .comment("Format for player tags. Available placeholders: {prefix}, {name}, {suffix}")
                .define("tag_format", "{prefix} {name} {suffix}");
        TAG_POSITION = builder
                .comment("The position of the tag. Options: ABOVE, BELOW")
                .defineEnum("tag_position", TagPosition.ABOVE);
        builder.pop();

        SPEC = builder.build();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC, "enhancedchattags/enhancedchattags.toml");
    }

    public static void reload() {
        Path configPath = FMLPaths.CONFIGDIR.get().resolve("enhancedchattags/enhancedchattags.toml");
        SPEC.setConfig(new net.minecraftforge.common.ForgeConfigSpec.Builder().build().buildFrozenConfiguration(configPath));
    }
}