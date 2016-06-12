package swiss.kamyh.elo.tools;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import swiss.kamyh.elo.gui.Menu;

/**
 * Created by Vincent on 05.06.2016.
 */
public class Coord {

    private Menu menu;
    private int x;
    private int y;

    public Coord(Menu menu, int x, int y) {
        this.menu = menu;
        this.x = x;
        this.y = y;
    }

    public Coord(Menu menu, int slot) {
        this.menu = menu;
        this.x = this.calculateCoordinates(slot)[0];
        this.y = this.calculateCoordinates(slot)[1];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Menu getMenu() {
        return menu;
    }

    public int asSlotNumber() {
        return this.toSlotNumber();
    }

    private int[] calculateCoordinates(int slot) {
        int slotx = (slot % 9) + 1;
        int sloty = (slot / 9) + 1;

        return new int[]{slotx, sloty};
    }

    private int toSlotNumber() {
        return ((this.y - 1) * 9) + (this.x - 1);
    }

    public static BlockFace getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw()) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 22.5) {
            return BlockFace.NORTH;
        } else if (22.5 <= rotation && rotation < 67.5) {
            return BlockFace.NORTH_EAST;
        } else if (67.5 <= rotation && rotation < 112.5) {
            return BlockFace.EAST;
        } else if (112.5 <= rotation && rotation < 157.5) {
            return BlockFace.SOUTH_EAST;
        } else if (157.5 <= rotation && rotation < 202.5) {
            return BlockFace.SOUTH;
        } else if (202.5 <= rotation && rotation < 247.5) {
            return BlockFace.SOUTH_WEST;
        } else if (247.5 <= rotation && rotation < 292.5) {
            return BlockFace.WEST;
        } else if (292.5 <= rotation && rotation < 337.5) {
            return BlockFace.NORTH_WEST;
        } else if (337.5 <= rotation && rotation < 360.0) {
            return BlockFace.NORTH;
        } else {
            return null;
        }
    }
}

