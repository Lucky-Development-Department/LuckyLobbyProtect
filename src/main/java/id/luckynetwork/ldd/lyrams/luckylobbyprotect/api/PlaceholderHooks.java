package id.luckynetwork.ldd.lyrams.luckylobbyprotect.api;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class PlaceholderHooks {

    private final LuckyLobbyProtect plugin;

    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier.equalsIgnoreCase("buildmode")) {
            return String.valueOf(plugin.getBuildModeList().contains(player));
        }
        return null;
    }
}
