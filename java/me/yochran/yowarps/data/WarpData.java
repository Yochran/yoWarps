package me.yochran.yowarps.data;

import me.yochran.yowarps.yoWarps;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class WarpData {

    private final yoWarps plugin;
    public File file;
    public FileConfiguration config;

    public WarpData() {
        plugin = yoWarps.getPlugin(yoWarps.class);
    }

    public void setupData() {
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();

        file = new File(plugin.getDataFolder(), "warps.yml");

        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getConsoleSender().sendMessage("[yoWarps] warps.yml file could not load.");
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getData() { return config; }

    public void saveData() {
        try { config.save(file); } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("[yoWarps] warps.yml file could not save.");
        }
    }

    public void reloadData() { config = YamlConfiguration.loadConfiguration(file); }
}
