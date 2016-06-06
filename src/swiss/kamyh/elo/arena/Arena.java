package swiss.kamyh.elo.arena;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.Party;
import swiss.kamyh.elo.gui.Item;
import swiss.kamyh.elo.gui.ItemArmor;
import swiss.kamyh.elo.gui.Menu;
import swiss.kamyh.elo.listeners.IAction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.menu;

/**
 * Created by Vincent on 06.06.2016.
 */
public class Arena {

    List<Item> items;
    List<Item> selectedItems;

    public Arena() {

        Menu menu = new Menu(Bukkit.createInventory(Elo.getInstance().getQueue().getCurrent().getInventory().getHolder(), 54, "Arena"));

        Item item = menu.addIcon(5, 3, Material.ARMOR_STAND);

        item.setActionListener(new IAction() {
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    armorSelection();
                }
            }
        });

        menu.addLeaveIcon(9, 6);
        menu.addPrevIcon(8, 6);

        menu.openForPlayer((Player) Elo.getInstance().getQueue().getCurrent().getInventory().getHolder());
    }

    private void armorSelection() {
        Menu menu = new Menu(Bukkit.createInventory(Elo.getInstance().getQueue().getCurrent().getInventory().getHolder(), 54, "Armor"));

        this.items = new ArrayList<>();
        this.selectedItems = new ArrayList<>();


        items.add(new ItemArmor(1, 1, Material.DIAMOND_SWORD, 1, menu));
        items.add(new ItemArmor(2, 1, Material.IRON_SWORD, 1, menu));
        items.add(new ItemArmor(3, 1, Material.GOLD_SWORD, 1, menu));

        for (Item item : items) {
            item.setActionListener(new IAction() {
                public void onClick(ClickType clickType, Item menuObject, Player player) {
                    if (clickType == ClickType.LEFT) {
                        //TODO max add check

                        selectedItems.add(menuObject);
                        System.out.println(selectedItems);
                    }
                }
            });
        }

        menu.addLeaveIcon(9, 6);
        menu.addPrevIcon(8, 6);

        menu.openForPlayer((Player) Elo.getInstance().getQueue().getCurrent().getInventory().getHolder());
    }
}
