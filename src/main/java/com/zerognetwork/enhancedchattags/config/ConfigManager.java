package com.zerognetwork.enhancedchattags.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ConfigManager {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> CHAT_FORMAT;
    public static final ForgeConfigSpec.ConfigValue<String> TAG_FORMAT;
    public static final ForgeConfigSpec.BooleanValue USE_MC2DISCORD;

    static {
        BUILDER.push("General");

        CHAT_FORMAT = BUILDER
                .comment("Format for chat messages. Available placeholders: {prefix}, {name}, {suffix}, {message}")
                .define("chatFormat", "{prefix}{name}{suffix}: {message}");

        TAG_FORMAT = BUILDER
                .comment("Format for player tags. Available placeholders: {prefix}, {name}, {suffix}")
                .define("tagFormat", "{prefix}{name}{suffix}");

        USE_MC2DISCORD = BUILDER
                .comment("Enable integration with MC2Discord")
                .define("useMC2Discord", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static void register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC);
    }
}