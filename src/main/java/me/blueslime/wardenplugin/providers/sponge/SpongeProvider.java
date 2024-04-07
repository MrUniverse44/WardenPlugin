package me.blueslime.wardenplugin.providers.sponge;

import me.blueslime.wardenplugin.configuration.file.ConfigurationFile;
import me.blueslime.wardenplugin.configuration.ConfigurationHandler;
import me.blueslime.wardenplugin.configuration.ConfigurationProvider;
import me.blueslime.wardenplugin.configuration.ConfigurationType;
import me.blueslime.wardenplugin.logs.WardenLog;
import me.blueslime.wardenplugin.logs.WardenLogs;
import me.blueslime.wardenplugin.providers.Provider;
import me.blueslime.wardenplugin.providers.custom.CustomProvider;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;

import java.util.EnumMap;
import java.util.Map;

public class SpongeProvider extends Provider {
    private final Map<WardenLog, String> logMap = new EnumMap<>(WardenLog.class);
    private final ConfigurationProvider configurations = new CustomProvider();

    public SpongeProvider(Object pluginInstance) {
        super(pluginInstance);
    }

    @Override
    public ConfigurationHandler load(ConfigurationType type, ConfigurationFile file) {
        return configurations.load(type, file);
    }

    @Override
    public void save(ConfigurationHandler config, ConfigurationFile file) throws Exception {
        configurations.save(config, file);
    }

    @Override
    public void send(String... messages) {
        Server server = Sponge.server();

        for (String message : messages) {
            server.sendMessage(
                LegacyComponentSerializer.legacyAmpersand().deserialize(message)
            );
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
