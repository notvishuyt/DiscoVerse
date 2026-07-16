package dev.discoverse.utils;

import java.util.UUID;

/**
 * ------------------------------------------------------------
 * DiscoVerse Skin Utility
 * ------------------------------------------------------------
 *
 * Utility methods for Minecraft player skins.
 *
 * Features:
 * - Avatar URL
 * - Body URL
 * - Head URL
 * - UUID support
 * - Username support
 *
 * Future:
 * - Mojang API
 * - Caching
 * - Skin downloading
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class SkinUtil {

    /**
     * Prevent instantiation.
     */
    private SkinUtil() {
        throw new UnsupportedOperationException(
                "SkinUtil is a utility class."
        );
    }

    /**
     * Returns a player's avatar URL.
     *
     * @param username Minecraft username.
     * @return Avatar URL.
     */
    public static String getAvatarUrl(final String username) {
        return "https://mc-heads.net/avatar/" + username;
    }

    /**
     * Returns a player's head URL.
     *
     * @param username Minecraft username.
     * @return Head URL.
     */
    public static String getHeadUrl(final String username) {
        return "https://mc-heads.net/head/" + username;
    }

    /**
     * Returns a player's full body render URL.
     *
     * @param username Minecraft username.
     * @return Body URL.
     */
    public static String getBodyUrl(final String username) {
        return "https://mc-heads.net/body/" + username;
    }

    /**
     * Returns an avatar URL using UUID.
     *
     * @param uuid Player UUID.
     * @return Avatar URL.
     */
    public static String getAvatarUrl(final UUID uuid) {
        return "https://mc-heads.net/avatar/" + uuid;
    }

    /**
     * Returns a head URL using UUID.
     *
     * @param uuid Player UUID.
     * @return Head URL.
     */
    public static String getHeadUrl(final UUID uuid) {
        return "https://mc-heads.net/head/" + uuid;
    }

    /**
     * Returns a body URL using UUID.
     *
     * @param uuid Player UUID.
     * @return Body URL.
     */
    public static String getBodyUrl(final UUID uuid) {
        return "https://mc-heads.net/body/" + uuid;
    }

    /**
     * Checks whether a username is valid.
     *
     * @param username Username.
     * @return true if valid.
     */
    public static boolean isValidUsername(final String username) {

        if (username == null) {
            return false;
        }

        return username.matches("^[A-Za-z0-9_]{3,16}$");
    }

}