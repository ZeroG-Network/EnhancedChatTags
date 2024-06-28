package com.zerognetwork.enhancedchattags.util;

import net.minecraft.ChatFormatting;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
    private static final Pattern COLOR_PATTERN = Pattern.compile("&([0-9a-fk-or])");

    public static String translateColorCodes(String text) {
        if (text == null) return "";
        
        // Translate hex color codes
        Matcher matcher = HEX_PATTERN.matcher(text);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String hexColor = matcher.group(1);
            matcher.appendReplacement(buffer, "\u00A7x\u00A7" + hexColor.charAt(0) + "\u00A7" + hexColor.charAt(1)
                    + "\u00A7" + hexColor.charAt(2) + "\u00A7" + hexColor.charAt(3)
                    + "\u00A7" + hexColor.charAt(4) + "\u00A7" + hexColor.charAt(5));
        }
        matcher.appendTail(buffer);
        text = buffer.toString();
        
        // Translate standard color codes
        matcher = COLOR_PATTERN.matcher(text);
        buffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(buffer, "\u00A7" + matcher.group(1).toLowerCase());
        }
        matcher.appendTail(buffer);
        
        return buffer.toString();
    }
}