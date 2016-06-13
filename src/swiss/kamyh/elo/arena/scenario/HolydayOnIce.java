package swiss.kamyh.elo.arena.scenario;

import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.enumerate.ScenarioEnum;

/**
 * Created by Vincent on 08.06.2016.
 *
 * Ice block for x sec where players walk
 */
public class HolydayOnIce extends Scenario implements IScenario{

    public HolydayOnIce() {
        super(ScenarioEnum.BRAIN_FUCK_TELEPORTATION);
    }

    @Override
    public void activate() {
        Elo.getInstance().setHolydayOnIce(true);
    }


}
