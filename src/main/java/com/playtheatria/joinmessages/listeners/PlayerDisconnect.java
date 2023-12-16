package com.playtheatria.joinmessages.listeners;

import com.playtheatria.joinmessages.JoinMessages;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnect implements Listener {

    private JoinMessages plugin;

    public PlayerDisconnect(JoinMessages plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLeave(PlayerDisconnectEvent e) {
        if (e.getPlayer().hasPermission("joinmessageplus.silent")) {
            return;
        }
        if (plugin.getConfig().isQuitMessageEnabled()) {
            plugin.getProxy().broadcast(new TextComponent(plugin.getConfig().getQuitMessage().replace("%player_name%", e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName())));
        }
    }
}