package id.luckynetwork.ldd.lyrams.luckylobbyprotect.listeners.blocker;

import id.luckynetwork.ldd.lyrams.luckylobbyprotect.LuckyLobbyProtect;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;

@RequiredArgsConstructor
public class PlayerListeners implements Listener {

    private final LuckyLobbyProtect plugin;

    @EventHandler
    public void onAchievementGet(PlayerAchievementAwardedEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPortal(PlayerPortalEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEditBook(PlayerEditBookEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            String eventName = event.getEventName() + "-left-block";
            if (plugin.getListenerManager().isDisabled(eventName)) {
                return;
            }

            Player player = event.getPlayer();
            if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
                return;
            }

            if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
                return;
            }

            if (!plugin.getBuildModeList().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract2(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            String eventName = event.getEventName() + "-left-air";
            if (plugin.getListenerManager().isDisabled(eventName)) {
                return;
            }

            Player player = event.getPlayer();
            if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
                return;
            }

            if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
                return;
            }

            if (!plugin.getBuildModeList().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract3(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            String eventName = event.getEventName() + "-right-block";
            if (plugin.getListenerManager().isDisabled(eventName)) {
                return;
            }

            Player player = event.getPlayer();
            if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
                return;
            }

            if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
                return;
            }

            if (!plugin.getBuildModeList().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract4(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            String eventName = event.getEventName() + "-right-air";
            if (plugin.getListenerManager().isDisabled(eventName)) {
                return;
            }

            Player player = event.getPlayer();
            if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
                return;
            }

            if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
                return;
            }

            if (!plugin.getBuildModeList().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract5(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL) {
            String eventName = event.getEventName() + "-physical";
            if (plugin.getListenerManager().isDisabled(eventName)) {
                return;
            }

            Player player = event.getPlayer();
            if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
                return;
            }

            if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
                return;
            }

            if (!plugin.getBuildModeList().contains(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSheer(PlayerShearEntityEvent event) {
        String eventName = event.getEventName();
        if (plugin.getListenerManager().isDisabled(eventName)) {
            return;
        }

        Player player = event.getPlayer();
        if (plugin.getListenerManager().isDisabledInWorld(player.getWorld())) {
            return;
        }

        if (plugin.getListenerManager().isWhitelisted(player, eventName)) {
            return;
        }

        if (!plugin.getBuildModeList().contains(player)) {
            event.setCancelled(true);
        }
    }
}
