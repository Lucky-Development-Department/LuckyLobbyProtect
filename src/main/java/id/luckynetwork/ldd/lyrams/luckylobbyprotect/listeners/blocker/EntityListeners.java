package id.luckynetwork.ldd.lyrams.luckylobbyprotect.listeners.blocker;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

@RequiredArgsConstructor
public class EntityListeners implements Listener {

    private final LuckyLobbyProtect plugin;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        if (plugin.getListenerManager().isDisabled(event)) {
            return;
        }

        Player player = (Player) event.getEntity();

        if (plugin.getListenerManager().isWhitelisted(player, event)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onDamageOthers(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        if (plugin.getListenerManager().isDisabled(event)) {
            return;
        }

        Player damager = (Player) event.getDamager();

        if (plugin.getListenerManager().isWhitelisted(damager, event)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(damager)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPortalCreate(EntityCreatePortalEvent event) {
        if (plugin.getListenerManager().isDisabled(event)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onFoodChange(FoodLevelChangeEvent event) {
        if (plugin.getListenerManager().isDisabled(event)) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted((Player) event.getEntity(), event)) {
            return;
        }

        event.setCancelled(true);
    }
}
