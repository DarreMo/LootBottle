package me.DarreMo.LootBottle.Commands;

import me.DarreMo.LootBottle.Commands.LBPermissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.ChatColor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;



public class BottleCmd implements CommandExecutor {

    static ItemStack normal = new ItemStack(Material.DRAGONS_BREATH, 10);
    static ItemStack shiny = new ItemStack(Material.DRAGONS_BREATH, 1);
    {
        shiny.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

        ItemMeta shinym = shiny.getItemMeta();
        shinym.setDisplayName(ChatColor.GREEN+ChatColor.BOLD.toString()+"Shiny "+ChatColor.LIGHT_PURPLE+"Bottle");
        shinym.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY+"------------------");
        lore.add(ChatColor.DARK_PURPLE + "How did this bottle get so shiny?");
        lore.add(ChatColor.LIGHT_PURPLE + "Nobody will ever now");
        lore.add(ChatColor.GRAY+ChatColor.ITALIC.toString()+"Right Click to use");
        lore.add(ChatColor.GRAY+"------------------");
        shinym.setLore(lore);
        shiny.setItemMeta(shinym);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("getbottle")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Not a Console command!");
                return true;
            }

            Player player = (Player) sender;

            if (!(player.hasPermission(LBPermissions.GetBottle))) {
                player.sendMessage(ChatColor.RED + "You're lacking permission");
                return true;
            }

            if (args.length == 0)
            {
                player.getInventory().addItem(normal);
                player.sendMessage(ChatColor.GREEN + "You got 10 Bottle's");
                return true;
            }

            else if (args.length == 1)
            {
                if (Objects.equals(args[0], "normal"))
                {
                    if (player.hasPermission(LBPermissions.NormalBottle))
                    {
                        player.getInventory().addItem(normal);
                        player.sendMessage(ChatColor.GREEN + "You got 10 Bottle's!");
                        return true;
                    }

                    player.sendMessage(ChatColor.RED + "You're lacking permission");
                    return true;

                } else if (Objects.equals(args[0], "shiny")) {

                    if (!(player.hasPermission(LBPermissions.ShinyBottle))) {
                        player.sendMessage(ChatColor.RED + "You're lacking permission");
                        return true;
                    }

                    player.getInventory().addItem(shiny);
                    player.sendMessage(ChatColor.GREEN+"You got a "+shiny.getItemMeta().getDisplayName());
                    return true;
                }

                player.sendMessage(ChatColor.RED + "That item does not exist!");
                return true;
                }

            else {
                player.sendMessage(ChatColor.RED + "You entered too many arguments");
                return true;
            }
        }
        return true;
    }
}
