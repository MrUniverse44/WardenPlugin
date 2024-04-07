package me.blueslime.wardenplugin.colors;

import me.blueslime.wardenplugin.platform.Platform;

public abstract class ColorHandler<T> {
    private static ColorHandler<?> INSTANCE = null;

    public ColorHandler() { }

    public static ColorHandler<?> get() {
        if (INSTANCE == null) {
            INSTANCE = create(Platform.PLATFORM_ID);
        }

        return INSTANCE;
    }

    public static ColorHandler<?> create(int id) {
        if (id == -1) {
            return null;
        }
        if (id == 0) {
            try {
                Class.forName("net.md_5.bungee.api.ChatColor");
                return new BungeecordColor();
            } catch (ClassNotFoundException var1) {
                return new BukkitColor();
            }
        } else if (id == 1) {
            return new BungeecordColor();
        } else {
            return new ComponentColor();
        }
    }

    public abstract T execute(String var1);

    /**
     * Converts a text to a color text
     * @param text String
     * @return {@link java.lang.String} or {@link net.kyori.adventure.text.Component} depending on the platform<br>
     * <li>Returns String for Bukkit, Spigot and BungeeCord or Waterfall</li>
     * <li>Returns Component for Velocity and Sponge</li>
     * <br>It can return null if the plugin don't found the correct platform yet.<br>
     * @param <T> parameter
     */
    @SuppressWarnings("unchecked")
    public static <T> T convert(String text) {
        ColorHandler<?> handler = get();
        if (handler == null) {
            return null;
        }
        return (T)get().execute(text);
    }
}
