package com.playtheatria.joinmessages;

import com.playtheatria.joinmessages.listeners.PlayerDisconnect;
import com.playtheatria.joinmessages.listeners.PlayerJoin;
import com.playtheatria.joinmessages.utils.ConfigManager;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Level;

public final class JoinMessages extends Plugin {

    private final ConfigManager config;

    public JoinMessages() {
        this.config = new ConfigManager(this);
    }

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "[#]==========< JoinMessagePlus >==========[#]");
        getLogger().log(Level.INFO, "Version: {0}", getDescription().getVersion());

        getProxy().getPluginManager().registerListener(this, new PlayerJoin(this));
        getProxy().getPluginManager().registerListener(this, new PlayerDisconnect(this));
    }

    public ConfigManager getConfig() { return config; }
}
