package dev.discoverse.discord;

import dev.discoverse.config.ConfigManager;
import dev.discoverse.utils.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

/**
 * ------------------------------------------------------------
 * DiscoVerse Webhook Manager
 * ------------------------------------------------------------
 *
 * Handles Discord webhook operations.
 *
 * Responsibilities:
 * - Webhook validation
 * - Sending webhook messages
 * - Future webhook creation
 * - Future webhook management
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class WebhookManager {

    private final ConfigManager configManager;

    /**
     * Creates a new WebhookManager.
     *
     * @param configManager Configuration manager.
     */
    public WebhookManager(final ConfigManager configManager) {
        this.configManager = configManager;
    }

    /**
     * Sends a webhook message.
     *
     * Currently sends a normal Discord message.
     * Future versions will use real webhooks.
     *
     * @param channelId Discord channel ID.
     * @param message Message content.
     */
    public void sendWebhookMessage(
            final String channelId,
            final String message
    ) {

        final JDA jda = JDAProvider.get();

        if (jda == null) {

            Logger.warn("Cannot send webhook message because JDA is not ready.");

            return;
        }

        final TextChannel channel = jda.getTextChannelById(channelId);

        if (channel == null) {

            Logger.warn("Webhook channel not found: " + channelId);

            return;
        }

        channel.sendMessage(message).queue(
                success -> Logger.info("Webhook message sent successfully."),
                error -> Logger.error(
                        "Failed to send webhook message.",
                        error
                )
        );

    }

    /**
     * Reload hook.
     */
    public void reload() {

        Logger.info("WebhookManager reloaded.");

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