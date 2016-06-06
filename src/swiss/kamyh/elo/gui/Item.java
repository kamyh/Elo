package swiss.kamyh.elo.gui;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import swiss.kamyh.elo.listeners.IAction;
import swiss.kamyh.elo.tools.Coord;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Vincent on 05.06.2016.
 */
public class Item {


    private ItemStack item;
    private Coord coordinates;
    private IAction actionListener;
    private HashMap<Object, Object> metadata;

    public void setIcon(ItemStack holder) {
        this.item = holder;

        update();
    }

    public void setIcon(Material icon, byte data, String name, List<String> tooltip) {
        item = new ItemStack(icon, 1, data);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(tooltip);
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        update();
    }

    public Item(ItemStack holder) {
        metadata = new HashMap<>();
        if (holder == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "The ItemStack used as a menu object was null." + ChatColor.RESET);
            throw new IllegalArgumentException();
        }


        this.item = holder;
        this.coordinates = null;
        this.actionListener = null;
    }

    public Item(Material icon, byte data, String name, List<String> tooltip) {
        metadata = new HashMap<>();
        item = new ItemStack(icon, 1, data);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(tooltip);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        this.coordinates = null;
        this.actionListener = null;
    }

    public ItemStack toItemStack() {
        return this.item;
    }

    public Coord getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coord coordinates) {
        this.coordinates = coordinates;
    }

    public void setActionListener(IAction actionListener) {
        this.actionListener = actionListener;
    }

    public IAction getActionListener() {
        return actionListener;
    }

    public void update() {
        coordinates.getMenu().getInventory().setItem(coordinates.asSlotNumber(), toItemStack());
    }

    public HashMap<Object, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<Object, Object> metadata) {
        this.metadata = metadata;
    }

    public Menu getMenu() {
        return getCoordinates().getMenu();
    }

    public ItemStack getItem() {
        return item;
    }


}
