package com.zerognetwork.enhancedchattags;

import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.event.server.ServerStartedEvent;
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
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("EnhancedChatTags is setting up.");
        // We'll initialize integrations in the ServerStartedEvent
    }

    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event) {
        LOGGER.info("Server started. Initializing integrations.");
        if (IntegrationManager.getLuckPermsHook().isAvailable()) {
            LOGGER.info("LuckPerms integration initialized successfully");
        } else {
            LOGGER.warn("LuckPerms is not available. Some features may not work as expected.");
        }
        IntegrationManager.initMC2Discord();
    }
}