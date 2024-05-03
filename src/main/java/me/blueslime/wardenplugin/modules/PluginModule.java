package me.blueslime.wardenplugin.modules;

import me.blueslime.wardenplugin.WardenPlugin;
import me.blueslime.wardenplugin.configuration.ConfigurationProvider;
import me.blueslime.wardenplugin.logs.WardenLogs;
import me.blueslime.wardenplugin.platform.Platform;

import java.io.File;

public interface PluginModule {

    void initialize();

    void shutdown();

    void reload();

    ConfigurationProvider getConfigurationProvider();

    WardenLogs getLogs();

    Platform getInformation();

    File getDataFolder();

    WardenPlugin<?> getPlugin();

    default <T> WardenModule<T> asWardenModule() {
        return null;
    }
}
