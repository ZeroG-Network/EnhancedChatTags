package com.MrWhiteFlamesYT.EnhancedChatTags.util;

import net.minecraft.ChatFormatting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {
    private static final Pattern COLOR_PATTERN = Pattern.compile("&([0-9a-fk-or])");

    public static String translateColorCodes(String text) {
        if (text == null) return "";
        Matcher matcher = COLOR_PATTERN.matcher(text);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, ChatFormatting.getByCode(matcher.group(1).charAt(0)).toString());
        }
        return matcher.appendTail(buffer).toString();
    }
}