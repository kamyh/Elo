package swiss.kamyh.elo.arena.scenario;

import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.enumerate.ScenarioEnum;

/**
 * Created by Vincent on 08.06.2016.
 *
 * When a player dies it spawn zombies
 */
public class WalkingDead extends Scenario implements IScenario{
    public WalkingDead() {
        super(ScenarioEnum.BRAIN_FUCK_TELEPORTATION);
    }

    @Override
    public void activate() {
        Elo.getInstance().setOnDeath(true);
    }
}
