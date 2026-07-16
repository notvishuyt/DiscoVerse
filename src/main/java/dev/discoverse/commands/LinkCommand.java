package dev.discoverse.commands;

import dev.discoverse.DiscoVerse;
import dev.discoverse.discord.AccountLinkManager;
import dev.discoverse.utils.ColorUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * ------------------------------------------------------------
 * DiscoVerse Link Command
 * ------------------------------------------------------------
 *
 * Generates a verification code that can be used
 * inside Discord to link a Minecraft account.
 *
 * Usage:
 * /link
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class LinkCommand implements CommandExecutor {

    private final DiscoVerse plugin;
    private final AccountLinkManager accountLinkManager;

    public LinkCommand(@NotNull DiscoVerse plugin) {
        this.plugin = plugin;
        this.accountLinkManager = plugin.getAccountLinkManager();
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {

        if (!(sender instanceof Player player)) {

            sender.sendMessage(ColorUtil.color(
                    "&cOnly players can use this command."
            ));

            return true;
        }

        if (accountLinkManager.isLinked(player.getUniqueId())) {

            player.sendMessage(ColorUtil.color(
                    "&cYour Minecraft account is already linked."
            ));

            return true;
        }

        String code = accountLinkManager.generateCode(player.getUniqueId());

        player.sendMessage(ColorUtil.color("&8&m----------------------------------------"));
        player.sendMessage(ColorUtil.color("&b&lDiscoVerse Account Linking"));
        player.sendMessage("");
        player.sendMessage(ColorUtil.color("&7Verification Code:"));
        player.sendMessage(ColorUtil.color("&a&l" + code));
        player.sendMessage("");
        player.sendMessage(ColorUtil.color("&7This code expires in &e15 minutes&7."));
        player.sendMessage(ColorUtil.color("&7Use the following command in Discord:"));
        player.sendMessage(ColorUtil.color("&f/link " + code));
        player.sendMessage(ColorUtil.color("&8&m----------------------------------------"));

        return true;
    }
  }