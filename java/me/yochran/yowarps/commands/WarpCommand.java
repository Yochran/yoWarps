package me.yochran.yowarps.commands;

import me.yochran.yowarps.gui.GUI;
import me.yochran.yowarps.gui.guis.WarpsGUI;
import me.yochran.yowarps.management.Warp;
import me.yochran.yowarps.utils.Utils;
import me.yochran.yowarps.yoWarps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    private final yoWarps plugin = yoWarps.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.translate("&cYou must be a player to use that command."));
            return true;
        }

        if (!sender.hasPermission("yowarps.warp")) {
            sender.sendMessage(Utils.translate("&cYou do not have permission to use that command."));
            return true;
        }

        if (args.length > 1) {
            sender.sendMessage(Utils.translate("&cIncorrect Usage! /warp <name>"));
            return true;
        }

        if (args.length == 1) {
            Warp warp = Warp.getWarp(args[0]);
            if (warp.getLocation() == null) {
                sender.sendMessage(Utils.translate("&cInvalid Warp!"));
                return true;
            }

            if (plugin.getConfig().getBoolean("PerWarpPermissions") && !sender.hasPermission(warp.getPermission())) {
                sender.sendMessage(Utils.translate("&cYou do not have permission for that warp."));
                return true;
            }

            Warp.teleportToWarp((Player) sender, warp);
            sender.sendMessage(Utils.translate("&bYou have been teleported to &3" + warp.getName()));
        } else {
            WarpsGUI warpsGUI = new WarpsGUI((Player) sender, 18, "&8Warps List:");
            warpsGUI.setup(1);
            GUI.open(warpsGUI.getGui());
        }

        return true;
    }
}
