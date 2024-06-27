package com.zerognetwork.enhancedchattags.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigManager {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> CHAT_FORMAT;
    public static final ForgeConfigSpec.ConfigValue<String> NAME_FORMAT;
    public static final ForgeConfigSpec.BooleanValue USE_MC2DISCORD;

    static {
        BUILDER.push("General");

        CHAT_FORMAT = BUILDER
                .comment("Format for chat messages. Available placeholders: {prefix}, {name}, {suffix}, {message}")
                .define("chatFormat", "{prefix}{name}{suffix}: {message}");

        NAME_FORMAT = BUILDER
                .comment("Format for player names. Available placeholders: {prefix}, {name}, {suffix}")
                .define("nameFormat", "{prefix}{name}{suffix}");

        USE_MC2DISCORD = BUILDER
                .comment("Enable integration with MC2Discord")
                .define("useMC2Discord", true);

        BUILDER.pop();
        COMMON_SPEC = BUILDER.build();
    }

    public static void register() {}
}