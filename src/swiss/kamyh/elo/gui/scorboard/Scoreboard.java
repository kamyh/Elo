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
public class Scoreboard {
    private String objectiveName;

    protected ScoreboardManager sbManager;

    protected org.bukkit.scoreboard.Scoreboard scoreBoard;
    private ArrayList<ScoreboardItem> items;
    protected Objective obj;

    private String name;
    private ArrayList<Player> players;
    private ScoreboardItem killTeam_1;
    private ScoreboardItem killTeam_2;
    private Object teamsKill;

    public Scoreboard(ArrayList<Player> players, String name) {
        this.name = name;
        this.players = players;
        this.objectiveName = "scorboard_base";
        this.items = new ArrayList<>();

        this.init();
    }

    public void initDisplayKill(String m1, String m2)
    {
        this.killTeam_1 = new ScoreboardItem(this,new CustomMessage[]{new CustomMessage(ChatColor.GOLD, m1)}, 7);
        this.killTeam_2 = new ScoreboardItem(this,new CustomMessage[]{new CustomMessage(ChatColor.GOLD, m2)}, 6);
        this.add(this.killTeam_1);
        this.add(this.killTeam_2);
    }

    public void displayKills(String m1, String m2) {
        this.killTeam_1.setMessage(new CustomMessage[]{new CustomMessage(ChatColor.GOLD, m1)});
        this.killTeam_2.setMessage(new CustomMessage[]{new CustomMessage(ChatColor.GOLD, m2)});
        this.update();
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

        if (!this.items.contains(item)) {
            this.items.add(item);
            this.update();
        }
        System.out.printf("Scoreboard has %ditems.%n", this.items.size());

        //TODO Throw error ???
    }

    public void update() {
        scoreBoard = sbManager.getNewScoreboard();
        obj = scoreBoard.registerNewObjective("ScoreBoard", this.objectiveName);

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(this.name);

        for (ScoreboardItem item : this.items) {
            Score score = obj.getScore(item.toString());
            score.setScore(item.getLine());
        }

        for (Player player : this.players) {
            player.setScoreboard(scoreBoard);
        }
    }

    public void replace(ScoreboardItem item) {
        if (this.items.contains(item)) {
            int i = this.items.indexOf(item);
            this.items.remove(i);
            this.items.add(item);
            this.update();
        }
    }

    public void removeItemAt(int i) {
        Iterator it = this.items.iterator();
        ScoreboardItem toRemove = null;
        for (ScoreboardItem scoreboardItem : this.items) {
            if (scoreboardItem.getLine() == i) {
                toRemove = scoreboardItem;
            }
        }
        this.items.remove(toRemove);
    }

    public ScoreboardItem getItemAtLine(int i) {
        Iterator it = this.items.iterator();
        for (ScoreboardItem scoreboardItem : this.items) {
            if (scoreboardItem.getLine() == i) {
                return scoreboardItem;
            }
        }
        return null;
    }

    public static String getElementSeparator(int n) {
        String str = "";
        for (int i = 0; i < n; i++)
            str += "-";
        return str;
    }

    public org.bukkit.scoreboard.Scoreboard getScoreBoard() {
        return scoreBoard;
    }

    public Object getTeamsKill() {
        return teamsKill;
    }
}
