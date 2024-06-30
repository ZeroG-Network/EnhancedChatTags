package com.zerognetwork.enhancedchattags;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;

import java.nio.file.Path;

public class ConfigHandler {
    public static final ForgeConfigSpec COMMON_SPEC;
    private static final Config COMMON;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        COMMON = new Config(builder);
        COMMON_SPEC = builder.build();
    }

    public static void loadConfig() {
        final Path configPath = Path.of("config/enhancedchattags-common.toml");
        final CommentedFileConfig configData = CommentedFileConfig.builder(configPath).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        COMMON_SPEC.setConfig(configData);
    }

    public static String getTagFormat() {
        return COMMON.tagFormat.get();
    }

    public static void setTagFormat(String format) {
        COMMON.tagFormat.set(format);
    }

    public static boolean isLoggingEnabled() {
        return COMMON.enableLogging.get();
    }

    private static class Config {
        final ForgeConfigSpec.ConfigValue<String> tagFormat;
        final ForgeConfigSpec.BooleanValue enableLogging;

        Config(ForgeConfigSpec.Builder builder) {
            builder.push("tags");
            tagFormat = builder.comment("The format for chat tags")
                    .define("tag.format", "<{prefix}> {name}");
            builder.pop();

            builder.push("logging");
            enableLogging = builder.comment("Enable logging of chat messages")
                    .define("enableLogging", false);
            builder.pop();
        }
    }
}
