package swiss.kamyh.elo.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;
import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.gui.Item;
import swiss.kamyh.elo.gui.Menu;
import swiss.kamyh.elo.tools.Coord;

/**
 * Created by Vincent on 05.06.2016.
 */
public class MenuListener implements Listener {
    @EventHandler
    public void onClick(final InventoryClickEvent event)
    {
        if(event.getCurrentItem() == null)
        {
            return;
        }

        final Menu menu = Elo.getInstance().getQueue().getByInventory(event.getInventory());
        if(menu == null)
        {
            return;
        }

        event.setCancelled(true);

        final Item menuObject = menu.getItemAt(new Coord(menu, event.getSlot()));

        if(menuObject == null)
        {
            return;
        }

        if(menuObject.getActionListener() == null)
        {
            return;
        }

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                menuObject.getActionListener().onClick(event.getClick(), menuObject, (Player) event.getWhoClicked());
            }
        }.runTask(Elo.getInstance());
    }
}
