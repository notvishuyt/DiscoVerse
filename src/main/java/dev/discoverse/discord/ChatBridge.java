package dev.discoverse.discord;

import dev.discoverse.config.ConfigManager;
import dev.discoverse.utils.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import org.bukkit.Bukkit;

/**
 * ------------------------------------------------------------
 * DiscoVerse Chat Bridge
 * ------------------------------------------------------------
 *
 * Handles communication between Minecraft
 * and Discord.
 *
 * Features:
 * - Minecraft -> Discord
 * - Discord -> Minecraft
 * - Channel validation
 *
 * Future:
 * - Webhook support
 * - Embed chat
 * - Reply support
 * - Attachments
 *
 * @author NOTVISHUYT
 * @version 1.0.0
 */
public final class ChatBridge {

    private final ConfigManager configManager;

    /**
     * Creates a new ChatBridge.
     *
     * @param configManager Configuration manager.
     */
    public ChatBridge(final ConfigManager configManager) {
        this.configManager = configManager;
    }

    /**
     * Sends a Minecraft chat message to Discord.
     *
     * @param player Player name.
     * @param message Chat message.
     */
    public void sendMinecraftMessage(
            final String player,
            final String message
    ) {

        JDA jda = JDAProvider.get();

        if (jda == null) {

            Logger.warn("Cannot send message because JDA is not ready.");

            return;
        }

        String channelId = configManager
                .getDiscordConfig()
                .getString("channels.chat");

        if (channelId == null || channelId.isBlank()) {

            Logger.warn("Chat channel is not configured.");

            return;
        }

        TextChannel channel = jda.getTextChannelById(channelId);

        if (channel == null) {

            Logger.warn("Discord chat channel not found.");

            return;
        }

        channel.sendMessage("**" + player + "**: " + message).queue();

    }

    /**
     * Sends a Discord message to Minecraft.
     *
     * @param author Discord username.
     * @param message Discord message.
     */
    public void sendDiscordMessage(
            final String author,
            final String message
    ) {

        Bukkit.broadcastMessage(
                "§9[Discord] §b"
                        + author
                        + "§7: "
                        + message
        );

    }

    /**
     * Reload hook.
     */
    public void reload() {

        Logger.info("ChatBridge reloaded.");

    }

    /**
     * Returns the configuration manager.
     *
     * @return ConfigManager.
     */
    public ConfigManager getConfigManager() {
        return configManager;
    }

}