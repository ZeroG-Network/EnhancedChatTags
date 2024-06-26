package com.MrWhiteFlamesYT.EnhancedChatTags.tag;

import com.MrWhiteFlamesYT.EnhancedChatTags.EnhancedChatTags;
import com.MrWhiteFlamesYT.EnhancedChatTags.config.EnhancedChatTagsConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EnhancedChatTags.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TagHandler {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof Player) {
            updatePlayerTag((Player) event.getEntity());
        }
    }

    private static void updatePlayerTag(Player player) {
        String prefix = "";

        if (EnhancedChatTags.isLuckPermsLoaded) {
            try {
                net.luckperms.api.LuckPerms api = net.luckperms.api.LuckPermsProvider.get();
                prefix = api.getPlayerAdapter(Player.class).getMetaData(player).getPrefix();
            } catch (Exception e) {
                EnhancedChatTags.LOGGER.error("Error getting LuckPerms data: ", e);
            }
        }

        String formattedTag = EnhancedChatTagsConfig.TAG_FORMAT.get()
                .replace("%prefix%", prefix != null ? prefix : "")
                .replace("%player%", player.getName().getString());

        player.setCustomName(Component.literal(formattedTag));
        player.setCustomNameVisible(true);
    }
}