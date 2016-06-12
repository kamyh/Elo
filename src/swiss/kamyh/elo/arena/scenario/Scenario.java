package swiss.kamyh.elo.arena.scenario;

import org.bukkit.entity.Player;
import swiss.kamyh.elo.enumerate.ScenarioEnum;
import swiss.kamyh.elo.tools.ListTools;

import java.util.ArrayList;

/**
 * Created by Vincent on 08.06.2016.
 *
 * Game modifier
 * can be cumulative
 */
public class Scenario {
    protected ScenarioEnum scenarioType;

    public Scenario(ScenarioEnum scenarioType)
    {
        this.scenarioType = scenarioType;
    }

    public ScenarioEnum getScenarioType() {
        return scenarioType;
    }

    @Override
    public String toString()
    {
        return Scenario.nameString(this.scenarioType);
    }

    public static String nameString(ScenarioEnum s)
    {
        switch (s){
            case BRAIN_FUCK_TELEPORTATION:
                return "Brain Fuck Teleportation";
            case BUNNY_UP:
                return "Bunny Up";
            case FLOWER_POWER:
                return "Flower Power";
            case FRIENDLY_FIRE:
                return "Friendly Fire";
            case GLADIATOR:
                return "Gladiator";
            case HOLYDAY_ON_ICE:
                return "Holyday On Ice";
            case MAGIC_PONEY:
                return "Magic Poney";
            case WALKING_DEAD:
                return "Waliking Dead";
        }

        return "";
    }
}
