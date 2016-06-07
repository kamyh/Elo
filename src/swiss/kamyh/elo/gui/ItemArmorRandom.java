package swiss.kamyh.elo.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import swiss.kamyh.elo.tools.GroupItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Vincent on 07.06.2016.
 */
public class ItemArmorRandom extends ItemArmor {

    //TODO if TNT --> make it exploding

    public ItemArmorRandom(int x, int y, Material material, int max, Menu menu, GroupItem groupItem) {
        super(x, y, material, max, menu, groupItem);

        this.updateTagQuantity();
    }

    public ItemStack getTrueObject()
    {
        return new ItemStack(ItemArmorRandom.randomItem());
    }

    @Override
    public String getOriginalName()
    {
        return "Objet surprise";
    }

    @Override
    public void updateTagQuantity() {
        Menu menu = this.getMenu();

        ItemMeta itemMeta = this.getItem().getItemMeta();

        itemMeta.setDisplayName(this.getOriginalName());
        String lore_1 = String.format("Quantity: %d/%d", this.number, this.max);
        String lore_2 = String.format("Quantity %s: %d/%d", this.groupItem.toString(), this.groupItem.getNumber(), this.groupItem.getMax());

        itemMeta.setLore(Arrays.asList(lore_1,lore_2));

        this.getItem().setItemMeta(itemMeta);
        menu.setMenuObjectAt(this.getCoordinates(), this);    }

    public static Material randomItem() {
        Material material = null;
        ArrayList<Material> possibleItems = ItemArmorRandom.getPossibleRandomItems();

        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(possibleItems.size());

        return possibleItems.get(index);
    }

    public static ArrayList<Material> getPossibleRandomItems() {
        return new ArrayList<Material>(
                Arrays.asList(
                        Material.TNT,
                        Material.MILK_BUCKET,
                        Material.FIREWORK,
                        Material.DIAMOND_HOE,
                        Material.EGG,
                        Material.ANVIL
                ));
    }
}