package swiss.kamyh.elo.tools;

import org.bukkit.inventory.Inventory;
import swiss.kamyh.elo.gui.Item;
import swiss.kamyh.elo.gui.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 05.06.2016.
 */
public class MenuQueue {

    private List<Menu> menus;

    public MenuQueue() {
        menus = new ArrayList<>();
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void push(Menu inv) {
        menus.add(inv);
    }

    public void pop() {
        menus.remove(this.menus.get(this.menus.size() - 1));
    }

    public Menu getByInventory(Inventory inventory) {
        for (Menu menu : menus) {
            if (menu.getInventory().equals(inventory)) {
                return menu;
            }
        }

        return null;
    }

    public Menu getCurrent() {
        return this.menus.get(this.menus.size() - 1);
    }

    @Override
    public String toString() {
        String str = "";

        for (Menu menu : menus) {
            str += "\nName: " + menu.getInventory().getName();
            str += "\nObjects number: " + menu.getObjects().size();

            for (Integer key : menu.getObjects().keySet()) {
                str += "\n\t" + menu.getObjects().get(key).getItem().getType();
            }
            str += "\n-------------------------------------\n";
        }

        return str;
    }
}
