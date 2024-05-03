package me.blueslime.wardenplugin.colors;

import org.bukkit.ChatColor;

public class BukkitColor extends ColorHandler<String> {
    public BukkitColor() {
    }

    public String execute(String text) {
        text = ChatColor.translateAlternateColorCodes('&', text);
        
        return getWardenResult(text);
    }
}