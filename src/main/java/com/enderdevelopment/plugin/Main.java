package com.enderdevelopment.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("MobSwapper plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("MobSwapper plugin has been disabled!");
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
            Player player = event.getPlayer();
            Entity caught = event.getCaught();

            if (caught != null) {
                Location playerLocation = player.getLocation();
                Location caughtLocation = caught.getLocation();

                // Swap positions
                player.teleport(caughtLocation);
                caught.teleport(playerLocation);

                player.sendMessage("You swapped positions with " + caught.getName() + "!");
            }
        } else if (event.getState() == PlayerFishEvent.State.IN_GROUND) {
            Player player = event.getPlayer();
            Location hookLocation = event.getHook().getLocation();

            // Teleport player to the hook location
            player.teleport(hookLocation);
            player.sendMessage("You were teleported to the fishing hook location!");
        }
    }
}