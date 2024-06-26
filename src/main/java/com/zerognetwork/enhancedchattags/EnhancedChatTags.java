package com.zerognetwork.enhancedchattags;

import com.zerognetwork.enhancedchattags.config.EnhancedChatTagsConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(EnhancedChatTags.MOD_ID)
public class EnhancedChatTags {
    public static final String MOD_ID = "enhancedchattags";
    public static final Logger LOGGER = LogManager.getLogger();
    public static boolean isLuckPermsLoaded = false;

    public EnhancedChatTags() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        EnhancedChatTagsConfig.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("EnhancedChatTags is setting up.");
        isLuckPermsLoaded = checkLuckPermsLoaded();
        if (!isLuckPermsLoaded) {
            LOGGER.warn("LuckPerms not found. Some features may be limited.");
        }
    }

    private boolean checkLuckPermsLoaded() {
        try {
            Class.forName("net.luckperms.api.LuckPerms");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}