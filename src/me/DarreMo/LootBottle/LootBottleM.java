package me.DarreMo.LootBottle;

import me.DarreMo.LootBottle.Commands.Commands;
import me.DarreMo.LootBottle.Commands.BottleCmd;
import me.DarreMo.LootBottle.Commands.LBConfigure;
import me.DarreMo.LootBottle.Commands.LBPermissions;
import me.DarreMo.LootBottle.ConfigManager.ConfigFile;
import me.DarreMo.LootBottle.ConfigManager.ConfigManager;
import me.DarreMo.LootBottle.EventListener.BottleListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Timon on 29-1-2017.
 */
public class LootBottleM extends JavaPlugin {


    private static ConfigFile settings;
    private static ConfigFile bottles;


    @Override
    public void onEnable() {

        settings = new ConfigFile(this, "settings");
        bottles = new ConfigFile(this, "bottles");

        Bukkit.getServer().getLogger().info(ChatColor.BLUE+"LootBottle Loaded");
        Bukkit.getPluginManager().registerEvents(new BottleListener(), this);

        Bukkit.getPluginManager().addPermission(LBPermissions.command);
        Bukkit.getPluginManager().addPermission(LBPermissions.Configure);

        Bukkit.getPluginManager().addPermission(LBPermissions.GetBottle);
        Bukkit.getPluginManager().addPermission(LBPermissions.NormalBottle);
        Bukkit.getPluginManager().addPermission(LBPermissions.ShinyBottle);

        getCommand("testcmd").setExecutor(new Commands()) ;
        getCommand("getbottle").setExecutor(new BottleCmd());
        getCommand("lbconfigure").setExecutor(new LBConfigure());
    }

    public static ConfigFile getSettings(){
        return settings;
    }
    public static ConfigFile getBottles(){
        return bottles;
    }
    public static ConfigurationSection getConfigurationSecion (String path) {
        return getBottles().getConfigurationSection(path);
    }

}