package me.blueslime.wardenplugin.utils;

public class PlatformUtil {

    private static PlatformUtil INSTANCE = null;

    private static PlatformUtil get(Class<?> clazzObject) {
        if (INSTANCE == null) {
            INSTANCE = new PlatformUtil(clazzObject);
        }
        return INSTANCE;
    }

    private final int platform;

    private PlatformUtil(Class<?> clazzObject) {
        this.platform = getAuto(clazzObject);
    }

    public static int getPlatform(Class<?> clazzObject) {
        return get(clazzObject).platform;
    }

    private int getAuto(Class<?> clazzObject) {
        if (exists("org.bukkit.Bukkit", "org.bukkit.ChatColor")) {
            return detect(clazzObject, 0);
        }
        if (exists("net.md_5.bungee.api.plugin.Plugin", "net.md_5.bungee.api.ProxyServer")) {
            return detect(clazzObject, 1);
        }
        if (exists("com.velocitypowered.api.proxy.ProxyServer")) {
            return detect(clazzObject, 2);
        }
        if (exists("org.spongepowered.api.Sponge")) {
            return detect(clazzObject, 3);
        }
        return detect(clazzObject, 1);
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

   private static final Class<?> bungeecordPlugin = PluginConsumer.ofUnchecked(
        () -> Class.forName("net.md_5.bungee.api.plugin.Plugin"),
        e -> {},
        null
    );

    private static final Class<?> velocityProxy = PluginConsumer.ofUnchecked(
        () -> Class.forName("com.velocitypowered.api.proxy.ProxyServer"),
        e -> {},
        null
    );

    private static final Class<?> spongeServer = PluginConsumer.ofUnchecked(
        () -> Class.forName("org.spongepowered.api.Server")
    );

    private static final Class<?> javaPlugin = PluginConsumer.ofUnchecked(
        () -> Class.forName("org.bukkit.plugin.java.JavaPlugin"),
        e -> {},
        null
    );

    private static int detect(Class<?> clazzObject, int defResult) {
        if (clazzObject == null) {
            throw new IllegalArgumentException("Class Object can't be null");
        }
        if (javaPlugin != null && javaPlugin.isInstance(clazzObject)) {
            return 0;
        }
        if (bungeecordPlugin != null && bungeecordPlugin.isInstance(clazzObject)) {
            return 1;
        }
        if (velocityProxy != null && velocityProxy.isInstance(clazzObject)) {
            return 2;
        }
        if (spongeServer != null && spongeServer.isInstance(clazzObject)) {
            return 3;
        }
        return defResult;
    }

}
