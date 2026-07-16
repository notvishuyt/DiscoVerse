package dev.discoverse.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.text.minimessage.MiniMessage;

/**
 * ------------------------------------------------------------
 * DiscoVerse Color Utility
 * ------------------------------------------------------------
 *
 * Handles all text formatting for DiscoVerse.
 *
 * Features:
 * - Legacy (&) colors
 * - MiniMessage support
 * - RGB/Hex colors
 * - Gradients
 * - Plain text conversion
 * - Color stripping
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class ColorUtil {

    /**
     * MiniMessage serializer.
     */
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    /**
     * Legacy serializer.
     */
    private static final LegacyComponentSerializer LEGACY =
            LegacyComponentSerializer.legacyAmpersand();

    /**
     * Plain text serializer.
     */
    private static final PlainTextComponentSerializer PLAIN =
            PlainTextComponentSerializer.plainText();

    /**
     * Prevent instantiation.
     */
    private ColorUtil() {
        throw new UnsupportedOperationException(
                "ColorUtil is a utility class and cannot be instantiated."
        );
    }

    /**
     * Automatically formats a string.
     *
     * @param text Input text.
     * @return Adventure Component.
     */
    public static Component color(final String text) {

        if (text == null || text.isBlank()) {
            return Component.empty();
        }

        if (isMiniMessage(text)) {
            return MINI_MESSAGE.deserialize(text);
        }

        return LEGACY.deserialize(text);
    }

    /**
     * Alias for color().
     *
     * @param text Input text.
     * @return Component.
     */
    public static Component component(final String text) {
        return color(text);
    }

    /**
     * Converts a Component into plain text.
     *
     * @param component Component.
     * @return Plain text.
     */
    public static String plain(final Component component) {
        return PLAIN.serialize(component);
    }

    /**
     * Converts a Component into MiniMessage.
     *
     * @param component Component.
     * @return MiniMessage string.
     */
    public static String serialize(final Component component) {
        return MINI_MESSAGE.serialize(component);
    }

    /**
     * Removes formatting from a string.
     *
     * @param text Input text.
     * @return Plain text.
     */
    public static String strip(final String text) {
        return plain(color(text));
    }

    /**
     * Detects MiniMessage formatting.
     *
     * @param text Input text.
     * @return true if MiniMessage.
     */
    public static boolean isMiniMessage(final String text) {

        if (text == null) {
            return false;
        }

        return text.contains("<")
                && text.contains(">");
    }

    /**
     * Detects legacy color codes.
     *
     * @param text Input text.
     * @return true if legacy colors exist.
     */
    public static boolean isLegacy(final String text) {

        if (text == null) {
            return false;
        }

        return text.contains("&");
    }

    /**
     * Detects hex colors.
     *
     * @param text Input text.
     * @return true if hex colors exist.
     */
    public static boolean hasHex(final String text) {

        if (text == null) {
            return false;
        }

        return text.matches(".*#[A-Fa-f0-9]{6}.*");
    }

    /**
     * Detects gradient tags.
     *
     * @param text Input text.
     * @return true if gradients exist.
     */
    public static boolean hasGradient(final String text) {

        if (text == null) {
            return false;
        }

        return text.contains("<gradient:");
    }

}