package me.HomelessSnail.CustomDrops;

import me.HomelessSnail.CustomDropsCommands.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
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
	    public CustomDropsListener listener;
	    public CustomDropsCommandDrop dropCommand;
	    public CustomDropsCommandDestroy destroyCommand;
	    public CustomDropsCommandCD cdCommand;
	    public CustomDropsMethods methods;
	    protected File configDir;
	    protected File configFile;
	    protected int damChance;
	    protected int damAmount;
	    protected int dropChance;
	    protected boolean glassPanes;
	    protected boolean glassConfig;
	    protected boolean debug;
	    public int glowstoneConfig;

	    public void onEnable() {
	        this.listener = new CustomDropsListener(this);
	        this.dropCommand = new CustomDropsCommandDrop(this);
	        this.destroyCommand = new CustomDropsCommandDestroy(this);
	        this.cdCommand = new CustomDropsCommandCD(this);
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
