package dev.discoverse.discord;

import dev.discoverse.config.ConfigManager;
import dev.discoverse.utils.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

/**
 * ------------------------------------------------------------
 * DiscoVerse Presence Manager
 * ------------------------------------------------------------
 *
 * Handles the Discord bot presence.
 *
 * Features:
 * - Online Status
 * - Bot Activity
 * - Reload Support
 *
 * Reads values from:
 * discord.yml
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class PresenceManager {

    private final ConfigManager configManager;

    /**
     * Creates a new PresenceManager.
     *
     * @param configManager Configuration manager.
     */
    public PresenceManager(final ConfigManager configManager) {
        this.configManager = configManager;
    }

    /**
     * Applies the configured Discord presence.
     */
    public void updatePresence() {

        final JDA jda = JDAProvider.get();

        if (jda == null) {

            Logger.warn("Cannot update Discord presence because JDA is not ready.");

            return;
        }

        final String status = configManager
                .getDiscordConfig()
                .getString("presence.status", "ONLINE");

        final String type = configManager
                .getDiscordConfig()
                .getString("presence.activity.type", "PLAYING");

        final String text = configManager
                .getDiscordConfig()
                .getString("presence.activity.text", "Minecraft");

        jda.getPresence().setStatus(parseStatus(status));
        jda.getPresence().setActivity(parseActivity(type, text));

        Logger.info("Discord presence updated successfully.");

    }

    /**
     * Reloads the configured presence.
     */
    public void reload() {
        updatePresence();
    }

    /**
     * Parses the configured online status.
     *
     * @param status Status string.
     * @return OnlineStatus.
     */
    private OnlineStatus parseStatus(final String status) {

        try {

            return OnlineStatus.valueOf(status.toUpperCase());

        } catch (IllegalArgumentException exception) {

            Logger.warn("Invalid Discord status: " + status);

            return OnlineStatus.ONLINE;

        }

    }

    /**
     * Parses the configured activity.
     *
     * @param type Activity type.
     * @param text Activity text.
     * @return Activity.
     */
    private Activity parseActivity(
            final String type,
            final String text
    ) {

        return switch (type.toUpperCase()) {

            case "PLAYING" -> Activity.playing(text);

            case "WATCHING" -> Activity.watching(text);

            case "LISTENING" -> Activity.listening(text);

            case "COMPETING" -> Activity.competing(text);

            default -> {
                Logger.warn("Unknown activity type: " + type + ". Using PLAYING.");
                yield Activity.playing(text);
            }

        };

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