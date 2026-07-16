package dev.discoverse.discord;

import dev.discoverse.DiscoVerse;
import dev.discoverse.config.ConfigManager;
import dev.discoverse.utils.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

/**
 * ------------------------------------------------------------
 * DiscoVerse Bot Manager
 * ------------------------------------------------------------
 *
 * Handles the Discord bot lifecycle.
 *
 * Responsibilities:
 * - Create JDA
 * - Login to Discord
 * - Shutdown JDA
 * - Store active JDA instance
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class BotManager {

    private final DiscoVerse plugin;
    private final ConfigManager configManager;

    private JDA jda;

    /**
     * Creates a new BotManager.
     *
     * @param plugin Plugin instance.
     * @param configManager Configuration manager.
     */
    public BotManager(
            final DiscoVerse plugin,
            final ConfigManager configManager
    ) {
        this.plugin = plugin;
        this.configManager = configManager;
    }

    /**
     * Starts the Discord bot.
     */
    public void start() {

        final String token = configManager
                .getDiscordConfig()
                .getString("bot.token");

        if (token == null || token.isBlank()) {

            Logger.error("Discord bot token is missing.");

            return;
        }

        try {

            Logger.info("Logging into Discord...");

            jda = JDABuilder
                    .createDefault(token)
                    .build();

            jda.awaitReady();

            // Store the active JDA instance globally
            JDAProvider.set(jda);

            Logger.info("Discord bot logged in successfully.");

        } catch (Exception exception) {

            Logger.error(
                    "Failed to start Discord Bot.",
                    exception
            );

        }

    }

    /**
     * Stops the Discord bot.
     */
    public void shutdown() {

        if (jda == null) {
            return;
        }

        Logger.info("Shutting down Discord Bot...");

        // Clear the global JDA reference
        JDAProvider.clear();

        jda.shutdown();

        Logger.info("Discord Bot shut down successfully.");
    }

    /**
     * Returns the active JDA instance.
     *
     * @return JDA instance.
     */
    public JDA getJda() {
        return jda;
    }

    /**
     * Returns whether the bot is online.
     *
     * @return true if connected.
     */
    public boolean isOnline() {

        return jda != null
                && jda.getStatus() == JDA.Status.CONNECTED;

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