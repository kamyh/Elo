package swiss.kamyh.elo;

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
import swiss.kamyh.elo.arena.Arena;
import swiss.kamyh.elo.gui.Item;
import swiss.kamyh.elo.gui.Menu;
import swiss.kamyh.elo.listeners.IAction;
import swiss.kamyh.elo.tools.Coord;
import swiss.kamyh.elo.tools.MenuQueue;
import swiss.kamyh.elo.tools.PlayerTools;

import java.util.ArrayList;
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

    /*private void buildHeadsMenu(Menu menu, int page) {
        //TODO add next  + prev icon
        menu.getInventory().clear();

        int pNumber = Math.min(this.players.size(), 36);
        System.out.println(pNumber);

        for (int i = 1; i < pNumber + 1; i++) {
            //this.addSkullToMenu(menu, this.players.get((page + 1) * i));
            this.addSkullToMenu(menu, this.sender, i);
        }
    }

    private void addSkullToMenu(Menu menu, Player teamMate, int index) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta player = (SkullMeta) skull.getItemMeta();
        player.setOwner(teamMate.getName());
        player.setDisplayName(ChatColor.GREEN + teamMate.getName());
        skull.setItemMeta(player);
        Item item = new Item(skull);

        item.setActionListener(new IAction() {
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    if (Party.this.team.size() < 3) {
                        Party.this.team.add(teamMate);
                    } else {
                        Party.this.sender.sendMessage("Too much Teammates!");
                    }
                }
            }
        });

        //TODO methods to place head
        Coord coordinates = new Coord(menu, index, 1);
        menu.setMenuObjectAt(coordinates, item);
    }

    private void buildHeadsMenu(Menu menu) {
        this.players = PlayerTools.getFake();
        System.out.println(this.players.size());
        this.team = new ArrayList<>();
        this.buildHeadsMenu(menu, 0);
    }*/



    private void addTeamMateSelectionIcon(Player sender, String[] teamates) {
        //TODO test if valid player
        int index = 0;

        for (String player_name : teamates) {

            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta player = (SkullMeta) skull.getItemMeta();
            player.setOwner(player_name);
            player.setDisplayName(ChatColor.GREEN + player_name);
            skull.setItemMeta(player);
            Item item = new Item(skull);

            item.setActionListener(new IAction() {
                public void onClick(ClickType clickType, Item menuObject, Player player) {
                    if (clickType == ClickType.LEFT) {
                        //TODO
                    }
                }
            });

            Coord coordinates = new Coord(Elo.getInstance().getQueue().getCurrent(), 4 + index, 2);
            Elo.getInstance().getQueue().getCurrent().setMenuObjectAt(coordinates, item);

            index++;
        }
    }

    /**
     * Public Methods
     */
    public void menu(CommandSender sender, Command cmd, String[] args) {
        //TODO test if 3 args
        if ((sender instanceof Player) && cmd.getName().compareTo("menu") == 0) {
            this.sender = (Player) sender;
            Inventory inventory = this.sender.getInventory();
            inventory.clear();

            Menu menu = new Menu(Bukkit.createInventory(this.sender, 54, "Elo"));

            menu.addLeaveIcon(9, 6);
            addTeamMateSelectionIcon(this.sender, args);

            Item item = menu.addIcon(5,3,Material.FENCE_GATE);
            item.setActionListener(new IAction() {
                @Override
                public void onClick(ClickType clickType, Item item, Player whoClicked) {
                    if (clickType == ClickType.LEFT) {
                        System.out.println(Elo.getInstance().getQueue().toString());
                        Arena arena = new Arena();
                    }
                }
            });

            menu.openForPlayer(this.sender);
        }
    }

    public void invitation(CommandSender sender, Command cmd) {
        System.out.print(cmd.getName());

        if ((sender instanceof Player) && cmd.getName().compareTo("party") == 0) {
            Player player = (Player) sender;

            // Create a new ItemStack (type: diamond)
            ItemStack diamond = new ItemStack(Material.DIAMOND);

            // Create a new ItemStack (type: brick)
            ItemStack bricks = new ItemStack(Material.BRICK);

            // Set the amount of the ItemStack
            bricks.setAmount(20);

            // Give the player our items (comma-seperated list of all ItemStack)
            player.getInventory().addItem(bricks, diamond);
        }
    }

    public InventoryHolder getPlayer() {
        return sender;
    }
}
