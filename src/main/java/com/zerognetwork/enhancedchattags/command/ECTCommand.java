package com.zerognetwork.enhancedchattags.command;

import com.mojang.brigadier.CommandDispatcher;
import com.zerognetwork.enhancedchattags.config.ConfigManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ECTCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ect")
            .requires(source -> source.hasPermission(2))
            .then(Commands.literal("reload")
                .executes(context -> {
                    // Implement config reload logic here
                    context.getSource().sendSuccess(() -> Component.literal("Configuration reloaded."), true);
                    return 1;
                }))
        );
    }
}