package dev.discoverse.discord;

import dev.discoverse.DiscoVerse;
import dev.discoverse.listeners.AfkListener;
import dev.discoverse.listeners.DiscordListener;
import dev.discoverse.listeners.PlayerListener;
import dev.discoverse.listeners.PunishmentListener;
import dev.discoverse.listeners.ServerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

/**
 * ------------------------------------------------------------
 * DiscoVerse Event Manager
 * ------------------------------------------------------------
 *
 * Registers all Bukkit and Discord event listeners.
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class EventManager {

    private final DiscoVerse plugin;

    /**
     * Creates a new EventManager.
     *
     * @param plugin Plugin instance.
     */
    public EventManager(@NotNull DiscoVerse plugin) {
        this.plugin = plugin;
    }

    /**
     * Registers all Bukkit and Discord listeners.
     */
    public void registerEvents() {

        PluginManager pluginManager = Bukkit.getPluginManager();

        // =========================================================
        // Bukkit Listeners
        // =========================================================

        pluginManager.registerEvents(new PlayerListener(plugin), plugin);
        pluginManager.registerEvents(new ServerListener(plugin), plugin);
        pluginManager.registerEvents(new PunishmentListener(plugin), plugin);
        pluginManager.registerEvents(new AfkListener(plugin), plugin);

        // =========================================================
        // Discord Listener
        // =========================================================

        DiscordManager discordManager = plugin.getDiscordManager();

        if (discordManager != null && discordManager.getJDA() != null) {

            discordManager
                    .getJDA()
                    .addEventListener(new DiscordListener(plugin));

        }

    }

    /**
     * Unregisters events.
     *
     * Reserved for future use.
     */
    public void unregisterEvents() {

        // Bukkit automatically unregisters all listeners
        // when the plugin is disabled.

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