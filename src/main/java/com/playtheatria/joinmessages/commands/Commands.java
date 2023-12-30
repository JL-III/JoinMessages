package com.playtheatria.joinmessages.commands;

import com.playtheatria.joinmessages.utils.ConfigManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class Commands extends Command {

    private final ConfigManager configManager;

    public Commands(String name, ConfigManager configManager) {
        super(name);
        this.configManager = configManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                configManager.reloadConfig();
                sender.sendMessage("§aJoinMessages config reloaded.");
            }
        }
    }
}
