package dev.discoverse.commands;

import dev.discoverse.DiscoVerse;
import dev.discoverse.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * ------------------------------------------------------------
 * DiscoVerse Reload Command
 * ------------------------------------------------------------
 *
 * Reloads all plugin configuration files.
 *
 * Usage:
 * /discoverse reload
 *
 * Permission:
 * discoverse.admin
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class ReloadCommand implements CommandExecutor {

    private final DiscoVerse plugin;

    public ReloadCommand(@NotNull DiscoVerse plugin) {
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

        try {

            plugin.reloadConfig();

            // These will be connected once ConfigManager is finalized.
            // plugin.getConfigManager().reload();

            sender.sendMessage(ColorUtil.color("&aDiscoVerse has been reloaded successfully."));
            sender.sendMessage(ColorUtil.color("&7Configuration files have been refreshed."));

        } catch (Exception exception) {

            sender.sendMessage(ColorUtil.color("&cFailed to reload DiscoVerse."));
            exception.printStackTrace();

        }

        return true;
    }
}
