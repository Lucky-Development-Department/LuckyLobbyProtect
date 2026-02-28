package id.luckynetwork.ldd.lyrams.luckylobbyprotect.managers;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class NeoListenerManager {

    private final LuckyLobbyProtect plugin;

    @Getter
    private final HashMap<Player, List<String>> whitelistedMap = new HashMap<>();
    @Getter
    private final Set<Player> toggledPlayers = new HashSet<>();

    private final Set<String> disabledListeners = new HashSet<>();
    private final Set<String> enabledWorlds = new HashSet<>();

    public boolean loadFromConfig() {
        Set<String> disabledListenersCopy = new HashSet<>(this.disabledListeners);
        Set<String> enabledWorldsCopy = new HashSet<>(this.enabledWorlds);

        this.disabledListeners.clear();
        this.enabledWorlds.clear();

        try {
            this.disabledListeners.addAll(plugin.getConfig().getStringList("disabled-listeners").stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList()));

            this.enabledWorlds.addAll(plugin.getConfig().getStringList("enabled-worlds").stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList()));
        } catch (Exception exception) {
            this.disabledListeners.clear();
            this.disabledListeners.addAll(disabledListenersCopy);

            this.enabledWorlds.clear();
            this.enabledWorlds.addAll(enabledWorldsCopy);
            return false;
        }

        return true;
    }

    public boolean isDisabled(String eventName) {
        return this.disabledListeners.contains(eventName.toLowerCase());
    }

    public boolean isDisabledInWorld(String worldName) {
        if (this.enabledWorlds.contains("*") || this.enabledWorlds.contains("all")) {
            return false;
        }

        return !this.enabledWorlds.contains(worldName.toLowerCase());
    }

    public boolean isWhitelisted(Player player, String eventName) {
        if (!whitelistedMap.containsKey(player)) {
            return false;
        }

        return whitelistedMap.get(player).stream().anyMatch(it -> it.equals(eventName));
    }
}
