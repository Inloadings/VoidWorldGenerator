package com.inloadings.voidWorldGenerator.command;

import com.inloadings.voidWorldGenerator.util.Util;
import com.inloadings.voidWorldGenerator.util.VoidWorldGenerator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.inloadings.voidWorldGenerator.util.Util.deleteWorldFolder;

public class VoidWorldCommand implements CommandExecutor, TabExecutor {
    private final WorldCreator worldCreator;
    private Location voidLocation;
    private World world;
    private final Logger logger;
    private final Location defaultWorldLocation;
    private final String usage;


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (args.length != 1) {
            sender.sendMessage(Component.text(usage, NamedTextColor.RED));
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "tp":
                Util.checkPlayer(sender).ifPresent(player -> {
                    if (world != null) {
                        player.teleportAsync(voidLocation);
                        sender.sendMessage(Component.text("Teleported to the void world.", NamedTextColor.GREEN));
                    } else {
                        sender.sendMessage(Component.text("Void world hasn't been created yet. Use /void create.", NamedTextColor.RED));
                    }
                });
                break;

            case "setspawn":
                Util.checkPlayer(sender).ifPresent(player -> {
                    if (world != null && world.getName().equals(player.getWorld().getName())) {
                        Location location = player.getLocation();
                        world.setSpawnLocation(location);
                        voidLocation = location;
                        sender.sendMessage(Component.text("Void world spawn location updated.", NamedTextColor.GREEN));
                    } else {
                        sender.sendMessage(Component.text("You are not in the void world.", NamedTextColor.RED));
                    }
                });
                break;

            case "create":
                if (world == null) {
                    world = Bukkit.getServer().createWorld(worldCreator);
                    sender.sendMessage(Component.text("Void world created.", NamedTextColor.GREEN));
                } else {
                    sender.sendMessage(Component.text("Void world already exists.", NamedTextColor.RED));
                }
                break;

            case "reset":
                if (world == null) {
                    sender.sendMessage(Component.text("There was no world to reset.", NamedTextColor.RED));
                } else {
                    for (Player player : world.getPlayers()) {
                        player.teleport(defaultWorldLocation);
                    }
                    File worldFolder = new File(Bukkit.getWorldContainer(), world.getName());
                    Bukkit.unloadWorld(world, true);
                    if (deleteWorldFolder(worldFolder)) {
                        sender.sendMessage(Component.text("Void world deleted and recreated.", NamedTextColor.GREEN));
                        world = Bukkit.getServer().createWorld(worldCreator);
                        voidLocation = world.getSpawnLocation();
                    } else {
                        sender.sendMessage(Component.text("Failed to delete the world folder.", NamedTextColor.RED));
                        logger.log(Level.WARNING, "Failed to delete world folder: " + worldFolder.getAbsolutePath());
                    }
                }
                break;

            case "leave":
                Util.checkPlayer(sender).ifPresent(player -> {
                    if (world != null && player.getWorld().getName().equals(world.getName())) {
                        player.teleportAsync(defaultWorldLocation);
                        sender.sendMessage(Component.text("You have left the void world.", NamedTextColor.GREEN));
                    } else {
                        sender.sendMessage(Component.text("You are not in the void world.", NamedTextColor.RED));
                    }
                });
                break;

            default:
                sender.sendMessage(Component.text(usage, NamedTextColor.RED));
                break;
        }
        return true;
    }

    public VoidWorldCommand(World world, Location voidLocation, Logger logger) {
        this.logger = logger;
        this.worldCreator = new WorldCreator("void-world").generator(new VoidWorldGenerator());
        if (world != null) {
            this.world = world;
            this.voidLocation = voidLocation;
        } else {
            this.world = Bukkit.getServer().createWorld(worldCreator);
            this.voidLocation = this.world.getSpawnLocation();
        }
        this.defaultWorldLocation = Bukkit.getWorlds().getFirst().getSpawnLocation();

        this.usage = """
                Usage:
                /void tp - Teleport to the void world
                /void setspawn - Set the spawn location in the void world
                /void create - Create the void world
                /void reset - Reset the void world
                /void leave - Leave the void world""";
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if (args.length == 1) return Arrays.asList("tp", "setspawn", "create", "reset", "leave");
        return List.of();
    }
}
