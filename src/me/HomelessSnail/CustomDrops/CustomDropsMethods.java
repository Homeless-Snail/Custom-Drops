package me.HomelessSnail.CustomDrops;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;

public class CustomDropsMethods extends CustomDropsMain{
	public CustomDropsMain plugin;

	public CustomDropsMethods(CustomDropsMain instance) {
		this.plugin = instance;
	}

	public void configCheck() {
		if (!configDir.exists()) {
			try {
				configDir.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void loadConfig() {
		damChance = config.getInt("Glass-Damage.Damage-Chance");
		damAmount = config.getInt("Glass-Damage.Damage-Amount");
		dropChance = config.getInt("Glass-Drops.Drop-Chance");
		glassConfig = config.getBoolean("Blocks-Dropped.Glass");
		glassPanes = config.getBoolean("Blocks-Dropped.Glass-Panes");
		debug = config.getBoolean("Debugging.Debug");
		glowstoneConfig = config.getInt("Blocks-Dropped.Glowstone");
		for (String worlds : getConfig().getStringList("Worlds")) {
			allowedWorlds.add(Bukkit.getWorld((String) worlds));
		}
		for (String material : getConfig().getStringList("Materials")) {
			allowedMaterials.add(Material.getMaterial(material));
		}

	}

	public void reloadCommand() {
	}

	public void blockCheck(BlockBreakEvent event) {
		Block b = event.getBlock();
		ArrayList<Material> bArray = new ArrayList<Material>();
		bArray.add(Material.GLASS_PANE);

		if (bArray.contains(b.getType())) {
			b.setType(Material.AIR);
			b.breakNaturally();
		}
	}
}
