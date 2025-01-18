package com.inloadings.voidWorldGenerator.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoidTabCompleter implements TabCompleter {
    private static final String[] COMMANDS = {"tp", "setspawn", "create", "reset", "leave"};

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            final List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], Arrays.asList(COMMANDS), completions);
            completions.sort(String.CASE_INSENSITIVE_ORDER);
            return completions;
        }

        return new ArrayList<>();
    }
}
