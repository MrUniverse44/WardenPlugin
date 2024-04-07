package me.blueslime.wardenplugin.utils;

public class PlatformUtil {

    private static PlatformUtil INSTANCE = null;

    private static PlatformUtil get() {
        if (INSTANCE == null) {
            INSTANCE = new PlatformUtil();
        }
        return INSTANCE;
    }

    private final int platform;

    private PlatformUtil() {
        this.platform = getAuto();
    }

    public static int getPlatform() {
        return get().platform;
    }

    private int getAuto() {
        if (exists("org.bukkit.Bukkit", "org.bukkit.ChatColor")) {
            return 0;
        }
        if (exists("net.md_5.bungee.api.plugin.Plugin", "net.md_5.bungee.api.plugin.PluginManager", "net.md_5.bungee.api.ProxyServer")) {
            return 1;
        }
        if (exists("com.velocitypowered.api.proxy.ProxyServer", "com.velocitypowered.api.proxy.ConsoleCommandSource")) {
            return 2;
        }
        if (exists("org.spongepowered.api.Sponge")) {
            return 3;
        }
        return 1;
    }

    private static boolean exists(String... locations) {
        for (String location : locations) {
            try {
                Class.forName(location);
            } catch (ClassNotFoundException ignored) {
                return false;
            }
        }
        return true;
    }
}
