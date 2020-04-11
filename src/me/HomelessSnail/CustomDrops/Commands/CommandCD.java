package me.HomelessSnail.CustomDrops.Commands;

import me.HomelessSnail.CustomDrops.CustomDropsMain;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class CommandCD implements CommandExecutor {
	public CustomDropsMain plugin;

    public CommandCD(CustomDropsMain instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] arg) {
        if (cmd.getName().equalsIgnoreCase("cd")) {
            if (arg.length == 1) {
                if (arg[0].equalsIgnoreCase("help") && (sender.hasPermission("customdrops.help") || sender.hasPermission("customdrops.*") || sender.isOp())) {
                    sender.sendMessage((Object)ChatColor.GOLD + "Custom Drops Help - Page 1");
                    sender.sendMessage((Object)ChatColor.BLUE + "/drop <Block>" + (Object)ChatColor.YELLOW + " - Drops Glass, Glasspanes, Glowstone");
                    sender.sendMessage((Object)ChatColor.BLUE + "/destroy <Block>" + (Object)ChatColor.YELLOW + " - Destroys Glass, Glasspanes, Glowstone");
                    sender.sendMessage((Object)ChatColor.BLUE + "/cd help" + (Object)ChatColor.YELLOW + " - Displays the help menu");
                }
            } else {
                sender.sendMessage((Object)ChatColor.RED + "[CustomDrops] Invalid command! Use /cd help for more information");
                return true;
            }
        }
        return true;
    }
}

