package com.zerognetwork.enhancedchattags;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class CommandHandler {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            LiteralArgumentBuilder.<CommandSourceStack>literal("enhancedchattags")
                .then(Commands.argument("tag", StringArgumentType.string())
                    .executes(CommandHandler::setTag))
        );
    }

    private static int setTag(CommandContext<CommandSourceStack> context) {
        String tag = StringArgumentType.getString(context, "tag");
        ConfigHandler.setTagFormat(tag);
        context.getSource().sendSuccess(() -> Component.literal("Tag format updated to: " + tag), true);
        return 1;
    }
}
