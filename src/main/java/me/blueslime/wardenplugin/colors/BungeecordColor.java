package me.blueslime.wardenplugin.colors;

import net.md_5.bungee.api.ChatColor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BungeecordColor extends ColorHandler<String> {
    private Method COLORIZE_METHOD;

    public BungeecordColor() {
        try {
            this.COLORIZE_METHOD = ChatColor.class.getDeclaredMethod("of", String.class);
        } catch (Exception var2) {
            this.COLORIZE_METHOD = null;
        }

    }

    public String execute(String message) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);

        while(matcher.find()) {
            String code = message.substring(matcher.start(), matcher.end());
            if (this.COLORIZE_METHOD != null) {
                try {
                    ChatColor color = (ChatColor)this.COLORIZE_METHOD.invoke(ChatColor.WHITE, code);
                    message = message.replace(code, color + "");
                    matcher = pattern.matcher(message);
                } catch (InvocationTargetException | IllegalAccessException var6) {
                    return message.replace(code, "");
                }
            }
        }

        message = ChatColor.translateAlternateColorCodes('&', message);

        return getWardenResult(message);
    }
}
