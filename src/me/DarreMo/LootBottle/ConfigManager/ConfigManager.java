package me.DarreMo.LootBottle.ConfigManager;

public class ConfigManager {

    private ConfigManager() {
    }

    private static ConfigManager instance = new ConfigManager();

    public static ConfigManager getInstance() {
        return instance;
    }


}