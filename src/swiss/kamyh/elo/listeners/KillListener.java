package swiss.kamyh.elo.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.CreatureType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scoreboard.Team;
import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.tools.Configuration;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Vincent on 05.06.2016.
 */
public class KillListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Elo.getInstance().getParty().getArena().addKill(e.getEntity().getKiller());

        e.setKeepInventory(true);
        if (Elo.getInstance().isOnDeath()) {

            Random r = new Random();

            int proba = r.nextInt();

            Location location = e.getEntity().getLocation();

            if (proba < 0.8) {
                location.getWorld().spawnCreature(location, CreatureType.ZOMBIE);
            } else {
                location.getWorld().spawnCreature(location, CreatureType.PIG_ZOMBIE);
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if (Elo.getInstance().isStarted()) {
            HashMap<Integer, Team> teams = Elo.getInstance().getTeams();

            System.out.println("Teams: " + e.getPlayer().getName());
            System.out.println("Teams: " + teams.get(0).hasEntry(e.getPlayer().getName()));
            System.out.println("Teams: " + teams.get(1).hasEntry(e.getPlayer().getName()));

            if (teams.get(0).hasEntry(e.getPlayer().getName())) {
                e.setRespawnLocation(new Location(Bukkit.getServer().getWorld("world"), Configuration.spawn_1[0], Configuration.spawn_1[1], Configuration.spawn_1[2]));
            } else if (teams.get(1).hasEntry(e.getPlayer().getName())) {
                e.setRespawnLocation(new Location(Bukkit.getServer().getWorld("world"), Configuration.spawn_2[0], Configuration.spawn_2[1], Configuration.spawn_2[2]));
            }
        }
    }
}