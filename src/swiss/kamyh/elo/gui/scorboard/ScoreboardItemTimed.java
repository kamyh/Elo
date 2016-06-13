package swiss.kamyh.elo.gui.scorboard;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import swiss.kamyh.elo.Elo;

/**
 * Created by Vincent on 10.06.2016.
 */
public class ScoreboardItemTimed extends ScoreboardItem{
    private int time;
    private int finalId;
    private ScoreboardTimerized parent;
    private IActionOnTimeOut onTimeOut;

    public ScoreboardItemTimed(ScoreboardTimerized parent, CustomMessage[] message, int line, int time) {
        super(parent, message, line);
        this.time = time;
        this.parent = parent;
    }

    public void start() {
        BukkitRunnable timer = new BukkitRunnable() {
            @Override
            public void run() {
                time--;
                ScoreboardItemTimed.this.parent.update();

                if (time <= 0) {
                    stop();
                }
            }
        };

        finalId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Elo.getInstance(), timer, 20, 20);
        timer.run();
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(finalId);
        //this.parent.timesUp();

        this.onTimeOut.callBack();
    }

    //TODO rethink
    @Override
    public String toString()
    {
        String str = "";

        for(CustomMessage s: this.message)
        {
            str += s.color + s.string;
        }

        return str + this.time + " s";
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getFinalId() {
        return finalId;
    }

    public void setFinalId(int finalId) {
        this.finalId = finalId;
    }

    public void addOnTimeOut(IActionOnTimeOut iActionOnTimeOut) {
        this.onTimeOut = iActionOnTimeOut;
    }
}
