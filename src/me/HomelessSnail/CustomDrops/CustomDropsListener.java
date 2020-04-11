package me.HomelessSnail.CustomDrops;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.ryanhamshire.GriefPrevention.GriefPrevention;

public class CustomDropsListener implements Listener {
    protected int damChance;
    protected int damAmount;
    protected int dropChance;
    protected int gsDropChance;
    protected boolean glassPanes;
    protected boolean glassConfig;
    protected boolean debug;
    protected boolean gpHook;
    int glowstoneConfig;
    Random chance = new Random();
    public static CustomDropsMain plugin;

    public CustomDropsListener(CustomDropsMain instance) {
        plugin = instance;
        FileConfiguration config = plugin.getConfig();
        this.damChance = config.getInt("Glass-Damage.Damage-Chance");
        this.damAmount = config.getInt("Glass-Damage.Damage-Amount");
        this.dropChance = config.getInt("Glass-Drops.Drop-Chance");
        this.gsDropChance = config.getInt("Glowstone-Drops.Drop-Chance");
        this.glassConfig = config.getBoolean("Blocks-Dropped.Glass");
        this.glassPanes = config.getBoolean("Blocks-Dropped.Glass-Panes");
        this.debug = config.getBoolean("Debugging.Debug");
        this.glowstoneConfig = config.getInt("Blocks-Dropped.GlowstoneDust");
        this.gpHook = config.getBoolean("Hooks.GriefPrevention");
    }

    @EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        String playerName = player.getName();
        World world = event.getPlayer().getWorld();
        if (CustomDropsListener.plugin.allowedWorlds.contains((Object)world)) {
            if (block.getType() == Material.GLASS) {
                if (this.gpHook) {
                    String canBreak = GriefPrevention.instance.allowBreak(player, block, block.getLocation());
                    if (canBreak != null) {
                        if (this.glassConfig) {
                            int damage = 1 + this.chance.nextInt(100);
                            int drop = 1 + this.chance.nextInt(100);
                            if (damage <= this.damChance) {
                                player.damage(this.damAmount);
                            }
                            if (!CustomDropsListener.plugin.glassToggle.contains(playerName)) {
                                if (drop <= this.dropChance) {
                                    block.setType(Material.AIR);
                                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLASS, 1));
                                    event.setCancelled(true);
                                }
                                if (this.debug) {
                                    CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glass Block was broken at " + (Object)block.getLocation());
                                }
                            }
                        }
                    } else {
                        return;
                    }
                }
                if (this.glassConfig) {
                    int damage = 1 + this.chance.nextInt(100);
                    int drop = 1 + this.chance.nextInt(100);
                    if (damage <= this.damChance) {
                        player.damage(this.damAmount);
                    }
                    if (!CustomDropsListener.plugin.glassToggle.contains(playerName)) {
                        if (drop <= this.dropChance) {
                            block.setType(Material.AIR);
                            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLASS, 1));
                            event.setCancelled(true);
                        }
                        if (this.debug) {
                            CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glass Block was broken at " + (Object)block.getLocation());
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
    public void BlockBreak(BlockBreakEvent ev) {
        Block block = ev.getBlock();
        Player player = ev.getPlayer();
        String playerName = player.getName();
        World world = player.getWorld();
        if (!CustomDropsListener.plugin.allowedWorlds.contains((Object)world) || block.getType() != Material.GLASS_PANE) return;
        if (this.gpHook) {
            String canBreak = GriefPrevention.instance.allowBreak(player, block, block.getLocation());
            if (canBreak == null) return;
            if (!this.glassPanes) return;
            int damage = 1 + this.chance.nextInt(100);
            int drop = 1 + this.chance.nextInt(100);
            if (damage <= this.damChance) {
                player.damage(this.damAmount);
            }
            if (CustomDropsListener.plugin.paneToggle.contains(playerName)) return;
            if (drop <= this.dropChance) {
                block.setType(Material.AIR);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLASS_PANE, 1));
                ev.setCancelled(true);
            }
            if (!this.debug) return;
            CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glass Pane Block was broken at " + (Object)block.getLocation());
            return;
        } else {
            if (!this.glassPanes) return;
            int damage = 1 + this.chance.nextInt(100);
            int drop = 1 + this.chance.nextInt(100);
            if (damage <= this.damChance) {
                player.damage(this.damAmount);
            }
            if (CustomDropsListener.plugin.paneToggle.contains(playerName)) return;
            if (drop <= this.dropChance) {
                block.setType(Material.AIR);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLASS_PANE, 1));
                ev.setCancelled(true);
            }
            if (!this.debug) return;
            CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glass Pane Block was broken at " + (Object)block.getLocation());
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
    public void gsBlockBreak(BlockBreakEvent ev) {
        Block block = ev.getBlock();
        Player player = ev.getPlayer();
        String playerName = player.getName();
        World world = player.getWorld();
        if (!CustomDropsListener.plugin.allowedWorlds.contains((Object)world) || block.getType() != Material.GLOWSTONE) return;
        if (this.gpHook) {
            String canBreak = GriefPrevention.instance.allowBreak(player, block, block.getLocation());
            if (canBreak == null) return;
            if (this.glowstoneConfig == 1) {
                int drop = 1 + this.chance.nextInt(100);
                if (CustomDropsListener.plugin.gsToggle.contains(playerName)) return;
                if (drop <= this.gsDropChance) {
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE_DUST, 1));
                    ev.setCancelled(true);
                }
                if (!this.debug) return;
                CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glowstone Block was broken at " + (Object)block.getLocation());
                return;
            } else if (this.glowstoneConfig == 2) {
                int drop = 1 + this.chance.nextInt(100);
                if (CustomDropsListener.plugin.gsToggle.contains(playerName)) return;
                if (drop <= this.gsDropChance) {
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE_DUST, 2));
                }
                if (!this.debug) return;
                CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glowstone Block was broken at " + (Object)block.getLocation());
                return;
            } else if (this.glowstoneConfig == 3) {
                int drop = 1 + this.chance.nextInt(100);
                if (CustomDropsListener.plugin.gsToggle.contains(playerName)) return;
                if (drop <= this.gsDropChance) {
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE_DUST, 3));
                    ev.setCancelled(true);
                }
                if (!this.debug) return;
                CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glowstone Block was broken at " + (Object)block.getLocation());
                return;
            } else if (this.glowstoneConfig == 4) {
                int drop = 1 + this.chance.nextInt(100);
                if (CustomDropsListener.plugin.gsToggle.contains(playerName)) return;
                if (drop <= this.gsDropChance) {
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE_DUST, 4));
                    ev.setCancelled(true);
                }
                if (!this.debug) return;
                CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glowstone Block was broken at " + (Object)block.getLocation());
                return;
            } else {
                if (this.glowstoneConfig != 0) return;
                Random ranDrop = new Random();
                int gsRanDrop = ranDrop.nextInt(4);
                int drop = 1 + this.chance.nextInt(100);
                if (CustomDropsListener.plugin.gsToggle.contains(playerName)) return;
                if (drop <= this.gsDropChance) {
                    block.setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE_DUST, gsRanDrop));
                }
                if (!this.debug) return;
                CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glowstone Block was broken at " + (Object)block.getLocation());
            }
            return;
        } else if (this.glowstoneConfig == 1) {
            int drop = 1 + this.chance.nextInt(100);
            if (CustomDropsListener.plugin.gsToggle.contains(playerName)) return;
            if (drop <= this.gsDropChance) {
                block.setType(Material.AIR);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE_DUST, 1));
                ev.setCancelled(true);
            }
            if (!this.debug) return;
            CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glowstone Block was broken at " + (Object)block.getLocation());
            return;
        } else if (this.glowstoneConfig == 2) {
            int drop = 1 + this.chance.nextInt(100);
            if (CustomDropsListener.plugin.gsToggle.contains(playerName)) return;
            if (drop <= this.gsDropChance) {
                block.setType(Material.AIR);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE_DUST, 2));
            }
            if (!this.debug) return;
            CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glowstone Block was broken at " + (Object)block.getLocation());
            return;
        } else if (this.glowstoneConfig == 3) {
            int drop = 1 + this.chance.nextInt(100);
            if (CustomDropsListener.plugin.gsToggle.contains(playerName)) return;
            if (drop <= this.gsDropChance) {
                block.setType(Material.AIR);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE_DUST, 3));
                ev.setCancelled(true);
            }
            if (!this.debug) return;
            CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glowstone Block was broken at " + (Object)block.getLocation());
            return;
        } else if (this.glowstoneConfig == 4) {
            int drop = 1 + this.chance.nextInt(100);
            if (CustomDropsListener.plugin.gsToggle.contains(playerName)) return;
            if (drop <= this.gsDropChance) {
                block.setType(Material.AIR);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE_DUST, 4));
                ev.setCancelled(true);
            }
            if (!this.debug) return;
            CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glowstone Block was broken at " + (Object)block.getLocation());
            return;
        } else if (this.glowstoneConfig == 0) {
            Random ranDrop = new Random();
            int gsRanDrop = ranDrop.nextInt(4);
            int drop = 1 + this.chance.nextInt(100);
            if (CustomDropsListener.plugin.gsToggle.contains(playerName)) return;
            if (drop <= this.gsDropChance) {
                block.setType(Material.AIR);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE_DUST, gsRanDrop));
            }
            if (!this.debug) return;
            CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glowstone Block was broken at " + (Object)block.getLocation());
            return;
        } else {
            if (this.glowstoneConfig != 5) return;
            int drop = 1 + this.chance.nextInt(100);
            if (CustomDropsListener.plugin.gsToggle.contains(playerName)) return;
            if (drop <= this.gsDropChance) {
                block.setType(Material.AIR);
                block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GLOWSTONE, 1));
                ev.setCancelled(true);
            }
            if (!this.debug) return;
            CustomDropsListener.plugin.log.info("[CustomDrops Debug] A Glowstone Block was broken at " + (Object)block.getLocation());
        }
    }
}

