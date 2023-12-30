package com.playtheatria.joinmessages.listeners;

import com.playtheatria.joinmessages.JoinMessages;
import com.playtheatria.joinmessages.utils.Debugger;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class PlayerDisconnect implements Listener {

    private final JoinMessages plugin;

    private final Debugger debugger;

    public PlayerDisconnect(JoinMessages plugin, Debugger debugger) {
        this.plugin = plugin;
        this.debugger = debugger;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerDisconnectEvent event) {
        debugger.log("PlayerDisconnectEvent called for " + event.getPlayer().getName());
        // here we will check the players uuid in the event to see if it matches the concurrent hashmap we created at startup.
        // if it does, then that means the player was not kicked, banned, or immediately disconnected. we can then proceed further with the quit message
        if (event.getPlayer().hasPermission("join-messages.silent")) {
            debugger.log("Removing " + event.getPlayer().getName() + " from disconnect notify list");
            plugin.getPlayerDisconnectNotify().remove(event.getPlayer().getUniqueId());
            return;
        }

        if (plugin.getConfigManager().isQuitMessageEnabled() && plugin.getPlayerDisconnectNotify().contains(event.getPlayer().getUniqueId())) {
            plugin.getProxy().broadcast(
                    new TextComponent(plugin.getConfigManager().getQuitMessage().replace("%player_name%",
                            event.getPlayer().getName()).replace("%player_displayname%", event.getPlayer().getDisplayName()))
            );
        }
        debugger.log("Removing " + event.getPlayer().getName() + " from disconnect notify list");
        plugin.getPlayerDisconnectNotify().remove(event.getPlayer().getUniqueId());
    }
}