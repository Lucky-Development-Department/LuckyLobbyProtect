package id.luckynetwork.ldd.lyrams.luckylobbyprotect.listeners.blocker;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

@RequiredArgsConstructor
public class PlayerListeners implements Listener {

    private final LuckyLobbyProtect plugin;

    @EventHandler
    public void onAchievementGet(PlayerAchievementAwardedEvent event) {
        if (plugin.getListenerManager().isDisabled(event)) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(event.getPlayer(), event)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent event) {
        if (plugin.getListenerManager().isDisabled(event)) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(event.getPlayer(), event)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (plugin.getListenerManager().isDisabled(event)) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(event.getPlayer(), event)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        if (plugin.getListenerManager().isDisabled(event)) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(event.getPlayer(), event)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        if (plugin.getListenerManager().isDisabled(event)) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(event.getPlayer(), event)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onEditBook(PlayerEditBookEvent event) {
        if (plugin.getListenerManager().isDisabled(event)) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(event.getPlayer(), event)) {
            return;
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
        if (plugin.getListenerManager().isDisabled(event)) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(event.getPlayer(), event)) {
            return;
        }

        event.setCancelled(true);
    }
}
