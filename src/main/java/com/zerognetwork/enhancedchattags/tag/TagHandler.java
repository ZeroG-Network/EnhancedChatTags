package com.zerognetwork.enhancedchattags.tag;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import com.zerognetwork.enhancedchattags.config.EnhancedChatTagsConfig;
import com.zerognetwork.enhancedchattags.util.ColorUtil;
import com.zerognetwork.enhancedchattags.util.LuckPermsCache;
import com.zerognetwork.enhancedchattags.util.PlaceholderManager;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EnhancedChatTags.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TagHandler {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof Player && EnhancedChatTagsConfig.ENABLE_TAGS.get()) {
            updatePlayerTag((Player) event.getEntity());
        }
    }

    private static void updatePlayerTag(Player player) {
        String formattedTag = EnhancedChatTagsConfig.TAG_FORMAT.get();
        
        if (EnhancedChatTags.isLuckPermsLoaded) {
            LuckPermsCache.UserData userData = LuckPermsCache.getUserData(player.getUUID());
            if (userData != null) {
                formattedTag = formattedTag.replace("{prefix}", userData.prefix != null ? userData.prefix : "")
                                           .replace("{suffix}", userData.suffix != null ? userData.suffix : "");
            }
        }
        
        formattedTag = PlaceholderManager.applyPlaceholders(formattedTag, player);
        formattedTag = ColorUtil.translateColorCodes(formattedTag);

        player.setCustomName(Component.literal(formattedTag));
        player.setCustomNameVisible(true);

        // Set tag position
        if (EnhancedChatTagsConfig.TAG_POSITION.get() == EnhancedChatTagsConfig.TagPosition.BELOW) {
            player.getEntityData().set(net.minecraft.world.entity.Entity.DATA_CUSTOM_NAME_VISIBLE, true);
        }
    }
}