package id.luckynetwork.ldd.lyrams.luckylobbyprotect.listeners.blocker;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;

@RequiredArgsConstructor
public class VehicleListeners implements Listener {

    private final LuckyLobbyProtect plugin;

    @EventHandler
    public void onEnter(VehicleEnterEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getVehicle().getWorld())) {
            return;
        }

        if (event.getEntered() instanceof Player) {
            Player player = (Player) event.getEntered();
            if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
                return;
            }

            if (!plugin.getBuildModeList().contains(player)) {
                event.setCancelled(true);
            }
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(VehicleDamageEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getVehicle().getWorld())) {
            return;
        }

        if (event.getAttacker() instanceof Player) {
            Player player = (Player) event.getAttacker();
            if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
                return;
            }

            if (!plugin.getBuildModeList().contains(player)) {
                event.setCancelled(true);
            }
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onDestroy(VehicleDestroyEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getVehicle().getWorld())) {
            return;
        }

        if (event.getAttacker() instanceof Player) {
            Player player = (Player) event.getAttacker();
            if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
                return;
            }

            if (!plugin.getBuildModeList().contains(player)) {
                event.setCancelled(true);
            }
            return;
        }

        event.setCancelled(true);
    }
}
