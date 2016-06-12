package swiss.kamyh.elo.arena;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;
import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.arena.scenario.*;
import swiss.kamyh.elo.enumerate.GroupItemType;
import swiss.kamyh.elo.enumerate.ScenarioEnum;
import swiss.kamyh.elo.gui.*;
import swiss.kamyh.elo.gui.scorboard.*;
import swiss.kamyh.elo.listeners.IAction;
import swiss.kamyh.elo.tools.Coord;
import swiss.kamyh.elo.tools.GroupItem;
import swiss.kamyh.elo.tools.ListTools;

import java.util.*;


/**
 * Created by Vincent on 06.06.2016.
 */
public class Arena {

    HashMap<org.bukkit.Material, ItemArmor> items;
    List<List<Player>> participants;
    private ScoreboardTimerized scoreboardArena;
    private ScoreboardItemTimed scoreBoardItemInventorySelection;
    private Team team_1;
    private Team team_2;

    public Arena(List<List<Player>> participants) {

        this.participants = participants;
        this.preparePlayers();

        createScorebord();

        Menu menu = new Menu(Bukkit.createInventory(Elo.getInstance().getQueue().getCurrent().getInventory().getHolder(), 54, "Arena"));

        Item item = menu.addIcon(5, 3, org.bukkit.Material.ARMOR_STAND);
        item.setCustumName("Selection de l'équipement");
        item.update();

        item.setActionListener(new IAction() {
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    Arena.this.scoreBoardItemInventorySelection.start();
                    armorSelection();
                }
            }
        });

        menu.addLeaveIcon(9, 6);
        menu.addPrevIcon(8, 6);

        menu.openForPlayer((Player) Elo.getInstance().getQueue().getCurrent().getInventory().getHolder());
    }

    private void preparePlayers() {
        //TODO move to server arena
        for (Player player : this.participants.get(0)) {
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            player.setWalkSpeed(0);

            player.teleport(new Location(Bukkit.getServer().getWorld("world"), 10, 5, 5));
        }

        for (Player player : this.participants.get(1)) {
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            player.setWalkSpeed(0);

            player.teleport(new Location(Bukkit.getServer().getWorld("world"), -10, 5, 5));
        }
    }

    private void createScorebord() {
        this.scoreboardArena = new ScoreboardTimerized((ArrayList<Player>) ListTools.flatten(this.participants), "Arena UHC", this);
        this.scoreBoardItemInventorySelection = new ScoreboardItemTimed(this.scoreboardArena, new CustomMessage[]{new CustomMessage(ChatColor.AQUA, "Time Left: ")}, 2, 100);
        ScoreboardItem scoreboardItemPhaseName = new ScoreboardItem(this.scoreboardArena, new CustomMessage[]{new CustomMessage(ChatColor.BLUE, "Selection de l'inventaire")}, 4);
        ScoreboardItem separator_1 = new ScoreboardItem(this.scoreboardArena, new CustomMessage[]{new CustomMessage(ChatColor.GRAY, Scoreboard.getElementSeparator(20))}, 3);
        this.scoreboardArena.add(this.scoreBoardItemInventorySelection);
        this.scoreboardArena.add(separator_1);
        this.scoreboardArena.add(scoreboardItemPhaseName);
    }

    private void armorSelection() {
        Menu menu = new Menu(Bukkit.createInventory(Elo.getInstance().getQueue().getCurrent().getInventory().getHolder(), 54, "Armor"));

        this.items = new HashMap<org.bukkit.Material, ItemArmor>();

        GroupItem groupItemSword = new GroupItem(GroupItemType.SOWRD, 1);

        items.put(org.bukkit.Material.DIAMOND_SWORD, new ItemArmor(1, 1, org.bukkit.Material.DIAMOND_SWORD, 1, menu, groupItemSword));
        items.put(org.bukkit.Material.IRON_SWORD, new ItemArmor(2, 1, org.bukkit.Material.IRON_SWORD, 1, menu, groupItemSword));
        items.put(org.bukkit.Material.GOLD_SWORD, new ItemArmor(3, 1, org.bukkit.Material.GOLD_SWORD, 1, menu, groupItemSword));
        items.put(org.bukkit.Material.WOOD_SWORD, new ItemArmor(4, 1, org.bukkit.Material.WOOD_SWORD, 1, menu, groupItemSword));

        GroupItem groupItemAxe = new GroupItem(GroupItemType.AXE, 2);

        items.put(org.bukkit.Material.DIAMOND_AXE, new ItemArmor(6, 1, org.bukkit.Material.DIAMOND_AXE, 1, menu, groupItemAxe));
        items.put(org.bukkit.Material.IRON_AXE, new ItemArmor(7, 1, org.bukkit.Material.IRON_AXE, 1, menu, groupItemAxe));
        items.put(org.bukkit.Material.GOLD_AXE, new ItemArmor(8, 1, org.bukkit.Material.GOLD_AXE, 1, menu, groupItemAxe));
        items.put(org.bukkit.Material.WOOD_AXE, new ItemArmor(9, 1, org.bukkit.Material.WOOD_AXE, 1, menu, groupItemAxe));

        GroupItem groupItemHelmet = new GroupItem(GroupItemType.HELMET, 1);

        items.put(org.bukkit.Material.DIAMOND_HELMET, new ItemArmor(1, 2, org.bukkit.Material.DIAMOND_HELMET, 1, menu, groupItemHelmet));
        items.put(org.bukkit.Material.IRON_HELMET, new ItemArmor(2, 2, org.bukkit.Material.IRON_HELMET, 1, menu, groupItemHelmet));
        items.put(org.bukkit.Material.GOLD_HELMET, new ItemArmor(3, 2, org.bukkit.Material.GOLD_HELMET, 1, menu, groupItemHelmet));

        GroupItem groupItemChestplat = new GroupItem(GroupItemType.CHESTPLAT, 1);

        items.put(org.bukkit.Material.DIAMOND_CHESTPLATE, new ItemArmor(7, 2, org.bukkit.Material.DIAMOND_CHESTPLATE, 1, menu, groupItemChestplat));
        items.put(org.bukkit.Material.IRON_CHESTPLATE, new ItemArmor(8, 2, org.bukkit.Material.IRON_CHESTPLATE, 1, menu, groupItemChestplat));
        items.put(org.bukkit.Material.GOLD_CHESTPLATE, new ItemArmor(9, 2, org.bukkit.Material.GOLD_CHESTPLATE, 1, menu, groupItemChestplat));

        GroupItem groupItemLegin = new GroupItem(GroupItemType.LEGIN, 1);

        items.put(org.bukkit.Material.DIAMOND_LEGGINGS, new ItemArmor(1, 3, org.bukkit.Material.DIAMOND_LEGGINGS, 1, menu, groupItemLegin));
        items.put(org.bukkit.Material.IRON_LEGGINGS, new ItemArmor(2, 3, org.bukkit.Material.IRON_LEGGINGS, 1, menu, groupItemLegin));
        items.put(org.bukkit.Material.GOLD_LEGGINGS, new ItemArmor(3, 3, org.bukkit.Material.GOLD_LEGGINGS, 1, menu, groupItemLegin));

        GroupItem groupItemBoots = new GroupItem(GroupItemType.BOOTS, 1);

        items.put(org.bukkit.Material.DIAMOND_BOOTS, new ItemArmor(7, 3, org.bukkit.Material.DIAMOND_BOOTS, 1, menu, groupItemBoots));
        items.put(org.bukkit.Material.IRON_BOOTS, new ItemArmor(8, 3, org.bukkit.Material.IRON_BOOTS, 1, menu, groupItemBoots));
        items.put(org.bukkit.Material.GOLD_BOOTS, new ItemArmor(9, 3, org.bukkit.Material.GOLD_BOOTS, 1, menu, groupItemBoots));

        GroupItem groupItemBlock = new GroupItem(GroupItemType.BLOCK, 21);

        items.put(org.bukkit.Material.HAY_BLOCK, new ItemArmor(1, 4, org.bukkit.Material.HAY_BLOCK, 15, menu, groupItemBlock));
        items.put(org.bukkit.Material.MELON_BLOCK, new ItemArmor(2, 4, org.bukkit.Material.MELON_BLOCK, 15, menu, groupItemBlock));
        items.put(org.bukkit.Material.SLIME_BLOCK, new ItemArmor(3, 4, org.bukkit.Material.SLIME_BLOCK, 15, menu, groupItemBlock));

        GroupItem groupItemVarious = new GroupItem(GroupItemType.VARIOUS, 1);

        items.put(org.bukkit.Material.FISHING_ROD, new ItemArmor(1, 5, org.bukkit.Material.FISHING_ROD, 1, menu, groupItemVarious));
        ItemArmor bow = new ItemArmor(2, 5, org.bukkit.Material.BOW, 1, menu, groupItemVarious);
        bow.setCustumName("Infinity");
        bow.enchant(Enchantment.ARROW_INFINITE, 1);
        bow.updateTagQuantity();
        items.put(org.bukkit.Material.BOW, bow);
        items.put(org.bukkit.Material.ENDER_PORTAL_FRAME, new ItemArmorRandom(3, 5, org.bukkit.Material.ENDER_PORTAL_FRAME, 1, menu, groupItemVarious));

        GroupItem groupItemCompos = new GroupItem(GroupItemType.COMPOS, 5);

        items.put(org.bukkit.Material.GOLDEN_APPLE, new ItemArmor(1, 6, org.bukkit.Material.GOLDEN_APPLE, 5, menu, groupItemCompos));
        items.put(org.bukkit.Material.FLINT_AND_STEEL, new ItemArmor(2, 6, org.bukkit.Material.FLINT_AND_STEEL, 1, menu, groupItemCompos));

        for (Item item : items.values()) {
            item.setActionListener(new IAction() {
                public void onClick(ClickType clickType, Item menuObject, Player player) {
                    if (clickType == ClickType.LEFT) {
                        int max = ((ItemArmor) menuObject).getMax();
                        int nItem = asTooManyItem(menuObject);
                        if (max + 1 > nItem) {
                            ((ItemArmor) menuObject).incr();
                        }
                    } else if (clickType == ClickType.RIGHT) {
                        ((ItemArmor) menuObject).decr();
                    }
                }
            });
        }

        menu.addLeaveIcon(9, 6);
        menu.addPrevIcon(8, 6);
        addValidate(7, 6, menu);

        menu.openForPlayer((Player) Elo.getInstance().getQueue().getCurrent().getInventory().getHolder());
    }

    private int asTooManyItem(Item menuObject) {
        int res = 0;

        for (Material key : items.keySet()) {
            ItemArmor item = items.get(key);
            if (item.getItem().getType() == menuObject.getItem().getType()) {
                res++;
            }
        }

        return res;
    }

    public void addValidate(int x, int y, Menu menu) {
        ItemStack itemStack = new ItemStack(org.bukkit.Material.ITEM_FRAME);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Valider");
        itemStack.setItemMeta(itemMeta);
        Item item = new Item(itemStack);

        item.setActionListener(new IAction() {
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    //validate choice
                    System.out.println("VALIDATION");
                    //TODO Not Working
                    Arena.this.items.remove(Arena.this.scoreBoardItemInventorySelection);
                    Arena.this.preGame();
                }
            }
        });

        Coord coordinates = new Coord(menu, x, y);
        menu.setMenuObjectAt(coordinates, item);
    }

    public void preGame() {
        for (Player player : (ArrayList<Player>) ListTools.flatten(this.participants)) {
            player.getInventory().clear();
        }

        this.team_1 = scoreboardArena.getScoreBoard().registerNewTeam("Team RAND NAME 1");
        this.team_2 = scoreboardArena.getScoreBoard().registerNewTeam("Team RAND NAME 2");

        for (Player player : this.participants.get(0)) {
            this.team_1.addEntry(player.getName());
        }

        for (Player player : this.participants.get(1)) {
            this.team_2.addEntry(player.getName());
        }

        askForScenarios();
    }

    public void startGame() {
        for (Map.Entry<Material, ItemArmor> entry : this.items.entrySet()) {
            for (Player player : (ArrayList<Player>) ListTools.flatten(this.participants)) {
                ItemArmor item = entry.getValue();
                for (int i = 0; i < item.getNumber(); i++) {
                    if (entry.getKey() == Material.ENDER_PORTAL_FRAME) {
                        player.getInventory().addItem(((ItemArmorRandom) item).getTrueObject());
                    } else {
                        ItemMeta itemMeta = item.getItem().getItemMeta();
                        itemMeta.setLore(Arrays.asList());

                        item.getItem().setItemMeta(itemMeta);
                        player.getInventory().addItem(item.getItem());
                    }
                }
            }
        }

        for (Player player : (ArrayList<Player>) ListTools.flatten(this.participants)) {
            //TODO remove all custum inventory ???
            player.closeInventory();
            player.setGameMode(GameMode.SURVIVAL);
            player.setWalkSpeed(0.4F);
        }
    }

    private void loadScenarios(ArrayList<ItemScenarios> itemScenarios) {
        for (ItemScenarios item : itemScenarios) {
            if (item.getSelected()) {
                IScenario s = null;
                switch (item.getScenario()) {
                    case BRAIN_FUCK_TELEPORTATION:
                        s = new BrainFuckTeleportation();
                        s.activate();
                        break;
                    case BUNNY_UP:
                        s = new BunnyUp();
                        s.activate();
                        break;
                    case FLOWER_POWER:
                        s = new FlowerPower();
                        s.activate();
                        break;
                    case FRIENDLY_FIRE:
                        s = new FriendlyFire(this.scoreboardArena);
                        s.activate();
                        break;
                    case GLADIATOR:
                        s = new Gladiator();
                        s.activate();
                        break;
                    case HOLYDAY_ON_ICE:
                        s = new HolydayOnIce();
                        s.activate();
                        break;
                    case MAGIC_PONEY:
                        s = new MagicPoney();
                        s.activate();
                        break;
                    case WALKING_DEAD:
                        s = new WalkingDead();
                        s.activate();
                        break;
                }
            }
        }
    }

    private void askForScenarios() {
        ArrayList<ItemScenarios> itemScenarios = new ArrayList<>();
        Menu menu = new Menu(Bukkit.createInventory(Elo.getInstance().getQueue().getCurrent().getInventory().getHolder(), 54, "Scénarios"));

        /* Friendly Fire */
        ItemScenarios itemFF = new ItemScenarios(ScenarioEnum.FRIENDLY_FIRE, 5, 3, Material.FENCE_GATE, menu);
        itemScenarios.add(itemFF);
        itemFF.update();
        itemFF.setActionListener(new IAction() {
            @Override
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    ItemScenarios itemS = ((ItemScenarios) menuObject);
                    itemS.setSelected(!itemS.getSelected());
                }
            }
        });

        /* Friendly Fire */
        ItemScenarios itemBU = new ItemScenarios(ScenarioEnum.BUNNY_UP, 4, 3, Material.RABBIT_FOOT, menu);
        itemScenarios.add(itemBU);
        itemBU.update();
        itemBU.setActionListener(new IAction() {
            @Override
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    ItemScenarios itemS = ((ItemScenarios) menuObject);
                    itemS.setSelected(!itemS.getSelected());
                }
            }
        });

        //TODO TODO TODO

        //menu.addLeaveIcon(9, 6);
        //menu.addPrevIcon(8, 6);
        Item validateItem = menu.addValidate(9, 6);

        validateItem.setActionListener(new IAction() {
            @Override
            public void onClick(ClickType clickType, Item item, Player whoClicked) {
                //TODO start realGame
                loadScenarios(itemScenarios);
                Arena.this.scoreBoardItemInventorySelection.stop();
            }
        });

        menu.openForPlayer((Player) Elo.getInstance().getQueue().getCurrent().getInventory().getHolder());
    }

}
