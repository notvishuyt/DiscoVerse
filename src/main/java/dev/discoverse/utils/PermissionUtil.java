package dev.discoverse.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * ------------------------------------------------------------
 * DiscoVerse Permission Utility
 * ------------------------------------------------------------
 *
 * Handles permission checking throughout the plugin.
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class PermissionUtil {

    /**
     * Prevent instantiation.
     */
    private PermissionUtil() {
        throw new UnsupportedOperationException(
                "PermissionUtil is a utility class."
        );
    }

    /**
     * Checks if a sender has a permission.
     *
     * @param sender Command sender.
     * @param permission Permission node.
     * @return true if permitted.
     */
    public static boolean hasPermission(
            final CommandSender sender,
            final String permission
    ) {
        return sender != null && sender.hasPermission(permission);
    }

    /**
     * Checks if a player has a permission.
     *
     * @param player Player.
     * @param permission Permission node.
     * @return true if permitted.
     */
    public static boolean hasPermission(
            final Player player,
            final String permission
    ) {
        return player != null && player.hasPermission(permission);
    }

    /**
     * Checks if a sender has at least one permission.
     *
     * @param sender Command sender.
     * @param permissions Permission list.
     * @return true if any permission matches.
     */
    public static boolean hasAnyPermission(
            final CommandSender sender,
            final String... permissions
    ) {

        if (sender == null || permissions == null) {
            return false;
        }

        for (String permission : permissions) {
            if (sender.hasPermission(permission)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Sends a no-permission message.
     *
     * @param sender Command sender.
     */
    public static void sendNoPermission(
            final CommandSender sender
    ) {

        if (sender == null) {
            return;
        }

        sender.sendMessage(
                ColorUtil.color(
                        "<red>You do not have permission to use this command."
                )
        );
    }

    /**
     * Checks permission and sends the
     * no-permission message automatically.
     *
     * @param sender Command sender.
     * @param permission Permission node.
     * @return true if allowed.
     */
    public static boolean requirePermission(
            final CommandSender sender,
            final String permission
    ) {

        if (hasPermission(sender, permission)) {
            return true;
        }

        sendNoPermission(sender);
        return false;
    }

}