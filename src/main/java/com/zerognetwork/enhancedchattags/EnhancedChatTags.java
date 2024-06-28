package com.zerognetwork.enhancedchattags;

import com.zerognetwork.enhancedchattags.chat.ChatManager;
import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EnhancedChatTags.MOD_ID)
public class EnhancedChatTags {
    public static final String MOD_ID = "enhancedchattags";
    public static final Logger LOGGER = LogManager.getLogger();

    public EnhancedChatTags() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        ConfigManager.register();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ChatManager());
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("EnhancedChatTags is setting up.");
        ChatManager.registerChatType();
    }

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        LOGGER.info("Server started. Initializing integrations.");
        IntegrationManager.initLuckPerms();
        IntegrationManager.initMC2Discord();
    }
}