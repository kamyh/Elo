package swiss.kamyh.elo.gui.messages;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Vincent on 08.06.2016.
 */
public class Message {
    private ArrayList<Player> targets;
    private String message;

    public Message(ArrayList<Player> targets, String message)
    {
        this.targets = targets;
        this.message = message;
    }

    public void show()
    {
        for(Player player: this.targets) {
            player.sendMessage(ChatColor.translateAlternateColorCodes(ChatColor.RED.getChar(), this.message));
        }
    }
}
