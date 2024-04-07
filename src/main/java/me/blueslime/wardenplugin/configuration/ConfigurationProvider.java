package me.blueslime.wardenplugin.configuration;

import me.blueslime.wardenplugin.configuration.file.ConfigurationFile;

public interface ConfigurationProvider {

    ConfigurationHandler load(ConfigurationType type, ConfigurationFile file);

    default ConfigurationHandler load(ConfigurationFile file) { return load(ConfigurationType.detect(file), file); }

    void save(ConfigurationHandler config, ConfigurationFile file) throws Exception;

}