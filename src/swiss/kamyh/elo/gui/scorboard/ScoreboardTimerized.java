package swiss.kamyh.elo.gui.scorboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;
import swiss.kamyh.elo.arena.Arena;

import java.util.ArrayList;

/**
 * Created by Vincent on 07.06.2016.
 * TODO TEST
 * Not Enough General --> parent/timesUp() ==> Replace by callback or event ?
 */
public class ScoreboardTimerized extends Scoreboard implements ITimerized{
    private Arena parents;

    public ScoreboardTimerized(ArrayList<Player> players, String name, Arena parents) {
        super(players, name);
        this.parents = parents;
    }

    @Override
    public void callbackTimer(ScoreboardItemTimed item) {
        this.update();
    }

    @Override
    public void timesUp() {
        this.parents.startGame();
    }


}
