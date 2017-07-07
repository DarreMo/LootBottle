package me.DarreMo.LootBottle.Commands;

import me.DarreMo.LootBottle.Commands.LBPermissions;
import me.DarreMo.LootBottle.LootBottleM;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.ChatColor;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.stream.Collectors;


public class BottleCmd implements CommandExecutor {

    static ItemStack normal = new ItemStack(Material.DRAGONS_BREATH, 10);
    static ItemStack shiny = new ItemStack(Material.DRAGONS_BREATH, 1);

    {
        shiny.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

        ItemMeta shinym = shiny.getItemMeta();
        shinym.setDisplayName(ChatColor.GREEN+ChatColor.BOLD.toString()+"Shiny "+ChatColor.LIGHT_PURPLE+"Bottle");
        shinym.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY+"------------------");
        lore.add(ChatColor.DARK_PURPLE + "How did this bottle get so shiny?");
        lore.add(ChatColor.LIGHT_PURPLE + "Nobody will ever now");
        lore.add(ChatColor.GRAY+ChatColor.ITALIC.toString()+"Right Click to use");
        lore.add(ChatColor.GRAY+"------------------");
        shinym.setLore(lore);
        shiny.setItemMeta(shinym);
    }

    // Maak map met alle items uit de config file
    static Map<String, ItemStack> bottleMap = new HashMap<>();

    {
        for (String s : LootBottleM.getBottles().getKeys("bottles")) {
            ConfigurationSection section = LootBottleM.getConfigurationSecion("bottles."+s);
            Bukkit.getServer().getLogger().info(ChatColor.BLUE+"bottle "+s+" Loading");
            bottleMap.put(s, createItemStack(section));
            Bukkit.getServer().getLogger().info(ChatColor.BLUE+"bottle "+s+" Loaded");
        }
    }
    //    Maak item met gegevens uit config file  van 1 item
    static ItemStack createItemStack(ConfigurationSection item) {
        ItemStack itemStack = new ItemStack(Material.DRAGONS_BREATH, 1);
        itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta metadata  = itemStack.getItemMeta();
        Set<String> itemProperties = item.getKeys(false);
        String name = "";
        List<String> lore = new ArrayList<>();
        for (String property : itemProperties) {
            switch (property) {
                case "name":
                    name = item.get("name").toString();
                    break;
                case "amount":
                    itemStack.setAmount(Integer.valueOf(item.get("amount").toString()));
                    break;
                case "lore":
                    // TODO splitter
                    String[] loreArray = item.get("lore").toString().split("|");
                    lore = Arrays.asList(loreArray);
                    break;

            }
        }
        metadata.setDisplayName(ChatColor.GREEN+ChatColor.BOLD.toString()+name+" "+ChatColor.LIGHT_PURPLE+"Bottle");
        metadata.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        if (lore.isEmpty()) {
            lore.add(ChatColor.GRAY + "------------------");
            lore.add(ChatColor.DARK_PURPLE + "How did this bottle get so shiny?");
            lore.add(ChatColor.LIGHT_PURPLE + "Nobody will ever now");
            lore.add(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Right Click to use");
            lore.add(ChatColor.GRAY + "------------------");
        }
         metadata.setLore(lore);
         itemStack.setItemMeta(metadata);

        return itemStack;
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
                if (player.hasPermission(LBPermissions.NormalBottle))
                {   player.getInventory().addItem(normal);
                    player.sendMessage(ChatColor.GREEN + "You got 10 Bottle's!");
                    return true;
                }
            }else if (args.length == 1)
            {
                if (Objects.equals(args[0], "normal"))
                {
                    if (player.hasPermission(LBPermissions.NormalBottle))
                    {   player.getInventory().addItem(normal);
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

                } else if ( bottleMap.containsKey(args[0])){
                    //
                    // als argument voorkomt in de map met items een item aanmaken
                    //
                    // TODO enable permision
                    //
                        // if (!(player.hasPermission(LBPermissions.getPermission(args[0])))) {
                        //     player.sendMessage(ChatColor.RED + "You're lacking permission");
                        //     return true;
                        // }

                    ItemStack item = bottleMap.get(args[0]);
                    player.getInventory().addItem(item);
                    player.sendMessage(ChatColor.GREEN+"You got a "+item.getItemMeta().getDisplayName());
                    return true;
                }

                player.sendMessage(ChatColor.RED + "That item does not exist!");
                player.sendMessage(args[0]);
                player.sendMessage(bottleMap.keySet().stream().map(a -> a).collect(Collectors.joining(", ")));
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
