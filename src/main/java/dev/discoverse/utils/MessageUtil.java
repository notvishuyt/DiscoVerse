package dev.discoverse.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * ------------------------------------------------------------
 * DiscoVerse Message Utility
 * ------------------------------------------------------------
 *
 * Handles sending formatted messages.
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class MessageUtil {

    /**
     * Prevent instantiation.
     */
    private MessageUtil() {
        throw new UnsupportedOperationException(
                "MessageUtil is a utility class."
        );
    }

    /**
     * Sends a formatted message.
     *
     * @param sender Command sender.
     * @param message Message.
     */
    public static void send(
            final CommandSender sender,
            final String message
    ) {

        if (sender == null || message == null) {
            return;
        }

        sender.sendMessage(
                ColorUtil.color(message)
        );
    }

    /**
     * Sends a formatted message to a player.
     *
     * @param player Player.
     * @param message Message.
     */
    public static void send(
            final Player player,
            final String message
    ) {

        if (player == null || message == null) {
            return;
        }

        player.sendMessage(
                ColorUtil.color(message)
        );
    }

    /**
     * Broadcasts a message.
     *
     * @param message Message.
     */
    public static void broadcast(
            final String message
    ) {

        Component component = ColorUtil.color(message);

        Bukkit.broadcast(component);
    }

    /**
     * Replaces placeholders.
     *
     * Example:
     * %player%
     *
     * @param text Text.
     * @param placeholder Placeholder.
     * @param value Value.
     * @return Updated text.
     */
    public static String placeholder(
            final String text,
            final String placeholder,
            final String value
    ) {

        if (text == null) {
            return "";
        }

        return text.replace(placeholder, value);
    }

    /**
     * Parses a message.
     *
     * Currently only applies colors.
     * Future versions will include:
     * - PlaceholderAPI
     * - Custom placeholders
     * - Config messages
     *
     * @param text Input.
     * @return Component.
     */
    public static Component parse(
            final String text
    ) {
        return ColorUtil.color(text);
    }

    /**
     * Reload hook.
     *
     * Future implementation.
     */
    public static void reload() {
        // Reserved for future updates.
    }

}