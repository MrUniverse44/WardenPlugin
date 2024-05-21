package me.blueslime.wardenplugin.initialization;

import com.velocitypowered.api.proxy.ProxyServer;
import me.blueslime.wardenplugin.WardenPlugin;

public abstract class VelocityWardenPlugin extends WardenPlugin<ProxyServer> {
    public VelocityWardenPlugin(ProxyServer plugin) {
        super(plugin.getClass(), plugin);
    }
}
