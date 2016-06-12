package swiss.kamyh.elo;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import swiss.kamyh.elo.arena.Party;
import swiss.kamyh.elo.listeners.MenuListener;
import swiss.kamyh.elo.listeners.MenuOver;
import swiss.kamyh.elo.tools.Coord;
import swiss.kamyh.elo.tools.MenuQueue;

/**
 * Created by Vincent on 05.06.2016.
 */
public class Elo extends JavaPlugin implements Listener {
    /**
     * attributs Methods
     */

    private Party party;
    private static Elo instance;
    private static MenuQueue queue;
    private boolean onInteract;
    private boolean onDeath;

    /**
     * override Methods
     */

    @Override
    public void onEnable() {

        this.instance = this;
        queue = new MenuQueue();
        this.instance.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        this.instance.getServer().getPluginManager().registerEvents(new MenuOver(), this);
        this.instance.getServer().getPluginManager().registerEvents(this, this);

        //Fired when the server enables the plugin
        this.party = new Party();
        this.getCommand("party").setExecutor(this);

        Bukkit.getConsoleSender().sendMessage("Initializing");
    }

    @Override
    public void onDisable() {
        //Fired when the server stops and disables all plugins
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        this.party.menu(sender, cmd, args);

        // If the player (or console) uses our command correct, we can return true
        return true;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (this.onInteract) {
            BlockFace dir = Coord.getCardinalDirection(e.getPlayer());
            Block toBlock = e.getTo().getBlock().getRelative(BlockFace.DOWN);
            Block backBlock = toBlock.getRelative(dir);
            Material to = backBlock.getType();
            if (to.equals(Material.GLASS) || to.equals(Material.GRASS)) {
                backBlock.setTypeId(174);
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        if(this.onDeath) {
            System.out.println("DEATH");
        }
    }

    /**
     * private Methods
     */

    /**
     * Public Methods
     */

    public static Elo getInstance() {
        return instance;
    }

    public MenuQueue getQueue() {
        return queue;
    }

    public void setOnInteract(boolean onInteract) {
        this.onInteract = onInteract;
    }
}
