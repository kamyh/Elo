package swiss.kamyh.elo.arena.scenario;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;
import swiss.kamyh.elo.enumerate.ScenarioEnum;
import swiss.kamyh.elo.gui.scorboard.ScoreboardTimerized;

import java.util.ArrayList;

/**
 * Created by Vincent on 08.06.2016.
 * speed + jump boost
 */
public class BunnyUp extends Scenario implements IScenario{
    private ArrayList<Player> participants;

    public BunnyUp(ArrayList<Player> participants) {
        super(ScenarioEnum.BRAIN_FUCK_TELEPORTATION);
        this.participants = participants;
    }

    @Override
    public void activate() {
        for(Player p: this.participants)
        {
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,9999999,2));
        }
    }
}
