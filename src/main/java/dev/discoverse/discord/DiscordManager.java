package dev.discoverse.discord;

import dev.discoverse.DiscoVerse;
import dev.discoverse.config.ConfigManager;
import dev.discoverse.utils.Logger;
import net.dv8tion.jda.api.JDA;

/**
 * ------------------------------------------------------------
 * DiscoVerse Discord Manager
 * ------------------------------------------------------------
 *
 * Responsible for:
 * - Starting the Discord module
 * - Stopping the Discord module
 * - Checking whether Discord is enabled
 * - Managing the BotManager lifecycle
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class DiscordManager {

    private final DiscoVerse plugin;
    private final ConfigManager configManager;

    private BotManager botManager;

    /**
     * Creates a new DiscordManager.
     *
     * @param plugin Plugin instance.
     * @param configManager Configuration manager.
     */
    public DiscordManager(
            final DiscoVerse plugin,
            final ConfigManager configManager
    ) {
        this.plugin = plugin;
        this.configManager = configManager;
    }

    /**
     * Starts the Discord module.
     */
    public void start() {

        if (!isEnabled()) {

            Logger.info("Discord integration is disabled.");

            return;
        }

        Logger.info("Starting Discord Manager...");

        botManager = new BotManager(
                plugin,
                configManager
        );

        botManager.start();

        Logger.info("Discord Manager started successfully.");
    }

    /**
     * Stops the Discord module.
     */
    public void shutdown() {

        if (botManager == null) {
            return;
        }

        Logger.info("Stopping Discord Manager...");

        botManager.shutdown();

        Logger.info("Discord Manager stopped.");
    }

    /**
     * Reloads the Discord module.
     */
    public void reload() {

        shutdown();

        start();
    }

    /**
     * Returns whether Discord is enabled.
     *
     * Reads:
     * modules.yml
     *
     * discord:
     *   enabled: true
     *
     * @return true if enabled.
     */
    public boolean isEnabled() {

        return configManager
                .getModules()
                .getBoolean("discord.enabled", true);

    }

    /**
     * Returns the active JDA instance.
     *
     * @return JDA instance or null.
     */
    public JDA getJDA() {

        if (botManager == null) {
            return null;
        }

        return botManager.getJda();

    }

    /**
     * Returns the BotManager.
     *
     * @return BotManager.
     */
    public BotManager getBotManager() {
        return botManager;
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