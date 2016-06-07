package swiss.kamyh.elo.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.*;
import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.arena.Arena;

import java.util.ArrayList;

/**
 * Created by Vincent on 07.06.2016.
 */
public class ScoreboardInventory {
    int time = 30;

    private ScoreboardManager sbManager;
    private Scoreboard scoreBoard;
    private Objective obj;

    private Score s0;
    private int finalId;
    private Arena parent;
    private ArrayList<Player> players;

    public ScoreboardInventory(Arena parent, ArrayList<Player> players)
    {
        this.parent = parent;
        this.players = players;
    }

    public void init() {

        sbManager = Bukkit.getScoreboardManager();
        scoreBoard = sbManager.getNewScoreboard();

        obj = scoreBoard.registerNewObjective("ScoreBoard", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("Elo Arena");

        s0 = obj.getScore(Bukkit.getOfflinePlayer("Selection d'inventaire: " + time));

        s0.setScore(6);

        for(Player player: this.players) {
            player.setScoreboard(scoreBoard);
        }

        BukkitRunnable timer = new BukkitRunnable()
        {
            @Override
            public void run()
            {
                time--;
                for(Player player: players) {
                    updateScoreBoard(player);
                }

                if(time <= 0)
                {
                    stopSelectionPhase();
                }
            }
        };

        finalId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Elo.getInstance(), timer, 20, 20);
        timer.run();
    }

    public void stopSelectionPhase() {
        Bukkit.getScheduler().cancelTask(finalId);
        parent.startGame();
    }

    public void updateScoreBoard(Player p) {

        scoreBoard = sbManager.getNewScoreboard();
        obj = scoreBoard.registerNewObjective("ScoreBoard", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("Elo Arena");

        s0 = obj.getScore(Bukkit.getOfflinePlayer("Selection d'inventaire"));
        s0 = obj.getScore(Bukkit.getOfflinePlayer("Time: " + time));

        s0.setScore(6);

        p.setScoreboard(scoreBoard);
    }
}
