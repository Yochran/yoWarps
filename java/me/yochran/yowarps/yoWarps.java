package me.yochran.yowarps;

import me.yochran.yowarps.commands.RemovewarpCommand;
import me.yochran.yowarps.commands.SetwarpCommand;
import me.yochran.yowarps.commands.WarpCommand;
import me.yochran.yowarps.data.WarpData;
import me.yochran.yowarps.management.Warp;
import me.yochran.yowarps.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class yoWarps extends JavaPlugin {

    private static yoWarps instance;
    public WarpData warpData;

    public final PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(Utils.translate("yoWarps v1.0 by Yochran is loading..."));

        instance = this;
        saveDefaultConfig();
        registerData();
        registerPermissions();

        getCommand("Warp").setExecutor(new WarpCommand());
        getCommand("Setwarp").setExecutor(new SetwarpCommand());
        getCommand("Removewarp").setExecutor(new RemovewarpCommand());

        Bukkit.getConsoleSender().sendMessage(Utils.translate("yoWarps v1.0 by Yochran has successfully loaded."));
    }

    public static yoWarps getInstance() { return instance; }

    private void registerData() {
        warpData = new WarpData();

        warpData.setupData();
        warpData.saveData();
        warpData.reloadData();

        new BukkitRunnable() {
            @Override
            public void run() { warpData.saveData(); }
        }.runTaskLater(this, 10);
    }

    private void registerPermissions() {
        if (getConfig().getBoolean("PerWarpPermissions")) {
            for (Warp warp : Warp.getWarps()) {
                Permission permission = new Permission(warp.getPermission());
                if (!pluginManager.getPermissions().contains(permission))
                    pluginManager.addPermission(permission);
            }
        }
    }
}
