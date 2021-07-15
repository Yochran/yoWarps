package me.yochran.yowarps.listeners;

import me.yochran.yowarps.gui.Button;
import me.yochran.yowarps.gui.GUI;
import me.yochran.yowarps.utils.XMaterial;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == XMaterial.AIR.parseMaterial())
            return;

        if (GUI.getOpenGUIs().containsKey(event.getWhoClicked())) {
            GUI gui = GUI.getOpenGUIs().get(event.getWhoClicked());
            if (gui != null && event.getClickedInventory() != null && event.getClickedInventory().equals(gui.getInventory())) {
                event.setCancelled(true);

                if (gui.getButton(event.getSlot()) != null) {
                    Button button = gui.getButton(event.getSlot());
                    if (button.getAction() != null)
                        button.getAction().run();
                }
            }
        }
    }
}
