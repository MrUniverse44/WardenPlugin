package me.blueslime.wardenplugin.initialization;

import me.blueslime.wardenplugin.WardenPlugin;
import net.md_5.bungee.api.plugin.Plugin;

public abstract class BungeeWardenPlugin extends WardenPlugin<Plugin> {
    public BungeeWardenPlugin(Plugin plugin) {
        super(plugin.getClass(), plugin);
    }
}
