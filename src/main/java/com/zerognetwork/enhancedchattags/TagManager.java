package com.zerognetwork.enhancedchattags;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TagManager {

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) event.getEntity();
            String prefix = getPrefixForPlayer(player);
            player.displayClientMessage(Component.literal(prefix + " " + player.getName().getString()), false);
        }
    }

    private static String getPrefixForPlayer(ServerPlayer player) {
        // Example logic to get prefix from LuckPerms or other system
        return "<default_prefix>";
    }
}
