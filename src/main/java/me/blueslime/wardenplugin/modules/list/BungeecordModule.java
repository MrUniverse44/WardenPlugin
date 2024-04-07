package me.blueslime.wardenplugin.modules.list;

import me.blueslime.wardenplugin.WardenPlugin;
import me.blueslime.wardenplugin.modules.WardenModule;
import net.md_5.bungee.api.plugin.Plugin;

public abstract class BungeecordModule extends WardenModule<Plugin> {
    public BungeecordModule(WardenPlugin<Plugin> plugin) {
        super(plugin);
    }
}
