package me.yochran.yowarps.utils;

import org.bukkit.ChatColor;

public class Utils {

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
