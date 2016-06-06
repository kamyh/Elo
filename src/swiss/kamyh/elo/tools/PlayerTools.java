package swiss.kamyh.elo.tools;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 05.06.2016.
 */
public class PlayerTools {
    public static List<Player> getFake()
    {
        List<Player> list = new ArrayList<>();

        list.add(Bukkit.getPlayer("Collins"));
        list.add(Bukkit.getPlayer("Fernandez"));
        list.add(Bukkit.getPlayer("Gonzalez"));
        list.add(Bukkit.getPlayer("Lambert"));
        list.add(Bukkit.getPlayer("Chapman"));

        return list;
    }
}
