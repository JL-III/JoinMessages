package com.playtheatria.joinmessages.utils;

import com.playtheatria.joinmessages.JoinMessages;

public class Debugger {

    private final JoinMessages plugin;

    private final ConfigManager configManager;

    public Debugger(JoinMessages plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
    }

    public void log(String message) {
        if (configManager.isDebug()) {
            plugin.getLogger().info(message);
        }
    }
}
