package me.HomelessSnail.CustomDrops;

import me.HomelessSnail.CustomDrops.Commands.CommandCD;
import me.HomelessSnail.CustomDrops.Commands.CommandDestroy;
import me.HomelessSnail.CustomDrops.Commands.CommandDrop;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomDropsMain extends JavaPlugin{

	  public Logger log = Logger.getLogger("Minecraft");
	    FileConfiguration config;
	    public ArrayList<String> glassToggle = new ArrayList<String>();
	    public ArrayList<String> paneToggle = new ArrayList<String>();
	    public ArrayList<String> gsToggle = new ArrayList<String>();
	    public List<World> allowedWorlds = new ArrayList<World>();
	    public List<Material> allowedMaterials = new ArrayList<>();
	    public CustomDropsListener listener;
	    public CommandDrop dropCommand;
	    public CommandDestroy destroyCommand;
	    public CommandCD cdCommand;
	    public CustomDropsMethods methods;
	    public File configDir;
	    public File configFile;
	    public int damChance;
	    public int damAmount;
	    public int dropChance;
	    public boolean glassPanes;
	    public boolean glassConfig;
	    public boolean debug;
	    public int glowstoneConfig;

	    public void onEnable() {
	        this.listener = new CustomDropsListener(this);
	        this.dropCommand = new CommandDrop(this);
	        this.destroyCommand = new CommandDestroy(this);
	        this.cdCommand = new CommandCD(this);
	        this.methods = new CustomDropsMethods(this);
	        try {
	            this.config = this.getConfig();
	        }
	        catch (Exception e) {
	            this.log.severe("[Custom Drops] Unale to load config file! Show Dev stack trace");
	            e.printStackTrace();
	        }
	        FileConfiguration config = this.getConfig();
	        this.configDir = this.getDataFolder();
	        this.configFile = new File(this.configDir, "config.yml");
	        this.methods.configCheck();
	        this.methods.loadConfig();
	        config.options().copyDefaults(true);
	        this.saveConfig();
	        this.getCommand("drop").setExecutor((CommandExecutor)this.dropCommand);
	        this.getCommand("destroy").setExecutor((CommandExecutor)this.destroyCommand);
	        this.getCommand("cd").setExecutor((CommandExecutor)this.cdCommand);
	        Bukkit.getPluginManager().registerEvents((Listener)this.listener, (Plugin)this);
	        PluginDescriptionFile pdf = this.getDescription();
	        this.log.info(String.valueOf(pdf.getName()) + " has been enabled with version " + pdf.getVersion());
	    }

	    public void onDisable() {
	        PluginDescriptionFile pdf = this.getDescription();
	        this.log.info(String.valueOf(pdf.getName()) + " has been disabled");
	    }
	
	
}
