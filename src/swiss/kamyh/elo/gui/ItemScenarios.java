package swiss.kamyh.elo.gui;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import swiss.kamyh.elo.arena.scenario.BrainFuckTeleportation;
import swiss.kamyh.elo.arena.scenario.Scenario;
import swiss.kamyh.elo.enumerate.ScenarioEnum;
import swiss.kamyh.elo.listeners.IAction;
import swiss.kamyh.elo.tools.Coord;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Vincent on 05.06.2016.
 */
public class ItemScenarios extends Item{

    private Boolean isSelected = false;
    private ScenarioEnum scenario;

    public ItemScenarios(ItemStack holder) {
        super(holder);
    }

    public ItemScenarios(Material icon, byte data, String name, List<String> tooltip) {
        super(icon, data, name, tooltip);
    }

    public ItemScenarios(ScenarioEnum scenario, int x, int y, Material material, Menu menu) {
        super(new ItemStack(material));
        this.setCoordinates(new Coord(menu, x, y));

        this.scenario = scenario;
        updateSelected();
    }

    public void updateSelected() {
        Menu menu = this.getMenu();
        ItemMeta itemMeta = this.getItem().getItemMeta();

        itemMeta.setDisplayName("Scenario: " + Scenario.nameString(this.getScenario()));

        String lore_1 = "Desactivé";

        if(this.isSelected) {
            lore_1 = "Activé";
        }

        itemMeta.setLore(Arrays.asList(lore_1));

        this.getItem().setItemMeta(itemMeta);
        menu.setMenuObjectAt(this.getCoordinates(), this);
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
        System.out.println(this.isSelected);
        this.updateSelected();
    }

    public ScenarioEnum getScenario() {
        return scenario;
    }
}
