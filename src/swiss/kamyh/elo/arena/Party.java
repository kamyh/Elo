package swiss.kamyh.elo.arena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.meta.SkullMeta;
import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.arena.Arena;
import swiss.kamyh.elo.gui.Item;
import swiss.kamyh.elo.gui.Menu;
import swiss.kamyh.elo.listeners.IAction;
import swiss.kamyh.elo.tools.Coord;
import swiss.kamyh.elo.tools.MenuQueue;
import swiss.kamyh.elo.tools.PlayerTools;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Vincent on 05.06.2016.
 */
public class Party {


    /**
     * attributs Methods
     */

    private List<Player> players;
    private List<Player> team;
    private Player sender;
    private InventoryHolder player;

    /**
     * override Methods
     */

    /**
     * private Methods
     */


    private void addTeamMateSelectionIcon(Player sender, List<Player> teamates, int fromX, int fromY) {
        //TODO test if valid player
        int index = 0;

        for (Player player : teamates) {

            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta playerSkull = (SkullMeta) skull.getItemMeta();
            playerSkull.setOwner(player.getName());
            playerSkull.setDisplayName(ChatColor.GREEN + player.getName());
            skull.setItemMeta(playerSkull);
            Item item = new Item(skull);

            item.setActionListener(new IAction() {
                public void onClick(ClickType clickType, Item menuObject, Player player) {
                    if (clickType == ClickType.LEFT) {
                        //TODO
                    }
                }
            });

            Coord coordinates = new Coord(Elo.getInstance().getQueue().getCurrent(), fromX + index, fromY);
            Elo.getInstance().getQueue().getCurrent().setMenuObjectAt(coordinates, item);

            index++;
        }
    }

    /**
     * Public Methods
     */
    public void menu(CommandSender sender, Command cmd, String[] args) {
        //TODO test if 3 args
        List<List<Player>> participants = new ArrayList<>();
        participants.add(new ArrayList<Player>());
        participants.add(new ArrayList<Player>());

        //TODO test if first ars is int
        //TODO test if versus < 5
        int versus = Integer.parseInt(args[0]);

        /*code for n*/
        /*for(String playerName:args)
        {
            Player player = Bukkit.getPlayer(playerName);
            participants.get(0).add(player);
        }*/

        for (int i = 1; i < versus + 1; i++) {
            //TODO test player validity
            Player player = Bukkit.getPlayer(args[i]);
            participants.get(0).add(player);
        }

        for (int i = versus + 1; i < versus * 2 + 1; i++) {
            //TODO test player validity
            Player player = Bukkit.getPlayer(args[i]);
            participants.get(1).add(player);
        }


        if ((sender instanceof Player) && cmd.getName().compareTo("menu") == 0) {
            this.sender = (Player) sender;
            Inventory inventory = this.sender.getInventory();
            inventory.clear();

            Menu menu = new Menu(Bukkit.createInventory(this.sender, 54, "Elo"));

            menu.addLeaveIcon(9, 6);
            addTeamMateSelectionIcon(this.sender, participants.get(0), 5 - (int) Math.floor(versus / 2.), 2);
            addTeamMateSelectionIcon(this.sender, participants.get(1), 5 - (int) Math.floor(versus / 2.), 4);

            //TODO match making instead
            Item item = menu.addIcon(5, 3, Material.FENCE_GATE);
            item.setActionListener(new IAction() {
                @Override
                public void onClick(ClickType clickType, Item item, Player whoClicked) {
                    if (clickType == ClickType.LEFT) {
                        System.out.println(Elo.getInstance().getQueue().toString());

                        Arena arena = new Arena(participants);
                    }
                }
            });

            menu.openForPlayer(this.sender);
        }
    }

    public InventoryHolder getPlayer() {
        return sender;
    }
}
