package id.luckynetwork.ldd.lyrams.luckylobbyprotect.listeners.blocker;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.ExplosionPrimeEvent;

@RequiredArgsConstructor
public class BlockListeners implements Listener {

    private final LuckyLobbyProtect plugin;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlace(BlockPlaceEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getBlock().getWorld())) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getBlock().getWorld())) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onLeafDecay(LeavesDecayEvent event) {
        if (plugin.getListenerManager().isDisabled(event.getEventName())) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getBlock().getWorld())) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBlockExplode(BlockExplodeEvent event) {
        if (plugin.getListenerManager().isDisabled(event.getEventName())) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getBlock().getWorld())) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBlockFade(BlockFadeEvent event) {
        if (plugin.getListenerManager().isDisabled(event.getEventName())) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getBlock().getWorld())) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBlockForm(BlockFormEvent event) {
        if (plugin.getListenerManager().isDisabled(event.getEventName())) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getBlock().getWorld())) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onExplosion(ExplosionPrimeEvent event) {
        if (plugin.getListenerManager().isDisabled(event.getEventName())) {
            return;
        }

        if (plugin.getListenerManager().isDisabledInWorld(event.getEntity().getWorld())) {
            return;
        }

        event.setCancelled(true);
    }
}
