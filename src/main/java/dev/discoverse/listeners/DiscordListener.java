package dev.discoverse.listeners;

import dev.discoverse.DiscoVerse;
import dev.discoverse.discord.AccountLinkManager;
import dev.discoverse.utils.Logger;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * ------------------------------------------------------------
 * DiscoVerse Discord Listener
 * ------------------------------------------------------------
 *
 * Handles all Discord related events.
 *
 * Events:
 * • Bot Ready
 * • Slash Commands
 * • Messages
 * • Member Join
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class DiscordListener extends ListenerAdapter {

    private final DiscoVerse plugin;
    private final AccountLinkManager accountLinkManager;

    public DiscordListener(@NotNull DiscoVerse plugin) {

        this.plugin = plugin;
        this.accountLinkManager = plugin.getAccountLinkManager();

    }

    /**
     * Called when the Discord bot is ready.
     */
    @Override
    public void onReady(@NotNull ReadyEvent event) {

        Logger.info("Discord bot is ready.");

        // TODO
        // Update bot presence
        // Register slash commands
        // Initialize webhooks

    }

    /**
     * Handles Discord slash commands.
     */
    @Override
    public void onSlashCommandInteraction(
            @NotNull SlashCommandInteractionEvent event
    ) {

        // TODO
        // /link
        // /unlink
        // /ping
        // /status
        // /help

    }

    /**
     * Handles received Discord messages.
     */
    @Override
    public void onMessageReceived(
            @NotNull MessageReceivedEvent event
    ) {

        if (event.getAuthor().isBot()) {
            return;
        }

        // TODO
        // Chat Bridge
        // Message Logging
        // Moderation

    }

    /**
     * Handles new Discord members.
     */
    @Override
    public void onGuildMemberJoin(
            @NotNull GuildMemberJoinEvent event
    ) {

        // TODO
        // Welcome Message
        // Auto Role
        // Link Reminder

    }

}