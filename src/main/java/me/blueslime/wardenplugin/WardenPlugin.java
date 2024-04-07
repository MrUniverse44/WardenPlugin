package me.blueslime.wardenplugin;

import me.blueslime.wardenplugin.configuration.ConfigurationProvider;
import me.blueslime.wardenplugin.information.WardenInformation;
import me.blueslime.wardenplugin.logs.WardenLogs;
import me.blueslime.wardenplugin.modules.WardenModule;
import me.blueslime.wardenplugin.platform.Platform;
import me.blueslime.wardenplugin.providers.Provider;
import me.blueslime.wardenplugin.providers.ProviderBuilder;
import me.blueslime.wardenplugin.utils.PlatformUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public abstract class WardenPlugin<T>  {
    private final Map<Class<?>, WardenModule<T>> moduleMap = new HashMap<>();
    private final Provider provider;
    private final Platform platform;
    private final T plugin;

    public WardenPlugin(T plugin) {
        this.plugin = plugin;
        if (getClass().isAnnotationPresent(WardenInformation.class)) {
            WardenInformation info = getClass().getAnnotation(WardenInformation.class);

            platform = Platform.build(plugin, PlatformUtil.getPlatform(), info.getName(), info.getAuthor(), info.getVersion(), info.getDescription(), info.getCollaborators());
        } else {
            platform = Platform.build(PlatformUtil.getPlatform(), plugin);
        }

        provider = ProviderBuilder.fromPlatform(platform, plugin);
    }

    public ConfigurationProvider getConfigurationProvider() {
        return provider;
    }

    public <K extends WardenModule<T>> K getModule(Class<K> module) {
        return (K) moduleMap.get(module);
    }

    @SafeVarargs
    public final void registerModule(WardenModule<T>... modules) {
        for (WardenModule<T> module : modules) {
            moduleMap.put(module.getClass(), module);
        }
    }

    public void registerModules(Consumer<Integer> consumer) {
        consumer.accept(platform.getId());
    }

    public abstract void registration();

    public WardenLogs getLogs() {
        return provider;
    }

    public Platform getInformation() {
        return platform;
    }

    public File getDataFolder() {
        return platform.getDataFolder();
    }

    public T getPlugin() {
        return plugin;
    }

    public void initialize() {
        registration();

        List<WardenModule<T>> moduleList = new ArrayList<>(moduleMap.values());

        for (WardenModule<T> module : moduleList) {
            module.initialize();
        }
    }

    public void shutdown() {
        List<WardenModule<T>> moduleList = new ArrayList<>(moduleMap.values());

        for (WardenModule<T> module : moduleList) {
            module.shutdown();
        }
    }

    public void reload() {
        List<WardenModule<T>> moduleList = new ArrayList<>(moduleMap.values());

        for (WardenModule<T> module : moduleList) {
            module.reload();
        }
    }
}