package com.playtheatria.joinmessages.events;

import net.md_5.bungee.api.plugin.Event;

import java.util.UUID;

public class PlayerJoinNotifyEvent extends Event {

        private final UUID player_uuid;

        public PlayerJoinNotifyEvent(UUID player_uuid) {
            this.player_uuid = player_uuid;
        }

        public UUID getPlayerUUID() {
            return player_uuid;
        }
}
