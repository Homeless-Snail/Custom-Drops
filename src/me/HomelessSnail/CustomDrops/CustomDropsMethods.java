package me.HomelessSnail.CustomDrops;

import org.bukkit.Bukkit;

public class CustomDropsMethods {
    public CustomDropsMain plugin;

    public CustomDropsMethods(CustomDropsMain instance) {
        this.plugin = instance;
    }

    public void configCheck() {
        if (!this.plugin.configDir.exists()) {
            try {
                this.plugin.configDir.mkdir();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!this.plugin.configFile.exists()) {
            try {
                this.plugin.configFile.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadConfig() {
        this.plugin.damChance = this.plugin.config.getInt("Glass-Damage.Damage-Chance");
        this.plugin.damAmount = this.plugin.config.getInt("Glass-Damage.Damage-Amount");
        this.plugin.dropChance = this.plugin.config.getInt("Glass-Drops.Drop-Chance");
        this.plugin.glassConfig = this.plugin.config.getBoolean("Blocks-Dropped.Glass");
        this.plugin.glassPanes = this.plugin.config.getBoolean("Blocks-Dropped.Glass-Panes");
        this.plugin.debug = this.plugin.config.getBoolean("Debugging.Debug");
        this.plugin.glowstoneConfig = this.plugin.config.getInt("Blocks-Dropped.Glowstone");
        for (String worlds : this.plugin.getConfig().getStringList("Worlds")) {
            this.plugin.allowedWorlds.add(Bukkit.getWorld((String)worlds));
        }
    }

    public void reloadCommand() {
    }
}

