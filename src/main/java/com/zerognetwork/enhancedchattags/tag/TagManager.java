package com.zerognetwork.enhancedchattags.tag;

import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import com.zerognetwork.enhancedchattags.util.ColorUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "enhancedchattags", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TagManager {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            updatePlayerTag((ServerPlayer) event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            updatePlayerTag((ServerPlayer) event.getEntity());
        }
    }

    private static void updatePlayerTag(ServerPlayer player) {
        String format = ConfigManager.TAG_FORMAT.get();
        String prefix = IntegrationManager.getLuckPermsHook().isAvailable() ? 
            IntegrationManager.getLuckPermsHook().getPrefix(player) : "";
        String suffix = IntegrationManager.getLuckPermsHook().isAvailable() ? 
            IntegrationManager.getLuckPermsHook().getSuffix(player) : "";
        String name = player.getName().getString();

        String formattedTag = format
                .replace("{prefix}", prefix != null ? prefix : "")
                .replace("{name}", name)
                .replace("{suffix}", suffix != null ? suffix : "");

        formattedTag = ColorUtil.translateColorCodes(formattedTag);

        player.setCustomName(Component.literal(formattedTag));
        player.setCustomNameVisible(true);
    }
}