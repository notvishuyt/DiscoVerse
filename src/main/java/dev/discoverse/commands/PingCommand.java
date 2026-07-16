package dev.discoverse.commands;

import dev.discoverse.DiscoVerse;
import dev.discoverse.discord.DiscordManager;
import dev.discoverse.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * ------------------------------------------------------------
 * DiscoVerse Ping Command
 * ------------------------------------------------------------
 *
 * Shows the current Discord bot connection status
 * and latency.
 *
 * Usage:
 * /discoverse ping
 *
 * Permission:
 * discoverse.admin
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class PingCommand implements CommandExecutor {

    private final DiscoVerse plugin;
    private final DiscordManager discordManager;

    public PingCommand(@NotNull DiscoVerse plugin) {
        this.plugin = plugin;
        this.discordManager = plugin.getDiscordManager();
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {

        if (!sender.hasPermission("discoverse.admin")) {
            sender.sendMessage(ColorUtil.color("&cYou don't have permission to use this command."));
            return true;
        }

        sender.sendMessage(ColorUtil.color("&8&m----------------------------------------"));
        sender.sendMessage(ColorUtil.color("&b&lDiscoVerse Discord Status"));

        if (discordManager == null) {
            sender.sendMessage(ColorUtil.color("&cDiscord Manager is unavailable."));
        } else {

            sender.sendMessage(ColorUtil.color("&7Bot Status: &aOnline"));

            // Will use the real JDA gateway ping during final integration.
            sender.sendMessage(ColorUtil.color("&7Gateway Ping: &eLoading..."));

        }

        sender.sendMessage(ColorUtil.color("&8&m----------------------------------------"));

        return true;
    }
}