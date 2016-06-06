package swiss.kamyh.elo.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import swiss.kamyh.elo.tools.Coord;

import java.util.List;

/**
 * Created by Vincent on 06.06.2016.
 */
public class ItemArmor extends Item {
    int max;

    public ItemArmor(ItemStack holder, int max) {
        super(holder);

        this.max = max;
    }

    public ItemArmor(Material icon, byte data, String name, List<String> tooltip, int max) {
        super(icon, data, name, tooltip);

        this.max = max;
    }

    public ItemArmor(int x, int y, Material material, int max, Menu menu) {
        super(new ItemStack(material));
        this.max = max;

        menu.setMenuObjectAt(new Coord(menu, x, y), this);
    }
}
