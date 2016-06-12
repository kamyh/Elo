package swiss.kamyh.elo.arena.scenario;

import org.bukkit.scoreboard.Team;
import swiss.kamyh.elo.enumerate.ScenarioEnum;
import swiss.kamyh.elo.gui.scorboard.ScoreboardTimerized;

/**
 * Created by Vincent on 08.06.2016.
 *
 *
 */
public class FriendlyFire extends Scenario implements IScenario{
    private ScoreboardTimerized scoreboardArena;

    public FriendlyFire(ScoreboardTimerized scoreboardArena) {
        super(ScenarioEnum.BRAIN_FUCK_TELEPORTATION);

        this.scoreboardArena = scoreboardArena;
    }

    @Override
    public void activate() {
        for(Team t:this.scoreboardArena.getScoreBoard().getTeams())
        {
            t.setAllowFriendlyFire(false);
        }
    }
}
