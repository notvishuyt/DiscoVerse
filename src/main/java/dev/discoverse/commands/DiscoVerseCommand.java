package dev.discoverse.commands;

import dev.discoverse.DiscoVerse;
import dev.discoverse.config.ConfigManager;
import dev.discoverse.discord.DiscordManager;
import dev.discoverse.utils.ColorUtil;
import dev.discoverse.utils.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ------------------------------------------------------------
 * DiscoVerse Main Command
 * ------------------------------------------------------------
 *
 * Handles:
 * - /discoverse
 * - /discoverse help
 * - /discoverse version
 * - /discoverse reload
 * - /discoverse ping
 * - /discoverse status
 *
 * Subcommands will later delegate to their own command classes.
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class DiscoVerseCommand implements CommandExecutor, TabCompleter {

    /**
     * Plugin instance.
     */
    private final DiscoVerse plugin;

    /**
     * Config manager.
     */
    private final ConfigManager configManager;

    /**
     * Discord manager.
     */
    private final DiscordManager discordManager;

    /**
     * Constructor.
     *
     * @param plugin Plugin instance.
     */
    public DiscoVerseCommand(@NotNull final DiscoVerse plugin) {

        this.plugin = plugin;
        this.configManager = plugin.getConfigManager();
        this.discordManager = plugin.getDiscordManager();

    }

    /**
     * Returns plugin instance.
     *
     * @return DiscoVerse instance.
     */
    public DiscoVerse getPlugin() {
        return plugin;
    }

    /**
     * Returns configuration manager.
     *
     * @return ConfigManager.
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

    /**
     * Returns Discord manager.
     *
     * @return DiscordManager.
     */
    public DiscordManager getDiscordManager() {
        return discordManager;
    }
  /**
     * Handles the main DiscoVerse command.
     */
    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {

        // Base command
        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {

            case "help" -> {
                sendHelp(sender);
            }

            case "version" -> {
                sender.sendMessage(ColorUtil.color(
                        "&8&m----------------------------------------"));
                sender.sendMessage(ColorUtil.color(
                        "&b&lDiscoVerse"));
                sender.sendMessage(ColorUtil.color(
                        "&7Version: &f" + plugin.getDescription().getVersion()));
                sender.sendMessage(ColorUtil.color(
                        "&7Author: &fNOTVISHUYT"));
                sender.sendMessage(ColorUtil.color(
                        "&8&m----------------------------------------"));
            }

            case "reload" -> {

                if (!sender.hasPermission("discoverse.admin")) {

                    sender.sendMessage(ColorUtil.color(
                            "&cYou don't have permission."));

                    return true;
                }

                sender.sendMessage(ColorUtil.color(
                        "&eReload feature will be implemented later."));
            }

            case "ping" -> {

                sender.sendMessage(ColorUtil.color(
                        "&aDiscord bot ping feature will be implemented later."));
            }

            case "status" -> {

                sender.sendMessage(ColorUtil.color(
                        "&aStatus feature will be implemented later."));
            }

            default -> {

                sender.sendMessage(ColorUtil.color(
                        "&cUnknown subcommand."));

                sendHelp(sender);

            }

        }

        return true;

    }
  /**
     * Sends the help menu.
     *
     * @param sender Command sender.
     */
    private void sendHelp(@NotNull final CommandSender sender) {

        sender.sendMessage(ColorUtil.color("&8&m----------------------------------------"));
        sender.sendMessage(ColorUtil.color("&b&lDiscoVerse &7Commands"));
        sender.sendMessage("");
        sender.sendMessage(ColorUtil.color("&f/discoverse help &8- &7Shows this help menu"));
        sender.sendMessage(ColorUtil.color("&f/discoverse version &8- &7Shows plugin version"));
        sender.sendMessage(ColorUtil.color("&f/discoverse reload &8- &7Reloads the plugin"));
        sender.sendMessage(ColorUtil.color("&f/discoverse ping &8- &7Shows Discord bot ping"));
        sender.sendMessage(ColorUtil.color("&f/discoverse status &8- &7Shows plugin status"));
        sender.sendMessage(ColorUtil.color("&f/link &8- &7Link your Discord account"));
        sender.sendMessage(ColorUtil.color("&f/unlink &8- &7Unlink your Discord account"));
        sender.sendMessage(ColorUtil.color("&8&m----------------------------------------"));

    }

    /**
     * Handles tab completion.
     */
    @Override
    public @Nullable List<String> onTabComplete(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String alias,
            @NotNull String[] args
    ) {

        if (args.length == 1) {

            List<String> suggestions = new ArrayList<>();

            suggestions.add("help");
            suggestions.add("version");
            suggestions.add("reload");
            suggestions.add("ping");
            suggestions.add("status");

            return suggestions;
        }

        return Collections.emptyList();

    }

}