package me.DarreMo.LootBottle.EventListener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Timon on 29-1-2017.
 */
public class EventListener implements Listener {

    @EventHandler
    public void onJoin (PlayerJoinEvent e) {
         Player player = e.getPlayer();
         player.sendMessage(ChatColor.AQUA + "Welcome to the server, " + ChatColor.DARK_AQUA+ player.getName() );
    }
}
