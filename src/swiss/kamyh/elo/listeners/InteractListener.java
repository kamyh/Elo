package swiss.kamyh.elo.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.tools.Coord;

import java.util.Random;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * Created by Vincent on 05.06.2016.
 *
 * BUGGY
 */
public class InteractListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (Elo.getInstance().isTeleportBrain()) {
            Random random = new Random();
            Player p = (Player)e.getDamager();

            int x = random.nextInt() % 5;
            int y = random.nextInt() % 5;
            int z = random.nextInt() % 5;

            Block b = p.getWorld().getBlockAt(x,y,z);
            System.out.println(x + " - " + y + " - " + z);

            while(b.getType() != Material.AIR & b.getRelative(BlockFace.UP).getType() != Material.AIR)
            {
                x = random.nextInt() % 5;
                y = random.nextInt() % 5;
                z = random.nextInt() % 5;

                System.out.println(x + " - " + y + " - " + z);
            }

            if(y < 0)
            {
                y *= -1;
            }

            p.teleport(new Location(p.getWorld(),x,y,z));
        }
    }
}