package me.blueslime.wardenplugin.configuration.handlers.custom;

import me.blueslime.wardenplugin.configuration.file.ConfigurationFile;
import me.blueslime.wardenplugin.configuration.ConfigurationHandler;
import me.blueslime.wardenplugin.configuration.ConfigurationSection;
import me.blueslime.wardenplugin.configuration.ConfigurationType;
import me.blueslime.wardenplugin.providers.custom.JsonConfiguration;
import me.blueslime.wardenplugin.providers.custom.CustomConfiguration;
import me.blueslime.wardenplugin.providers.custom.YamlConfiguration;
import me.blueslime.wardenplugin.utils.FileUtil;
import me.blueslime.wardenplugin.utils.PluginConsumer;

import java.util.*;

public class CustomConfigurationHandler extends ConfigurationHandler {
    private CustomConfiguration configuration = new CustomConfiguration();

    public CustomConfigurationHandler(ConfigurationType type, ConfigurationFile file) {
        super(type, file);
    }

    public CustomConfigurationHandler(ConfigurationType type) {
        super(type, null);
    }

    public CustomConfigurationHandler(CustomConfiguration configuration) {
        super(null, null);
        this.configuration = configuration;
    }

    @Override
    public void load(ConfigurationType type, ConfigurationFile file) {
        if (file == null && type == null) {
            return;
        }
        if (type == null || file == null) {
            configuration = new CustomConfiguration();
            return;
        }
        if (type == ConfigurationType.JSON) {
            configuration = PluginConsumer.ofUnchecked(
                () -> {
                    FileUtil.saveResource(file.getFile(), file.getResource());

                    return JsonConfiguration.load(file.getFile());
                },
                ignored -> {},
                CustomConfiguration::new
            );
            return;
        }
        configuration = PluginConsumer.ofUnchecked(
            () -> {
                FileUtil.saveResource(file.getFile(), file.getResource());

                return YamlConfiguration.load(file.getFile());
            },
            ignored -> {},
            CustomConfiguration::new
        );
    }

    @Override
    public ConfigurationHandler getMainHandler() {
        return this;
    }

    @Override
    public ConfigurationSection asSection() {
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T toSpecifiedConfiguration() {
        if (configuration != null) {
            return (T) configuration;
        }
        return null;
    }

    /**
     * Gives a list
     *
     * @param path Path of the list
     * @return List
     */
    @Override
    public List<?> getList(String path) {
        return configuration.getList(path);
    }

    /**
     * Gives a list
     *
     * @param path Path of the list
     * @param defList  Default List
     * @return List. If path-list exists gives the list of the path
     * If the path list doesn't exist, gives the default list
     */
    @Override
    public List<?> getList(String path, List<?> defList) {
        return configuration.getList(path, defList);
    }

    /**
     * Gives a String-Text
     *
     * @param path String Path Location
     * @param defString  If the path doesn't exist, this will be the default result or null
     * @return String
     */
    @Override
    public String getString(String path, String defString) {
        return configuration.getString(path, defString);
    }

    /**
     * Gives a StringList
     *
     * @param path Path of the list
     * @return String List
     */
    @Override
    public List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    /**
     * Gives an Integer List
     *
     * @param path Integer List Location Path
     * @return Integer List
     */
    @Override
    public List<Integer> getIntList(String path) {
        return configuration.getIntList(path);
    }

    @Override
    public long getLong(String path, long defLong) {
        return configuration.getLong(path, defLong);
    }

    @Override
    public long getLong(String path) {
        return configuration.getLong(path);
    }

    @Override
    public List<Long> getLongList(String path) {
        return configuration.getLongList(path);
    }

    @Override
    public List<Boolean> getBooleanList(String path) {
        return configuration.getBooleanList(path);
    }

    @Override
    public List<Byte> getByteList(String path) {
        return configuration.getByteList(path);
    }

    @Override
    public List<Character> getCharList(String path) {
        return configuration.getCharList(path);
    }

    @Override
    public List<Float> getFloatList(String path) {
        return configuration.getFloatList(path);
    }

    @Override
    public Object get(String path) {
        return configuration.get(path);
    }

    @Override
    public Object get(String path, Object defObject) {
        return configuration.get(path, defObject);
    }

    @Override
    public int getInt(String path, int defInt) {
        return configuration.getInt(path, defInt);
    }

    @Override
    public int getInt(String path) {
        return configuration.getInt(path);
    }

    @Override
    public boolean contains(String path) {
        return configuration.contains(path);
    }

    /**
     * Check if the path exists, if the path doesn't exist this will return
     * false, but if the path exists it will return true.
     *
     * @param path Path Location
     * @return Boolean
     */
    @Override
    public boolean getStatus(String path) {
        return configuration.getBoolean(path);
    }

    /**
     * Check if the path exists, if the path doesn't exist this will return
     * the default boolean specified, but if the path exists it will return true.
     *
     * @param path Path Location
     * @param defBoolean  Default Result if the path doesn't exist
     * @return Boolean
     */
    @Override
    public boolean getStatus(String path, boolean defBoolean) {
        return configuration.getBoolean(path, defBoolean);
    }

    /**
     * Set a path to a specified result
     *
     * @param path  Path Location
     * @param value Value of the path
     */
    @Override
    public void set(String path, Object value) {
        configuration.set(path, value);
    }

    /**
     * Get keys of a specified path
     *
     * @param path    Path of the content
     * @param getKeys get the content with keys
     * @return StringList
     */
    @Override
    public List<String> getKeys(String path, boolean getKeys) {
        List<String> rx = new ArrayList<>();

        CustomConfiguration section = configuration.getSection(path);

        if (section == null) {
            return rx;
        }

        rx.addAll(section.getKeys());

        return Collections.unmodifiableList(rx);
    }

    /**
     * Get a configuration handler section
     *
     * @param path Section location
     * @return ConfigurationHandler
     */
    @Override
    public ConfigurationHandler getSection(String path) {
        return new CustomConfigurationHandlerSection(
            this,
            configuration.getSection(path)
        );
    }

    /**
     * Create a new Configuration handler section
     *
     * @param path Section location
     * @return ConfigurationHandler
     */
    @Override
    public ConfigurationHandler createSection(String path) {
        return new CustomConfigurationHandlerSection(
            this,
            configuration.getSection(path)
        );
    }

    @Override
    public Set<String> getKeySet(boolean deep) {
        return (Set<String>) configuration.getKeys();
    }
}
