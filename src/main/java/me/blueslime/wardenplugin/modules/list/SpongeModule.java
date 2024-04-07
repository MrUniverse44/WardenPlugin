package me.blueslime.wardenplugin.modules.list;

import me.blueslime.wardenplugin.WardenPlugin;
import me.blueslime.wardenplugin.modules.WardenModule;
import org.spongepowered.api.Server;

public abstract class SpongeModule extends WardenModule<Server> {
    public SpongeModule(WardenPlugin<Server> plugin) {
        super(plugin);
    }
}
