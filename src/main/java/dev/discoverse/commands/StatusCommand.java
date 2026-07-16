package dev.discoverse.commands;

import dev.discoverse.DiscoVerse;
import dev.discoverse.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * ------------------------------------------------------------
 * DiscoVerse Status Command
 * ------------------------------------------------------------
 *
 * Shows the current status of the plugin.
 *
 * Usage:
 * /discoverse status
 *
 * Permission:
 * discoverse.admin
 */
public final class StatusCommand implements CommandExecutor {

    private final DiscoVerse plugin;

    public StatusCommand(@NotNull DiscoVerse plugin) {
        this.plugin = plugin;
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
        sender.sendMessage(ColorUtil.color("&b&lDiscoVerse Status"));

        sender.sendMessage(ColorUtil.color("&7Plugin: &aEnabled"));
        sender.sendMessage(ColorUtil.color("&7Version: &f" + plugin.getDescription().getVersion()));

        if (plugin.getDiscordManager() != null) {
            sender.sendMessage(ColorUtil.color("&7Discord: &aConnected"));
        } else {
            sender.sendMessage(ColorUtil.color("&7Discord: &cDisconnected"));
        }

        sender.sendMessage(ColorUtil.color("&8&m----------------------------------------"));

        return true;
    }
}