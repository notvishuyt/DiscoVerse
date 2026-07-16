package dev.discoverse.utils;

import org.bukkit.Bukkit;

import java.util.logging.Level;

/**
 * ------------------------------------------------------------
 * DiscoVerse Logger
 * ------------------------------------------------------------
 *
 * Handles all console logging for the plugin.
 *
 * Features:
 * - INFO logs
 * - WARNING logs
 * - ERROR logs
 * - DEBUG logs
 * - Automatic DISCOVERSE prefix
 * - Future-ready for file logging
 * - Future-ready for Discord logging
 *
 * Java Version : 21
 * Paper Version: 1.21+
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class Logger {

    /**
     * Plugin prefix shown in console.
     */
    private static final String PREFIX = "[DISCOVERSE] ";

    /**
     * Prevent class instantiation.
     */
    private Logger() {
        throw new UnsupportedOperationException(
                "Logger is a utility class and cannot be instantiated."
        );
    }

    /**
     * Sends an INFO message.
     *
     * @param message Message to send.
     */
    public static void info(final String message) {
        Bukkit.getLogger().log(
                Level.INFO,
                PREFIX + message
        );
    }

    /**
     * Sends a WARNING message.
     *
     * @param message Message to send.
     */
    public static void warn(final String message) {
        Bukkit.getLogger().log(
                Level.WARNING,
                PREFIX + message
        );
    }

    /**
     * Sends an ERROR message.
     *
     * @param message Message to send.
     */
    public static void error(final String message) {
        Bukkit.getLogger().log(
                Level.SEVERE,
                PREFIX + message
        );
    }

    /**
     * Sends an ERROR message with an exception.
     *
     * @param message Message to send.
     * @param throwable Exception to log.
     */
    public static void error(final String message, final Throwable throwable) {
        Bukkit.getLogger().log(
                Level.SEVERE,
                PREFIX + message,
                throwable
        );
    }

    /**
     * Sends a DEBUG message.
     *
     * Debug messages are only printed when
     * debug mode is enabled.
     *
     * @param message Message to send.
     */
    public static void debug(final String message) {

        if (!isDebugEnabled()) {
            return;
        }

        Bukkit.getLogger().log(
                Level.INFO,
                PREFIX + "[DEBUG] " + message
        );
    }

    /**
     * Logs an exception in debug mode.
     *
     * @param throwable Exception.
     */
    public static void debug(final Throwable throwable) {

        if (!isDebugEnabled()) {
            return;
        }

        Bukkit.getLogger().log(
                Level.SEVERE,
                PREFIX + "[DEBUG] Exception occurred.",
                throwable
        );
    }

    /**
     * Checks whether debug mode is enabled.
     *
     * Temporary implementation.
     * Will later read the value from ConfigManager.
     *
     * @return true if debug mode is enabled.
     */
    private static boolean isDebugEnabled() {
        return false;
    }

    /**
     * Future hook for logging messages to a file.
     *
     * This method is intentionally left empty for v1.0.
     * File logging will be implemented in a future update.
     *
     * @param level Logging level.
     * @param message Message to log.
     */
    @SuppressWarnings("unused")
    private static void logToFile(final Level level, final String message) {
        // TODO: Implement file logging.
    }

    /**
     * Future hook for logging messages to Discord.
     *
     * This method is intentionally left empty for v1.0.
     * Discord logging will be implemented in a future update.
     *
     * @param level Logging level.
     * @param message Message to log.
     */
    @SuppressWarnings("unused")
    private static void logToDiscord(final Level level, final String message) {
        // TODO: Implement Discord logging.
    }

}