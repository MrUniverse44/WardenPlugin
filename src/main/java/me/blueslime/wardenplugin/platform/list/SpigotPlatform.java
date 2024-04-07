package me.blueslime.wardenplugin.platform.list;

import me.blueslime.wardenplugin.platform.Platform;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotPlatform {
    private final Platform platform;

    public SpigotPlatform(int id, Object plugin) {
        if (plugin instanceof JavaPlugin) {
            JavaPlugin javaPlugin = (JavaPlugin) plugin;
            platform = Platform.build(
                javaPlugin.getDataFolder(),
                id,
                javaPlugin.getDescription().getName(),
                String.join(", ", javaPlugin.getDescription().getAuthors()),
                javaPlugin.getDescription().getVersion(),
                javaPlugin.getDescription().getDescription(),
                String.join(", ", javaPlugin.getDescription().getAuthors())
            );
        } else if (plugin instanceof Plugin) {
            Plugin bukkitPlugin = (Plugin) plugin;

            platform = Platform.build(
                bukkitPlugin.getDataFolder(),
                id,
                bukkitPlugin.getDescription().getName(),
                String.join(", ", bukkitPlugin.getDescription().getAuthors()),
                bukkitPlugin.getDescription().getVersion(),
                bukkitPlugin.getDescription().getDescription(),
                String.join(", ", bukkitPlugin.getDescription().getAuthors())
            );
        } else {
            platform = Platform.empty(plugin, id);
        }
    }

    public Platform getResult() {
        return platform;
    }
}
