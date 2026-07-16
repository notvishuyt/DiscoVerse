package dev.discoverse.utils;

import org.bukkit.Bukkit;

/**
 * ------------------------------------------------------------
 * DiscoVerse Version Utility
 * ------------------------------------------------------------
 *
 * Provides information about the running server and
 * Minecraft version.
 *
 * Features:
 * - Minecraft Version
 * - Bukkit Version
 * - Server Brand
 * - Paper Detection
 * - Folia Detection
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class VersionUtil {

    /**
     * Prevent instantiation.
     */
    private VersionUtil() {
        throw new UnsupportedOperationException(
                "VersionUtil is a utility class and cannot be instantiated."
        );
    }

    /**
     * Returns the Minecraft version.
     *
     * @return Minecraft version.
     */
    public static String getMinecraftVersion() {
        return Bukkit.getMinecraftVersion();
    }

    /**
     * Returns the Bukkit version.
     *
     * @return Bukkit version.
     */
    public static String getBukkitVersion() {
        return Bukkit.getBukkitVersion();
    }

    /**
     * Returns the server implementation name.
     *
     * @return Server name.
     */
    public static String getServerBrand() {
        return Bukkit.getName();
    }

    /**
     * Checks whether the server is Paper.
     *
     * @return true if Paper.
     */
    public static boolean isPaper() {
        return getServerBrand().equalsIgnoreCase("Paper");
    }

    /**
     * Checks whether the server is Folia.
     *
     * @return true if Folia.
     */
    public static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException exception) {
            return false;
        }
    }

    /**
     * Returns the complete server version string.
     *
     * @return Full server version.
     */
    public static String getServerVersion() {
        return Bukkit.getVersion();
    }

    /**
     * Checks if the server version starts with the given version.
     *
     * Example: supports("1.21")
     *
     * @param version Version prefix.
     * @return true if supported.
     */
    public static boolean supports(final String version) {
        return getMinecraftVersion().startsWith(version);
    }

}