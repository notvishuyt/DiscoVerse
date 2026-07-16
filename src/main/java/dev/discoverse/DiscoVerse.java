package dev.discoverse;

import dev.discoverse.commands.DebugCommand;
import dev.discoverse.commands.DiscoVerseCommand;
import dev.discoverse.commands.HelpCommand;
import dev.discoverse.config.ConfigManager;
import dev.discoverse.discord.AccountLinkManager;
import dev.discoverse.discord.ChatBridgeManager;
import dev.discoverse.discord.DiscordManager;
import dev.discoverse.discord.EmbedManager;
import dev.discoverse.discord.EventManager;
import dev.discoverse.discord.PresenceManager;
import dev.discoverse.discord.RoleSyncManager;
import dev.discoverse.discord.SlashCommandManager;
import dev.discoverse.discord.WebhookManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DiscoVerse extends JavaPlugin {

    private ConfigManager configManager;

    private DiscordManager discordManager;
    private EventManager eventManager;

    private AccountLinkManager accountLinkManager;
    private ChatBridgeManager chatBridgeManager;
    private EmbedManager embedManager;
    private PresenceManager presenceManager;
    private RoleSyncManager roleSyncManager;
    private SlashCommandManager slashCommandManager;
    private WebhookManager webhookManager;

    @Override
    public void onEnable() {

        getLogger().info("========================================");
        getLogger().info("Starting DiscoVerse...");
        getLogger().info("Developer: NOTVISHUYT");
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("========================================");

        // Configuration
        configManager = new ConfigManager(this);

        // Managers
        discordManager = new DiscordManager(this, configManager);

        accountLinkManager = new AccountLinkManager(configManager);
        chatBridgeManager = new ChatBridgeManager(configManager);
        embedManager = new EmbedManager(configManager);
        presenceManager = new PresenceManager(configManager);
        roleSyncManager = new RoleSyncManager(configManager);
        slashCommandManager = new SlashCommandManager(configManager);
        webhookManager = new WebhookManager(configManager);

        eventManager = new EventManager(this);

        // Discord
        discordManager.start();

        if (discordManager.getBotManager() != null
                && discordManager.getBotManager().isOnline()) {

            presenceManager.updatePresence();
            slashCommandManager.registerCommands();

        }

        // Events
        eventManager.registerEvents();

        // Commands
        if (getCommand("discoverse") != null) {
            getCommand("discoverse").setExecutor(new DiscoVerseCommand(this));
        }

        if (getCommand("dvhelp") != null) {
            getCommand("dvhelp").setExecutor(new HelpCommand(this));
        }

        if (getCommand("dvdebug") != null) {
            getCommand("dvdebug").setExecutor(new DebugCommand(this));
        }

        getLogger().info("========================================");
        getLogger().info("DiscoVerse has been enabled successfully!");
        getLogger().info("========================================");

    }

    @Override
    public void onDisable() {

        if (discordManager != null) {
            discordManager.shutdown();
        }

        getLogger().info("========================================");
        getLogger().info("DiscoVerse has been disabled.");
        getLogger().info("========================================");

    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public DiscordManager getDiscordManager() {
        return discordManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public AccountLinkManager getAccountLinkManager() {
        return accountLinkManager;
    }

    public ChatBridgeManager getChatBridgeManager() {
        return chatBridgeManager;
    }

    public EmbedManager getEmbedManager() {
        return embedManager;
    }

    public PresenceManager getPresenceManager() {
        return presenceManager;
    }

    public RoleSyncManager getRoleSyncManager() {
        return roleSyncManager;
    }

    public SlashCommandManager getSlashCommandManager() {
        return slashCommandManager;
    }

    public WebhookManager getWebhookManager() {
        return webhookManager;
    }

}