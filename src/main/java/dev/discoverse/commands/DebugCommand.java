package dev.discoverse.commands;

import dev.discoverse.DiscoVerse;
import dev.discoverse.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * ------------------------------------------------------------
 * DiscoVerse Debug Command
 * ------------------------------------------------------------
 *
 * Shows useful debug information about the plugin.
 *
 * Usage:
 * /discoverse debug
 *
 * Permission:
 * discoverse.admin
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class DebugCommand implements CommandExecutor {

    private final DiscoVerse plugin;

    public DebugCommand(@NotNull DiscoVerse plugin) {
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

        Runtime runtime = Runtime.getRuntime();

        long maxMemory = runtime.maxMemory() / 1024 / 1024;
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long freeMemory = runtime.freeMemory() / 1024 / 1024;
        long usedMemory = totalMemory - freeMemory;

        sender.sendMessage(ColorUtil.color("&8&m----------------------------------------"));
        sender.sendMessage(ColorUtil.color("&b&lDiscoVerse Debug"));
        sender.sendMessage(ColorUtil.color("&7Plugin Version: &f" + plugin.getDescription().getVersion()));
        sender.sendMessage(ColorUtil.color("&7Minecraft Version: &f" + Bukkit.getVersion()));
        sender.sendMessage(ColorUtil.color("&7Java Version: &f" + System.getProperty("java.version")));
        sender.sendMessage(ColorUtil.color("&7Online Players: &f" + Bukkit.getOnlinePlayers().size()));
        sender.sendMessage(ColorUtil.color("&7Discord Manager: &f" + (plugin.getDiscordManager() != null ? "&aLoaded" : "&cNot Loaded")));
        sender.sendMessage(ColorUtil.color("&7Memory Usage: &f" + usedMemory + "MB / " + maxMemory + "MB"));
        sender.sendMessage(ColorUtil.color("&8&m----------------------------------------"));

        return true;
    }
}