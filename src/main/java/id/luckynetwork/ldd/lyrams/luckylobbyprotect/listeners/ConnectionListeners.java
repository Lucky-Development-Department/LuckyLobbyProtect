package id.luckynetwork.ldd.lyrams.luckylobbyprotect.listeners;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class ConnectionListeners implements Listener {

    private final LuckyLobbyProtect plugin;

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        plugin.getListenerManager().getWhitelistedMap().remove(event.getPlayer());
    }
}
