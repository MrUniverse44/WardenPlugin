package me.blueslime.wardenplugin.providers;

import me.blueslime.wardenplugin.platform.Platform;
import me.blueslime.wardenplugin.providers.bukkit.BukkitProvider;
import me.blueslime.wardenplugin.providers.bungeecord.BungeecordProvider;
import me.blueslime.wardenplugin.providers.sponge.SpongeProvider;
import me.blueslime.wardenplugin.providers.velocity.VelocityProvider;

public class ProviderBuilder {
    public static Provider fromPlatform(Platform platform, Object pluginInstance) {
        int id = platform.getId();

        if (id == 0) {
            return new BukkitProvider(pluginInstance);
        } else if (id == 1) {
            return new BungeecordProvider(pluginInstance);
        } else if (id == 2) {
            return new VelocityProvider(pluginInstance);
        } else if (id == 3) {
            return new SpongeProvider(pluginInstance);
        }
        return new BungeecordProvider(pluginInstance);
    }
}
