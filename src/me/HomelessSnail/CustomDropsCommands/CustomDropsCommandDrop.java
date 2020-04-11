package me.HomelessSnail.CustomDropsCommands;

import me.HomelessSnail.CustomDrops.CustomDropsMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomDropsCommandDrop implements CommandExecutor {
	public CustomDropsMain plugin;

    public CustomDropsCommandDrop(CustomDropsMain instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] arg) {
        Player player = (Player)sender;
        String PlayerName = player.getName();
        if (cmd.getName().equalsIgnoreCase("drop")) {
            if (arg.length == 1) {
                if (arg[0].equalsIgnoreCase("glass")) {
                    if (sender.hasPermission("customdrops.toggle.*") || sender.hasPermission("customdrops.toggle.glass") || sender.hasPermission("customdrops.*") || sender.isOp()) {
                        if (this.plugin.glassToggle.contains(PlayerName)) {
                            this.plugin.glassToggle.remove(PlayerName);
                            sender.sendMessage((Object)ChatColor.GREEN + "Glass will now be dropped");
                            return true;
                        }
                        if (!this.plugin.glassToggle.contains(PlayerName)) {
                            sender.sendMessage((Object)ChatColor.GREEN + "Glass is already being dropped!");
                            return true;
                        }
                    }
                } else if (arg[0].equalsIgnoreCase("glasspanes")) {
                    if (sender.hasPermission("customdrops.toggle.*") || sender.hasPermission("customdrops.toggle.glasspanes") || sender.hasPermission("customdrops.*") || sender.isOp()) {
                        if (this.plugin.paneToggle.contains(PlayerName)) {
                            this.plugin.paneToggle.remove(PlayerName);
                            sender.sendMessage((Object)ChatColor.GREEN + "Glass Panes will now be dropped");
                            return true;
                        }
                        if (!this.plugin.paneToggle.contains(PlayerName)) {
                            sender.sendMessage((Object)ChatColor.GREEN + "Glass Panes are already being dropped!");
                            return true;
                        }
                    }
                } else if (arg[0].equalsIgnoreCase("glowstone") && (sender.hasPermission("customdrops.toggle.*") || sender.hasPermission("customdrops.toggle.glowstone") || sender.hasPermission("customdrops.*") || sender.isOp())) {
                    if (this.plugin.gsToggle.contains(PlayerName)) {
                        this.plugin.gsToggle.remove(PlayerName);
                        sender.sendMessage((Object)ChatColor.GREEN + "Glowstone Dust will now drop " + this.plugin.glowstoneConfig + " on each break");
                        return true;
                    }
                    if (!this.plugin.gsToggle.contains(PlayerName)) {
                        sender.sendMessage((Object)ChatColor.GREEN + "Glowstone Dust is already being dropped at " + this.plugin.glowstoneConfig + " dust per break");
                        return true;
                    }
                }
                return true;
            }
            sender.sendMessage((Object)ChatColor.RED + "[CustomDrops] Invalid command! Use /cd help for more information");
            return true;
        }
        return true;
    }
}

