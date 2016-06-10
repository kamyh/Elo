package swiss.kamyh.elo.gui.messages;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;


/**
 * Created by Vincent on 08.06.2016.
 * Use title API ????
 */
public class Message {
    private ArrayList<Player> targets;
    private String messageTitle;
    private String messageSubTitle;

    public Message(ArrayList<Player> targets, String messageTitle, String messageSubTitle)
    {
        this.targets = targets;
        this.messageTitle = messageTitle;
        this.messageSubTitle = messageSubTitle;
    }

    public void clear() {
        this.messageTitle = "";
        this.messageSubTitle = "";
        this.show();
    }

    public void show()
    {
        for(Player player: this.targets) {
            player.sendMessage(ChatColor.translateAlternateColorCodes(ChatColor.RED.getChar(), this.messageTitle));
            player.sendMessage(ChatColor.translateAlternateColorCodes(ChatColor.BLUE.getChar(), this.messageSubTitle));
        }
    }


}
