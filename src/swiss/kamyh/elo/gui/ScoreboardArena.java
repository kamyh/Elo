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
public class ScoreboardArena {
    int time = 30;

    private ScoreboardManager sbManager;
    private Scoreboard scoreBoard;
    private Objective obj;

    private Score s0;
    private Score s1;
    private int finalId;
    private Arena parent;
    private ArrayList<Player> players;

    public ScoreboardArena(Arena parent, ArrayList<Player> players)
    {
        this.parent = parent;
        this.players = players;
    }

    public void init() {

        sbManager = Bukkit.getScoreboardManager();
        scoreBoard = sbManager.getNewScoreboard();

        obj = scoreBoard.registerNewObjective("ScoreBoard", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("Game");

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
        for(Player player: players) {
            this.scoreBoardKill(player,0);
        }
        parent.startGame();
    }

    private void scoreBoardKill(Player p, int kill) {
        scoreBoard = sbManager.getNewScoreboard();
        obj = scoreBoard.registerNewObjective("ScoreBoard", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("Elo Arena");

        Score scoreKill = obj.getScore(Bukkit.getOfflinePlayer("Kill: " + kill));

        scoreKill.setScore(6);

        p.setScoreboard(scoreBoard);
    }

    public void updateScoreBoard(Player p) {

        scoreBoard = sbManager.getNewScoreboard();
        obj = scoreBoard.registerNewObjective("ScoreBoard", "dummy");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("Elo Arena");

        s0 = obj.getScore(Bukkit.getOfflinePlayer("Selection d'inventaire"));
        s1 = obj.getScore(Bukkit.getOfflinePlayer("La partie commence dans: " + time));

        s1.setScore(6);
        s0.setScore(7);

        p.setScoreboard(scoreBoard);
    }
}
