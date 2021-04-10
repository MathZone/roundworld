package org.catplugin.eu.Mobs;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.catplugin.eu.Main;

public class BossFight implements Listener {

    @EventHandler
    public void onBossSpawn(EntitySpawnEvent e) {
        //Bukkit.broadcastMessage("spawn event");
        if (e.getEntity() instanceof WitherSkeleton) {
            if (ChatColor.stripColor(e.getEntity().getName()).equals("Wither Skeleton BOSS")) {
                WitherSkeleton witherSkeleton = (WitherSkeleton) e.getEntity();
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("CatPlugin"), new Runnable() {
                    @Override
                    public void run() {
                        witherSkeleton.setHealth(0);
                    }
                }, 6000);


                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("CatPlugin"), new Runnable() {
                    @Override
                    public void run() {
                        if (!e.getEntity().isDead()) {
                            Location loc = e.getLocation();
                            WitherAttacker witherAttacker = new WitherAttacker(loc);
                            //WorldServer world = Main.worldServer;
                            Main.worldServer.addEntity(witherAttacker);
                            runAgain(e);

                        }
                    }
                }, 100);
            }
        }
    }

    @EventHandler
    public void runAgain(EntitySpawnEvent e){
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("CatPlugin"), new Runnable() {
            @Override
            public void run() {
                if (!e.getEntity().isDead()) {
                    Location loc = e.getLocation();
                    WitherAttacker defender = new WitherAttacker(loc);
                    //WorldServer world = Main.worldServer;
                    Main.worldServer.addEntity(defender);
                    runAgain(e);
                }
            }
        }, 100);
    }
    @EventHandler
    public void onDamage (EntityDamageByEntityEvent e){
        Player player = (Player) e.getDamager();
           if (e.getEntity() instanceof WitherSkele) {
               if (((WitherSkele) e.getEntity()).getMaxHealth() / 2 > ((WitherSkele) e.getEntity()).getHealth()) {
                   Mikid m = new Mikid();
                   m.spawn(player);
                   Bukkit.getServer().broadcastMessage("MiKid2015 has spawned at" + main.x + ", " + main.y + ", " + main.y);
                }

                Random rnd = new Random();
                if (rnd.nextInt(100) <= 25) {
                    NetheriteDefender nether = new NetheriteDefender(e.getEntity().getLocation());
                    WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
                    world.addEntity(nether);
                }
            }
        }
    }

