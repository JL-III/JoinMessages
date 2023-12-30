package com.playtheatria.joinmessages.listeners;


import com.playtheatria.joinmessages.JoinMessages;
import com.playtheatria.joinmessages.events.PlayerJoinNotifyEvent;
import com.playtheatria.joinmessages.utils.Debugger;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.util.concurrent.TimeUnit;

public class PlayerJoin implements Listener {

    private final JoinMessages plugin;

    private final Debugger debugger;

    public PlayerJoin(JoinMessages plugin, Debugger debugger) {
        this.plugin = plugin;
        this.debugger = debugger;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PostLoginEvent event) {
        // here we will send a runnable that will check if the player is still logged in after 2 seconds
        // if so then they were not kicked, banned, or immediately disconnected. we can then proceed further with the join message
        plugin.getProxy().getScheduler().schedule(plugin, new Runnable() {
            @Override
            public void run() {
                if (event.getPlayer().isConnected()) {
                    plugin.getProxy().getPluginManager().callEvent(new PlayerJoinNotifyEvent(event.getPlayer().getUniqueId()));
                    debugger.log("PlayerJoinNotifyEvent called for " + event.getPlayer().getName());
                }
            }
        }, 2000, TimeUnit.MILLISECONDS);
    }

}