package me.blueslime.wardenplugin.modules.list;

import me.blueslime.wardenplugin.WardenPlugin;
import me.blueslime.wardenplugin.modules.WardenModule;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BukkitModule extends WardenModule<JavaPlugin> {
    public BukkitModule(WardenPlugin<JavaPlugin> plugin) {
        super(plugin);
    }
}
