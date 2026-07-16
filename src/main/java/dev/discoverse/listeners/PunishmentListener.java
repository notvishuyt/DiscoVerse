package dev.discoverse.listeners;

import dev.discoverse.DiscoVerse;
import dev.discoverse.utils.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

/**
 * ------------------------------------------------------------
 * DiscoVerse Punishment Listener
 * ------------------------------------------------------------
 *
 * Handles punishment related events.
 *
 * Events:
 * • Player Kick
 * • Player Login
 * • Punishment Logging
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class PunishmentListener implements Listener {

    private final DiscoVerse plugin;

    public PunishmentListener(@NotNull DiscoVerse plugin) {
        this.plugin = plugin;
    }

    /**
     * Called when a player is kicked.
     */
    @EventHandler
    public void onPlayerKick(@NotNull PlayerKickEvent event) {

        // TODO
        // Discord kick log
        // Punishment history
        // Staff notification

        Logger.info(event.getPlayer().getName() + " was kicked.");

    }

    /**
     * Called when a player attempts to join.
     */
    @EventHandler
    public void onPlayerLogin(@NotNull PlayerLoginEvent event) {

        // TODO
        // Ban synchronization
        // Discord verification check
        // Temporary punishment checks

    }

    /**
     * Called after a punished player leaves.
     */
    @EventHandler
    public void onPlayerQuit(@NotNull PlayerQuitEvent event) {

        // TODO
        // Cleanup punishment cache
        // Update moderation data

    }

}