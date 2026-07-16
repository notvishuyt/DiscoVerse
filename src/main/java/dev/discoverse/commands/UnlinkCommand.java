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
 * DiscoVerse Unlink Command
 * ------------------------------------------------------------
 *
 * Unlinks a Minecraft account from Discord.
 *
 * Usage:
 * /unlink
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class UnlinkCommand implements CommandExecutor {

    private final DiscoVerse plugin;
    private final AccountLinkManager accountLinkManager;

    public UnlinkCommand(@NotNull DiscoVerse plugin) {
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
            sender.sendMessage(ColorUtil.color("&cOnly players can use this command."));
            return true;
        }

        if (!accountLinkManager.isLinked(player.getUniqueId())) {
            player.sendMessage(ColorUtil.color("&cYour Minecraft account is not linked."));
            return true;
        }

        boolean success = accountLinkManager.unlinkAccount(player.getUniqueId());

        if (success) {
            accountLinkManager.removePendingCode(player.getUniqueId());

            player.sendMessage(ColorUtil.color("&aYour Discord account has been successfully unlinked."));
        } else {
            player.sendMessage(ColorUtil.color("&cFailed to unlink your account."));
        }

        return true;
    }
}