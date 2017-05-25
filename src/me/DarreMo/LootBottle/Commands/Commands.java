package me.DarreMo.LootBottle.Commands;

import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.GREEN + "Command From Console");
            return true;
        } else {
            Player player = (Player) sender;
            if (player.hasPermission(LBPermissions.command))
            {
                    player.sendMessage(ChatColor.GREEN + "InGame Command");
                    return true;
            }
        }
        return true;
    }
}
