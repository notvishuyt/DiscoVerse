package dev.discoverse.discord;

import dev.discoverse.config.ConfigManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.Color;
import java.time.Instant;

/**
 * ------------------------------------------------------------
 * DiscoVerse Embed Manager
 * ------------------------------------------------------------
 *
 * Creates Discord embeds used throughout the plugin.
 *
 * Responsibilities:
 * - Default embeds
 * - Success embeds
 * - Error embeds
 * - Warning embeds
 * - Information embeds
 *
 * Future:
 * - Load colors from embeds.yml
 * - Load footer from config
 * - Load thumbnail/icon automatically
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class EmbedManager {

    private final ConfigManager configManager;

    /**
     * Creates a new EmbedManager.
     *
     * @param configManager Configuration manager.
     */
    public EmbedManager(final ConfigManager configManager) {
        this.configManager = configManager;
    }

    /**
     * Creates a generic embed.
     *
     * @param title Embed title.
     * @param description Embed description.
     * @param color Embed color.
     * @return MessageEmbed.
     */
    private MessageEmbed createEmbed(
            final String title,
            final String description,
            final Color color
    ) {

        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setColor(color)
                .setTimestamp(Instant.now())
                .build();

    }

    /**
     * Creates a default embed.
     *
     * @param title Embed title.
     * @param description Embed description.
     * @return MessageEmbed.
     */
    public MessageEmbed create(
            final String title,
            final String description
    ) {

        return createEmbed(title, description, Color.CYAN);

    }

    /**
     * Creates a success embed.
     *
     * @param title Embed title.
     * @param description Embed description.
     * @return MessageEmbed.
     */
    public MessageEmbed success(
            final String title,
            final String description
    ) {

        return createEmbed(title, description, Color.GREEN);

    }

    /**
     * Creates an error embed.
     *
     * @param title Embed title.
     * @param description Embed description.
     * @return MessageEmbed.
     */
    public MessageEmbed error(
            final String title,
            final String description
    ) {

        return createEmbed(title, description, Color.RED);

    }

    /**
     * Creates a warning embed.
     *
     * @param title Embed title.
     * @param description Embed description.
     * @return MessageEmbed.
     */
    public MessageEmbed warning(
            final String title,
            final String description
    ) {

        return createEmbed(title, description, Color.ORANGE);

    }

    /**
     * Creates an information embed.
     *
     * @param title Embed title.
    