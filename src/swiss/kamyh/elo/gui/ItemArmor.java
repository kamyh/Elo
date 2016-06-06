package swiss.kamyh.elo.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import swiss.kamyh.elo.tools.Coord;
import swiss.kamyh.elo.tools.GroupItem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Vincent on 06.06.2016.
 */
public class ItemArmor extends Item {
    private int max;
    private int number;
    private GroupItem groupItem;

    public ItemArmor(ItemStack holder, int max) {
        super(holder);

        this.max = max;
        this.number = 0;
    }

    public ItemArmor(Material icon, byte data, String name, List<String> tooltip, int max) {
        super(icon, data, name, tooltip);

        this.max = max;
    }

    public ItemArmor(int x, int y, Material material, int max, Menu menu, GroupItem groupItem) {
        super(new ItemStack(material));
        this.max = max;
        this.setCoordinates(new Coord(menu, x, y));
        this.groupItem = groupItem;
        this.groupItem.addItem(this);

        updateTagQuantity();
    }

    public int getMax() {
        return max;
    }

    public void incr() {
        if (this.max > this.number && this.groupItem.canAdd()) {
            this.number++;
            this.groupItem.incr();

            for (ItemArmor item : this.groupItem.getItems()) {
                item.updateTagQuantity();
                System.out.println("---> " + item.getItem().getType());
            }
        }
    }

    public void decr() {
        if (0 < this.number && this.groupItem.canRemove()) {
            this.number--;
            this.groupItem.decr();

            for (ItemArmor item : this.groupItem.getItems()) {
                item.updateTagQuantity();
            }
        }
    }

    public void updateTagQuantity() {
        Menu menu = this.getMenu();

        ItemMeta itemMeta = this.getItem().getItemMeta();
        String tag = "Quantity: " + this.number + "/" + this.max;
        String lore_1 = "Quantity " + this.groupItem.toString() + ": " + this.groupItem.getNumber() + "/" + this.groupItem.getMax();

        itemMeta.setDisplayName(tag);
        itemMeta.setLore(Arrays.asList(lore_1));

        this.getItem().setItemMeta(itemMeta);
        menu.setMenuObjectAt(this.getCoordinates(), this);
    }
}
