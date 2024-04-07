package me.blueslime.wardenplugin.configuration.handlers.bungeecord;

import me.blueslime.wardenplugin.configuration.file.ConfigurationFile;
import me.blueslime.wardenplugin.configuration.ConfigurationHandler;
import me.blueslime.wardenplugin.configuration.ConfigurationSection;
import me.blueslime.wardenplugin.configuration.ConfigurationType;
import me.blueslime.wardenplugin.utils.FileUtil;
import me.blueslime.wardenplugin.utils.PluginConsumer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.JsonConfiguration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.util.*;

public class BungeeConfigurationHandler extends ConfigurationHandler {

    private Configuration configuration = new Configuration();
    private final ConfigurationHandler main;

    public BungeeConfigurationHandler(ConfigurationType type, ConfigurationFile file) {
        super(type, file);
        this.main = this;
    }

    public BungeeConfigurationHandler(ConfigurationHandler main, Configuration file) {
        super(main.getType(), null);
        this.configuration = file;
        this.main = main;
    }

    @Override
    public void load(ConfigurationType type, ConfigurationFile file) {
        if (file != null) {
            configuration = loadConfig(file.getFile(), file.getResource());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T toSpecifiedConfiguration() {
        return (T) configuration;
    }

    @Override
    public Object get(String path) { return configuration.get(path); }

    @Override
    public Object get(String path,Object def) { return configuration.get(path,def); }

    @Override
    public long getLong(String path,long def) { return configuration.getLong(path,def); }

    @Override
    public long getLong(String path) { return configuration.getLong(path); }

    @Override
    public List<Long> getLongList(String path) { return configuration.getLongList(path); }

    @Override
    public List<Boolean> getBooleanList(String path) { return configuration.getBooleanList(path); }

    @Override
    public List<Byte> getByteList(String path) { return configuration.getByteList(path); }

    @Override
    public List<Character> getCharList(String path) { return configuration.getCharList(path); }
    @Override
    public List<Float> getFloatList(String path) { return configuration.getFloatList(path); }

    private Configuration loadConfig(File file, InputStream resource) {
        if (!file.exists()) {
            FileUtil.saveResource(file, resource);
        }

        if (getType() == ConfigurationType.YAML) {
            return PluginConsumer.ofUnchecked(
                () -> ConfigurationProvider.getProvider(YamlConfiguration.class).load(file),
                ignored -> {
                },
                Configuration::new
            );
        } else {
            return PluginConsumer.ofUnchecked(
                () -> ConfigurationProvider.getProvider(JsonConfiguration.class).load(file),
                ignored -> {
                },
                Configuration::new
            );
        }
    }

    @Override
    public List<?> getList(String path) {
        return configuration.getList(path);
    }

    @Override
    public List<?> getList(String path, List<?> def) {
        return configuration.getList(path,def);
    }

    @Override
    public String getString(String path) {
        return configuration.getString(path);
    }

    @Override
    public String getString(String path, String def) {
        return configuration.getString(path, def);
    }

    @Override
    public List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    @Override
    public List<String> getKeys(String path, boolean getKeys) {
        List<String> rx = new ArrayList<>();

        Configuration section = configuration.getSection(path);

        if (section == null) {
            return rx;
        }
        rx.addAll(section.getKeys());

        return Collections.unmodifiableList(rx);
    }

    @Override
    public ConfigurationHandler getSection(String path) {
        return new BungeeConfigurationHandler(main, configuration.getSection(path));
    }

    @Override
    public ConfigurationHandler createSection(String path) {
        return new BungeeConfigurationHandler(main, configuration.getSection(path));
    }

    @Override
    public List<Integer> getIntList(String path) {
        return configuration.getIntList(path);
    }

    @Override
    public int getInt(String path, int def) {
        return configuration.getInt(path,def);
    }

    @Override
    public int getInt(String path) {
        return configuration.getInt(path);
    }

    @Override
    public boolean contains(String path) {
        return configuration.contains(path);
    }

    @Override
    public boolean getStatus(String path) {
        return configuration.getBoolean(path);
    }

    @Override
    public boolean getStatus(String path, boolean def) {
        return configuration.getBoolean(path, def);
    }

    @Override
    public void set(String path, Object value) {
        configuration.set(path,value);
    }

    @Override
    public Set<String> getKeySet(boolean deep) {
        return new HashSet<>(configuration.getKeys());
    }

    @Override
    public ConfigurationHandler getMainHandler() {
        return main;
    }

    @Override
    public ConfigurationSection asSection() {
        return this;
    }
}
