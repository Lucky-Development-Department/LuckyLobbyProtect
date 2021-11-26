package id.luckynetwork.ldd.lyrams.luckylobbyprotect.listeners.blocker;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@RequiredArgsConstructor
public class CommandListeners implements Listener {

    private final LuckyLobbyProtect plugin;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        String command = event.getMessage();
        if (command.length() >= 2 && command.charAt(1) == '/') {
            event.setCancelled(true);
            return;
        }

        if (command.startsWith("/fill") || command.startsWith("/minecraft:fill") ||
                command.startsWith("/setblock") || command.startsWith("/minecraft:setblock") ||
                command.startsWith("/clone") || command.startsWith("/minecraft:clone") ||
                command.startsWith("/blockdata") || command.startsWith("/minecraft:blockdata")) {
            event.setCancelled(true);
        }
    }
}
