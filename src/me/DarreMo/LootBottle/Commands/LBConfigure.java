package me.DarreMo.LootBottle.Commands;

import me.DarreMo.LootBottle.LootBottleM;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Timon on 31-1-2017.
 */
public class LBConfigure implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("lbconfigure") && sender.hasPermission(LBPermissions.Configure))
        {
            int amount = args.length;

            if (amount < 1){
                sender.sendMessage(ChatColor.RED + "Error: " + ChatColor.GREEN + "Please choose configure option: "+ ChatColor.AQUA+"new "+ChatColor.DARK_AQUA+"edit "+ChatColor.AQUA+"del");
                return true;
            }
            else{
                switch (args[0]) {

                    case "new":

                        if (amount == 1) {
                            sender.sendMessage(ChatColor.RED + "Error: " + ChatColor.GREEN + "Please name the Bottle's you want to make");
                        }

                        else {
                            String message = "Bottle ";
                            Arrays.asList(args).stream().skip(1).forEach( a -> LootBottleM.getBottles().set("bottles."+a+".name", a));
                            message += Arrays.asList(args).stream().skip(1).map(a -> a).collect(Collectors.joining(", "));
                            LootBottleM.getBottles().save();

                            // TODO Permission

                            message += " created." ;
                            sender.sendMessage(ChatColor.GREEN+message);
                        }
                        break;

                    case "edit":
                        if (!(sender instanceof Player)){
                            sender.sendMessage(ChatColor.RED+"Error: "+ChatColor.GREEN+"Please specify the Bottle you want to edit");
                        }
                        else {
                            Player player = (Player) sender;
                            player.sendMessage(ChatColor.GREEN+"Opening Bottle Edit Menu");
                            Inventory inv = Bukkit.createInventory(null, 9, "Bottle Select");

                            inv.setItem(0, BottleCmd.shiny);
                            player.openInventory(inv);
                        }

                        break;

                    case "del":
                        if (!(sender instanceof Player)){
                            sender.sendMessage(ChatColor.RED+"Error: "+ChatColor.GREEN+"Please specify the Bottle you want to Delete");
                        }
                        else {
                            Player player = (Player) sender;

                            player.sendMessage(ChatColor.GREEN+"Opening Bottle DELETE Menu");

                            Inventory inv = Bukkit.createInventory(null, 9, "Bottle DELETE");

                            inv.setItem(0, BottleCmd.shiny);
                            player.openInventory(inv);
                        }

                        break;

                    default:
                        sender.sendMessage(ChatColor.RED + "Error:" + ChatColor.GREEN + "Invalid Arguments, please try: " + ChatColor.BLUE + "create " + ChatColor.DARK_AQUA + "edit"+ ChatColor.BLUE + "del");

                        break;
                        }

                }
            }
        else {
            sender.sendMessage(ChatColor.RED + "You are not allowed configure LootBottle's");
            return true;
        }
        return true;
    }

}
