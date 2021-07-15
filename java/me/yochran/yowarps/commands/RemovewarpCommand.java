package me.yochran.yowarps.commands;

import me.yochran.yowarps.management.Warp;
import me.yochran.yowarps.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RemovewarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("yowarps.delwarp")) {
            sender.sendMessage(Utils.translate("&cYou do not have permission to use that command."));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(Utils.translate("&cIncorrect Usage! /delwarp <name>"));
            return true;
        }

        if (!Warp.warpAlreadyExists(args[0])) {
            sender.sendMessage(Utils.translate("&cA warp with that name doesn't exist!"));
            return true;
        }

        Warp.deleteWarp(args[0]);
        sender.sendMessage(Utils.translate("&bSuccessfully deleted warp &3" + args[0] + "&b."));

        return true;
    }
}
