package dev.discoverse.discord;

import net.dv8tion.jda.api.JDA;

/**
 * ------------------------------------------------------------
 * DiscoVerse JDA Provider
 * ------------------------------------------------------------
 *
 * Stores the active JDA instance used throughout
 * the plugin.
 *
 * Every Discord-related class should access the
 * bot through this provider instead of storing
 * separate JDA references.
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class JDAProvider {

    /**
     * Active JDA instance.
     */
    private static JDA jda;

    /**
     * Prevent instantiation.
     */
    private JDAProvider() {
        throw new UnsupportedOperationException(
                "JDAProvider is a utility class."
        );
    }

    /**
     * Sets the active JDA instance.
     *
     * @param instance Active JDA instance.
     */
    public static void set(final JDA instance) {
        jda = instance;
    }

    /**
     * Returns the active JDA instance.
     *
     * @return JDA instance.
     */
    public static JDA get() {
        return jda;
    }

    /**
     * Returns whether JDA has been initialized.
     *
     * @return true if available.
     */
    public static boolean isReady() {
        return jda != null;
    }

    /**
     * Clears the stored JDA instance.
     */
    public static void clear() {
        jda = null;
    }

}