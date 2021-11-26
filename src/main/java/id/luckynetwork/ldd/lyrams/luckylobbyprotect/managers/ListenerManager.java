package id.luckynetwork.ldd.lyrams.luckylobbyprotect.managers;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.Getter;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListenerManager {

    private final LuckyLobbyProtect plugin;
    @Getter
    private final HashMap<Player, List<String>> whitelistedMap = new HashMap<>();
    @Getter
    private final Set<String> activeListeners = new HashSet<>();
    private List<String> disabledListeners;
    private List<String> enabledWorlds;

    public ListenerManager(LuckyLobbyProtect plugin) {
        this.plugin = plugin;
        this.disabledListeners = plugin.getConfig().getStringList("disabled-listeners").stream().map(String::toLowerCase).collect(Collectors.toList());
        this.enabledWorlds = plugin.getConfig().getStringList("enabled-worlds").stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    public void reload() {
        this.disabledListeners.clear();
        this.disabledListeners = plugin.getConfig().getStringList("disabled-listeners").stream().map(String::toLowerCase).collect(Collectors.toList());
        this.enabledWorlds = plugin.getConfig().getStringList("enabled-worlds").stream().map(String::toLowerCase).collect(Collectors.toList());
        this.activeListeners.clear();
    }

    public boolean isDisabled(String eventName) {
        this.activeListeners.add(eventName.toLowerCase());

        return disabledListeners.contains(eventName.toLowerCase());
    }

    public boolean isWhitelisted(Player player, String eventName) {
        if (!whitelistedMap.containsKey(player)) {
            return false;
        }

        return whitelistedMap.get(player).stream().map(String::toLowerCase).anyMatch(it -> it.equals(eventName.toLowerCase()));
    }

    public boolean isDisabledInWorld(World world) {
        if (enabledWorlds.contains("*") || enabledWorlds.contains("all")) {
            return false;
        }

        return !enabledWorlds.contains(world.getName().toLowerCase());
    }
}
