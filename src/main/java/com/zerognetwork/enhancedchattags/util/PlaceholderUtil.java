package com.zerognetwork.enhancedchattags.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PlaceholderUtil {
    private static final Map<String, Function<Object, String>> placeholders = new HashMap<>();

    public static void registerPlaceholder(String key, Function<Object, String> resolver) {
        placeholders.put(key, resolver);
    }

    public static String applyPlaceholders(String text, Object context) {
        for (Map.Entry<String, Function<Object, String>> entry : placeholders.entrySet()) {
            text = text.replace("%" + entry.getKey() + "%", entry.getValue().apply(context));
        }
        return text;
    }
}