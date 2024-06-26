package com.zerognetwork.enhancedchattags.util;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LuckPermsCache {
    private static final Map<UUID, UserData> userCache = new HashMap<>();
    private static final long CACHE_EXPIRY_MS = 60000; // 1 minute

    public static void initialize() {
        // This method can be used to set up any initial cache data if needed
    }

    public static UserData getUserData(UUID uuid) {
        UserData cachedData = userCache.get(uuid);
        if (cachedData != null && !cachedData.isExpired()) {
            return cachedData;
        }
        
        // Fetch new data from LuckPerms
        LuckPerms api = LuckPermsProvider.get();
        User user = api.getUserManager().getUser(uuid);
        if (user != null) {
            UserData newData = new UserData(user.getCachedData().getMetaData().getPrefix(),
                                            user.getCachedData().getMetaData().getSuffix(),
                                            System.currentTimeMillis());
            userCache.put(uuid, newData);
            return newData;
        }
        return null;
    }

    public static class UserData {
        public String prefix;
        public String suffix;
        private long timestamp;

        UserData(String prefix, String suffix, long timestamp) {
            this.prefix = prefix;
            this.suffix = suffix;
            this.timestamp = timestamp;
        }

        boolean isExpired() {
            return System.currentTimeMillis() - timestamp > CACHE_EXPIRY_MS;
        }
    }
}