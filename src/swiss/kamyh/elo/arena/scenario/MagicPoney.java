package swiss.kamyh.elo.arena.scenario;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import swiss.kamyh.elo.enumerate.ScenarioEnum;
import swiss.kamyh.elo.tools.Configuration;

import java.util.ArrayList;

/**
 * Created by Vincent on 08.06.2016.
 * <p>
 * uses horses to fight
 */
public class MagicPoney extends Scenario implements IScenario {
    private final ArrayList<Player> participants;

    public MagicPoney(ArrayList<Player> participants) {
        super(ScenarioEnum.BRAIN_FUCK_TELEPORTATION);
        this.participants = participants;
    }

    @Override
    public void activate() {
        for (Player p : this.participants) {
            Location location = p.getLocation();

            Horse horse = (Horse) location.getWorld().spawnCreature(location, EntityType.HORSE);
            horse.playEffect(EntityEffect.FIREWORK_EXPLODE);
            horse.setDomestication(1);
            horse.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,9999999,2));
            horse.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,9999999,2));
            horse.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,9999999,2));
            horse.setMaxHealth(20);
            horse.setHealth(20);
            horse.setJumpStrength(2);
            horse.getInventory().addItem(new ItemStack(Material.SADDLE));
            horse.setPassenger(p);
        }
    }
}
