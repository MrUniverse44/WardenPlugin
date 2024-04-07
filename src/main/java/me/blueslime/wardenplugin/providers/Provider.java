package me.blueslime.wardenplugin.providers;

import me.blueslime.wardenplugin.configuration.ConfigurationProvider;
import me.blueslime.wardenplugin.logs.WardenLogs;

public abstract class Provider implements ConfigurationProvider, WardenLogs {
    protected final Object pluginInstance;
    public Provider(Object pluginInstance) {
        this.pluginInstance = pluginInstance;
    }
}
