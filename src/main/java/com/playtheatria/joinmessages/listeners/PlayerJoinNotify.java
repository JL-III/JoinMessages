package com.playtheatria.joinmessages.listeners;

import com.playtheatria.joinmessages.JoinMessages;
import com.playtheatria.joinmessages.events.PlayerJoinNotifyEvent;
import com.playtheatria.joinmessages.utils.Debugger;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerJoinNotify implements Listener {

    private final JoinMessages plugin;

    private final Debugger debugger;

    public PlayerJoinNotify(JoinMessages plugin, Debugger debugger) {
        this.plugin = plugin;
        this.debugger = debugger;
    }

    @EventHandler
    public void onJoinNotify(PlayerJoinNotifyEvent event) {
        // by the time this event is called, the player has been verified to have been allowed on the server
        // we will then add them to the disconnect notify list
        // the disconnect will verify that they have the correct permission to be notified, so that this class does not have to worry about that
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(event.getPlayerUUID());

        debugger.log("Adding " + player.getName() + " to disconnect notify list");
        plugin.getPlayerDisconnectNotify().add(player.getUniqueId());

        if (player.hasPermission("join-messages.silent")) {
            return;
        }
        if (plugin.getConfigManager().isJoinMessageEnabled()) {
            plugin.getProxy().broadcast(
                    new TextComponent(plugin.getConfigManager().getJoinMessage().replace("%player_name%",
                            player.getName()).replace("%player_displayname%", player.getDisplayName()))
            );
        }
    }
}
