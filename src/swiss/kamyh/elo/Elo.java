package swiss.kamyh.elo;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;
import swiss.kamyh.elo.arena.Arena;
import swiss.kamyh.elo.arena.Party;
import swiss.kamyh.elo.listeners.*;
import swiss.kamyh.elo.tools.MenuQueue;

import java.util.HashMap;

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
    private boolean holydayOnIce;
    private boolean onDeath;
    private HashMap<Integer, Team> teams;
    private boolean started;
    private boolean tron;
    private boolean teleportBrain;

    /**
     * override Methods
     */

    @Override
    public void onEnable() {

        this.instance = this;
        queue = new MenuQueue();
        this.instance.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        this.instance.getServer().getPluginManager().registerEvents(new MenuOver(), this);
        this.instance.getServer().getPluginManager().registerEvents(new KillListener(), this);
        this.instance.getServer().getPluginManager().registerEvents(new MoveListener(), this);
        this.instance.getServer().getPluginManager().registerEvents(new InteractListener(), this);
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

    public void setHolydayOnIce(boolean holydayOnIce) {
        this.holydayOnIce = holydayOnIce;
    }

    public boolean isOnDeath() {
        return onDeath;
    }

    public void setOnDeath(boolean onDeath) {
        this.onDeath = onDeath;
    }

    public void setTeam(HashMap<Integer, Team> teams) {
        this.teams = teams;
    }

    public HashMap<Integer, Team> getTeams() {
        return teams;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isHolydayOnIce() {
        return holydayOnIce;
    }

    public boolean isTron() {
        return tron;
    }

    public void setTron(boolean tron) {
        this.tron = tron;
    }

    public boolean isTeleportBrain() {
        return teleportBrain;
    }

    public void setTeleportBrain(boolean teleportBrain) {
        this.teleportBrain = teleportBrain;
    }

    public Party getParty() {
        return party;
    }
}
