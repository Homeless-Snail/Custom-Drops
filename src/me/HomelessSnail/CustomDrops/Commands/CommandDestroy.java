package me.HomelessSnail.CustomDrops.Commands;

import me.HomelessSnail.CustomDrops.CustomDropsMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDestroy implements CommandExecutor {
    
	public CustomDropsMain plugin;

    public CommandDestroy(CustomDropsMain instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] arg) {
        Player player = (Player)sender;
        String PlayerName = player.getName();
        if (cmd.getName().equalsIgnoreCase("destroy")) {
            if (arg.length == 1) {
                if (arg[0].equalsIgnoreCase("glass")) {
                    if (sender.hasPermission("cutomdrops.toggle.*") || sender.hasPermission("customdrops.toggle.glass") || sender.hasPermission("customdrops.*") || sender.isOp()) {
                        if (!this.plugin.glassToggle.contains(PlayerName)) {
                            this.plugin.glassToggle.add(PlayerName);
                            sender.sendMessage((Object)ChatColor.RED + "Glass will now be destroyed");
                            return true;
                        }
                        if (this.plugin.glassToggle.contains(PlayerName)) {
                            sender.sendMessage((Object)ChatColor.RED + "Glass is already being destroyed!");
                            return true;
                        }
                    }
                } else if (arg[0].equalsIgnoreCase("glasspanes")) {
                    if (sender.hasPermission("customdrops.toggle.*") || sender.hasPermission("customdrops.toggle.glasspanes") || sender.hasPermission("customdrops.*") || sender.isOp()) {
                        if (!this.plugin.paneToggle.contains(PlayerName)) {
                            this.plugin.paneToggle.add(PlayerName);
                            sender.sendMessage((Object)ChatColor.RED + "Glass Panes will now be destroyed");
                            return true;
                        }
                        if (this.plugin.paneToggle.contains(PlayerName)) {
                            sender.sendMessage((Object)ChatColor.RED + "Glass Panes are alrady being destryoed!");
                            return true;
                        }
                    }
                } else if (arg[0].equalsIgnoreCase("glowstone")) {
                    if (sender.hasPermission("customdrops.toggle.*") || sender.hasPermission("customdrops.toggle.glowstone") || sender.hasPermission("customdrops.*") || sender.isOp()) {
                        if (!this.plugin.gsToggle.contains(PlayerName)) {
                            this.plugin.gsToggle.add(PlayerName);
                            sender.sendMessage((Object)ChatColor.RED + "Glowstone Dust will have will now be dropped at random");
                            return true;
                        }
                        if (this.plugin.gsToggle.contains(PlayerName)) {
                            sender.sendMessage((Object)ChatColor.RED + "Glowstone Dust is already being dropped randomly");
                            return true;
                        }
                    }
                    return true;
                }
            } else {
                sender.sendMessage((Object)ChatColor.RED + "[CustomDrops] Invalid command! Use /cd help for more information");
                return true;
            }
        }
        return false;
    }
}

