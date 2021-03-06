package me.DarreMo.LootBottle.Commands;

import org.bukkit.permissions.Permission;

public class LBPermissions extends Permission {

    public static Permission command = new Permission("LootBottle.command") ;

    public static Permission GetBottle = new Permission("LootBottle.BottleCmd");
    public static Permission NormalBottle = new Permission("LootBottle.BottleCmd.normal");
    public static Permission ShinyBottle = new Permission("LootBottle.BottleCmd.shiny");

    public static Permission Configure = new Permission("LootBottle.Admin.Configure");

    public LBPermissions(String name) {
        super(name);
    }
}
