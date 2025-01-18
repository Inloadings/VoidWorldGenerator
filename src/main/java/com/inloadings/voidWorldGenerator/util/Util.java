package com.inloadings.voidWorldGenerator.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

public class Util {
    public static Optional<Player> checkPlayer(CommandSender sender) {
        if (sender instanceof Player player) {
            return Optional.of(player);
        } else {
            if (sender != null) {
                sender.sendMessage("You cannot execute this command as console!");
            }
            return Optional.empty();
        }
    }

    public static boolean deleteWorldFolder(File folder) {
        if (folder.isDirectory()) {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                deleteWorldFolder(file);
            }
        }
        return folder.delete();
    }
}
