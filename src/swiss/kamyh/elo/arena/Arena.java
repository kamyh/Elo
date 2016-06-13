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
import swiss.kamyh.elo.tools.*;

import java.util.*;


/**
 * Created by Vincent on 06.06.2016.
 */
public class Arena {

    HashMap<org.bukkit.Material, ItemArmor> items;
    List<List<Player>> participants; //TODO replace with team ????
    private ScoreboardTimerized scoreboardArena;
    private ScoreboardItemTimed scoreBoardItemInventorySelection;
    private Team team_1;
    private Team team_2;
    private ArrayList<ItemScenarios> itemScenarios;
    private int time = Configuration.basePartyTime;
    private ScoreboardItem itemPartyTime;
    private int[] kill;

    public Arena(List<List<Player>> participants) {

        this.participants = participants;
        this.preparePlayers();
        this.kill = new int[2];
        this.kill[0] = 0;
        this.kill[1] = 0;

        createScorebord();

        Menu menu = new Menu(Bukkit.createInventory(Elo.getInstance().getQueue().getCurrent().getInventory().getHolder(), 54, "Arena"));

        Item item = menu.addIcon(5, 3, org.bukkit.Material.ARMOR_STAND);
        item.setCustumName("Selection de l'équipement");
        item.update();

        Item itemMoreTime = menu.addIcon(6, 3, Material.WATCH);
        itemMoreTime.setCustumName(String.format("+ %d sec", Configuration.timeModification));
        itemMoreTime.update();

        Item itemLessTime = menu.addIcon(4, 3, Material.WATCH);
        itemLessTime.setCustumName(String.format("- %d sec", Configuration.timeModification));
        itemLessTime.update();

        item.setActionListener(new IAction() {
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    Arena.this.scoreBoardItemInventorySelection.start();
                    armorSelection();
                }
            }
        });

        itemMoreTime.setActionListener(new IAction() {
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    Arena.this.time += Configuration.timeModification;
                    Arena.this.itemPartyTime.setMessage(new CustomMessage[]{new CustomMessage(ChatColor.GOLD, "Temps de jeu: " + Arena.this.time + " s")});
                    Arena.this.scoreboardArena.update();
                }
            }
        });

        itemLessTime.setActionListener(new IAction() {
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    if (Arena.this.time > Configuration.timeModification) {
                        Arena.this.time -= Configuration.timeModification;
                        Arena.this.itemPartyTime.setMessage(new CustomMessage[]{new CustomMessage(ChatColor.GOLD, "Temps de jeu: " + Arena.this.time + " s")});
                        Arena.this.scoreboardArena.update();
                    }
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

            player.teleport(new Location(Bukkit.getServer().getWorld("world"), Configuration.spawn_1[0], Configuration.spawn_1[1], Configuration.spawn_1[2]));
        }

        for (Player player : this.participants.get(1)) {
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            player.setWalkSpeed(0);

            player.teleport(new Location(Bukkit.getServer().getWorld("world"), Configuration.spawn_2[0], Configuration.spawn_2[1], Configuration.spawn_2[2]));
        }

        //Bukkit.getServer().getWorld("world").setGameRuleValue("KeepInventory","true");
    }

    private void createScorebord() {
        this.scoreboardArena = new ScoreboardTimerized((ArrayList<Player>) ListTools.flatten(this.participants), "Arena UHC", this);
        this.scoreBoardItemInventorySelection = new ScoreboardItemTimed(this.scoreboardArena, new CustomMessage[]{new CustomMessage(ChatColor.AQUA, "Time Left: ")}, 2, Configuration.selectionPhase);
        this.scoreBoardItemInventorySelection.addOnTimeOut(new IActionOnTimeOut() {
            @Override
            public void callBack() {
                Arena.this.scoreboardArena.timesUp();
            }
        });
        ScoreboardItem scoreboardItemPhaseName = new ScoreboardItem(this.scoreboardArena, new CustomMessage[]{new CustomMessage(ChatColor.BLUE, "Selection de l'inventaire")}, 4);
        ScoreboardItem separator_1 = new ScoreboardItem(this.scoreboardArena, new CustomMessage[]{new CustomMessage(ChatColor.GRAY, Scoreboard.getElementSeparator(20))}, 3);
        this.itemPartyTime = new ScoreboardItem(this.scoreboardArena, new CustomMessage[]{new CustomMessage(ChatColor.GOLD, "Temps de jeu: " + this.time)}, 5);
        this.scoreboardArena.add(this.scoreBoardItemInventorySelection);
        this.scoreboardArena.add(separator_1);
        this.scoreboardArena.add(scoreboardItemPhaseName);
        this.scoreboardArena.add(this.itemPartyTime);
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

        GroupItem groupItemBlock = new GroupItem(GroupItemType.BLOCK, Configuration.maxBlocks);

        items.put(org.bukkit.Material.HAY_BLOCK, new ItemArmor(1, 4, org.bukkit.Material.HAY_BLOCK, Configuration.maxSameBlocks, menu, groupItemBlock));
        items.put(org.bukkit.Material.MELON_BLOCK, new ItemArmor(2, 4, org.bukkit.Material.MELON_BLOCK, Configuration.maxSameBlocks, menu, groupItemBlock));
        items.put(org.bukkit.Material.SLIME_BLOCK, new ItemArmor(3, 4, org.bukkit.Material.SLIME_BLOCK, Configuration.maxSameBlocks, menu, groupItemBlock));

        GroupItem groupItemVarious = new GroupItem(GroupItemType.VARIOUS, 1);

        items.put(org.bukkit.Material.FISHING_ROD, new ItemArmor(1, 5, org.bukkit.Material.FISHING_ROD, 1, menu, groupItemVarious));
        ItemArmor bow = new ItemArmor(2, 5, org.bukkit.Material.BOW, 1, menu, groupItemVarious);
        bow.setCustumName("Infinity");
        bow.enchant(Enchantment.ARROW_INFINITE, 1);
        bow.updateTagQuantity();
        items.put(org.bukkit.Material.BOW, bow);
        items.put(org.bukkit.Material.ENDER_PORTAL_FRAME, new ItemArmorRandom(3, 5, org.bukkit.Material.ENDER_PORTAL_FRAME, 1, menu, groupItemVarious));

        GroupItem groupItemCompos = new GroupItem(GroupItemType.COMPOS, Configuration.maxCompos);

        items.put(org.bukkit.Material.GOLDEN_APPLE, new ItemArmor(1, 6, org.bukkit.Material.GOLDEN_APPLE, Configuration.maxGoldenApple, menu, groupItemCompos));
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

        String[] names = Auxiliary.getRandomName();
        this.team_1 = scoreboardArena.getScoreBoard().registerNewTeam("Team " + names[0]);
        this.team_2 = scoreboardArena.getScoreBoard().registerNewTeam("Team " + names[1]);

        HashMap<Integer,Team> teams = new HashMap<>();
        teams.put(0,team_1);
        teams.put(1,team_2);

        Elo.getInstance().setTeam(teams);

        for (Player player : this.participants.get(0)) {
            this.team_1.addEntry(player.getName());
            player.sendMessage("Vous etes dans l'équipe: " + this.team_1.getName());
        }

        for (Player player : this.participants.get(1)) {
            this.team_2.addEntry(player.getName());
            player.sendMessage("Vous etes dans l'équipe: " + this.team_2.getName());
        }

        askForScenarios();
    }

    public void startGame() {
        String scenario = "";
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

        for (ItemScenarios s : this.itemScenarios) {
            if (s.getSelected()) {
                scenario += Scenario.nameString(s.getScenario()) + " - ";
            }
        }

        if (!scenario.equals("")) {
            scenario = scenario.substring(0, scenario.length() - 2);
        }

        for (Player player : (ArrayList<Player>) ListTools.flatten(this.participants)) {
            //TODO remove all custum inventory ???
            player.closeInventory();
            player.setGameMode(GameMode.SURVIVAL);
            player.setWalkSpeed(Configuration.speedWalk);
            if (!scenario.equals("")) {
                player.sendMessage(scenario);
            }
        }

        this.scoreboardArena.removeItemAt(5);
        this.scoreboardArena.removeItemAt(2);

        ScoreboardItemTimed partyTimer = new ScoreboardItemTimed(this.scoreboardArena, new CustomMessage[]{new CustomMessage(ChatColor.AQUA, "Time Left: ")}, 2, this.time);
        partyTimer.addOnTimeOut(new IActionOnTimeOut() {
            @Override
            public void callBack() {
                Arena.this.finishGame();
            }
        });
        this.scoreboardArena.add(partyTimer);
        partyTimer.start();
        Elo.getInstance().setStarted(true);

        this.scoreboardArena.initDisplayKill(this.team_1.getName() + ": " + this.kill[0], this.team_2.getName() + ": " + this.kill[1]);

        loadScenarios(itemScenarios);

        this.scoreboardArena.removeItemAt(4);
    }

    private void finishGame() {
        if(this.kill[0] > this.kill[1])
        {
            //TODO FINISH
        }else{

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
                        s = new BunnyUp((ArrayList<Player>) ListTools.flatten(this.participants));
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
                        s = new MagicPoney((ArrayList<Player>) ListTools.flatten(this.participants));
                        s.activate();
                        break;
                    case WALKING_DEAD:
                        s = new WalkingDead();
                        s.activate();
                        break;
                    case TRON:
                        s = new Tron();
                        s.activate();
                        break;
                }
            }
        }
    }

    private void askForScenarios() {
        itemScenarios = new ArrayList<>();
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

        /* Holyday On Ice */
        ItemScenarios itemHOI = new ItemScenarios(ScenarioEnum.HOLYDAY_ON_ICE, 6, 3, Material.ICE, menu);
        itemScenarios.add(itemHOI);
        itemHOI.update();
        itemHOI.setActionListener(new IAction() {
            @Override
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    ItemScenarios itemS = ((ItemScenarios) menuObject);
                    itemS.setSelected(!itemS.getSelected());
                }
            }
        });

        /* Walking Dead */
        ItemScenarios itemWD = new ItemScenarios(ScenarioEnum.WALKING_DEAD, 3, 3, Material.ROTTEN_FLESH, menu);
        itemScenarios.add(itemWD);
        itemWD.update();
        itemWD.setActionListener(new IAction() {
            @Override
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    ItemScenarios itemS = ((ItemScenarios) menuObject);
                    itemS.setSelected(!itemS.getSelected());
                }
            }
        });

        /* Magic Poney */
        ItemScenarios itemMP = new ItemScenarios(ScenarioEnum.MAGIC_PONEY, 7, 3, Material.SADDLE, menu);
        itemScenarios.add(itemMP);
        itemMP.update();
        itemMP.setActionListener(new IAction() {
            @Override
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    ItemScenarios itemS = ((ItemScenarios) menuObject);
                    itemS.setSelected(!itemS.getSelected());
                }
            }
        });

        /* Tron */
        ItemScenarios itemT = new ItemScenarios(ScenarioEnum.TRON, 6, 4, Material.WOOL, menu);
        itemScenarios.add(itemT);
        itemT.update();
        itemT.setActionListener(new IAction() {
            @Override
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    ItemScenarios itemS = ((ItemScenarios) menuObject);
                    itemS.setSelected(!itemS.getSelected());
                }
            }
        });

        /* Teleport Brain */
        /* NOT WORKING */
        /*
        ItemScenarios itemTB = new ItemScenarios(ScenarioEnum.BRAIN_FUCK_TELEPORTATION, 4, 4, Material.BEACON, menu);
        itemScenarios.add(itemTB);
        itemTB.update();
        itemTB.setActionListener(new IAction() {
            @Override
            public void onClick(ClickType clickType, Item menuObject, Player player) {
                if (clickType == ClickType.LEFT) {
                    ItemScenarios itemS = ((ItemScenarios) menuObject);
                    itemS.setSelected(!itemS.getSelected());
                }
            }
        });
        */

        Item validateItem = menu.addValidate(9, 6);

        validateItem.setActionListener(new IAction() {
            @Override
            public void onClick(ClickType clickType, Item item, Player whoClicked) {
                Arena.this.scoreBoardItemInventorySelection.stop();
            }
        });

        menu.openForPlayer((Player) Elo.getInstance().getQueue().getCurrent().getInventory().getHolder());
    }

    public Scoreboard getScoreboard() {
        return this.scoreboardArena;
    }

    public void addKill(Player killer) {
        int index = (this.team_1.hasEntry(killer.getName())) ? 1 : 0;
        this.kill[index] += 1;

        this.scoreboardArena.displayKills(this.team_1.getName() + ": " + this.kill[0], this.team_2.getName() + ": " + this.kill[1]);
    }
}
