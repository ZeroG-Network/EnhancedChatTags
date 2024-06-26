package com.MrWhiteFlamesYT.EnhancedChatTags.chat;

import com.MrWhiteFlamesYT.EnhancedChatTags.EnhancedChatTags;
import com.MrWhiteFlamesYT.EnhancedChatTags.config.EnhancedChatTagsConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EnhancedChatTags.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChatHandler {

    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        String message = event.getMessage().getString();
        
        String prefix = "";
        String suffix = "";

        if (EnhancedChatTags.isLuckPermsLoaded) {
            try {
                net.luckperms.api.LuckPerms api = net.luckperms.api.LuckPermsProvider.get();
                prefix = api.getPlayerAdapter(ServerPlayer.class).getMetaData(player).getPrefix();
                suffix = api.getPlayerAdapter(ServerPlayer.class).getMetaData(player).getSuffix();
            } catch (Exception e) {
                EnhancedChatTags.LOGGER.error("Error getting LuckPerms data: ", e);
            }
        }

        String formattedMessage = EnhancedChatTagsConfig.CHAT_FORMAT.get()
                .replace("%prefix%", prefix != null ? prefix : "")
                .replace("%player%", player.getName().getString())
                .replace("%suffix%", suffix != null ? suffix : "")
                .replace("%message%", message);

        event.setMessage(Component.literal(formattedMessage));
    }
}