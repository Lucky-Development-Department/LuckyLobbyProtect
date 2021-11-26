package id.luckynetwork.ldd.lyrams.luckylobbyprotect.listeners.blocker;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

@RequiredArgsConstructor
public class EntityListeners implements Listener {

    private final LuckyLobbyProtect plugin;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onDamage(EntityDamageEvent event) {
        String eventName = event.getEventName() + "-" + event.getCause().name();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getEntity().getWorld())) {
            return;
        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
                return;
            }

            if (!plugin.getBuildModeList().contains(player)) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onDamageBlocks(EntityDamageByBlockEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getEntity().getWorld())) {
            return;
        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
                return;
            }

            if (!plugin.getBuildModeList().contains(player)) {
                event.setCancelled(true);
            }
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onDamageOthers(EntityDamageByEntityEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getEntity().getWorld())) {
            return;
        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
                return;
            }

            if (event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();
                if (plugin.getListenerManager().isWhitelisted(damager, eventName)) {
                    event.setCancelled(false);
                    return;
                }

                if (plugin.getBuildModeList().contains(damager)) {
                    event.setCancelled(false);
                    return;
                }

                event.setCancelled(true);
                return;
            }

            if (!plugin.getBuildModeList().contains(player)) {
                event.setCancelled(true);
            }
        } else {
            if (event.getDamager() instanceof Player) {
                Player damager = (Player) event.getDamager();
                if (plugin.getListenerManager().isWhitelisted(damager, eventName)) {
                    event.setCancelled(false);
                    return;
                }
            }

            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPortalCreate(EntityCreatePortalEvent event) {
        if (plugin.getListenerManager().isDisabled(event.getEventName())) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getEntity().getWorld())) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onFoodChange(FoodLevelChangeEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getEntity().getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted((Player) event.getEntity(), eventName)) {
            return;
        }

        event.setCancelled(true);
    }
}
