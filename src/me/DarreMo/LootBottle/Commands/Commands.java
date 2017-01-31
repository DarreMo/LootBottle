package me.DarreMo.LootBottle.Commands;

import me.DarreMo.LootBottle.LootBottleM;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;


/**
 * Created by Timon on 29-1-2017.
 */
public class Commands implements CommandExecutor {

    public static Permission command = new Permission("Lootbottle.command") ;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission(command)) {

            if (label.equalsIgnoreCase("testcmd")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.GREEN + "Command From Console");
                    return true;
                }

                Player player = (Player) sender;
                player.sendMessage(ChatColor.GREEN + "InGame Command");
            }

        }
        return true ;
    }
}
