package dev.discoverse.discord;

import dev.discoverse.config.ConfigManager;
import dev.discoverse.utils.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

/**
 * ------------------------------------------------------------
 * DiscoVerse Role Sync Manager
 * ------------------------------------------------------------
 *
 * Handles synchronization between Minecraft
 * permissions/groups and Discord roles.
 *
 * Future Features:
 * - LuckPerms integration
 * - Automatic role synchronization
 * - Rank synchronization
 * - Join role assignment
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class RoleSyncManager {

    private final ConfigManager configManager;

    /**
     * Creates a new RoleSyncManager.
     *
     * @param configManager Configuration manager.
     */
    public RoleSyncManager(final ConfigManager configManager) {
        this.configManager = configManager;
    }

    /**
     * Synchronizes a Discord role.
     *
     * @param guildId Discord guild ID.
     * @param discordUserId Discord user ID.
     * @param roleId Discord role ID.
     */
    public void syncRole(
            final String guildId,
            final String discordUserId,
            final String roleId
    ) {

        final JDA jda = JDAProvider.get();

        if (jda == null) {

            Logger.warn("Cannot synchronize roles because JDA is not ready.");

            return;
        }

        final Guild guild = jda.getGuildById(guildId);

        if (guild == null) {

            Logger.warn("Guild not found: " + guildId);

            return;
        }

        final Member member = guild.retrieveMemberById(discordUserId).complete();

        if (member == null) {

            Logger.warn("Member not found: " + discordUserId);

            return;
        }

        final Role role = guild.getRoleById(roleId);

        if (role == null) {

            Logger.warn("Role not found: " + roleId);

            return;
        }

        guild.addRoleToMember(member, role).queue();

        Logger.info("Discord role synchronized successfully.");

    }

    /**
     * Removes a Discord role.
     *
     * @param guildId Discord guild ID.
     * @param discordUserId Discord user ID.
     * @param roleId Discord role ID.
     */
    public void removeRole(
            final String guildId,
            final String discordUserId,
            final String roleId
    ) {

        final JDA jda = JDAProvider.get();

        if (jda == null) {

            Logger.warn("Cannot remove role because JDA is not ready.");

            return;
        }

        final Guild guild = jda.getGuildById(guildId);

        if (guild == null) {

            Logger.warn("Guild not found: " + guildId);

            return;
        }

        final Member member = guild.retrieveMemberById(discordUserId).complete();

        if (member == null) {

            Logger.warn("Member not found: " + discordUserId);

            return;
        }

        final Role role = guild.getRoleById(roleId);

        if (role == null) {

            Logger.warn("Role not found: " + roleId);

            return;
        }

        guild.removeRoleFromMember(member, role).queue();

        Logger.info("Discord role removed successfully.");

    }

    /**
     * Reload hook.
     */
    public void reload() {

        Logger.info("RoleSyncManager reloaded.");

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