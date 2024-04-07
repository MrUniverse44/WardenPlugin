package me.blueslime.wardenplugin.platform;

import me.blueslime.wardenplugin.platform.list.BungeePlatform;
import me.blueslime.wardenplugin.platform.list.SpigotPlatform;

import java.io.File;

public class Platform {
    public static int PLATFORM_ID = -1;
    private final String collaborators;
    private final String description;
    private final String version;
    private final String author;
    private final String name;
    private File dataFolder;
    private final int id;

    private Platform(File dataFolder, int id, String name, String author, String version, String description, String collaborators) {
        this.collaborators = collaborators;
        this.description = description;
        this.dataFolder = dataFolder;
        this.version = version;
        this.author = author;
        this.name = name;
        this.id = id;

        PLATFORM_ID = id;
    }

    public static Platform empty(File dataFolder, int id) {
        PLATFORM_ID = id;
        return new Platform(dataFolder, id, "BlueSlime", "JustJustin", "1.0.0", "A WardenPlugin system", "MrUniverse44");
    }

    public static Platform empty(Object pluginInstance, int id) {
        PLATFORM_ID = id;
        return empty(
            directory(pluginInstance),
            id
        );
    }

    private static File directory(Object pluginInstance) {
        return new File(pluginInstance.getClass().getSimpleName().replace(".class", "").replace(".java", ""));
    }

    /**
     * Get the Platform ID of your current platform<br><br>
     * <p>
     * The Platform ID corresponds to:
     * <ul>
     *   <li><a href="https://hub.spigotmc.org/stash/projects/spigot/repos/craftbukkit/browse">Bukkit</a> or <a href="https://hub.spigotmc.org/stash/projects/SPIGOT/repos/spigot/browse">Spigot</a> for platform ID 0</li>
     *   <li><a href="https://github.com/SpigotMC/BungeeCord">BungeeCord</a> for platform ID 1</li>
     *   <li><a href="https://papermc.io/software/velocity">Velocity</a> for platform ID 2</li>
     *   <li><a href="https://spongepowered.org/">Sponge</a> for platform ID 3</li>
     * </ul>
     * For more information, you can visit our website: <a href="http://www.github.com/MrUniverse44/WardenPlugin/wiki/platforms">WardenPlugin Wiki</a>
     * </p>
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public File getDataFolder() {
        return dataFolder;
    }

    public String getCollaborators() {
        return collaborators;
    }

    public String getDescription() {
        return description;
    }

    public Platform setDataFolder(File dataFolder) {
        this.dataFolder = dataFolder;
        return this;
    }

    public static Platform build(File dataFolder, int id, String name, String author, String version, String description, String collaborators) {
        PLATFORM_ID = id;
        return new Platform(dataFolder, id, name, author, version, description, collaborators);
    }

    public static Platform build(Object pluginInstance, int id, String name, String author, String version, String description, String collaborators) {
        File directory;
        if (id == 0) {
            directory = new SpigotPlatform(id, pluginInstance).getResult().getDataFolder();
        } else if (id == 1) {
            directory = new BungeePlatform(id, pluginInstance).getResult().getDataFolder();
        } else {
            directory = directory(pluginInstance);
        }
        PLATFORM_ID = id;
        return new Platform(directory, id, name, author, version, description, collaborators);
    }

    /**
     * Get the Platform ID of your current platform
     * <p>0 is for Bukkit or Spigot</p>
     * <p>1 is for BungeeCord</p>
     * <p>2 is for Velocity</p>
     * <p>3 is for Sponge</p>
     * @return platform identifier
     */
    public static Platform build(int id, Object pluginInstance) {
        if (id == 0) {
            return new SpigotPlatform(id, pluginInstance).getResult();
        }
        if (id == 1) {
            return new BungeePlatform(id, pluginInstance).getResult();
        }
        PLATFORM_ID = id;
        return empty(pluginInstance, id);
    }
}
