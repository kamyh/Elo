package swiss.kamyh.elo.gui.scorboard;

/**
 * Created by Vincent on 10.06.2016.
 */
public interface ITimerized {
    public void callbackTimer(ScoreboardItemTimed item);
    public void timesUp();
}
