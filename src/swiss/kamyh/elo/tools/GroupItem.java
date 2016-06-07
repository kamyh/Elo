package swiss.kamyh.elo.tools;

import swiss.kamyh.elo.enumerate.GroupItemType;
import swiss.kamyh.elo.gui.Item;
import swiss.kamyh.elo.gui.ItemArmor;

import java.util.ArrayList;

/**
 * Created by Vincent on 06.06.2016.
 */
public class GroupItem {
    private final int max;
    private int number;
    private GroupItemType type;
    private ArrayList<ItemArmor> items;

    public GroupItem(GroupItemType type, int max) {
        this.type = type;
        this.max = max;
        this.number = 0;
        this.items = new ArrayList<>();
    }

    public void incr() {
        this.number++;
    }

    public void decr() {
        this.number--;
    }

    public int getMax() {
        return max;
    }

    public int getNumber() {
        return number;
    }

    public GroupItemType getType() {
        return type;
    }

    public void addItem(ItemArmor item) {
        this.items.add(item);
    }

    public boolean canRemove() {

        return this.number > 0;
    }

    public boolean canAdd() {

        return this.number < this.max;
    }

    @Override
    public String toString() {
        switch (this.getType()) {
            case SOWRD:
                return "Sword";
            case AXE:
                return "Axe";
            case HELMET:
                return "Helmet";
            case CHESTPLAT:
                return "Chestplat";
            case LEGIN:
                return "Legin";
            case BOOTS:
                return "Boots";
            case COMPOS:
                return "Consomable";
            case VARIOUS:
                return "Divers";
            case BLOCK:
                return "Block";
            case POTIONS:
                return "Potions";
        }

        return null;
    }

    public ArrayList<ItemArmor> getItems() {
        return items;
    }
}