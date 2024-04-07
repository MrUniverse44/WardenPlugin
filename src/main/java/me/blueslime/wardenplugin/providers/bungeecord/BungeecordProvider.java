package me.blueslime.wardenplugin.providers.bungeecord;

import me.blueslime.wardenplugin.colors.ColorHandler;
import me.blueslime.wardenplugin.configuration.file.ConfigurationFile;
import me.blueslime.wardenplugin.configuration.ConfigurationHandler;
import me.blueslime.wardenplugin.configuration.ConfigurationType;
import me.blueslime.wardenplugin.configuration.handlers.bungeecord.BungeeConfigurationHandler;
import me.blueslime.wardenplugin.logs.WardenLog;
import me.blueslime.wardenplugin.logs.WardenLogs;
import me.blueslime.wardenplugin.providers.Provider;
import me.blueslime.wardenplugin.utils.FileUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.JsonConfiguration;
import net.md_5.bungee.config.YamlConfiguration;

import java.util.EnumMap;
import java.util.Map;

public class BungeecordProvider extends Provider {
    private final Map<WardenLog, String> logMap = new EnumMap<>(WardenLog.class);
    public BungeecordProvider(Object pluginInstance) {
        super(pluginInstance);
    }

    @Override
    public ConfigurationHandler load(ConfigurationType type, ConfigurationFile file) {
        return new BungeeConfigurationHandler(type, file);
    }

    @Override
    public void save(ConfigurationHandler config, ConfigurationFile file) throws Exception {
        if (config == null || file == null) {
            return;
        }

        FileUtil.saveResource(file.getFile(), file.getResource());

        if (config.getType() == ConfigurationType.YAML) {
            net.md_5.bungee.config.ConfigurationProvider.getProvider(YamlConfiguration.class).save(
                config.getMainHandler().toSpecifiedConfiguration(), file.getFile()
            );
        } else {
            net.md_5.bungee.config.ConfigurationProvider.getProvider(JsonConfiguration.class).save(
                config.getMainHandler().toSpecifiedConfiguration(), file.getFile()
            );
        }
    }

    @Override
    public void send(String... messages) {
        Plugin plugin = (Plugin)pluginInstance;
        CommandSender console = plugin.getProxy().getConsole();

        for (String message : messages) {
            String convert = ColorHandler.convert(message);
            if (convert == null) {
                console.sendMessage(
                    new TextComponent(
                        message
                    )
                );
            } else {
                console.sendMessage(
                     new TextComponent(
                        convert
                     )
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

    }
}
