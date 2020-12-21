package id.luckynetwork.ldd.lyrams.luckylobbyprotect.managers;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ListenerManager {

    private final LuckyLobbyProtect plugin;
    @Getter
    private final HashMap<Player, List<String>> whitelistedMap = new HashMap<>();
    private List<String> disabledListeners;

    public ListenerManager(LuckyLobbyProtect plugin) {
        this.plugin = plugin;
        this.disabledListeners = plugin.getConfig().getStringList("disabled-listeners").stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    public void reload() {
        this.disabledListeners.clear();
        this.disabledListeners = plugin.getConfig().getStringList("disabled-listeners").stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    public boolean isDisabled(Event event) {
        return disabledListeners.contains(event.getEventName().toLowerCase());
    }

    public boolean isWhitelisted(Player player, Event event) {
        if (!whitelistedMap.containsKey(player)) {
            return false;
        }

        return whitelistedMap.get(player).stream().map(String::toLowerCase).anyMatch(it -> it.equals(event.getEventName().toLowerCase()));
    }
}
