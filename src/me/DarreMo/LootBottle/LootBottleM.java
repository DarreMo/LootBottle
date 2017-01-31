package me.DarreMo.LootBottle;

import me.DarreMo.LootBottle.Commands.Commands;
import me.DarreMo.LootBottle.Commands.GetBottle.GetBottle;
import me.DarreMo.LootBottle.EventListener.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Timon on 29-1-2017.
 */
public class LootBottleM extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getServer().getLogger().info("LootBottle Loaded");
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);

        Bukkit.getPluginManager().addPermission(Commands.command);
        Bukkit.getPluginManager().addPermission(GetBottle.GetBottle);
        Bukkit.getPluginManager().addPermission(GetBottle.NormalBottle);
        Bukkit.getPluginManager().addPermission(GetBottle.ShinyBottle);

        getCommand("testcmd").setExecutor(new Commands()) ;
        getCommand("getbottle").setExecutor(new GetBottle());
    }


}