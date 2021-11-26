package id.luckynetwork.ldd.lyrams.luckylobbyprotect.listeners.blocker;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.PortalCreateEvent;

@RequiredArgsConstructor
public class MiscListeners implements Listener {

    private final LuckyLobbyProtect plugin;

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (plugin.getListenerManager().isDisabled(event.getEventName())) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getWorld())) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onPortalCreate(PortalCreateEvent event) {
        if (plugin.getListenerManager().isDisabled(event.getEventName())) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getWorld())) {
            return;
        }

        event.setCancelled(true);
    }
}
