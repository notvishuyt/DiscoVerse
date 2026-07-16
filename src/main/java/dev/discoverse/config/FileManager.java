package dev.discoverse.config;

import dev.discoverse.DiscoVerse;
import dev.discoverse.utils.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * ------------------------------------------------------------
 * DiscoVerse File Manager
 * ------------------------------------------------------------
 *
 * Handles creation, loading, saving and reloading
 * of YAML configuration files.
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class FileManager {

    private final DiscoVerse plugin;

    private final String fileName;

    private File file;

    private FileConfiguration configuration;

    /**
     * Creates a new FileManager.
     *
     * @param plugin Plugin instance.
     * @param fileName Configuration file name.
     */
    public FileManager(
            final DiscoVerse plugin,
            final String fileName
    ) {
        this.plugin = plugin;
        this.fileName = fileName;

        create();
        reload();
    }

    /**
     * Creates the configuration file if it
     * does not already exist.
     */
    private void create() {

        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }

        file = new File(plugin.getDataFolder(), fileName);

        if (file.exists()) {
            return;
        }

        plugin.saveResource(fileName, false);

        Logger.info(fileName + " has been created.");
    }

    /**
     * Reloads the configuration.
     */
    public void reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Saves the configuration.
     */
    public void save() {

        try {
            configuration.save(file);

        } catch (IOException exception) {

            Logger.error(
                    "Failed to save " + fileName,
                    exception
            );

        }

    }

    /**
     * Returns the configuration.
     *
     * @return FileConfiguration.
     */
    public FileConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Returns the configuration file.
     *
     * @return File.
     */
    public File getFile() {
        return file;
    }

    /**
     * Returns the file name.
     *
     * @return File name.
     */
    public String getFileName() {
        return fileName;
    }

}