package me.blueslime.wardenplugin.providers.bukkit;

import me.blueslime.wardenplugin.colors.ColorHandler;
import me.blueslime.wardenplugin.configuration.file.ConfigurationFile;
import me.blueslime.wardenplugin.configuration.ConfigurationHandler;
import me.blueslime.wardenplugin.configuration.ConfigurationType;
import me.blueslime.wardenplugin.configuration.handlers.bukkit.BukkitConfigurationHandler;
import me.blueslime.wardenplugin.logs.WardenLog;
import me.blueslime.wardenplugin.logs.WardenLogs;
import me.blueslime.wardenplugin.providers.Provider;
import me.blueslime.wardenplugin.providers.custom.JsonConfiguration;
import me.blueslime.wardenplugin.utils.FileUtil;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.EnumMap;
import java.util.Map;

public class BukkitProvider extends Provider {
    private final Map<WardenLog, String> logMap = new EnumMap<>(WardenLog.class);

    public BukkitProvider(Object pluginInstance) {
        super(pluginInstance);
    }

    @Override
    public ConfigurationHandler load(ConfigurationType type, ConfigurationFile file) {
        return new BukkitConfigurationHandler(type, file);
    }

    @Override
    public void save(ConfigurationHandler config, ConfigurationFile file) throws Exception {
        if (config == null || file == null) {
            return;
        }

        FileUtil.saveResource(file.getFile(), file.getResource());

        if (config.getType() == ConfigurationType.YAML) {
            FileConfiguration configuration = config.toSpecifiedConfiguration();

            configuration.save(file.getFile());
        } else {
            JsonConfiguration.save(config.toSpecifiedConfiguration(), file.getFile());
        }
    }

    @Override
    public void send(String... messages) {
        ConsoleCommandSender sender = ((Plugin)pluginInstance).getServer().getConsoleSender();

        for (String message : messages) {
            String convert = ColorHandler.convert(message);
            if (convert == null) {
                sender.sendMessage(
                    message
                );
            } else {
                sender.sendMessage(
                    convert
                );
            }
        }
    }

    @Override
    public WardenLogs setPrefix(WardenLog log, String prefix) {
        logMap.put(log, prefix);
        return this;
    }

    @Override
    public String getPrefix(WardenLog prefix) {
        return logMap.computeIfAbsent(
            prefix,
            (k) -> prefix.getDefaultPrefix()
        );
    }

    @Override
    public void build() {
        //* This no makes nothing
    }
}
