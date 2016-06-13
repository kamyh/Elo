package swiss.kamyh.elo.arena.scenario;

import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.enumerate.ScenarioEnum;

/**
 * Created by Vincent on 08.06.2016.
 * Flower explosions
 */
public class Tron extends Scenario implements IScenario {

    public Tron() {
        super(ScenarioEnum.TRON);
    }

    @Override
    public void activate() {
        Elo.getInstance().setTron(true);
    }
}
