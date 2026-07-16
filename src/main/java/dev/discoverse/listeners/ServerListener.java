package dev.discoverse.listeners;

import dev.discoverse.DiscoVerse;
import dev.discoverse.utils.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.jetbrains.annotations.NotNull;

/**
 * ------------------------------------------------------------
 * DiscoVerse Server Listener
 * ------------------------------------------------------------
 *
 * Handles server related events.
 *
 * Events:
 * • Server Load
 * • Plugin Enable
 * • Plugin Disable
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class ServerListener implements Listener {

    private final DiscoVerse plugin;

    public ServerListener(@NotNull DiscoVerse plugin) {
        this.plugin = plugin;
    }

    /**
     * Called when the server has finished loading.
     */
    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {

        Logger.info("Server has finished loading.");

        // TODO
        // Initialize Discord presence
        // Register slash commands
        // Start scheduled tasks

    }

    /**
     * Called when any plugin is enabled.
     */
    @EventHandler
    public void onPluginEnable(PluginEnableEvent event) {

        // TODO
        // Detect PlaceholderAPI
        // Detect LuckPerms
        // Detect Vault

    }

    /**
     * Called when any plugin is disabled.
     */
    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {

        if (event.getPlugin().equals(plugin)) {

            Logger.info("DiscoVerse is shutting down.");

            // TODO
            // Shutdown Discord bot
            // Save pending data
            // Cleanup resources

        }

    }

}