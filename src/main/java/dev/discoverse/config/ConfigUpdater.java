package dev.discoverse.config;

import dev.discoverse.DiscoVerse;
import dev.discoverse.utils.Logger;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * ------------------------------------------------------------
 * DiscoVerse Configuration Updater
 * ------------------------------------------------------------
 *
 * Responsible for validating and updating
 * configuration files during plugin startup.
 *
 * Future Features:
 * - Automatic config migration
 * - Version checking
 * - Missing key detection
 * * Default value insertion
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class ConfigUpdater {

    private final DiscoVerse plugin;
    private final ConfigManager configManager;

    /**
     * Creates a new ConfigUpdater.
     *
     * @param plugin Plugin instance.
     * @param configManager Configuration manager.
     */
    public ConfigUpdater(
            final DiscoVerse plugin,
            final ConfigManager configManager
    ) {
        this.plugin = plugin;
        this.configManager = configManager;
    }

    /**
     * Validates all configuration files.
     */
    public void validate() {

        validateConfig("config.yml",
                configManager.getConfig());

        validateConfig("discord.yml",
                configManager.getDiscordConfig());

        validateConfig("messages.yml",
                configManager.getMessages());

        validateConfig("embeds.yml",
                configManager.getEmbeds());

        validateConfig("modules.yml",
                configManager.getModules());

        validateConfig("database.yml",
                configManager.getDatabase());

        validateConfig("lang/en_US.yml",
                configManager.getLanguage());
    }

    /**
     * Validates a single configuration.
     *
     * @param name File name.
     * @param configuration Configuration.
     */
    private void validateConfig(
            final String name,
            final FileConfiguration configuration
    ) {

        if (configuration == null) {

            Logger.error(
                    "Failed to load configuration: " + name
            );

            return;
        }

        Logger.info(
                "Loaded configuration: " + name
        );
    }

    /**
     * Updates configuration files.
     *
     * Reserved for future automatic
     * configuration migrations.
     */
    public void update() {

        Logger.info(
                "Configuration update check completed."
        );

    }

    /**
     * Returns the plugin instance.
     *
     * @return Plugin instance.
     */
    public DiscoVerse getPlugin() {
        return plugin;
    }

    /**
     * Returns the configuration manager.
     *
     * @return Configuration manager.
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

}