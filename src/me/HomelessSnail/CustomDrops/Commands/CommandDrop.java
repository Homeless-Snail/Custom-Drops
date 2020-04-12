package me.HomelessSnail.CustomDrops.Commands;

import me.HomelessSnail.CustomDrops.CustomDropsMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDrop extends CustomDropsMain implements CommandExecutor  {
	public CustomDropsMain plugin;

    public CommandDrop(CustomDropsMain instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] arg) {
        Player player = (Player)sender;
        String PlayerName = player.getName();
        if (cmd.getName().equalsIgnoreCase("drop")) {
            if (arg.length == 1) {
                if (arg[0].equalsIgnoreCase("glass")) {
                    if (sender.hasPermission("customdrops.toggle.*") || sender.hasPermission("customdrops.toggle.glass") || sender.hasPermission("customdrops.*") || sender.isOp()) {
                        if (glassToggle.contains(PlayerName)) {
                            glassToggle.remove(PlayerName);
                            sender.sendMessage((Object)ChatColor.GREEN + "Glass will now be dropped");
                            return true;
                        }
                        if (!glassToggle.contains(PlayerName)) {
                            sender.sendMessage((Object)ChatColor.GREEN + "Glass is already being dropped!");
                            return true;
                        }
                    }
                } else if (arg[0].equalsIgnoreCase("glasspanes")) {
                    if (sender.hasPermission("customdrops.toggle.*") || sender.hasPermission("customdrops.toggle.glasspanes") || sender.hasPermission("customdrops.*") || sender.isOp()) {
                        if (paneToggle.contains(PlayerName)) {
                            paneToggle.remove(PlayerName);
                            sender.sendMessage((Object)ChatColor.GREEN + "Glass Panes will now be dropped");
                            return true;
                        }
                        if (!paneToggle.contains(PlayerName)) {
                            sender.sendMessage((Object)ChatColor.GREEN + "Glass Panes are already being dropped!");
                            return true;
                        }
                    }
                } else if (arg[0].equalsIgnoreCase("glowstone") && (sender.hasPermission("customdrops.toggle.*") || sender.hasPermission("customdrops.toggle.glowstone") || sender.hasPermission("customdrops.*") || sender.isOp())) {
                    if (gsToggle.contains(PlayerName)) {
                        gsToggle.remove(PlayerName);
                        sender.sendMessage((Object)ChatColor.GREEN + "Glowstone Dust will now drop " + glowstoneConfig + " on each break");
                        return true;
                    }
                    if (!gsToggle.contains(PlayerName)) {
                        sender.sendMessage((Object)ChatColor.GREEN + "Glowstone Dust is already being dropped at " + glowstoneConfig + " dust per break");
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

