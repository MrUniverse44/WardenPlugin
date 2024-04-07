package me.blueslime.wardenplugin.providers.custom;

import me.blueslime.wardenplugin.configuration.file.ConfigurationFile;
import me.blueslime.wardenplugin.configuration.ConfigurationHandler;
import me.blueslime.wardenplugin.configuration.ConfigurationProvider;
import me.blueslime.wardenplugin.configuration.ConfigurationType;
import me.blueslime.wardenplugin.configuration.handlers.custom.CustomConfigurationHandler;
import me.blueslime.wardenplugin.utils.FileUtil;

public class CustomProvider implements ConfigurationProvider {
    @Override
    public ConfigurationHandler load(ConfigurationType type, ConfigurationFile file) {
        return new CustomConfigurationHandler(type, file);
    }

    @Override
    public void save(ConfigurationHandler config, ConfigurationFile file) throws Exception {
        if (config == null || file == null) {
            return;
        }

        FileUtil.saveResource(file.getFile(), file.getResource());

        if (config.getType() == ConfigurationType.JSON) {
            JsonConfiguration.save(config.getMainHandler().toSpecifiedConfiguration(), file.getFile());
        } else {
            YamlConfiguration.save(config.getMainHandler().toSpecifiedConfiguration(), file.getFile());
        }
    }
}
