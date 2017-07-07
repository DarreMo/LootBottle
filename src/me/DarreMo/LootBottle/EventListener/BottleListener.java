package me.DarreMo.LootBottle.EventListener;

import me.DarreMo.LootBottle.ConfigManager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class BottleListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            Player player = e.getPlayer();
            if (player.getInventory().getItemInMainHand().getType() == Material.DRAGONS_BREATH) {

                Inventory inv = Bukkit.createInventory(null, 9, "Bottle Opening: ");
                player.openInventory(inv);

                //TODO generate loot

            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (e.getInventory().getName().equalsIgnoreCase("Bottle select")){
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.DRAGONS_BREATH){



            }
        }
    }
}
