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