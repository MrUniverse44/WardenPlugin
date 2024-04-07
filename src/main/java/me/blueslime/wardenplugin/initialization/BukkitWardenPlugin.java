package me.blueslime.wardenplugin.initialization;

import me.blueslime.wardenplugin.WardenPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BukkitWardenPlugin extends WardenPlugin<JavaPlugin> {
    public BukkitWardenPlugin(JavaPlugin plugin) {
        super(plugin);
    }
}
