package com.zerognetwork.enhancedchattags.chat;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import com.zerognetwork.enhancedchattags.util.ColorUtil;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;

@Mod.EventBusSubscriber(modid = EnhancedChatTags.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChatManager {

    private static final ResourceLocation CUSTOM_CHAT = new ResourceLocation(EnhancedChatTags.MOD_ID, "custom_chat");
    public static ChatType CUSTOM_CHAT_TYPE;

    public static void registerChatType() {
        CUSTOM_CHAT_TYPE = ChatType.register("enhancedchattags:custom_chat", CUSTOM_CHAT);
    }

    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event) {
        ServerPlayer player = event.getPlayer();
        Component originalMessage = event.getMessage();

        String formattedMessage = formatChatMessage(player, originalMessage.getString());

        Component newMessage = Component.literal(formattedMessage);
        event.setMessage(newMessage);

        // Send message to all players using custom chat type
        ServerLifecycleHooks.getCurrentServer().getPlayerList().broadcastChatMessage(newMessage, player, CUSTOM_CHAT_TYPE);

        // Cancel the original event to prevent double messages
        event.setCanceled(true);

        // Send to Discord if enabled
        if (ConfigManager.USE_MC2DISCORD.get()) {
            IntegrationManager.getMC2DiscordHook().sendMessageToDiscord(player.getName().getString(), originalMessage.getString());
        }
    }

    @SubscribeEvent
    public static void onPlayerNameFormat(PlayerEvent.NameFormat event) {
        if (event.getEntity() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            String formattedName = formatPlayerName(player);
            event.setDisplayname(Component.literal(formattedName));
        }
    }

    private static String formatChatMessage(ServerPlayer player, String message) {
        String format = ConfigManager.CHAT_FORMAT.get();
        String prefix = IntegrationManager.getLuckPermsHook().getPrefix(player);
        String suffix = IntegrationManager.getLuckPermsHook().getSuffix(player);
        String name = player.getName().getString();

        return ColorUtil.translateColorCodes(
            format.replace("{prefix}", prefix != null ? prefix : "")
                  .replace("{name}", name)
                  .replace("{suffix}", suffix != null ? suffix : "")
                  .replace("{message}", message)
        );
    }

    private static String formatPlayerName(ServerPlayer player) {
        String format = ConfigManager.NAME_FORMAT.get();
        String prefix = IntegrationManager.getLuckPermsHook().getPrefix(player);
        String suffix = IntegrationManager.getLuckPermsHook().getSuffix(player);
        String name = player.getName().getString();

        return ColorUtil.translateColorCodes(
            format.replace("{prefix}", prefix != null ? prefix : "")
                  .replace("{name}", name)
                  .replace("{suffix}", suffix != null ? suffix : "")
        );
    }
}