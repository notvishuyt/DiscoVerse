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
 * - Automatic footer
 * - Automatic thumbnails
 * - Author icons
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
    public EmbedManager(
            final ConfigManager configManager
    ) {

        this.configManager = configManager;

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

        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setColor(Color.CYAN)
                .setTimestamp(Instant.now())
                .build();

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

        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setColor(Color.GREEN)
                .setTimestamp(Instant.now())
                .build();

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

        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setColor(Color.RED)
                .setTimestamp(Instant.now())
                .build();

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

        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setColor(Color.ORANGE)
                .setTimestamp(Instant.now())
                .build();

    }

    /**
     * Creates an information embed.
     *
     * @param title Embed title.
     * @param description Embed description.
     * @return MessageEmbed.
     */
    public MessageEmbed info(
            final String title,
            final String description
    ) {

        return new EmbedBuilder()
                .setTitle(title)
                .setDescription(description)
                .setColor(Color.BLUE)
                .setTimestamp(Instant.now())
                .build();

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
    
