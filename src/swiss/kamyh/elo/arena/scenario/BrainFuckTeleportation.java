package swiss.kamyh.elo.arena.scenario;

import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.enumerate.ScenarioEnum;

/**
 * Created by Vincent on 08.06.2016.
 * when hit --> randomly teleported
 */
public class BrainFuckTeleportation extends Scenario implements IScenario{
    public BrainFuckTeleportation() {
        super(ScenarioEnum.BRAIN_FUCK_TELEPORTATION);
    }

    @Override
    public void activate() {
        Elo.getInstance().setTeleportBrain(true);
    }
}
