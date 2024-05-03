package me.blueslime.wardenplugin.colors.warden;

import me.blueslime.wardenplugin.colors.WardenColor;

public class BungeecordWarden extends WardenColor<String, String> {
    @Override
    public String build(String entry) {
        return entry;
    }
}
