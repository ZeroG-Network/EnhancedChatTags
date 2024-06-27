package com.zerognetwork.enhancedchattags.tag;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.util.ColorUtil;
import com.zerognetwork.enhancedchattags.util.PlaceholderUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EnhancedChatTags.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TagManager {

    public static void init() {
        EnhancedChatTags.LOGGER.info("Initializing TagManager");
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        updatePlayerTag(event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        updatePlayerTag(event.getEntity());
    }

    private static void updatePlayerTag(Player player) {
        if (!ConfigManager.ENABLE_TAGS.get()) return;

        String format = ConfigManager.TAG_FORMAT.get();
        String formattedTag = ColorUtil.translateColorCodes(PlaceholderUtil.replacePlaceholders(format, player, null));

        player.setCustomName(Component.literal(formattedTag));
        player.setCustomNameVisible(true);

        if (ConfigManager.TAG_POSITION.get() == ConfigManager.TagPosition.ABOVE) {
            player.setGlowingTag(true);
        } else {
            player.setGlowingTag(false);
        }
    }
}