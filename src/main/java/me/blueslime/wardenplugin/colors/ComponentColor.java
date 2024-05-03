package me.blueslime.wardenplugin.colors;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ComponentColor extends ColorHandler<Component> {

    @Override
    public Component execute(String text) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(text);
    }
}
