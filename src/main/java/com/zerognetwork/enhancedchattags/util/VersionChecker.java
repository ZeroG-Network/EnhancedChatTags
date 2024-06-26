package com.zerognetwork.enhancedchattags.util;

import com.zerognetwork.enhancedchattags.EnhancedChatTags;
import net.minecraft.SharedConstants;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;

public class VersionChecker {
    private static final String SUPPORTED_MC_VERSION = "1.20";
    private static final String MINIMUM_FORGE_VERSION = "47.0.0";

    public static void checkVersions() {
        String currentMcVersion = SharedConstants.getCurrentVersion().getName();
        if (!currentMcVersion.startsWith(SUPPORTED_MC_VERSION)) {
            EnhancedChatTags.LOGGER.error("EnhancedChatTags is not compatible with Minecraft version {}. Supported version: {}", 
                         currentMcVersion, SUPPORTED_MC_VERSION);
        }

        String forgeVersion = getForgeVersion();
        if (compareVersions(forgeVersion, MINIMUM_FORGE_VERSION) < 0) {
            EnhancedChatTags.LOGGER.error("EnhancedChatTags requires Forge version {} or higher. Current version: {}", 
                         MINIMUM_FORGE_VERSION, forgeVersion);
        }
    }

    private static String getForgeVersion() {
        IModInfo forgeModInfo = ModList.get().getModContainerById("forge").get().getModInfo();
        return forgeModInfo.getVersion().toString();
    }

    private static int compareVersions(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int length = Math.max(v1.length, v2.length);
        for (int i = 0; i < length; i++) {
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (num1 < num2) return -1;
            if (num1 > num2) return 1;
        }
        return 0;
    }
}