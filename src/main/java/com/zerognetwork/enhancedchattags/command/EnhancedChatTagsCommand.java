package com.zerognetwork.enhancedchattags.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import com.zerognetwork.enhancedchattags.config.EnhancedChatTagsConfig;
import com.zerognetwork.enhancedchattags.placeholder.PlaceholderManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class EnhancedChatTagsCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("enhancedchattags")
            .requires(source -> source.hasPermission(2))
            .then(Commands.literal("reload")
                .executes(context -> {
                    EnhancedChatTagsConfig.reload();
                    context.getSource().sendSuccess(Component.literal("Configuration reloaded."), true);
                    return 1;
                }))
            .then(Commands.literal("addplaceholder")
                .then(Commands.argument("key", StringArgumentType.word())
                .then(Commands.argument("value", StringArgumentType.greedyString())
                    .executes(context -> {
                        String key = StringArgumentType.getString(context, "key");
                        String value = StringArgumentType.getString(context, "value");
                        PlaceholderManager.registerCustomPlaceholder(key, value);
                        context.getSource().sendSuccess(Component.literal("Custom placeholder added: " + key), true);
                        return 1;
                    })))));
    }
}