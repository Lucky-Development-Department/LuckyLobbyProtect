package id.luckynetwork.ldd.lyrams.luckylobbyprotect;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.api.PlaceholderHooks;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.api.impl.PlaceholderAPIExpansion;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.command.BuildModeCommand;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.listeners.blocker.*;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.managers.ListenerManager;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.managers.LoggingManager;
import id.luckynetwork.ldd.lyrams.luckylobbyprotect.managers.NeoListenerManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LuckyLobbyProtect extends JavaPlugin {

    private final List<Player> buildModeList = new ArrayList<>();
    private PlaceholderHooks placeholderHooks;
    private ListenerManager listenerManager;

    private NeoListenerManager neoListenerManager;
    private LoggingManager loggingManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.placeholderHooks = new PlaceholderHooks(this);
        this.listenerManager = new ListenerManager(this);

        PluginManager pluginManager = Bukkit.getPluginManager();
        if (pluginManager.getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPIExpansion(this).register();
        }

        new BuildModeCommand(this);
        pluginManager.registerEvents(new BlockListeners(this), this);
        pluginManager.registerEvents(new CommandListeners(this), this);
        pluginManager.registerEvents(new EntityListeners(this), this);
        pluginManager.registerEvents(new MiscListeners(this), this);
        pluginManager.registerEvents(new PlayerListeners(this), this);
        pluginManager.registerEvents(new VehicleListeners(this), this);
    }

    @Override
    public void onDisable() {

    }
}
