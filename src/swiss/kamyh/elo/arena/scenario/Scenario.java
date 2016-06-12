package swiss.kamyh.elo.arena.scenario;

import swiss.kamyh.elo.enumerate.ScenarioEnum;

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
}
