package me.blueslime.wardenplugin;

import me.blueslime.wardenplugin.configuration.ConfigurationProvider;
import me.blueslime.wardenplugin.information.WardenInformation;
import me.blueslime.wardenplugin.logs.WardenLogs;
import me.blueslime.wardenplugin.modules.ModuleContainer;
import me.blueslime.wardenplugin.modules.PluginModule;
import me.blueslime.wardenplugin.platform.Platform;
import me.blueslime.wardenplugin.providers.Provider;
import me.blueslime.wardenplugin.providers.ProviderBuilder;
import me.blueslime.wardenplugin.utils.PlatformUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class WardenPlugin<T>  {
    private final Map<Class<?>, PluginModule> moduleMap = new HashMap<>();
    private final Provider provider;
    private final Platform platform;
    private final T plugin;

    public WardenPlugin(T plugin) {
        this.plugin = plugin;
        if (getClass().isAnnotationPresent(WardenInformation.class)) {
            WardenInformation info = getClass().getAnnotation(WardenInformation.class);

            platform = Platform.build(plugin, PlatformUtil.getPlatform(plugin.getClass()), info.getName(), info.getAuthor(), info.getVersion(), info.getDescription(), info.getCollaborators());
        } else {
            platform = Platform.build(PlatformUtil.getPlatform(plugin.getClass()), plugin);
        }

        provider = ProviderBuilder.fromPlatform(platform, plugin);
    }

    public ConfigurationProvider getConfigurationProvider() {
        return provider;
    }

    public <K extends PluginModule> K getModule(Class<K> module) {
        return (K) moduleMap.get(module);
    }

    public final void registerModule(ModuleContainer... containers) {
        if (containers == null) {
            return;
        }
        for (ModuleContainer container : containers) {
            ModuleContainer finalContainer = container.verify(getLogs(), platform.getId());

            if (finalContainer == null) {
                continue;
            }

            for (PluginModule module : finalContainer.getModuleList()) {
                moduleMap.put(module.getClass(), module);
            }
        }
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

        List<PluginModule> moduleList = new ArrayList<>(moduleMap.values());

        for (PluginModule module : moduleList) {
            module.initialize();
        }
    }

    public void shutdown() {
        List<PluginModule> moduleList = new ArrayList<>(moduleMap.values());

        for (PluginModule module : moduleList) {
            module.shutdown();
        }
    }

    public void reload() {
        List<PluginModule> moduleList = new ArrayList<>(moduleMap.values());

        for (PluginModule module : moduleList) {
            module.reload();
        }
    }
}
