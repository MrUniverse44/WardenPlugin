package me.blueslime.wardenplugin.modules.list;

import com.velocitypowered.api.proxy.ProxyServer;
import me.blueslime.wardenplugin.WardenPlugin;
import me.blueslime.wardenplugin.modules.WardenModule;

public abstract class VelocityModule extends WardenModule<ProxyServer> {
    public VelocityModule(WardenPlugin<ProxyServer> plugin) {
        super(plugin);
    }
}
