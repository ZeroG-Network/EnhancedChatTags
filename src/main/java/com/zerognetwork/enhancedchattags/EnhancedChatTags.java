package com.zerognetwork.enhancedchattags;

import com.zerognetwork.enhancedchattags.chat.ChatManager;
import com.zerognetwork.enhancedchattags.config.ConfigManager;
import com.zerognetwork.enhancedchattags.integration.IntegrationManager;
import com.zerognetwork.enhancedchattags.tag.TagManager;
import com.zerognetwork.enhancedchattags.command.ECTCommand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
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
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("EnhancedChatTags is setting up.");
        IntegrationManager.init();
        ChatManager.init();
        TagManager.init();
    }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        ECTCommand.register(event.getDispatcher());
    }
}