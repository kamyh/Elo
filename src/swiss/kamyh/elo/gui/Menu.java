package swiss.kamyh.elo.gui;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.Party;
import swiss.kamyh.elo.listeners.IAction;
import swiss.kamyh.elo.tools.Coord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.SystemColor.menu;

/**
 * Created by Vincent on 05.06.2016.
 */
public class Menu {
    private HashMap<Object, Object> metadata;
    private Inventory inv;
    private HashMap<Integer, Item> objects;

    public Menu(Inventory inv) {
        Elo.getInstance().getQueue().push(this);
        objects = new HashMap<>();
        this.inv = inv;
        metadata = new HashMap<>();
    }

    public Item addIcon(int x, int y, Material material) {
        ItemStack itemStack = new ItemStack(material);
        Item item = new Item(itemStack);

        Coord coordinates = new Coord(this, x, y);
        this.setMenuObjectAt(coordinates, item);

        return item;
    }

    public Inventory getInventory() {
        return inv;
    }

    public void setInventory(Inventory inv) {
        objects.clear();
        this.inv = inv;
    }

    public Item getItemAt(Coord coordinates) {
        return this.equals(coordinates.getMenu()) ? objects.get(coordinates.asSlotNumber()) : null;
    }

    public void setMenuObjectAt(Coord coordinates, Item menuObject) {
        if (menuObject.getCoordinates() != null && objects.containsKey(menuObject.getCoordinates().asSlotNumber())) {
            objects.remove(menuObject.getCoordinates().asSlotNumber());
        }

        objects.put(coordinates.asSlotNumber(), menuObject);
        menuObject.setCoordinates(coordinates);

        int slot = coordinates.asSlotNumber();

        if (slot >= inv.getSize() || slot < 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Unreachable coordinates \"(" + coordinates.getX() + ", " + coordinates.getY() + ")\"! These coordinates measure to " + slot + " which cannot be mapped on an inventory with a size of " + inv.getSize() + "." + ChatColor.RESET);
            throw new IllegalArgumentException();
        }

        inv.setItem(slot, menuObject.toItemStack());
    }

    public void removeMenuObjectAt(Coord coordinates) {
        coordinates.getMenu().getItemAt(coordinates).setCoordinates(null);
        inv.setItem(coordinates.asSlotNumber(), null);
    }

    public void addMenuObject(Item... menuObject) {
        for (Item me : menuObject) {
            if (inv.firstEmpty() != -1) {
                setMenuObjectAt(new Coord(this, inv.firstEmpty()), me);
            }
        }
    }

    @Deprecated
    public void close() {
        System.out.println("Close Inv");
        //this.objects.clear();
        //inv.clear();
        Elo.getInstance().getQueue().pop();
        for (HumanEntity viewer : inv.getViewers()) {
            viewer.closeInventory();
        }
    }

    public void clear() {
        this.objects.clear();
        inv.clear();
    }

    public void close(Player p) {
        p.closeInventory();
    }

    public void openForPlayer(Player p) {
        p.openInventory(inv);
    }

    public Item getItemByItemStack(ItemStack currentItem) {
        for (Map.Entry<Integer, Item> entry : objects.entrySet()) {
            if (entry.getValue().toItemStack().equals(currentItem)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public HashMap<Integer, Item> getObjects() {
        return objects;
    }

    public HashMap<Object, Object> getMetadata() {
        return metadata;
    }


    public void setMetadata(HashMap<Object, Object> metadata) {
        this.metadata = metadata;
    }


    public void addPrevIcon(int x, int y) {
        ItemStack itemStack = new ItemStack(Material.IRON_DOOR);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Retour");
        itemStack.setItemMeta(itemMeta);
        Item item = new Item(itemStack);

        item.setActionListener(new IAction() {
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    Elo.getInstance().getQueue().pop();
                    Elo.getInstance().getQueue().getCurrent().display(player);
                }
            }
        });

        Coord coordinates = new Coord(this, x, y);
        this.setMenuObjectAt(coordinates, item);
    }

    public void display(Player player) {
        Elo.getInstance().getQueue().getCurrent().openForPlayer(player);
    }

    public void addLeaveIcon(int x, int y) {
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Quitter");
        itemStack.setItemMeta(itemMeta);
        Item item = new Item(itemStack);

        item.setActionListener(new IAction() {
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    player.closeInventory();

                    List<Menu> menus = Elo.getInstance().getQueue().getMenus();

                    if (menus != null) {
                        for(Menu menu: menus) {
                            menu.getObjects().clear();
                            menu.getInventory().clear();
                        }
                    }
                }
            }
        });

        Coord coordinates = new Coord(this, x, y);
        this.setMenuObjectAt(coordinates, item);
    }
}
