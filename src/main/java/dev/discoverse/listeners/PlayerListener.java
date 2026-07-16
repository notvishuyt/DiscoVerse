package dev.discoverse.listeners;

import dev.discoverse.DiscoVerse;
import dev.discoverse.discord.AccountLinkManager;
import dev.discoverse.utils.ColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

/**
 * ------------------------------------------------------------
 * DiscoVerse Player Listener
 * ------------------------------------------------------------
 *
 * Handles all player related events.
 *
 * Events:
 * • Join
 * • Quit
 * • Chat
 * • Death
 * • Commands
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class PlayerListener implements Listener {

    private final DiscoVerse plugin;
    private final AccountLinkManager accountLinkManager;

    public PlayerListener(@NotNull DiscoVerse plugin) {

        this.plugin = plugin;
        this.accountLinkManager = plugin.getAccountLinkManager();

    }

    /**
     * Player Join
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (!accountLinkManager.isLinked(player.getUniqueId())) {

            player.sendMessage(ColorUtil.color(
                    "&eYour Discord account is not linked."
            ));

            player.sendMessage(ColorUtil.color(
                    "&7Use &f/link &7to link your Discord account."
            ));

        }

        // TODO
        // Discord Join Log
        // Role Synchronization
        // Presence Update

    }

    /**
     * Player Quit
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        // TODO
        // Discord Quit Log
        // Presence Update

    }

    /**
     * Player Chat
     */
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        // TODO
        // Chat Bridge
        // Chat Formatting

    }

    /**
     * Player Death
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();

        // TODO
        // Discord Death Log

    }

    /**
     * Player Commands
     */
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();

        // TODO
        // Command Logging
        // Command Bridge

    }

}