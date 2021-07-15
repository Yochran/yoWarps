package me.yochran.yowarps.utils;

import org.bukkit.ChatColor;

public class Utils {

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static int[] getSlotData(int id) {
        int[] data = new int[] { 1, 0 };

        if (id >= 0 && id < 9) data = new int[] { 1, id };
        if (id >= 9 && id < 18) data = new int[] { 2, (id - 9) };
        if (id >= 18 && id < 27) data = new int[] { 3, (id - 18) };;
        if (id >= 27 && id < 36) data = new int[] { 4, (id - 27) };
        if (id >= 36 && id < 45) data = new int[] { 5, (id - 36) };
        if (id >= 45 && id < 54) data = new int[] { 6, (id - 45) };
        if (id >= 54 && id < 63) data = new int[] { 7, (id - 54) };
        if (id >= 63 && id < 72) data = new int[] { 8, (id - 63) };
        if (id >= 72 && id < 81) data = new int[] { 9, (id - 72) };

        if (id >= 81) {
            System.out.println("Too many pages!");
            return null;
        }

        return data;
    }
}
