package com.inloadings.voidWorldGenerator;

import com.destroystokyo.paper.utils.PaperPluginLogger;
import com.inloadings.voidWorldGenerator.command.VoidTabCompleter;
import com.inloadings.voidWorldGenerator.command.VoidWorldCommand;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidWorld extends JavaPlugin {
    Location location;
    World world;

    @Override
    public void onEnable() {
        world = Bukkit.getServer().getWorld("void-world");
        if (world != null) location = new Location(world, 0, 50 ,0);
        Bukkit.getServer().getPluginCommand("void").setExecutor(new VoidWorldCommand(world, location, getLogger()));
        Bukkit.getServer().getPluginCommand("void").setTabCompleter(new VoidTabCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
