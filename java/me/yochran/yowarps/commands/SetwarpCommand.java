package me.yochran.yowarps.commands;

import me.yochran.yowarps.management.Warp;
import me.yochran.yowarps.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetwarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Utils.translate("&cYou must be a player to use that command."));
            return true;
        }

        if (!sender.hasPermission("yowarps.setwarp")) {
            sender.sendMessage(Utils.translate("&cYou do not have permission to use that command."));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(Utils.translate("&cIncorrect Usage! /setwarp <name>"));
            return true;
        }

        if (Warp.warpAlreadyExists(args[0])) {
            sender.sendMessage(Utils.translate("&cA warp with that name already exists!"));
            return true;
        }

        Warp warp = new Warp(args[0], ((Player) sender).getLocation());
        warp.createWarp();

        sender.sendMessage(Utils.translate("&bCreated the warp &3" + warp.getName() + "&b."));

        return true;
    }
}
