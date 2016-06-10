package swiss.kamyh.elo.gui.scorboard;

import org.bukkit.ChatColor;

/**
 * Created by Vincent on 10.06.2016.
 */
public class CustomMessage {
    public ChatColor color;
    public String string;

    public CustomMessage(ChatColor color, String string) {
        this.color = color;
        this.string = string;
    }
}
