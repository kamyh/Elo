package swiss.kamyh.elo.gui.scorboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Vincent on 07.06.2016.
 * TODO TEST
 */
public class Scoreboard{
    public static int NumberOfLine = 13;
    private String objectiveName;

    protected ScoreboardManager sbManager;
    protected org.bukkit.scoreboard.Scoreboard scoreBoard;
    private ArrayList<ScoreboardItem> items;
    protected Objective obj;

    private String name;
    private ArrayList<Player> players;

    public Scoreboard(ArrayList<Player> players, String name) {
        this.name = name;
        this.players = players;
        this.objectiveName = "scorboard_base";
        this.items = new ArrayList<>();

        this.init();
    }

    public void init() {

        sbManager = Bukkit.getScoreboardManager();
        scoreBoard = sbManager.getNewScoreboard();

        obj = scoreBoard.registerNewObjective("ScoreBoard", this.objectiveName);

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(this.name);

        for (Player player : this.players) {
            player.setScoreboard(scoreBoard);
        }
    }

    public void add(ScoreboardItem item) {
        if(!this.items.contains(item)) {
            this.items.add(item);
            this.update(item);
        }

        //TODO Trhow error ???
    }

    protected void update(ScoreboardItem item) {
        scoreBoard = sbManager.getNewScoreboard();
        obj = scoreBoard.registerNewObjective("ScoreBoard", this.objectiveName);

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(this.name);

        Score score = obj.getScore(item.toString());
        score.setScore(item.getLine());

        for (Player player : this.players) {
            player.setScoreboard(scoreBoard);
        }
    }

    public void replace(ScoreboardItem item) {
        if(this.items.contains(item)) {
            int i = this.items.indexOf(item);
            this.items.remove(i);
            this.items.add(item);
            this.update(item);
        }
    }

    public void removeItemAt(int i)
    {
        Iterator it = this.items.iterator();
        for(ScoreboardItem scoreboardItem: this.items)
        {
            if(scoreboardItem.getLine() == i)
            {
                this.items.remove(scoreboardItem);
            }
        }
    }

    public ScoreboardItem getItemAtLine(int i) {
        Iterator it = this.items.iterator();
        for(ScoreboardItem scoreboardItem: this.items)
        {
            if(scoreboardItem.getLine() == i)
            {
                return scoreboardItem;
            }
        }
        return null;
    }
}
