package me.blueslime.wardenplugin.modules;

import me.blueslime.wardenplugin.logs.WardenLogs;
import me.blueslime.wardenplugin.platform.Platforms;
import me.blueslime.wardenplugin.utils.PluginConsumer;
import me.blueslime.wardenplugin.utils.PluginExecutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleContainer {
    private final List<PluginModule> moduleList = new ArrayList<>();
    private final PluginExecutable<ModuleContainer> executable;
    private final int platform;

    private ModuleContainer(int platform, PluginExecutable<ModuleContainer> executable) {
        this.platform = platform;
        this.executable = executable;
    }

    public static ModuleContainer create(int platform, PluginExecutable<ModuleContainer> consumer) {
        return new ModuleContainer(platform, consumer);
    }

    public ModuleContainer verify(WardenLogs logs, int platform) {
        logs.info(platform + " id verifying with " + this.platform);
        if (platform != this.platform && platform != Platforms.UNIVERSAL) {
            logs.info("Platform not found: " + this.platform + " for " + platform);
            return null;
        }
        logs.info("Continued");
        PluginConsumer.process(
            () -> executable.execute(this)
        );
        return this;
    }

    public void register(PluginModule... modules) {
        if (modules == null) {
            return;
        }
        moduleList.addAll(Arrays.asList(modules));
    }

    public List<PluginModule> getModuleList() {
        return moduleList;
    }

    public PluginExecutable<ModuleContainer> getExecutable() {
        return executable;
    }

    public int getPlatform() {
        return platform;
    }
}
