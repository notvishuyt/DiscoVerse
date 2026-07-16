package dev.discoverse.discord;

import dev.discoverse.config.ConfigManager;
import dev.discoverse.utils.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

/**
 * ------------------------------------------------------------
 * DiscoVerse Slash Command Manager
 * ------------------------------------------------------------
 *
 * Handles registration of Discord slash commands.
 *
 * Features:
 * - Register commands
 * - Update commands
 * - Reload support
 *
 * Future:
 * - Dynamic command loading
 * - Permission checks
 * - Localization
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class SlashCommandManager {

    private final ConfigManager configManager;

    /**
     * Creates a new SlashCommandManager.
     *
     * @param configManager Configuration manager.
     */
    public SlashCommandManager(final ConfigManager configManager) {
        this.configManager = configManager;
    }

    /**
     * Registers all Discord slash commands.
     */
    public void registerCommands() {

        final JDA jda = JDAProvider.get();

        if (jda == null) {

            Logger.warn("Cannot register Discord slash commands because JDA is not ready.");

            return;
        }

        jda.updateCommands()

                .addCommands(

                        Commands.slash(
                                "link",
                                "Link your Minecraft account."
                        ),

                        Commands.slash(
                                "unlink",
                                "Unlink your Minecraft account."
                        ),

                        Commands.slash(
                                "status",
                                "Shows the server status."
                        ),

                        Commands.slash(
                                "ping",
                                "Shows the bot latency."
                        )

                )

                .queue(
                        success -> Logger.info("Discord slash commands registered successfully."),
                        error -> Logger.error(
                                "Failed to register Discord slash commands.",
                                error
                        )
                );

    }

    /**
     * Reloads slash commands.
     */
    public void reload() {

        registerCommands();

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