package swiss.kamyh.elo.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import swiss.kamyh.elo.gui.Item;

/**
 * Created by Vincent on 05.06.2016.
 */
public interface IAction {
    public void onClick(ClickType clickType, Item item, Player whoClicked);
}
