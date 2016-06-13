package swiss.kamyh.elo.gui.scorboard;

/**
 * Created by Vincent on 10.06.2016.
 */
public class ScoreboardItem {
    protected CustomMessage[] message;
    private int line;
    private Scoreboard parent;

    public ScoreboardItem(Scoreboard parent, CustomMessage[] message, int line) {
        this.message = message;
        this.line = line;
        this.parent = parent;
    }

    public CustomMessage[] getMessage() {
        return message;
    }

    public void setMessage(CustomMessage[] message) {
        this.message = message;
    }

    public int getLine() {
        return line;
    }

    public String toString() {
        String str = "";

        for (CustomMessage s : this.message) {
            str += s.color + s.string;
        }

        return str;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public Scoreboard getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object item) {
        if(item instanceof ScoreboardItem && item != null) {
            if (((ScoreboardItem) item).getLine() != this.line) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        System.out.println("===========================");
        int hash = 7;
        hash = 17 * hash + (this.line != -1 ? this.line : 0);
        return hash;
    }

}
