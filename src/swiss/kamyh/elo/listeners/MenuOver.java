package swiss.kamyh.elo.listeners;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.gui.Menu;

import java.util.List;

/**
 * Created by Vincent on 05.06.2016.
 */
public class MenuOver implements Listener {
    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Menu menu = Elo.getInstance().getQueue().getByInventory(event.getInventory());

        List<HumanEntity> viewers = event.getViewers();
        if (menu != null) {
            viewers.remove(event.getPlayer()); // Precaution, really.
            if (viewers.size() == 0) {
                /*menu.getObjects().clear();
                menu.getInventory().clear();*/
                //TODO clear all menu
            }
        }
    }
}