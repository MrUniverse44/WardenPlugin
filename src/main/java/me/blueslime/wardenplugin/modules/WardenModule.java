package me.blueslime.wardenplugin.modules;

import me.blueslime.wardenplugin.WardenPlugin;
import me.blueslime.wardenplugin.configuration.ConfigurationProvider;
import me.blueslime.wardenplugin.logs.WardenLogs;
import me.blueslime.wardenplugin.platform.Platform;

import java.io.File;

public abstract class WardenModule<T> implements PluginModule {
    private final WardenPlugin<T> plugin;

    public WardenModule(WardenPlugin<T> plugin) {
        this.plugin = plugin;
    }

    public abstract void initialize();

    public abstract void shutdown();

    public abstract void reload();

    public ConfigurationProvider getConfigurationProvider() {
        return plugin.getConfigurationProvider();
    }

    public WardenLogs getLogs() {
        return plugin.getLogs();
    }

    public Platform getInformation() {
        return plugin.getInformation();
    }

    public File getDataFolder() {
        return plugin.getDataFolder();
    }

    public WardenPlugin<T> getPlugin() {
        return plugin;
    }
}
