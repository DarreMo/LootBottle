package me.DarreMo.LootBottle.Commands;

import org.bukkit.permissions.Permission;

import java.util.HashMap;
import java.util.Map;

public class LBPermissions extends Permission {

    public static Permission command = new Permission("LootBottle.command") ;

    public static Permission GetBottle = new Permission("LootBottle.BottleCmd");
    public static Permission NormalBottle = new Permission("LootBottle.BottleCmd.normal");
    public static Permission ShinyBottle = new Permission("LootBottle.BottleCmd.shiny");
    public static Map<String, Permission> bottlePermissions = new HashMap<>();
    // public static Perm ConfigBottle = new Perm (" LootBottle.BottleCmd.bottle1));

    public static Permission Configure = new Permission("LootBottle.Admin.Configure");

    public static void addPermission(String bottleName){
        bottlePermissions.put(bottleName,new Permission("LootBottle.BottleCmd."+bottleName));
    }
    public static Permission getPermission(String bottleName){
        return bottlePermissions.get(bottleName);
    }

    public LBPermissions(String name) {
        super(name);
    }
}
