package me.blueslime.wardenplugin.configuration;

import me.blueslime.wardenplugin.configuration.file.ConfigurationFile;

import java.util.Locale;

public enum ConfigurationType {
    JSON,
    YAML;

    public static ConfigurationType detect(ConfigurationFile file) {
        String name = file.getFile().getName().toLowerCase(Locale.ENGLISH);

        if (name.endsWith(".yaml") || name.endsWith(".yml")) {
            return YAML;
        }
        return JSON;
    }
}
