package autumnvn.hitSwapFix.listener;

import com.google.common.collect.Multimap;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerEventListener implements Listener {

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
         if (event.getNewSlot() >= 0 && event.getNewSlot() < 9 && event.getNewSlot() != event.getPreviousSlot()) {
             event.getPlayer().resetCooldown();
             refreshAttributes(event.getPlayer(), event.getPlayer().getInventory().getItem(event.getNewSlot()));
         }
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        event.getPlayer().resetCooldown();
        refreshAttributes(event.getPlayer(), event.getMainHandItem());
    }

    private void refreshAttributes(Player player, ItemStack itemStack) {
        if (itemStack == null) return;

        Multimap<Attribute, AttributeModifier> itemModifiers;

        if (itemStack.getItemMeta() != null && itemStack.getItemMeta().getAttributeModifiers() != null) {
            itemModifiers = itemStack.getItemMeta().getAttributeModifiers();
        } else {
            itemModifiers = itemStack.getType().getDefaultAttributeModifiers();
        }

        for (Attribute attribute : Registry.ATTRIBUTE) {
            AttributeInstance attributeInstance = player.getAttribute(attribute);
            if (attributeInstance != null) {
                itemModifiers.get(attribute).stream().findFirst().ifPresent(modifier -> {
                    attributeInstance.removeModifier(modifier);
                    attributeInstance.addTransientModifier(modifier);
                });
            }
        }
    }
}
