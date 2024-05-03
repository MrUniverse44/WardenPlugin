package me.blueslime.wardenplugin.colors.warden;

import me.blueslime.wardenplugin.colors.WardenColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ComponentWarden extends WardenColor<Component, String> {
    @Override
    public Component build(String entry) {
        return entry.contains("wardencolor") ? checkSolid(entry) : LegacyComponentSerializer.legacyAmpersand().deserialize(entry);
    }

    public Component checkSolid(String entry) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(entry);
    }
}
