package dev.discoverse.discord;

import dev.discoverse.config.ConfigManager;
import dev.discoverse.utils.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ------------------------------------------------------------
 * DiscoVerse Account Link Manager
 * ------------------------------------------------------------
 *
 * Handles Minecraft ↔ Discord account linking.
 *
 * Features:
 * - Secure verification codes
 * - 15-minute expiration
 * - UUID ↔ Discord ID mapping
 * - Thread-safe collections
 *
 * @author NOTVISHUYT
 * @version 2.0.0
 */
public final class AccountLinkManager {

    /**
     * Verification code lifetime.
     */
    private static final Duration CODE_EXPIRATION =
            Duration.ofMinutes(15);

    /**
     * Verification code characters.
     */
    private static final String CODE_CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * Verification code length.
     */
    private static final int CODE_LENGTH = 6;

    /**
     * Secure random generator.
     */
    private static final SecureRandom RANDOM =
            new SecureRandom();

    /**
     * Configuration manager.
     */
    private final ConfigManager configManager;

    /**
     * Pending verification codes.
     */
    private final Map<UUID, PendingLink> pendingLinks =
            new ConcurrentHashMap<>();

    /**
     * Minecraft UUID -> Discord ID.
     */
    private final Map<UUID, String> minecraftToDiscord =
            new ConcurrentHashMap<>();

    /**
     * Discord ID -> Minecraft UUID.
     */
    private final Map<String, UUID> discordToMinecraft =
            new ConcurrentHashMap<>();

    /**
     * Constructor.
     *
     * @param configManager Configuration manager.
     */
    public AccountLinkManager(
            @NotNull final ConfigManager configManager
    ) {

        this.configManager = configManager;

        Logger.info("AccountLinkManager initialized.");

    }

    /**
     * Represents a pending verification.
     */
    private static final class PendingLink {

        private final String code;
        private final Instant createdAt;
        private final Instant expiresAt;

        private PendingLink(
                final String code,
                final Instant createdAt,
                final Instant expiresAt
        ) {

            this.code = code;
            this.createdAt = createdAt;
            this.expiresAt = expiresAt;

        }

    }
    
    /**
     * Generates a new verification code.
     *
     * @param playerUUID Minecraft player UUID.
     * @return Verification code.
     */
    @NotNull
    public String generateCode(@NotNull UUID playerUUID) {

        removeExpiredCodes();

        String code;

        do {
            code = randomCode();
        } while (isCodeInUse(code));

        PendingLink pendingLink = new PendingLink(
                code,
                Instant.now(),
                Instant.now().plus(CODE_EXPIRATION)
        );

        pendingLinks.put(playerUUID, pendingLink);

        return code;

    }

    /**
     * Returns the player's active verification code.
     *
     * @param playerUUID Player UUID.
     * @return Verification code or null.
     */
    @Nullable
    public String getCode(@NotNull UUID playerUUID) {

        removeExpiredCodes();

        PendingLink pending = pendingLinks.get(playerUUID);

        return pending == null ? null : pending.code;

    }

    /**
     * Checks whether a verification code exists.
     *
     * @param code Verification code.
     * @return true if found.
     */
    public boolean isCodeInUse(@NotNull String code) {

        removeExpiredCodes();

        return pendingLinks.values()
                .stream()
                .anyMatch(link -> link.code.equalsIgnoreCase(code));

    }

    /**
     * Returns the player UUID for a verification code.
     *
     * @param code Verification code.
     * @return Player UUID or null.
     */
    @Nullable
    public UUID getPlayerFromCode(@NotNull String code) {

        removeExpiredCodes();

        for (Map.Entry<UUID, PendingLink> entry : pendingLinks.entrySet()) {

            if (entry.getValue().code.equalsIgnoreCase(code)) {
                return entry.getKey();
            }

        }

        return null;

    }

    /**
     * Removes expired verification codes.
     */
    public void removeExpiredCodes() {

        Instant now = Instant.now();

        pendingLinks.entrySet().removeIf(entry ->
                entry.getValue().expiresAt.isBefore(now));

    }

    /**
     * Generates a random verification code.
     *
     * @return Verification code.
     */
    @NotNull
    private String randomCode() {

        StringBuilder builder = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {

            builder.append(
                    CODE_CHARACTERS.charAt(
                            RANDOM.nextInt(CODE_CHARACTERS.length())
                    )
            );

        }

        return builder.toString();

    }
    
    /**
     * Links a Minecraft account to a Discord account.
     *
     * @param playerUUID Minecraft player UUID.
     * @param discordId Discord user ID.
     * @return true if linked successfully.
     */
    public boolean linkAccount(
            @NotNull UUID playerUUID,
            @NotNull String discordId
    ) {

        removeExpiredCodes();

        // Player must have an active verification code
        if (!pendingLinks.containsKey(playerUUID)) {
            return false;
        }

        // Player is already linked
        if (minecraftToDiscord.containsKey(playerUUID)) {
            return false;
        }

        // Discord account is already linked
        if (discordToMinecraft.containsKey(discordId)) {
            return false;
        }

        minecraftToDiscord.put(playerUUID, discordId);
        discordToMinecraft.put(discordId, playerUUID);

        pendingLinks.remove(playerUUID);

        Logger.info(
                "Linked Minecraft account "
                        + playerUUID
                        + " with Discord account "
                        + discordId
        );

        return true;

    }

    /**
     * Unlinks a Minecraft account.
     *
     * @param playerUUID Player UUID.
     * @return true if unlinked.
     */
    public boolean unlinkAccount(@NotNull UUID playerUUID) {

        String discordId = minecraftToDiscord.remove(playerUUID);

        if (discordId == null) {
            return false;
        }

        discordToMinecraft.remove(discordId);

        Logger.info("Unlinked Minecraft account " + playerUUID);

        return true;

    }

    /**
     * Returns whether the player is linked.
     *
     * @param playerUUID Player UUID.
     * @return true if linked.
     */
    public boolean isLinked(@NotNull UUID playerUUID) {
        return minecraftToDiscord.containsKey(playerUUID);
    }

    /**
     * Gets the Discord ID linked to a Minecraft account.
     *
     * @param playerUUID Player UUID.
     * @return Discord ID or null.
     */
    @Nullable
    public String getDiscordId(@NotNull UUID playerUUID) {
        return minecraftToDiscord.get(playerUUID);
    }

    /**
     * Gets the Minecraft UUID linked to a Discord account.
     *
     * @param discordId Discord ID.
     * @return Minecraft UUID or null.
     */
    @Nullable
    public UUID getMinecraftUUID(@NotNull String discordId) {
        return discordToMinecraft.get(discordId);
    }

    /**
     * Removes a pending verification code.
     *
     * @param playerUUID Player UUID.
     */
    public void removePendingCode(@NotNull UUID playerUUID) {
        pendingLinks.remove(playerUUID);
    }

    /**
     * Clears all cached data.
     */
    public void clear() {

        pendingLinks.clear();
        minecraftToDiscord.clear();
        discordToMinecraft.clear();

    }

    /**
     * Returns the configuration manager.
     *
     * @return Configuration manager.
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

                               }
