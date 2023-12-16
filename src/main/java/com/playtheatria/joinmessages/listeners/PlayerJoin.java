package com.playtheatria.joinmessages.listeners;


import com.playtheatria.joinmessages.JoinMessages;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class PlayerJoin implements Listener {

    private final JoinMessages plugin;

    public PlayerJoin(JoinMessages plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PostLoginEvent e) {
        if (e.getPlayer().hasPermission("join-messages.silent")) {
            return;
        }
        if (plugin.getConfig().isJoinMessageEnabled()) {
            plugin.getProxy().broadcast(
                    new TextComponent(plugin.getConfig().getJoinMessage().replace("%player_name%",
                            e.getPlayer().getName()).replace("%player_displayname%", e.getPlayer().getDisplayName()))
            );
        }
    }

}