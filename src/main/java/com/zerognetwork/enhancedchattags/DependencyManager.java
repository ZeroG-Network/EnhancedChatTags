package com.zerognetwork.enhancedchattags;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.IEventBus;

@Mod.EventBusSubscriber(modid = EnhancedChatTags.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DependencyManager {

    public DependencyManager() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ConfigHandler.loadConfig();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // Inter-mod communication enqueue logic here
    }

    private void processIMC(final InterModProcessEvent event) {
        // Inter-mod communication process logic here
    }
}
