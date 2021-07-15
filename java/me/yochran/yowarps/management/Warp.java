package me.yochran.yowarps.management;

import me.yochran.yowarps.yoWarps;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Warp {

    private final yoWarps plugin = yoWarps.getInstance();
    private static final yoWarps splugin;

    static {
        splugin = yoWarps.getInstance();
    }

    private String name;
    private Location location;

    public Warp(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public void createWarp() {
        plugin.warpData.config.set("Warps." + getName().toUpperCase() + ".ID", getName().toUpperCase());
        plugin.warpData.config.set("Warps." + getName().toUpperCase() + ".Name", getName());
        plugin.warpData.config.set("Warps." + getName().toUpperCase() + ".Location.World", getLocation().getWorld().getName());
        plugin.warpData.config.set("Warps." + getName().toUpperCase() + ".Location.X", getLocation().getX());
        plugin.warpData.config.set("Warps." + getName().toUpperCase() + ".Location.Y", getLocation().getY());
        plugin.warpData.config.set("Warps." + getName().toUpperCase() + ".Location.Z", getLocation().getZ());
        plugin.warpData.config.set("Warps." + getName().toUpperCase() + ".Location.Yaw", getLocation().getYaw());
        plugin.warpData.config.set("Warps." + getName().toUpperCase() + ".Location.Pitch", getLocation().getPitch());
        plugin.warpData.saveData();
    }

    public static void deleteWarp(String name) {
        splugin.warpData.config.set("Warps." + name.toUpperCase(), null);
        splugin.warpData.saveData();
    }

    public static Warp getWarp(String name) {
        Warp warp = new Warp(null, null);

        try {
            String World = splugin.warpData.config.getString("Warps." + name.toUpperCase() + ".Location.World");
            double X = splugin.warpData.config.getDouble("Warps." + name.toUpperCase() + ".Location.X");
            double Y = splugin.warpData.config.getDouble("Warps." + name.toUpperCase() + ".Location.Y");
            double Z = splugin.warpData.config.getDouble("Warps." + name.toUpperCase() + ".Location.Z");
            double Yaw = splugin.warpData.config.getDouble("Warps." + name.toUpperCase() + ".Location.Yaw");
            double Pitch = splugin.warpData.config.getDouble("Warps." + name.toUpperCase() + ".Location.Pitch");

            Location location = new Location(Bukkit.getWorld(World), X, Y, Z, (float) Yaw, (float) Pitch);

            warp.setName(splugin.warpData.config.getString("Warps." + name.toUpperCase() + ".Name"));
            warp.setLocation(location);
        } catch (NullPointerException | IllegalArgumentException ignored) {}

        return warp;
    }

    public static List<Warp> getWarps() {
        List<Warp> warps = new ArrayList<>();

        if (splugin.warpData.config.contains("Warps")) {
            for (String warp : splugin.warpData.config.getConfigurationSection("Warps").getKeys(false))
                warps.add(getWarp(splugin.warpData.config.getString("Warps." + warp + ".Name")));
        }

        return warps;
    }

    public String getName() { return name; }
    public Location getLocation() { return location; }
    public String getPermission() { return "yowarps.warp." + getName().toLowerCase(); }

    public void setName(String name) { this.name = name; }
    public void setLocation(Location location) { this.location = location; }

    public static boolean warpAlreadyExists(String name) {
        return splugin.warpData.config.contains("Warps." + name.toUpperCase());
    }

    public static void teleportToWarp(Player player, Warp warp) {
        player.teleport(warp.getLocation());
    }
}
