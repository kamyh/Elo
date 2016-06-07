package swiss.kamyh.elo;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import swiss.kamyh.elo.listeners.MenuListener;
import swiss.kamyh.elo.listeners.MenuOver;
import swiss.kamyh.elo.tools.MenuQueue;

/**
 * Created by Vincent on 05.06.2016.
 */
public class Elo extends JavaPlugin{
    /**
     * attributs Methods
     */

    private Party party;
    private static Elo instance;
    private static MenuQueue queue;

    /**
     * override Methods
     */

    @Override
    public void onEnable(){
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");


        this.instance = this;
        queue = new MenuQueue();
        this.instance.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        this.instance.getServer().getPluginManager().registerEvents(new MenuOver(), this);

        //Fired when the server enables the plugin
        this.party = new Party();
        this.getCommand("party").setExecutor(new Elo());

        Bukkit.getConsoleSender().sendMessage("Initializing ");
    }

    @Override
    public void onDisable() {
        //Fired when the server stops and disables all plugins
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        this.party.menu(sender, cmd, args);
        this.party.invitation(sender, cmd);

        // If the player (or console) uses our command correct, we can return true
        return true;
    }

    /**
     * private Methods
     */

    /**
     * Public Methods
     */

    public static Elo getInstance()
    {
        return instance;
    }

    public MenuQueue getQueue()
    {
        return queue;
    }

}
