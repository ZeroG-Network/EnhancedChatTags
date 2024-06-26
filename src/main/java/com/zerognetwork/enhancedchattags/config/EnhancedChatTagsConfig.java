package com.zerognetwork.enhancedchattags.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class EnhancedChatTagsConfig {
    public static ForgeConfigSpec SPEC;
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
        CommentedFileConfig configData = CommentedFileConfig.builder(configPath)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        configData.load();
        SPEC.setConfig(configData);
    }

    public static Map<String, String> getCustomPlaceholders() {
        // Implement this method to return your custom placeholders
        // For now, we'll return an empty map. You can expand this later.
        return new HashMap<>();
    }
}