package swiss.kamyh.elo.unitTest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.junit.Test;
import swiss.kamyh.elo.gui.scorboard.Scoreboard;
import swiss.kamyh.elo.gui.scorboard.ScoreboardItem;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;


/**
 * Created by Vincent on 10.06.2016.
 */
public class ScorboardUnitTest {

    @Test
    public final void testScorboardCreation() {
        String titleScorboard = "UnitTests scorboard";
        ArrayList<Player> players = new ArrayList<>();
        players.add((Player) Bukkit.getOfflinePlayer(UUID.fromString("bd04d543-9114-4f37-8ad8-b1fe02ca8257")));

        Scoreboard scoreboard = new Scoreboard(players,titleScorboard);

        assertNotNull("Scorboard created" ,scoreboard);
    }

    @Test
    public final void testAddItemToScorboard() {
        /*String str = "Test scorboard";
        String titleScorboard = "UnitTests scorboard";
        ArrayList<Player> players = new ArrayList<>();
        //players.add(Bukkit.getPlayer("kamhy"));

        Scoreboard scoreboard = new Scoreboard(players,titleScorboard);

        ScoreboardItem scoreboardItem = new ScoreboardItem(scoreboard, "UnitTest scorboard message", 2);
        scoreboard.add(scoreboardItem);

        assertEquals("Scorboard contains massage set in String format", str, scoreboard.getItemAtLine(2).getMessage());*/
    }

    @Test
    public final void testReplaceItemInScoreboard() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public final void testTimedScoreboard() {
        fail("Not yet implemented"); // TODO
    }
}
