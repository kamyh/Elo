package swiss.kamyh.elo.tests;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import swiss.kamyh.elo.gui.scorboard.CustomMessage;
import swiss.kamyh.elo.gui.scorboard.Scoreboard;
import swiss.kamyh.elo.gui.scorboard.ScoreboardItem;
import swiss.kamyh.elo.gui.scorboard.ScoreboardTimerized;
import swiss.kamyh.elo.tools.ListTools;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Vincent on 10.06.2016.
 */
public class Dummy {

    public void test_1()
    {
        String str = "Test scorboard";
        String titleScorboard = "UnitTests scorboard";
        ArrayList<Player> players = new ArrayList<>();
        //players.add(Bukkit.getPlayer("kamhy"));

        Scoreboard scoreboard = new Scoreboard(players,titleScorboard);

        ScoreboardItem scoreboardItem = new ScoreboardItem(scoreboard, new CustomMessage[]{new CustomMessage(ChatColor.AQUA, "UnitTest scorboard message")}, 2);
        scoreboard.add(scoreboardItem);

         scoreboard.getItemAtLine(2).getMessage();
    }

    public static void main (String[] args){
        Dummy d = new Dummy();
        d.test_1();
    }
}
