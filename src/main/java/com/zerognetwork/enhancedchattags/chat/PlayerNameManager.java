package com.zerognetwork.enhancedchattags.chat;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import com.zerognetwork.enhancedchattags.util.ChatUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modId = EnhancedChatTags.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerNameManager {
    @SubscribeEvent
    public static void onPlayerNameFormat(PlayerEvent.NameFormat event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer) {
            String formattedName = ChatUtil.formatPlayerName((ServerPlayer) player);
            event.setDisplayname(Component.literal(formattedName));
        }
    }
}