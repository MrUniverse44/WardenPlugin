package me.blueslime.wardenplugin.platform.list;

import me.blueslime.wardenplugin.platform.Platform;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeePlatform {
    private final Platform platform;

    public BungeePlatform(int id, Object plugin) {
        if (plugin instanceof Plugin) {
            Plugin bungeePlugin = (Plugin) plugin;

            platform = Platform.build(
                bungeePlugin.getDataFolder(),
                id,
                bungeePlugin.getDescription().getName(),
                bungeePlugin.getDescription().getAuthor(),
                bungeePlugin.getDescription().getVersion(),
                bungeePlugin.getDescription().getDescription(),
                bungeePlugin.getDescription().getAuthor()
            );

        } else {
            platform = Platform.empty(plugin, id);
        }
    }

    public Platform getResult() {
        return platform;
    }
}

