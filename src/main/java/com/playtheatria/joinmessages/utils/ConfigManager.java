package com.playtheatria.joinmessages.utils;

import com.google.common.io.ByteStreams;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.file.Files;
import java.util.logging.Level;

import com.playtheatria.joinmessages.JoinMessages;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ConfigManager {

    private final JoinMessages plugin;

    private boolean debug;

    private final File file;

    private Configuration configuration;

    private String joinMessage;

    private String quitMessage;

    private boolean joinMessageEnabled;

    private boolean quitMessageEnabled;

    public ConfigManager(JoinMessages plugin) {
        String name = "config";
        this.plugin = plugin;

        this.file = new File(this.plugin.getDataFolder(), name + ".yml");

        if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                InputStream is = plugin.getResourceAsStream(name + "-bungee.yml");
                OutputStream os = Files.newOutputStream(file.toPath());
                ByteStreams.copy(is, os);
            } catch (IOException err) {
                plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
            }
        }
        try {
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException err) {
            plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
        }

        String JoinMessageFile = getConfig().getString("GlobalJoinMessage.Message");
        assert JoinMessageFile != null;
        joinMessage = JoinMessageFile.replace("&", "§");

        String QuitMessageFile = getConfig().getString("GlobalQuitMessage.Message");
        assert QuitMessageFile != null;
        quitMessage = QuitMessageFile.replace("&", "§");

        joinMessageEnabled = getConfig().getBoolean("GlobalJoinMessage.Enabled");
        quitMessageEnabled = getConfig().getBoolean("GlobalQuitMessage.Enabled");

        debug = getConfig().getBoolean("debug");
    }

    public Configuration getConfig() {
        return this.configuration;
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException err) {
            plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
        }
    }

    public void reloadConfig() {
        try {
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

            String JoinMessageFile = getConfig().getString("GlobalJoinMessage.Message");
            assert JoinMessageFile != null;
            joinMessage = JoinMessageFile.replace("&", "§");

            String QuitMessageFile = getConfig().getString("GlobalQuitMessage.Message");
            assert QuitMessageFile != null;
            quitMessage = QuitMessageFile.replace("&", "§");

            joinMessageEnabled = getConfig().getBoolean("GlobalJoinMessage.Enabled");
            quitMessageEnabled = getConfig().getBoolean("GlobalQuitMessage.Enabled");

            debug = getConfig().getBoolean("debug");
        } catch (IOException err) {
            plugin.getLogger().log(Level.WARNING, "Config: {0}", err);
        }
    }

    public String getJoinMessage() { return joinMessage; }

    public String getQuitMessage() { return quitMessage; }

    public boolean isJoinMessageEnabled() { return joinMessageEnabled; }

    public boolean isQuitMessageEnabled() { return quitMessageEnabled; }

    public boolean isDebug() { return debug; }

}
