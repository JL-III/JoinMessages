package com.playtheatria.joinmessages;

import com.playtheatria.joinmessages.commands.Commands;
import com.playtheatria.joinmessages.listeners.PlayerDisconnect;
import com.playtheatria.joinmessages.listeners.PlayerJoin;
import com.playtheatria.joinmessages.listeners.PlayerJoinNotify;
import com.playtheatria.joinmessages.utils.ConfigManager;
import com.playtheatria.joinmessages.utils.Debugger;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;

public final class JoinMessages extends Plugin {

    private final ConfigManager configManager;

    private final CopyOnWriteArrayList<UUID> player_disconnect_notify = new CopyOnWriteArrayList<>();

    public JoinMessages() {
        this.configManager = new ConfigManager(this);
    }

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "[#]==========< JoinMessages >==========[#]");
        getLogger().log(Level.INFO, "Version: {0}", getDescription().getVersion());
        Debugger debugger = new Debugger(this, configManager);
        getProxy().getPluginManager().registerCommand(this, new Commands("joinmessages", configManager));

        getProxy().getPluginManager().registerListener(this, new PlayerJoin(this, debugger));
        getProxy().getPluginManager().registerListener(this, new PlayerJoinNotify(this, debugger));
        getProxy().getPluginManager().registerListener(this, new PlayerDisconnect(this, debugger));
    }

    public ConfigManager getConfigManager() { return configManager; }

    public CopyOnWriteArrayList<UUID> getPlayerDisconnectNotify() { return player_disconnect_notify; }
}
