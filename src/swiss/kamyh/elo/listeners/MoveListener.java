package swiss.kamyh.elo.listeners;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.material.Dye;
import org.bukkit.material.Wool;
import swiss.kamyh.elo.Elo;
import swiss.kamyh.elo.tools.Coord;

/**
 * Created by Vincent on 05.06.2016.
 */
public class MoveListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (Elo.getInstance().isHolydayOnIce()) {
            BlockFace dir = Coord.getCardinalDirection(e.getPlayer());
            Block toBlock = e.getTo().getBlock().getRelative(BlockFace.DOWN);
            Block backBlock = toBlock.getRelative(dir);
            Material to = backBlock.getType();
            if (to.equals(Material.GLASS) || to.equals(Material.GRASS)) {
                backBlock.setTypeId(174);
            }
        }

        if (Elo.getInstance().isTron()) {
            Dye dye = new Dye();
            switch ((int) (e.getPlayer().getHealth() % 15)){
                case 0:
                    dye.setColor(DyeColor.WHITE);
                    break;
                case 1:
                    dye.setColor(DyeColor.ORANGE);
                    break;
                case 2:
                    dye.setColor(DyeColor.MAGENTA);
                    break;
                case 3:
                    dye.setColor(DyeColor.LIGHT_BLUE);
                    break;
                case 4:
                    dye.setColor(DyeColor.YELLOW);
                    break;
                case 5:
                    dye.setColor(DyeColor.LIME);
                    break;
                case 6:
                    dye.setColor(DyeColor.PINK);
                    break;
                case 7:
                    dye.setColor(DyeColor.GRAY);
                    break;
                case 8:
                    dye.setColor(DyeColor.SILVER);
                    break;
                case 9:
                    dye.setColor(DyeColor.CYAN);
                    break;
                case 10:
                    dye.setColor(DyeColor.PURPLE);
                    break;
                case 11:
                    dye.setColor(DyeColor.BLUE);
                    break;
                case 12:
                    dye.setColor(DyeColor.BROWN);
                    break;
                case 13:
                    dye.setColor(DyeColor.GREEN);
                    break;
                case 14:
                    dye.setColor(DyeColor.RED);
                    break;
                case 15:
                    dye.setColor(DyeColor.BLACK);
                    break;
            }

            BlockFace dir = Coord.getCardinalDirection(e.getPlayer());
            Block toBlock = e.getTo().getBlock().getRelative(BlockFace.DOWN);
            Block backBlock = toBlock.getRelative(dir).getRelative(dir);
            Material to = backBlock.getType();
            if (to.equals(Material.GLASS) || to.equals(Material.GRASS)) {


                Block b1 = backBlock.getRelative(BlockFace.UP);
                b1.setType(Material.WOOL);
                b1.setData(dye.getData());

                Block b2 = b1.getRelative(BlockFace.UP);
                b2.setType(Material.WOOL);
                b2.setData(dye.getData());

            }
        }
    }
}