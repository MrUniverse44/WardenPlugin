package me.blueslime.wardenplugin.initialization;

import me.blueslime.wardenplugin.WardenPlugin;
import org.spongepowered.api.Server;

public abstract class SpongeWardenPlugin extends WardenPlugin<Server> {
    public SpongeWardenPlugin(Server plugin) {
        super(plugin.getClass(), plugin);
    }
}
