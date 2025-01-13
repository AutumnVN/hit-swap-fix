package autumnvn.hitSwapNerf.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerEventListener implements Listener {

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
         if (event.getNewSlot() >= 0 && event.getNewSlot() < 9 && event.getNewSlot() != event.getPreviousSlot()) {
             event.getPlayer().resetCooldown();
         }
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        event.getPlayer().resetCooldown();
    }
}
