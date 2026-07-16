package dev.discoverse.listeners;

import dev.discoverse.DiscoVerse;
import dev.discoverse.utils.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ------------------------------------------------------------
 * DiscoVerse AFK Listener
 * ------------------------------------------------------------
 *
 * Handles AFK tracking.
 *
 * Features:
 * • AFK cache
 * • Movement detection
 * • Chat activity
 * • Join/Quit cleanup
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class AfkListener implements Listener {

    private final DiscoVerse plugin;

    /**
     * AFK players cache.
     */
    private final Map<UUID, Boolean> afkPlayers =
            new ConcurrentHashMap<>();

    public AfkListener(@NotNull DiscoVerse plugin) {
        this.plugin = plugin;
    }

    /**
     * Player joins.
     */
    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent event) {

        afkPlayers.put(event.getPlayer().getUniqueId(), false);

    }

    /**
     * Player quits.
     */
    @EventHandler
    public void onQuit(@NotNull PlayerQuitEvent event) {

        afkPlayers.remove(event.getPlayer().getUniqueId());

    }

    /**
     * Player moves.
     */
    @EventHandler
    public void onMove(@NotNull PlayerMoveEvent event) {

        Player player = event.getPlayer();

        afkPlayers.put(player.getUniqueId(), false);

    }

    /**
     * Player chats.
     */
    @EventHandler
    public void onChat(@NotNull AsyncPlayerChatEvent event) {

        afkPlayers.put(event.getPlayer().getUniqueId(), false);

    }

    /**
     * Checks whether a player is AFK.
     *
     * @param uuid Player UUID.
     * @return true if AFK.
     */
    public boolean isAfk(@NotNull UUID uuid) {

        return afkPlayers.getOrDefault(uuid, false);

    }

    /**
     * Sets AFK state.
     *
     * @param uuid Player UUID.
     * @param afk AFK state.
     */
    public void setAfk(@NotNull UUID uuid, boolean afk) {

        afkPlayers.put(uuid, afk);

        Logger.info("AFK Status Updated: " + uuid + " -> " + afk);

    }

}