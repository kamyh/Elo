package swiss.kamyh.elo.arena;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.Party;
import swiss.kamyh.elo.enumerate.GroupItemType;
import swiss.kamyh.elo.gui.Item;
import swiss.kamyh.elo.gui.ItemArmor;
import swiss.kamyh.elo.gui.Menu;
import swiss.kamyh.elo.listeners.IAction;
import swiss.kamyh.elo.tools.Coord;
import swiss.kamyh.elo.tools.GroupItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.awt.SystemColor.menu;

/**
 * Created by Vincent on 06.06.2016.
 */
public class Arena {

    HashMap<Material, ItemArmor> items;

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

        this.items = new HashMap<Material, ItemArmor>();

        GroupItem groupItemSword = new GroupItem(GroupItemType.SOWRD, 1);

        items.put(Material.DIAMOND_SWORD, new ItemArmor(1, 1, Material.DIAMOND_SWORD, 1, menu, groupItemSword));
        items.put(Material.IRON_SWORD, new ItemArmor(2, 1, Material.IRON_SWORD, 1, menu, groupItemSword));
        items.put(Material.GOLD_SWORD, new ItemArmor(3, 1, Material.GOLD_SWORD, 1, menu, groupItemSword));
        items.put(Material.WOOD_SWORD, new ItemArmor(4, 1, Material.WOOD_SWORD, 1, menu, groupItemSword));

        GroupItem groupItemAxe = new GroupItem(GroupItemType.AXE, 2);

        items.put(Material.DIAMOND_AXE, new ItemArmor(6, 1, Material.DIAMOND_AXE, 1, menu, groupItemAxe));
        items.put(Material.IRON_AXE, new ItemArmor(7, 1, Material.IRON_AXE, 1, menu, groupItemAxe));
        items.put(Material.GOLD_AXE, new ItemArmor(8, 1, Material.GOLD_AXE, 1, menu, groupItemAxe));
        items.put(Material.WOOD_AXE, new ItemArmor(9, 1, Material.WOOD_AXE, 1, menu, groupItemAxe));

        GroupItem groupItemHelmet = new GroupItem(GroupItemType.HELMET, 1);

        items.put(Material.DIAMOND_HELMET, new ItemArmor(1, 2, Material.DIAMOND_HELMET, 1, menu, groupItemHelmet));
        items.put(Material.IRON_HELMET, new ItemArmor(2, 2, Material.IRON_HELMET, 1, menu, groupItemHelmet));
        items.put(Material.GOLD_HELMET, new ItemArmor(3, 2, Material.GOLD_HELMET, 1, menu, groupItemHelmet));

        GroupItem groupItemChestplat = new GroupItem(GroupItemType.CHESTPLAT, 1);

        items.put(Material.DIAMOND_CHESTPLATE, new ItemArmor(7, 2, Material.DIAMOND_CHESTPLATE, 1, menu, groupItemChestplat));
        items.put(Material.IRON_CHESTPLATE, new ItemArmor(8, 2, Material.IRON_CHESTPLATE, 1, menu, groupItemChestplat));
        items.put(Material.GOLD_CHESTPLATE, new ItemArmor(9, 2, Material.GOLD_CHESTPLATE, 1, menu, groupItemChestplat));

        GroupItem groupItemLegin = new GroupItem(GroupItemType.LEGIN, 1);

        items.put(Material.DIAMOND_LEGGINGS, new ItemArmor(1, 3, Material.DIAMOND_LEGGINGS, 1, menu, groupItemLegin));
        items.put(Material.IRON_LEGGINGS, new ItemArmor(2, 3, Material.IRON_LEGGINGS, 1, menu, groupItemLegin));
        items.put(Material.GOLD_LEGGINGS, new ItemArmor(3, 3, Material.GOLD_LEGGINGS, 1, menu, groupItemLegin));

        GroupItem groupItemBoots = new GroupItem(GroupItemType.BOOTS, 1);

        items.put(Material.DIAMOND_BOOTS, new ItemArmor(7, 3, Material.DIAMOND_BOOTS, 1, menu, groupItemBoots));
        items.put(Material.IRON_BOOTS, new ItemArmor(8, 3, Material.IRON_BOOTS, 1, menu, groupItemBoots));
        items.put(Material.GOLD_BOOTS, new ItemArmor(9, 3, Material.GOLD_BOOTS, 1, menu, groupItemBoots));

        GroupItem groupItemBlock = new GroupItem(GroupItemType.BLOCK, 21);

        items.put(Material.HAY_BLOCK, new ItemArmor(1, 4, Material.HAY_BLOCK, 15, menu, groupItemBlock));
        items.put(Material.MELON_BLOCK, new ItemArmor(2, 4, Material.MELON_BLOCK, 15, menu, groupItemBlock));
        items.put(Material.SLIME_BLOCK, new ItemArmor(3, 4, Material.SLIME_BLOCK, 15, menu, groupItemBlock));

        GroupItem groupItemVarious = new GroupItem(GroupItemType.VARIOUS, 1);

        items.put(Material.FISHING_ROD, new ItemArmor(1, 5, Material.FISHING_ROD, 1, menu, groupItemVarious));
        //TODO arc infinity

        GroupItem groupItemCompos = new GroupItem(GroupItemType.COMPOS, 5);

        items.put(Material.GOLDEN_APPLE, new ItemArmor(1, 6, Material.GOLDEN_APPLE, 5, menu, groupItemCompos));
        items.put(Material.FLINT_AND_STEEL, new ItemArmor(2, 6, Material.FLINT_AND_STEEL, 1, menu, groupItemCompos));

        for (Item item : items.values()) {
            item.setActionListener(new IAction() {
                public void onClick(ClickType clickType, Item menuObject, Player player) {
                    if (clickType == ClickType.LEFT) {
                        int max = ((ItemArmor) menuObject).getMax();
                        int nItem = asTooManyItem(menuObject);
                        if (max + 1 > nItem) {
                            ((ItemArmor) menuObject).incr();
                        }
                    }else if(clickType == ClickType.RIGHT)
                    {
                        ((ItemArmor) menuObject).decr();
                    }
                }
            });
        }

        menu.addLeaveIcon(9, 6);
        menu.addPrevIcon(8, 6);
        addValidate(7, 6, menu);

        menu.openForPlayer((Player) Elo.getInstance().getQueue().getCurrent().getInventory().getHolder());
    }

    private int asTooManyItem(Item menuObject) {
        int res = 0;

        for (Material key : items.keySet()) {
            ItemArmor item = items.get(key);
            if (item.getItem().getType() == menuObject.getItem().getType()) {
                res++;
            }
        }

        return res;
    }

    public void addValidate(int x, int y, Menu menu) {
        ItemStack itemStack = new ItemStack(Material.ITEM_FRAME);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Valider");
        itemStack.setItemMeta(itemMeta);
        Item item = new Item(itemStack);

        item.setActionListener(new IAction() {
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    //validate choice
                    System.out.println("VALIDATION");
                }
            }
        });

        Coord coordinates = new Coord(menu, x, y);
        menu.setMenuObjectAt(coordinates, item);
    }
}
