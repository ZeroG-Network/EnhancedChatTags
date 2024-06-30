package com.zerognetwork.enhancedchattags;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EnhancedChatTags.MODID)
public class ChatEventHandler {
    @SubscribeEvent
    public static void onChatMessage(ServerChatEvent event) {
        String format = ConfigHandler.getTagFormat();
        MutableComponent formattedMessage = Component.literal(format.replace("{name}", event.getPlayer().getName().getString())
                .replace("{message}", event.getMessage().getString()));
        event.setMessage(formattedMessage);

        if (ConfigHandler.isLoggingEnabled()) {
            System.out.println("Chat message formatted: " + formattedMessage.getString());
        }
    }
}
