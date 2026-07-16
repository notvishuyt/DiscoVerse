package dev.discoverse.config;

import dev.discoverse.DiscoVerse;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * ------------------------------------------------------------
 * DiscoVerse Configuration Manager
 * ------------------------------------------------------------
 *
 * Manages all configuration files used by DiscoVerse.
 *
 * Files:
 * - config.yml
 * - discord.yml
 * - messages.yml
 * - embeds.yml
 * - modules.yml
 * - database.yml
 * - lang/en_US.yml
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class ConfigManager {

    private final DiscoVerse plugin;

    private final FileManager config;
    private final FileManager discord;
    private final FileManager messages;
    private final FileManager embeds;
    private final FileManager modules;
    private final FileManager database;
    private final FileManager language;

    /**
     * Creates the configuration manager.
     *
     * @param plugin Plugin instance.
     */
    public ConfigManager(final DiscoVerse plugin) {

        this.plugin = plugin;

        this.config = new FileManager(plugin, "config.yml");
        this.discord = new FileManager(plugin, "discord.yml");
        this.messages = new FileManager(plugin, "messages.yml");
        this.embeds = new FileManager(plugin, "embeds.yml");
        this.modules = new FileManager(plugin, "modules.yml");
        this.database = new FileManager(plugin, "database.yml");
        this.language = new FileManager(plugin, "lang/en_US.yml");
    }

    /**
     * Reloads every configuration.
     */
    public void reloadAll() {

        config.reload();
        discord.reload();
        messages.reload();
        embeds.reload();
        modules.reload();
        database.reload();
        language.reload();
    }

    /**
     * Saves every configuration.
     */
    public void saveAll() {

        config.save();
        discord.save();
        messages.save();
        embeds.save();
        modules.save();
        database.save();
        language.save();
    }

    /**
     * Returns config.yml
     */
    public FileConfiguration getConfig() {
        return config.getConfiguration();
    }

    /**
     * Returns discord.yml
     */
    public FileConfiguration getDiscordConfig() {
        return discord.getConfiguration();
    }

    /**
     * Returns messages.yml
     */
    public FileConfiguration getMessages() {
        return messages.getConfiguration();
    }

    /**
     * Returns embeds.yml
     */
    public FileConfiguration getEmbeds() {
        return embeds.getConfiguration();
    }

    /**
     * Returns modules.yml
     */
    public FileConfiguration getModules() {
        return modules.getConfiguration();
    }

    /**
     * Returns database.yml
     */
    public FileConfiguration getDatabase() {
        return database.getConfiguration();
    }

    /**
     * Returns language file.
     */
    public FileConfiguration getLanguage() {
        return language.getConfiguration();
    }

    /**
     * Returns the plugin instance.
     *
     * @return Plugin instance.
     */
    public DiscoVerse getPlugin() {
        return plugin;
    }

}